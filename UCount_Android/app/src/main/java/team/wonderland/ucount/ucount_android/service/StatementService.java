package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.exception.MyResponseErrorHandler;
import team.wonderland.ucount.ucount_android.json.BalanceSheetJson;
import team.wonderland.ucount.ucount_android.json.CashFlowJson;
import team.wonderland.ucount.ucount_android.json.IncomeStatementJson;
import team.wonderland.ucount.ucount_android.util.RestAPI;

import java.util.List;
import java.util.Map;

/**
 * 获取报表
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = RestAPI.URL, converters = {FormHttpMessageConverter.class, MappingJackson2HttpMessageConverter.class, StringHttpMessageConverter.class},responseErrorHandler = MyResponseErrorHandler.class)
public interface StatementService {
    /**
     * 获取资产负债表
     * @param username
     * @param beginDate
     * @param endDate
     * @return
     */
    @Get("/statements/balanceSheet?username={username}&beginDate={beginDate}&endDate={endDate}")
    BalanceSheetJson getBalanceSheet(@Path String username,
                                     @Path String beginDate,
                                     @Path String endDate);

    /**
     * 获取收支储蓄表（利润表）
     * @param username
     * @param beginDate
     * @param endDate
     * @return
     */
    @Get("/statements/incomeStatement?username={username}&beginDate={beginDate}&endDate={endDate}")
    IncomeStatementJson getIncomeStatement(@Path String username,
                                           @Path String beginDate,
                                           @Path String endDate);

    /**
     * 获取现金流量表
     * @param username
     * @param beginDate
     * @param endDate
     * @return
     */
    @Get("/statements/cashFlows?username={username}&beginDate={beginDate}&endDate={endDate}")
    List<CashFlowJson> getCashFlowsStatement(@Path String username,
                                             @Path String beginDate,
                                             @Path String endDate);
}
