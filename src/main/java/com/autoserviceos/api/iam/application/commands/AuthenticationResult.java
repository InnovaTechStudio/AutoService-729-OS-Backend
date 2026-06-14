package com.autoserviceos.api.iam.application.commands;

import com.autoserviceos.api.iam.domain.model.aggregates.User;

/**
 * Response structure encapsulating the authenticated User aggregate and its generated JWT token.
 */
public record AuthenticationResult(User user, String token) {
}