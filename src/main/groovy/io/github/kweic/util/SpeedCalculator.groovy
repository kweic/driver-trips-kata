package io.github.kweic.util

class SpeedCalculator {

    static Double getAverageSpeed(Long minutes, Long miles) {
        def hoursTraveled = minutes / 60
        if(hoursTraveled > 0){
            return miles / hoursTraveled
        }
        return 0
    }
}
