package org.crypto.hydro.auth;

import java.io.IOException;

/**
 * Created by serkanalgul on 2.05.2018.
 */
public interface IRestClient {

    <T extends BaseApiResponse> T post(String url, Object body, Class<T> classOfT) throws IOException;

}
