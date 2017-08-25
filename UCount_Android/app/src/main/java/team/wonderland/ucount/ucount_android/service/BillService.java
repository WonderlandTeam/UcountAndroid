package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.BillAddJson;
import team.wonderland.ucount.ucount_android.json.BillInfoJson;

import java.util.List;
import java.util.Map;

/**
 * 账目管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "localhost:8080/api/",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface BillService {
    /**
     * 获取单条账目信息
     * @param account_id         账户id
     * @param bill_id            账目id
     * @return                  账目信息vo
     */
    @Get("/accounts/{account_id}/bills/{bill_id}")
    public BillInfoJson getBill(@Path Long account_id, @Path Long bill_id);

    /**
     * 获取账户所有账目信息
     * @param account_id         账户id
     *                           pageable筛选信息传什么？
     * @return                  账目列表
     */
    //TODO
    @Get("/accounts/{account_id}/bills")
    public List<BillInfoJson> getBillsByAccount(@Path Long account_id);

    /**
     * 获取用户所有账目信息
     * @param username          用户id
     *                           pageable筛选信息传什么？
     * @return                  账目列表
     */
    //TODO
    @Get("/users/{username}/bills")
    public List<BillInfoJson> getBillsByUser(@Path String username);

    /**
     * 用户手动记账
     * @param account_id         账户id
     * @param billAddJson       账目添加信息
     * @return                  新增账目id
     */
    @Post("/accounts/{account_id}/bills")
    public Map<String, Object> addBillManually(@Path Long account_id, @Body BillAddJson billAddJson);

    /**
     * 删除单条账目信息
     * @param account_id         账户id
     * @param bill_id            账目id
     */
    @Delete("/accounts/{account_id}/bills/{bill_id}")
    public Map<String, Object> deleteBill(@Path Long account_id, @Path Long bill_id);
}
