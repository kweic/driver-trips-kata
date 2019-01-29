package io.github.kweic

import io.github.kweic.dto.DriverDto
import io.github.kweic.model.Driver;
import io.github.kweic.service.DriverRegistry
import io.github.kweic.util.ParsedDriverInput
import spock.lang.Shared
import spock.lang.Specification

class DriverRegistryTest extends Specification {

    @Shared DriverRegistry registry

    def setup(){
        this.registry = new DriverRegistry()
    }

    def "Drivers are added to registry"(){
        when: "Drivers with unique names are added"
            addDrivers(["Dan","Jill", "UmgullianbBlob"])
        then: "Drivers will be saved in registry"
            registry.getDrivers().size() == 3
            registry.findByName("Dan") != null
            registry.findByName("Jill") != null
            registry.findByName("UmgullianbBlob") != null

    }

    def "Trip is added to driver through the registry"(){
        given: "Driver exists in registry"
            registry.readInput(ParsedDriverInput.create("Driver Dan"))
        when: "Trip is added"
            registry.readInput(ParsedDriverInput.create("Trip Dan 07:15 07:45 17.3"))
        then: "Trip is saved"
            Driver driverDan = registry.findByName("Dan")
            driverDan.getTotalDistance() == 17L
            driverDan.getTotalTravelTime() == 30
    }

    def "Multiple trips are added to driver through the registry"(){
        given: "Driver exists in registry"
            registry.readInput(ParsedDriverInput.create("Driver Dan"))
        when: "Trips are added"
            registry.readInput(ParsedDriverInput.create("Trip Dan 07:15 07:45 100"))// 30 minutes
            registry.readInput(ParsedDriverInput.create("Trip Dan 06:15 12:59 50"))// 404 minutes
            registry.readInput(ParsedDriverInput.create("Trip Dan 15:00 15:10 22"))// 10 minutes
        then: "Trips are saved"
            Driver driverDan = registry.findByName("Dan")
            driverDan.getTotalDistance() == 172
            driverDan.getTotalTravelTime() == 444
    }

    def "Drivers by distance returns a list of DriverDto ordered by their total distance"(){
        given: "Drivers with unique names are added"
            addDrivers(["Dan","Jill", "UmgullianbBlob"])
        and: "Their related trips are entered"
            registry.readInput(ParsedDriverInput.create("Trip Dan 07:15 07:45 30"))
            registry.readInput(ParsedDriverInput.create("Trip Jill 12:09 15:45 150"))
            registry.readInput(ParsedDriverInput.create("Trip UmgullianbBlob 01:00 01:01 100"))
        when: "Drivers by distance method is called"
            List<DriverDto> drivers = registry.driversByDistance()
        then: "Drivers will be saved in registry"
            drivers.size() == 3
            drivers[0].getName() == "Jill" //150
            drivers[1].getName() == "UmgullianbBlob" //100
            drivers[2].getName() == "Dan" //30
    }

    def "Throws exception when duplicate driver is added"(){
        given: "A driver exists"
            registry.readInput(ParsedDriverInput.create("Driver DoubleBob"))
        when: "A driver with the same name is added"
            registry.readInput(ParsedDriverInput.create("Driver DoubleBob"))
        then: "An Exception is thrown"
            thrown UnsupportedOperationException
    }

    private addDrivers(List<String> drivers){
        drivers.each{ driver ->
            registry.readInput(ParsedDriverInput.create("Driver ${driver}"))
        }
    }

}
