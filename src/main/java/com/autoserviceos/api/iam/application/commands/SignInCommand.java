package com.autoserviceos.api.iam.application.commands;

/**
 * Command encapsulating user login credentials for authentication checks.
 */
public record SignInCommand(String email, String password) {
}