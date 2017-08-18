package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 帖子发布信息
 * Created by CLL on 17/8/18.
 */
public class PostShareJson implements Serializable {
    private String username;//用户名
    private String title;//主题
    private String content;//内容
    private String time;//发布时间

    public PostShareJson(String username, String title, String content, String time) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
