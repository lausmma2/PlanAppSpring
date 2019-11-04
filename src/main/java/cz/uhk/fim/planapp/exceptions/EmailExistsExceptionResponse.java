package cz.uhk.fim.planapp.exceptions;

public class EmailExistsExceptionResponse {

    private String email;

    public EmailExistsExceptionResponse(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
