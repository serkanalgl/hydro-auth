package org.crypto.hydro.auth;

public class Authenticate extends BaseApiResponse {

    private boolean authenticated;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String toString() {
        return "Authenticate{" +
                "success='" + super.isSuccess() + '\'' +
                "message='" + super.getMessage() + '\'' +
                "authenticated=" + authenticated + '}';
    }
}
