package com.autoserviceos.api.customermanagement.application.queries;

/**
 * Query to retrieve all customers associated with a specific workshop.
 */
public record GetAllCustomersByWorkshopIdQuery(String workshopId) {
}