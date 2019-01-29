package io.github.kweic.model

class Driver {
    private String name
    List<Trip> trips

    static Driver create(String name) {
        return new Driver(name: name, trips: [])
    }

    def addTrip(Trip trip) {
        trips.add(trip)
    }

    def getName() {
        return name
    }

    long getTotalDistance() {
        if(trips.size() <= 0) return 0

        return trips.sum{ it.getDistance() } as Long
    }

    long getTotalTravelTime() {
        if(trips.size() <= 0) return 0

        return trips.sum{ it.getTotalDriveTimeMinutes() } as Long
    }

    @Override
    int hashCode() {
        return name.toLowerCase().hashCode()
    }

    @Override
    boolean equals(Object obj) {
        println "doing comaprison of names"
        if(obj.class == this.class){
            return this.name.equalsIgnoreCase( ((Driver)obj).name )
        }
        return false
    }
}
