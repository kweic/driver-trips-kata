package io.github.kweic.model

import static io.github.kweic.TripValidation.*
import io.github.kweic.util.SpeedCalculator

import java.time.LocalTime
import static java.time.temporal.ChronoUnit.MINUTES

class Trip {
    private LocalTime start
    private LocalTime end
    private double distance

    static Trip create(LocalTime startTime, LocalTime endTime, double distance) {
        return new Trip(start: startTime, end: endTime, distance: distance)
    }

    long getTotalDriveTimeMinutes() {
        return MINUTES.between(start, end)
    }

    def getDistance() {
        return distance
    }

    double getAverageSpeed() {
        return SpeedCalculator.getAverageSpeed(getTotalDriveTimeMinutes(), distance as Long)
    }

    boolean hasValidSpeed() {
        double averageSpeed = getAverageSpeed()
        return averageSpeed >= MIN_SPEED.value && averageSpeed <= MAX_SPEED.value
    }
}
