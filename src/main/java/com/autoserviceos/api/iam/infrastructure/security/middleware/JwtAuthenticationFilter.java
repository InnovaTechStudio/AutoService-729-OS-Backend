package com.autoserviceos.api.iam.infrastructure.security.middleware;

import com.autoserviceos.api.iam.infrastructure.security.token.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * Filter middleware intercepting requests to validate HTTP Bearer headers and mount Spring Security authentication contexts.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = getJwtFromRequest(request);

            if (StringUtils.hasText(token)) {
                System.out.println("[JWT FILTER] Token recibido en backend correctamente.");

                boolean isValid = tokenProvider.validateToken(token);
                System.out.println("[JWT FILTER] ¿El token pasó la validación de firma/expiración?: " + isValid);

                if (isValid) {
                    Claims claims = tokenProvider.getClaimsFromToken(token);
                    String username = claims.getSubject();

                    String role = claims.get("role", String.class);
                    System.out.println("[JWT FILTER] Usuario extraído: " + username + " | Rol extraído: " + role);

                    if (role == null) {
                        System.out.println("[JWT FILTER] ADVERTENCIA: El token no contiene un claim llamado 'role'. Usando 'USER' por defecto para evitar caídas.");
                        role = "USER";
                    }

                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
                    var authentication = new UsernamePasswordAuthenticationToken(username, claims, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("[JWT FILTER] Autenticación inyectada con éxito en el contexto de Spring.");
                } else {
                    System.out.println("[JWT FILTER] El token es inválido (Expirado o firma incorrecta).");
                }
            }
        } catch (Exception e) {
            System.err.println("[JWT FILTER] Error crítico procesando el token: " + e.getMessage());
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}