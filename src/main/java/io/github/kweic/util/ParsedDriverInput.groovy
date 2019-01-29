package io.github.kweic.util

import io.github.kweic.CommandType

import java.time.LocalTime

class ParsedDriverInput {

    private String[] input

    static ParsedDriverInput create(String input) {
        return new ParsedDriverInput(input: input.split())
    }

    boolean isAddDriver() {
        return isCommandOfType(CommandType.DRIVER)
    }

    boolean isAddTrip() {
        return isCommandOfType(CommandType.TRIP)
    }

    boolean isCommandOfType(CommandType commandType) {
        return input[0].equalsIgnoreCase(commandType.toString())
    }

    String getName() {
        return input[1]
    }

    LocalTime getStartTime() {
        LocalTime.parse(input[2])
    }

    LocalTime getEndTime() {
        LocalTime.parse(input[3])
    }

    double getDistance() {
        input[4] as Double
    }
}
