package io.github.kweic.dto;

import io.github.kweic.model.Driver
import io.github.kweic.util.SpeedCalculator;

class DriverDto {
    String name
    long totalDistance
    long totalMinutes

    static DriverDto create(Driver driver) {
        return new DriverDto(
                name: driver.getName(),
                totalDistance: driver.getTotalDistance(),
                totalMinutes: driver.getTotalTravelTime())
    }

    @Override
    String toString() {
            return "${name}: ${totalDistance} miles" +
                    (totalDistance > 0 ? " @ ${Math.round( SpeedCalculator.getAverageSpeed(totalMinutes, totalDistance) )} mph" : "")
    }
}
