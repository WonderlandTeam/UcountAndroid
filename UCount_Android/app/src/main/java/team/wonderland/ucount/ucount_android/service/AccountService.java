package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import team.wonderland.ucount.ucount_android.exception.MyResponseErrorHandler;
import team.wonderland.ucount.ucount_android.json.AccountAddJson;
import team.wonderland.ucount.ucount_android.json.AccountInfoJson;
import team.wonderland.ucount.ucount_android.util.RestAPI;

import java.util.List;
import java.util.Map;

/**
 * 用户账户管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = RestAPI.URL, converters = {FormHttpMessageConverter.class, MappingJackson2HttpMessageConverter.class},responseErrorHandler = MyResponseErrorHandler.class)
public interface AccountService {
    /**
     * 获取账户信息
     * @param account_id
     * @return
     */
    @Get("/accounts/{account_id}")
    AccountInfoJson getAccountByID(@Path Long account_id);

    /**
     * 获取用户所有账户信息
     * @param username
     * @return
     */
    @Get("/accounts?username={username}")
    List<AccountInfoJson> getAccountsByUser(@Path String username);

    /**
     * 添加账户信息
     * @param accountAddJson
     * @return account_id
     */
    @Post("/accounts")
    Long addAccount(@Body AccountAddJson accountAddJson);

    /**
     * 删除账户信息
     * @param account_id
     */
    @Delete("/accounts/{account_id}")
    void deleteAccount(@Path Long account_id);
}
