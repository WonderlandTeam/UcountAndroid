package team.wonderland.ucount.ucount_android.exception;

import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by huangxiao on 2017/9/8.
 */

public class MyResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode() != HttpStatus.OK;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String message = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody(), Charset.forName("UTF-8")));
        String line;
        while ((line = br.readLine()) != null) {
            message += line;
        }

        throw new ResponseException(message);
    }

}
