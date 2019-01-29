package io.github.kweic

enum TripValidation {
    MIN_SPEED(5),
    MAX_SPEED(100)

    TripValidation(int v){
        this.value = v
    }

    private final int value

    int getValue(){
        return value
    }
}