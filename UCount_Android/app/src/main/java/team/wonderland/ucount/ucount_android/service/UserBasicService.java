package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Part;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import team.wonderland.ucount.ucount_android.exception.MyResponseErrorHandler;
import team.wonderland.ucount.ucount_android.json.UserInfoJson;
import team.wonderland.ucount.ucount_android.json.UserSignUpJson;
import team.wonderland.ucount.ucount_android.util.RestAPI;

import java.util.Map;

/**
 * 注册和登录
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = RestAPI.URL,
        converters = {FormHttpMessageConverter.class, MappingJackson2HttpMessageConverter.class},
        responseErrorHandler = MyResponseErrorHandler.class)
public interface UserBasicService {
    /**
     * 用户注册
     * @param userSignUpJson
     * @return
     */
    @Post("/users")
    void signUp(@Body UserSignUpJson userSignUpJson);

    /**
     * 用户登录
     * @return
     */
    @Post("/users/login")
    UserInfoJson login(@Part String username, @Part String password);
}
