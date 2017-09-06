package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import team.wonderland.ucount.ucount_android.json.LoginJson;
import team.wonderland.ucount.ucount_android.json.UserSignUpJson;

import java.util.Map;

/**
 * 注册和登录
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "http://172.17.137.180:8080/api",converters = {MappingJackson2HttpMessageConverter.class})
public interface UserBasicService {
    /**
     * 用户注册
     * @param userSignUpJson
     * @return
     */
    @Post("/users")
    Map<String, Object> signUp(@Body UserSignUpJson userSignUpJson);

    /**
     * 用户登录
     * @return
     */
    @Post("/users/login")
    Map<String, Object> login(@Body LoginJson loginJson);
}
