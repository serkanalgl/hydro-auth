package org.crypto.hydro.auth;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by serkanalgul on 2.05.2018.
 */
public class HydroAuthenticatorConfig {

    private ApiCredentials apiCredentials;
    private HttpClient httpClient = HttpClientBuilder.create().build();

    public HydroAuthenticatorConfig(ApiCredentials apiCredentials) {
        this.apiCredentials = apiCredentials;
    }

    public ApiCredentials getApiCredentials() {
        return apiCredentials;
    }

    public void setApiCredentials(ApiCredentials apiCredentials) {
        this.apiCredentials = apiCredentials;
    }

    public void setCustomHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public static class Builder {

        private ApiCredentials apiCredentials;
        private HttpClient httpClient;

        public Builder apiCredentials(ApiCredentials apiCredentials) {
            this.apiCredentials = apiCredentials;
            return this;
        }

        public Builder setCustomHttpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public HydroAuthenticatorConfig build() {
            HydroAuthenticatorConfig hydroAuthenticatorConfig = new HydroAuthenticatorConfig(apiCredentials);
            if (httpClient != null) {
                hydroAuthenticatorConfig.setCustomHttpClient(httpClient);
            }
            return hydroAuthenticatorConfig;
        }

    }

}
