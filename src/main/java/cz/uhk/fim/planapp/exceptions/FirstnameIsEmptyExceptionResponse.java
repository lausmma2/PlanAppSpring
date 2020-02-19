package cz.uhk.fim.planapp.exceptions;

public class FirstnameIsEmptyExceptionResponse {

    private String firstname;

    public FirstnameIsEmptyExceptionResponse(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
