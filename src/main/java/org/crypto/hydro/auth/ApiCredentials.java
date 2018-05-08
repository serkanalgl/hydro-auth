package org.crypto.hydro.auth;

/**
 * Created by serkanalgul on 2.05.2018.
 */
final class ApiCredentials {

    private String username;
    private String key;

    public ApiCredentials(String key, String username) {
        this.username = username;
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ApiCredentials{" +
                "username='" + username + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
