package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.UserInfoJson;
import team.wonderland.ucount.ucount_android.json.UserModifyJson;

/**
 * 用户
 * Created by CLL on 17/8/16.
 */

@Rest(rootUrl = "",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface UserInfoService {
    /**
     * 获得用户信息
     * @param userID
     * @return
     */
    @Get("/users/{user_id}")
    public UserInfoJson getUserInfo(@Path Long userID);

    /**
     * 修改用户信息
     * @param userID
     * @param userModifyJson
     */
    @Put("/users/{user_id}")
    void modifyUserInfo(@Path Long userID, @Body UserModifyJson userModifyJson);

}
