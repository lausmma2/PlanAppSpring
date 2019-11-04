package cz.uhk.fim.planapp.exceptions;

public class UsernameExistsExceptionResponse {

    private String username;

    public UsernameExistsExceptionResponse(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
