package team.wonderland.ucount.ucount_android.json;

/**
 * Created by green-cherry on 2017/9/9.
 */

public class PostReplyAddJson {
    public String username;     // 用户名
    public String content;      // 回复内容

    public PostReplyAddJson() {
    }

    public PostReplyAddJson(String username, String content) {
        this.username = username;
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostReplyAddJson{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
