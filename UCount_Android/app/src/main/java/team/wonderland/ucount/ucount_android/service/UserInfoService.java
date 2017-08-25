package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.UserModifyJson;

import java.util.Map;

/**
 * 用户
 * Created by CLL on 17/8/16.
 */

@Rest(rootUrl = "localhost:8080/api/",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface UserInfoService {
    /**
     * 获得用户信息
     * @param username
     * @return
     */
    @Get("/users/{username}")
    public Map<String, Object> getUserInfo(@Path String username);

    /**
     * 修改用户信息
     * @param username
     * @param userModifyJson
     */
    @Put("/users/{username}")
    public Map<String, Object> modifyUserInfo(@Path String username, @Body UserModifyJson userModifyJson);

}
