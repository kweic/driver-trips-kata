package io.github.kweic

import io.github.kweic.service.DriverRegistry
import io.github.kweic.util.ParsedDriverInput

class RootKata {

    static void main(String[] args){

        DriverRegistry driverRegistry = new DriverRegistry()
        
        new File("src/main/resources/input.txt").readLines().each{ line ->
            ParsedDriverInput driverInput = ParsedDriverInput.create(line)
            driverRegistry.readInput(driverInput)
        }

        driverRegistry.driversByDistance().each{
            println it
        }
    }
}
