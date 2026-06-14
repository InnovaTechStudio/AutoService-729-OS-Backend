package com.autoserviceos.api.iam.application.commands;

/**
 * Command containing payload details required to register a new user identity.
 */
public record SignUpCommand(String email, String password, String role, String workshopId) {
}