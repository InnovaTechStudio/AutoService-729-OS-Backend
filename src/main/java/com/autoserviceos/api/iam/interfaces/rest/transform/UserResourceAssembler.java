package com.autoserviceos.api.iam.interfaces.rest.transform;

import com.autoserviceos.api.iam.application.commands.AuthenticationResult;
import com.autoserviceos.api.iam.application.commands.SignInCommand;
import com.autoserviceos.api.iam.application.commands.SignUpCommand;
import com.autoserviceos.api.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.autoserviceos.api.iam.interfaces.rest.resources.SignInResource;
import com.autoserviceos.api.iam.interfaces.rest.resources.SignUpResource;

/**
 * Data mapping component translating representation layouts between infrastructure boundaries.
 */
public class UserResourceAssembler {

    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.email(), resource.password());
    }

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.email(), resource.password(), resource.role(), resource.workshopId());
    }

    public static AuthenticatedUserResource toResourceFromResult(AuthenticationResult result, Long mechanicId) {
        return new AuthenticatedUserResource(
                result.user().getId(),
                result.user().getEmail(),
                result.user().getRole(),
                result.user().getWorkshopId(),
                mechanicId,
                result.token()
        );
    }
}