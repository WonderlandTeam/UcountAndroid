package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.BillAddJson;
import team.wonderland.ucount.ucount_android.json.BillInfoJson;

import java.util.List;

/**
 * 账目管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface BillService {
    /**
     * 获取单条账目信息
     * @param accountId         账户id
     * @param billId            账目id
     * @return                  账目信息vo
     */
    @Get("/accounts/{account_id}/bills/{bill_id}")
    public BillInfoJson getBill(@Path Long accountId, @Path Long billId);

    /**
     * 获取账户所有账目信息
     * @param accountId         账户id
     * @return                  账目列表
     */
    @Get("/accounts/{account_id}/bills")
    public List<BillInfoJson> getBillsByAccount(@Path Long accountId);

    /**
     * 获取用户所有账目信息
     * @param userId            用户id
     * @return                  账目列表
     */
    @Get("/users/{user_id}/bills")
    public List<BillInfoJson> getBillsByUser(@Path Long userId);

    /**
     * 用户手动记账
     * @param accountId         账户id
     * @param billAddJson       账目添加信息
     * @return                  新增账目id
     */
    @Post("/accounts/{account_id}/bills")
    public Long addBillManually(@Path Long accountId, @Body BillAddJson billAddJson);

    /**
     * 用户实时同步记账
     * @param accountId         账户id
     * @return                  同步账目信息列表
     */
    @Put("/accounts/{account_id}/bills")
    public List<BillInfoJson> addBillAutomatically(@Path Long accountId);

    /**
     * 删除单条账目信息
     * @param accountId         账户id
     * @param billId            账目id
     */
    @Delete("/accounts/{account_id}/bills/{bill_id}")
    public void deleteBill(@Path Long accountId,@Path Long billId);
}
