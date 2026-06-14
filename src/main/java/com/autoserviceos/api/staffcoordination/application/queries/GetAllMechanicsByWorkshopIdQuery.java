package com.autoserviceos.api.staffcoordination.application.queries;
/** Query to retrieve all mechanics explicitly associated with a workshop scope. */
public record GetAllMechanicsByWorkshopIdQuery(String workshopId) {}