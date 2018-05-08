package org.crypto.hydro.auth;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by serkanalgul on 2.05.2018.
 */
public final class RestClient implements IRestClient {

    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8;";
    private static final Gson gson = new Gson();
    private HttpClient httpClient;

    public RestClient() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(200);
        connManager.setDefaultMaxPerRoute(50);

        httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
    }

    public <T extends BaseApiResponse> T post(String url, Object body, Class<T> classOfT) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", CONTENT_TYPE);

        String bodyStr = null;

        if (body != null) {
            bodyStr = gson.toJson(body);
            httpPost.setEntity(new StringEntity(bodyStr));
        }

        logger.debug("http POST request executing. url : {}, body : {}", url, bodyStr);

        HttpResponse httpResponse = httpClient.execute(httpPost);

        return gson.fromJson(resolveHttpResponse(httpResponse), classOfT);
    }

    private String resolveHttpResponse(HttpResponse response) throws IOException {

        BufferedReader bufferedReader = null;
        String jsonResponse = "";

        int statusCode = 200;
        String reasonPhrase = "";

        try {

            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();

            statusCode = statusLine.getStatusCode();
            reasonPhrase = statusLine.getReasonPhrase();

            logger.debug("POST response is received by the service. status code : {}, reason phrase : {}", statusCode, reasonPhrase);

            if (statusCode == 404) {
                throw new HttpResponseException(statusCode, reasonPhrase);
            }

            InputStream instream = entity.getContent();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }

                jsonResponse = result.toString();

                if (statusCode != 200) {
                    throw new HttpResponseException(statusCode, jsonResponse);
                }
            } finally {
                instream.close();
            }

            return jsonResponse;

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    logger.error("error when bufferedReader closing.");
                    throw new RuntimeException("error when bufferedReader closing.", ex);
                }
            }
        }
    }
}
