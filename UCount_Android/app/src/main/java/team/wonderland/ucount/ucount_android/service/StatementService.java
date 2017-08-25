package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Map;

/**
 * 获取报表
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "localhost:8080/api/",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface StatementService {
    /**
     * 获取资产负债表
     * @param username
     * @param beginDate
     * @param endDate
     * @return
     */
    @Get("/statement/balanceSheet?username={username}&beginDate={beginDate}&endDate={endDate}")
    public Map<String, Object> getBalanceSheet(@Path String username,
                                               @Path String beginDate,
                                               @Path String endDate);

    /**
     * 获取收支储蓄表（利润表）
     * @param username
     * @param beginDate
     * @param endDate
     * @return
     */
    @Get("/statement/incomeStatement?username={username}&beginDate={beginDate}&endDate={endDate}")
    public Map<String, Object> getIncomeStatement(@Path String username,
                                                  @Path String beginDate,
                                                  @Path String endDate);

    /**
     * 获取现金流量表
     * @param username
     * @param beginDate
     * @param endDate
     * @return
     */
    @Get("/statement/cashFlowsStatement?username={username}&beginDate={beginDate}&endDate={endDate}")
    public Map<String, Object> getCashFlowsStatement(@Path String username,
                                                     @Path String beginDate,
                                                     @Path String endDate);
}
