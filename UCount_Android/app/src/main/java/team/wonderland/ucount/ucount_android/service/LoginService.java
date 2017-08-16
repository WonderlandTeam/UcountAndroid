package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.LoginJson;

/**
 * 登录
 * Created by CLL on 17/8/16.
 */

@Rest(rootUrl = "",converters = {MappingJackson2HttpMessageConverter.class})
public interface LoginService {
    @Post("")
    public LoginJson login(@Body LoginJson loginJson);
}
