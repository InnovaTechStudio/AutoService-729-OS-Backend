package com.autoserviceos.api.iam.application.internal.commandservices;

import com.autoserviceos.api.iam.application.commands.AuthenticationResult;
import com.autoserviceos.api.iam.application.commands.SignInCommand;
import com.autoserviceos.api.iam.application.commands.SignUpCommand;
import com.autoserviceos.api.iam.application.commandservices.IamCommandService;
import com.autoserviceos.api.iam.domain.model.aggregates.User;
import com.autoserviceos.api.iam.domain.model.repositories.UserRepository;
import com.autoserviceos.api.iam.infrastructure.security.token.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service orchestrator implementing authentication, hashing and authorization token assembly routines.
 */
@Service
public class IamCommandServiceImpl implements IamCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public IamCommandServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @Transactional
    public User handle(SignUpCommand command) {
        if (userRepository.findByEmail(command.email()).isPresent()) {
            throw new IllegalArgumentException("Email is already taken.");
        }

        String passwordHash = passwordEncoder.encode(command.password());
        User user = User.create(command.email(), passwordHash, command.role(), command.workshopId());
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthenticationResult> handle(SignInCommand command) {
        var userOptional = userRepository.findByEmail(command.email());
        if (userOptional.isEmpty() || !passwordEncoder.matches(command.password(), userOptional.get().getPasswordHash())) {
            return Optional.empty();
        }

        User user = userOptional.get();
        String token = tokenProvider.generateToken(user);
        return Optional.of(new AuthenticationResult(user, token));
    }
}