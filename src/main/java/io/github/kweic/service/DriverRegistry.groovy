package io.github.kweic.service

import io.github.kweic.dto.DriverDto
import io.github.kweic.model.Driver
import io.github.kweic.model.Trip
import io.github.kweic.util.ParsedDriverInput

class DriverRegistry {
    private Set<Driver> drivers

    DriverRegistry() {
        this.drivers = []
    }

    def addDriver(Driver driver) {
        if(drivers.contains(driver)){
            throw new UnsupportedOperationException("Duplicate driver ${driver.getName()}")
        }

        drivers.add(driver)
    }

    Driver findByName(String name) {
        return drivers.find{ it.name == name }
    }

    def readInput(ParsedDriverInput input) {
        if(input.isAddDriver()) {
            addDriver(Driver.create(input.driverName))
        } else if(input.isAddTrip()) {
            addTripByDriverName(input.driverName, Trip.create(input.startTime, input.endTime, input.distance))
        }
    }

    def addTripByDriverName(String driverName, Trip trip) {
        if (trip.hasValidSpeed()) {
            findByName(driverName).addTrip(trip)
        }
    }

    List<DriverDto> getDriverDtos() {
        return drivers.collect{ DriverDto.create(it) }
    }

    List<DriverDto> driversByDistance() {
        return driverDtos.sort{ it.totalDistance }.reverse()
    }

    def getDrivers() {
        return drivers
    }
}
