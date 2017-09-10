package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 用户信息
 * Created by CLL on 17/8/18.
 */
public class UserInfoJson implements Serializable{
    public String userName;    //用户名
    public String password;    //用户密码
    public String tel;         //用户手机
    public String email;       //用户邮箱

    public UserInfoJson() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
