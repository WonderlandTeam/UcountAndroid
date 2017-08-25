package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.UserSignUpJson;

import java.util.Map;

/**
 * 注册和登录
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "localhost:8080/api/",converters = {MappingJackson2HttpMessageConverter.class})
public interface UserBasicService {
    /**
     * 用户注册
     * @param userSignUpJson
     * @return
     */
    @Post("/users")
    public Map<String, Object> signUp(@Body UserSignUpJson userSignUpJson);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Post("/users/login?username={username}&password={password}")
    public Map<String, Object> login(@Path String username, @Path String password);
}
