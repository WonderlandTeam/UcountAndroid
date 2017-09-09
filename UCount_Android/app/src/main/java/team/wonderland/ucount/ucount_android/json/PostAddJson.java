package team.wonderland.ucount.ucount_android.json;

/**
 * Created by green-cherry on 2017/9/9.
 */

public class PostAddJson {

    public String username;
    public String title;
    public String content;

    public PostAddJson(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostAddJson{" +
                "username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
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
}
