package cz.uhk.fim.planapp.exceptions;

public class TripNotFoundExceptionResponse {

    private String TripNotFound;

    public TripNotFoundExceptionResponse(String tripNotFound) {
        TripNotFound = tripNotFound;
    }

    public String getTripNotFound() {
        return TripNotFound;
    }

    public void setTripNotFound(String tripNotFound) {
        TripNotFound = tripNotFound;
    }
}
