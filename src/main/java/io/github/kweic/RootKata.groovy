package io.github.kweic

import io.github.kweic.service.DriverRegistry
import io.github.kweic.util.ParsedDriverInput

class RootKata {

    static void main(String[] args){

        if(args == null || args.length == 0){
            throw new FileNotFoundException("Driver input file required.")
        }

        DriverRegistry driverRegistry = new DriverRegistry()
        
        new File(args[0]).readLines().each{ line ->
            ParsedDriverInput driverInput = ParsedDriverInput.create(line)
            driverRegistry.readInput(driverInput)
        }

        driverRegistry.driversByDistance().each{ driver ->
            println driver
        }
    }
}
