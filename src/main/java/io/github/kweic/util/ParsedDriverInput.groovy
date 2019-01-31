package io.github.kweic.util

import io.github.kweic.CommandType

import static io.github.kweic.CommandType.*

import java.time.LocalTime

class ParsedDriverInput {

    private String[] input

    static ParsedDriverInput create(String input) {
        return new ParsedDriverInput(input: input.split())
    }

    boolean isAddDriver() {
        return isCommandType(ADD_NEW_DRIVER)
    }

    boolean isAddTrip() {
        return isCommandType(ADD_NEW_TRIP)
    }

    private boolean isCommandType(CommandType command) {
        return input[0].equalsIgnoreCase(command.toString())
    }

    String getDriverName() {
        return input[1]
    }

    LocalTime getStartTime() {
        return LocalTime.parse(input[2])
    }

    LocalTime getEndTime() {
        return LocalTime.parse(input[3])
    }

    double getDistance() {
        return input[4] as Double
    }
}
