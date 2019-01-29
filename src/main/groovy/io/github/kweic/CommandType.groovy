package io.github.kweic

enum CommandType {
    DRIVER("driver"),
    TRIP("trip")

    CommandType(String v){
        this.value = v
    }

    private final String value

    @Override
    String toString() {
        return value
    }
}