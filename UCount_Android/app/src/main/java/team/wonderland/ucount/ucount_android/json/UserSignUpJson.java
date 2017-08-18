package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 登录信息
 * Created by CLL on 17/8/16.
 */
public class UserSignUpJson implements Serializable{
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
