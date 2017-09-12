package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 计划信息（攒钱id，用户名，攒钱项目，攒钱总额，已攒金额，开始时间，预计完成日期，每日应攒金额,计划状态）
 * 时间格式为yyyy-MM-dd
 * Created by green-cherry on 2017/8/21.
 */

public class TaskInfoJson implements Serializable{
    private Long id;
    private String username;
    private String taskContent;
    private double upper;
    private double savedMoney;
    private String createTime;
    private String deadline;
    private double haveToSaveEveryday;
    private String taskState;

    public TaskInfoJson() {
    }

    public TaskInfoJson(Long id, String username, String taskContent, double upper, double savedMoney, String createTime, String deadline, double haveToSaveEveryday, String taskState) {
        this.id = id;
        this.username = username;
        this.taskContent = taskContent;
        this.upper = upper;
        this.savedMoney = savedMoney;
        this.createTime = createTime;
        this.deadline = deadline;
        this.haveToSaveEveryday = haveToSaveEveryday;
        this.taskState = taskState;
    }

    @Override
    public String toString() {
        return "TaskInfoJson{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", upper=" + upper +
                ", savedMoney=" + savedMoney +
                ", createTime='" + createTime + '\'' +
                ", deadline='" + deadline + '\'' +
                ", haveToSaveEveryday=" + haveToSaveEveryday +
                ", taskState='" + taskState + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public double getUpper() {
        return upper;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }

    public double getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(double savedMoney) {
        this.savedMoney = savedMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public double getHaveToSaveEveryday() {
        return haveToSaveEveryday;
    }

    public void setHaveToSaveEveryday(double haveToSaveEveryday) {
        this.haveToSaveEveryday = haveToSaveEveryday;
    }


    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }
}
