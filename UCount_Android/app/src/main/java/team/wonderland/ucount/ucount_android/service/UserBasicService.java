package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.UserSignUpJson;

/**
 * 注册和登录
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "",converters = {MappingJackson2HttpMessageConverter.class})
public interface UserBasicService {
    /**
     * 用户注册
     * @param userSignUpJson
     * @return
     */
    @Post("/users")
    public Long signUp(@Body UserSignUpJson userSignUpJson);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Post("")
    public Long login(@Path String userName,@Path String password);
}
