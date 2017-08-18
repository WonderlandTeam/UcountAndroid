package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.AccountAddJson;
import team.wonderland.ucount.ucount_android.json.AccountInfoJson;

import java.util.List;

/**
 * 用户账户管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface AccountService {
    /**
     * 获取账户信息
     * @param account_id
     * @return
     */
    @Get("/accounts/{account_id}")
    public AccountInfoJson getAccountByID(@Path Long account_id);

    /**
     * 获取用户所有账户信息
     * @param
     * @return
     */
    @Get("/accounts")
    public List<AccountInfoJson> getAccountsByUser();

    /**
     * 添加账户信息
     * @param accountAddJson
     * @return account_id
     */
    @Post("/accounts")
    public Long addAccount(@Body AccountAddJson accountAddJson);

    /**
     * 删除账户信息
     * @param account_id
     */
    @Delete("/accounts/{account_id}")
    public void deleteAccount(@Path Long account_id);
}
