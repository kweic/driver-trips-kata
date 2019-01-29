package io.github.kweic.service

import io.github.kweic.CommandType
import io.github.kweic.dto.DriverDto
import io.github.kweic.model.Driver
import io.github.kweic.model.Trip
import io.github.kweic.util.ParsedDriverInput

class DriverRegistry {
    private Set<Driver> drivers

    DriverRegistry(){
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

    def readInput(ParsedDriverInput input){
        if(input.isAddDriver()) {
            addDriver(Driver.create(input.name))
        } else if(input.isAddTrip()) {
            addTripFromInput(input)
        }
    }

    def addTripFromInput(ParsedDriverInput input) {
        findByName(input.name).addTrip(
                Trip.create(input.startTime, input.endTime, input.distance)
        )
    }

    List<DriverDto> driversByDistance() {
        List<DriverDto> driverDtos = []
        drivers.each{
            driverDtos.add( DriverDto.create(it) )
        }

        return driverDtos.sort{ it.totalDistance }.reverse()
    }

    def getDrivers() {
        return drivers
    }
}
