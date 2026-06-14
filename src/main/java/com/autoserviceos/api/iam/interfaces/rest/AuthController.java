package com.autoserviceos.api.iam.interfaces.rest;

import com.autoserviceos.api.iam.application.commands.SignUpCommand;
import com.autoserviceos.api.iam.application.commandservices.IamCommandService;
import com.autoserviceos.api.iam.interfaces.rest.resources.SignInResource;
import com.autoserviceos.api.iam.interfaces.rest.resources.SignUpResource;
import com.autoserviceos.api.iam.interfaces.rest.resources.SignUpWorkshopResource;
import com.autoserviceos.api.iam.interfaces.rest.transform.UserResourceAssembler;
import com.autoserviceos.api.tenantmanagement.application.commands.CreateWorkshopCommand;
import com.autoserviceos.api.tenantmanagement.application.commandservices.WorkshopCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Exposes entry authentication checkpoints handlers inside web api execution contexts.
 */
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Entry verification operations endpoints.")
public class AuthController {

    private final IamCommandService iamCommandService;
    private final WorkshopCommandService workshopCommandService;

    public AuthController(IamCommandService iamCommandService, WorkshopCommandService workshopCommandService) {
        this.iamCommandService = iamCommandService;
        this.workshopCommandService = workshopCommandService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInResource resource) {
        var command = UserResourceAssembler.toCommandFromResource(resource);
        var result = iamCommandService.handle(command);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Email or password is incorrect"));
        }

        // Simulating cross-context lookup for mechanic profile if the user's role requires it
        Long simulatedMechanicId = "mechanic".equalsIgnoreCase(result.get().user().getRole()) ? 45L : null;
        var responseBody = UserResourceAssembler.toResourceFromResult(result.get(), simulatedMechanicId);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpResource resource) {
        var command = UserResourceAssembler.toCommandFromResource(resource);
        var user = iamCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "User created successfully", "userId", user.getId()));
    }

    @PostMapping("/register-workshop")
    public ResponseEntity<?> registerWorkshop(@Valid @RequestBody SignUpWorkshopResource resource) {
        // 1. Coordinate the creation of the workshop tenant structure
        var createWorkshopCommand = new CreateWorkshopCommand(resource.workshopName());
        var workshopOptional = workshopCommandService.handle(createWorkshopCommand);

        if (workshopOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Could not create workshop corporate structure."));
        }

        var createdWorkshop = workshopOptional.get();

        // 2. Cascade create the primary administrator account aligned with the new workshop's operational tenant identity
        var signUpAdminCommand = new SignUpCommand(
                resource.email(),
                resource.password(),
                "admin",
                createdWorkshop.getTenantId()
        );
        var user = iamCommandService.handle(signUpAdminCommand);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Workshop and admin account created successfully",
                "workshopId", createdWorkshop.getTenantId(),
                "userId", user.getId()
        ));
    }
}