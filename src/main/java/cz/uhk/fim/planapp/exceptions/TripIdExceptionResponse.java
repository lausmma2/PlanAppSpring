package cz.uhk.fim.planapp.exceptions;

public class TripIdExceptionResponse {

    private String tripIdentifier;

    public TripIdExceptionResponse(String tripIdentifier){
        this.tripIdentifier = tripIdentifier;
    }

    public String getTripIdentifier() {
        return tripIdentifier;
    }

    public void setTripIdentifier(String tripIdentifier) {
        this.tripIdentifier = tripIdentifier;
    }
}