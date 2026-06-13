package com.autoserviceos.api.fleetmanagement.interfaces.rest.transform;

import com.autoserviceos.api.fleetmanagement.application.commands.CreateVehicleCommand;
import com.autoserviceos.api.fleetmanagement.application.commands.UpdateVehicleCommand;
import com.autoserviceos.api.fleetmanagement.domain.model.aggregates.Vehicle;
import com.autoserviceos.api.fleetmanagement.interfaces.rest.resources.CreateVehicleResource;
import com.autoserviceos.api.fleetmanagement.interfaces.rest.resources.UpdateVehicleResource;
import com.autoserviceos.api.fleetmanagement.interfaces.rest.resources.VehicleResource;

public class VehicleResourceAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(entity.getId(), entity.getPlate(), entity.getBrand(), entity.getModel(),
                entity.getYear(), entity.getColor(), entity.getStatus(), entity.getImage(), entity.getCustomerId());
    }

    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(resource.plate(), resource.brand(), resource.model(), resource.year(),
                resource.color(), resource.status(), resource.image(), resource.customerId());
    }

    public static UpdateVehicleCommand toCommandFromResource(Long id, UpdateVehicleResource resource) {
        return new UpdateVehicleCommand(id, resource.plate(), resource.brand(), resource.model(), resource.year(),
                resource.color(), resource.status(), resource.image(), resource.customerId());
    }
}