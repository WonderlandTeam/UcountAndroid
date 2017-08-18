package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 请求拦截器
 * Created by CLL on 17/8/18.
 */
@EBean(scope = EBean.Scope.Singleton)
public class BasicAuthInterceptor implements ClientHttpRequestInterceptor {

    String username;
    String password;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        HttpAuthentication auth = new HttpBasicAuthentication(username, password);
        headers.setAuthorization(auth);
        return execution.execute(request, body);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void init(String username,String password){
        this.username = username;
        this.password = password;
    }

    public void clear(){
        username = null;
        password = null;
    }
}
