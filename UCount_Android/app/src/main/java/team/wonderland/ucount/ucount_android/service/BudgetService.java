package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.BudgetAddJson;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.json.BudgetModifyJson;

import java.util.List;

/**
 * 预算管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface BudgetService {
    /**
     * 获取预算信息
     * @param budget_id         预算id
     * @return                  预算信息vo
     */
    @Get("/budgets/{budget_id}")
    public BudgetInfoJson getBudget(@Path Long budget_id);

    /**
     * 获取用户所有预算信息
     * @param user_id           用户id
     * @param date
     * @return                  用户预算列表
     */
    @Get("/budgets?budgetId={user_id}&date={date}")
    public List<BudgetInfoJson> getBudgetsByUser(@Path Long user_id,@Path String date);

    /**
     * 添加预算
     * @param budgetAddJson       新建预算信息vo
     * @return                  预算id
     */
    @Post("/budgets")
    public Long addBudget(@Body BudgetAddJson budgetAddJson);

    /**
     * 修改预算信息
     * @param budget_id          预算id
     * @param budgetModifyJson    预算修改信息vo
     */
    @Put("/budgets/{budget_id}")
    public void updateBudget(@Path Long budget_id, @Body BudgetModifyJson budgetModifyJson);

    /**
     * 删除预算
     * @param budget_id          预算id
     */
    @Delete("/budgets/{budget_id}")
    public void deleteBudget(@Path Long budget_id);
}
