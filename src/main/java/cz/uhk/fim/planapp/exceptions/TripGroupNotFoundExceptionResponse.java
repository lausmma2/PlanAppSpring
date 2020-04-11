package cz.uhk.fim.planapp.exceptions;

public class TripGroupNotFoundExceptionResponse {

    private String TripGroupNotFound;

    public TripGroupNotFoundExceptionResponse(String TripGroupNotFound) {
        TripGroupNotFound = TripGroupNotFound;
    }

    public String getTripGroupNotFound() {
        return TripGroupNotFound;
    }

    public void setTripGroupNotFound(String TripGroupNotFound) {
        TripGroupNotFound = TripGroupNotFound;
    }
}
