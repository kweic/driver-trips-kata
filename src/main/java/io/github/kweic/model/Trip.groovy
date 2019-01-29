package io.github.kweic.model

import java.time.LocalTime
import static java.time.temporal.ChronoUnit.MINUTES

class Trip {
    private LocalTime start
    private LocalTime end
    private double distance

    static Trip create(LocalTime startTime, LocalTime endTime, double distance) {
        return new Trip(start: startTime, end: endTime, distance: distance)
    }

    long getTotalDriveTimeMinutes(){
        return MINUTES.between(start, end)
    }

    def getDistance(){
        return distance
    }
}
