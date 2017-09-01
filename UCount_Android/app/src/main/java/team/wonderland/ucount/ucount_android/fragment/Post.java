package team.wonderland.ucount.ucount_android.fragment;

import java.io.Serializable;

/**
 * Created by liuyu on 2017/8/31.
 */

public class Post implements Serializable{
    private String title;
    private String date;
    private String author;
    private String content;

    public Post(String title, String date, String author, String content) {
        this.title = title;
        this.date = date;
        this.author = author;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
