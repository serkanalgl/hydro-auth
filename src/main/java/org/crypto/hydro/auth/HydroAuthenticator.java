package org.crypto.hydro.auth;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by serkanalgul on 2.05.2018.
 */
public class HydroAuthenticator implements IHydroAuthenticator {

    private static final Logger logger = LoggerFactory.getLogger(HydroAuthenticator.class);

    private String apiUrl = "https://api.hydrogenplatform.com/hydro/v1";

    private HydroAuthenticatorConfig hydroAuthenticatorConfig;
    private IRestClient restClient;

    public HydroAuthenticator(String apiKey, String apiUsername, boolean useTestnet) {

        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(apiUsername)) {
            throw new IllegalArgumentException("apiKey and apiUsername can not be null or empty");
        }

        this.hydroAuthenticatorConfig = new HydroAuthenticatorConfig(new ApiCredentials(apiKey, apiUsername));
        this.restClient = new RestClient();

        if (useTestnet) {
            apiUrl = "https://sandbox.hydrogenplatform.com/hydro/v1";
            logger.info("HydroAuthenticator initialized for TestNet: {}", apiUrl);
        } else {
            logger.info("HydroAuthenticator initialized for MainNet: {}", apiUrl);
        }

    }

    @Override
    public Whitelist whitelist(String ethAddress) {

        try {

            if (StringUtils.isEmpty(ethAddress)) {
                throw new IllegalArgumentException("parameter ethAddress can not be null or empty");
            }

            String url = String.format(apiUrl + "/whitelist/%s", ethAddress);
            return restClient.post(url, hydroAuthenticatorConfig.getApiCredentials(), Whitelist.class);

        } catch (Exception e) {
            logger.error("an error occurred while whitelist request was performed");
            Whitelist whitelist = new Whitelist();
            whitelist.setSuccess(false);
            whitelist.setMessage(e.getMessage());
            return whitelist;
        }
    }

    public Challenge challenge(String hydroAddressId) {

        try {

            if (StringUtils.isEmpty(hydroAddressId)) {
                throw new IllegalArgumentException("parameter hydroAddressId can not be null or empty");
            }

            String url = String.format(apiUrl + "/challenge?hydro_address_id=%s", hydroAddressId);
            return restClient.post(url, hydroAuthenticatorConfig.getApiCredentials(), Challenge.class);

        } catch (Exception e) {
            logger.error("an error occurred while challenge request was performed", e);
            Challenge challenge = new Challenge();
            challenge.setSuccess(false);
            challenge.setMessage(e.getMessage());
            return challenge;
        }

    }

    public Authenticate authenticate(String hydroAddressId) {

        try {

            if (StringUtils.isEmpty(hydroAddressId)) {
                throw new IllegalArgumentException("parameter hydroAddressId can not be null or empty");
            }

            String url = String.format(apiUrl + "/authenticate?hydro_address_id=%s", hydroAddressId);
            return restClient.post(url, hydroAuthenticatorConfig.getApiCredentials(), Authenticate.class);

        } catch (Exception e) {
            logger.error("an error occurred while authenticate request was performed", e);
            Authenticate authenticate = new Authenticate();
            authenticate.setSuccess(false);
            authenticate.setMessage(e.getMessage());
            return authenticate;
        }
    }

}
