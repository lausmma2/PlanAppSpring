package cz.uhk.fim.planapp.exceptions;

public class TripGroupIdExceptionResponse {

    private String tripGroupIdentifier;

    public TripGroupIdExceptionResponse(String tripGroupIdentifier){
        this.tripGroupIdentifier = tripGroupIdentifier;
    }

    public String getTripGroupIdentifier() {
        return tripGroupIdentifier;
    }

    public void setTripGroupIdentifier(String tripGroupIdentifier) {
        this.tripGroupIdentifier = tripGroupIdentifier;
    }
}
