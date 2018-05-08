package org.crypto.hydro.auth;

/**
 * Created by serkanalgul on 2.05.2018.
 */
public interface IHydroAuthenticator {

    public Whitelist whitelist(String ethAddress);

    public Challenge challenge(String hydroAddressId);

    public BaseApiResponse authenticate(String hydroAddressId);
}
