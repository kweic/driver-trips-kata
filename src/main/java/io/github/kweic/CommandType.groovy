package io.github.kweic

enum CommandType {
    ADD_NEW_DRIVER("driver"),
    ADD_NEW_TRIP("trip")

    CommandType(String v){
        this.value = v
    }

    private final String value

    @Override
    String toString() {
        return value
    }
}