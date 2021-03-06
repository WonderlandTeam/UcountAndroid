package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import team.wonderland.ucount.ucount_android.exception.MyResponseErrorHandler;
import team.wonderland.ucount.ucount_android.json.TaskAddJson;
import team.wonderland.ucount.ucount_android.json.TaskInfoJson;
import team.wonderland.ucount.ucount_android.json.TaskModifyJson;
import team.wonderland.ucount.ucount_android.util.RestAPI;

import java.util.List;
import java.util.Map;

/**
 * 任务管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = RestAPI.URL, converters = {FormHttpMessageConverter.class, MappingJackson2HttpMessageConverter.class},responseErrorHandler = MyResponseErrorHandler.class)
public interface TaskService {
    /**
     * 获取单个计划信息
     * @param taskId
     * @return
     */
    @Get("/tasks/{taskId}")
    TaskInfoJson getTask(@Path Long taskId);

    /**
     * 获取用户不同状态的攒钱计划信息
     * @param username
     * @param taskState
     * @return
     */
    @Get("/tasks/states?username={username}&taskState={taskState}")
    List<TaskInfoJson> getTasksByState(@Path String username, @Path String taskState);

    /**
     *获取用户所有攒钱计划信息
     * @param username
     * @return
     */
    @Get("/tasks?username={username}")
    List<TaskInfoJson> getTasksByUser(@Path String username);

    /**
     * 用户添加攒钱计划
     * @param taskAddJson
     * @return
     */
    @Post("/tasks/")
    Long addTask(@Body TaskAddJson taskAddJson);

    /**
     * 更新攒钱计划
     * @param taskId
     * @param taskModifyJson
     * @return
     */
    @Post("/tasks/{taskId}/")
    void updateTask(@Path Long taskId,@Body TaskModifyJson taskModifyJson);

    /**
     * 删除攒钱计划
     * @param taskId
     * @return
     */
    @Delete("/tasks/{taskId}")
    void deleteTask(@Path Long taskId);
}
