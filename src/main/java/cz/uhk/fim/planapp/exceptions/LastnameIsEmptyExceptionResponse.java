package cz.uhk.fim.planapp.exceptions;

public class LastnameIsEmptyExceptionResponse {

    private String lastname;

    public LastnameIsEmptyExceptionResponse(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
