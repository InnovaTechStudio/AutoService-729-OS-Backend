package com.autoserviceos.api.iam.application.commandservices;

import com.autoserviceos.api.iam.application.commands.AuthenticationResult;
import com.autoserviceos.api.iam.application.commands.SignInCommand;
import com.autoserviceos.api.iam.application.commands.SignUpCommand;
import com.autoserviceos.api.iam.domain.model.aggregates.User;
import java.util.Optional;

/**
 * Inbound application service interface handling security command behaviors.
 */
public interface IamCommandService {
    User handle(SignUpCommand command);
    Optional<AuthenticationResult> handle(SignInCommand command);
}