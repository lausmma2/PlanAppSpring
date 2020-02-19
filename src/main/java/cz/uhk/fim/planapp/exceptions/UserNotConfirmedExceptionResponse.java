package cz.uhk.fim.planapp.exceptions;

public class UserNotConfirmedExceptionResponse {

    private String username;

    public UserNotConfirmedExceptionResponse(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
