package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import team.wonderland.ucount.ucount_android.exception.MyResponseErrorHandler;
import team.wonderland.ucount.ucount_android.json.UserInfoJson;
import team.wonderland.ucount.ucount_android.json.UserModifyJson;
import team.wonderland.ucount.ucount_android.util.RestAPI;

import java.util.Map;

/**
 * 用户
 * Created by CLL on 17/8/16.
 */

@Rest(rootUrl = RestAPI.URL, converters = {FormHttpMessageConverter.class, MappingJackson2HttpMessageConverter.class}, responseErrorHandler = MyResponseErrorHandler.class)
public interface UserInfoService {
    /**
     * 获得用户信息
     *
     * @param username
     * @return
     */
    @Get("/users/{username}")
    UserInfoJson getUserInfo(@Path String username);

    /**
     * 修改用户信息
     *
     * @param username
     * @param userModifyJson
     */
    @Put("/users/{username}")
    String modifyUserInfo(@Path String username, @Body UserModifyJson userModifyJson);

}
