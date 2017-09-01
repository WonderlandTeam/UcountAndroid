package team.wonderland.ucount.ucount_android.entity;

/**
 * Created by CLL on 17/9/1.
 */
public class Reply {
    private String title;//帖子标题
    private String context;//回复内容
    private String username;//被回复帖子的用户

    public Reply(String title, String context, String username) {
        this.title = title;
        this.context = context;
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
