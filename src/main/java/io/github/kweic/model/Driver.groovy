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
        return sumTripValue({ trips.sum{Trip t -> t.getDistance() }})
    }

    long getTotalTravelTime() {
        return sumTripValue({ trips.sum{Trip t -> t.getTotalDriveTimeMinutes() }})
    }

    private long sumTripValue(Closure c){
        if(trips.size() <= 0) return 0
        return c.call(trips) as Long
    }

    @Override
    int hashCode() {
        return name.toLowerCase().hashCode()
    }

    @Override
    boolean equals(Object obj) {
        if(obj.class == this.class){
            return this.name.equalsIgnoreCase( ((Driver)obj).name )
        }
        return false
    }
}
