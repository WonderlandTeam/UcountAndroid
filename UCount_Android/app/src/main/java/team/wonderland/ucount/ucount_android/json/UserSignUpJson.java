package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 注册信息
 * Created by CLL on 17/8/16.
 */
public class UserSignUpJson implements Serializable {
    private String userName;//用户名
    private String password;//密码
    private String tel;//电话号码
    private String email;//邮箱
//    private String alipayAccount;//支付宝账号
//    private String cardId;//银行卡号
//    private String schoolCardId;//校园卡号

    public UserSignUpJson() {
    }

    /**
     * 其余参数set,可以为空
     * @param username
     * @param password
     * @param tel
     * @param email
     */
    public UserSignUpJson(String username, String password, String tel, String email) {
        this.userName = username;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
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

//    public String getAlipayAccount() {
//        return alipayAccount;
//    }
//
//    public void setAlipayAccount(String alipayAccount) {
//        this.alipayAccount = alipayAccount;
//    }
//
//    public String getCardId() {
//        return cardId;
//    }
//
//    public void setCardId(String cardId) {
//        this.cardId = cardId;
//    }
//
//    public String getSchoolCardId() {
//        return schoolCardId;
//    }
//
//    public void setSchoolCardId(String schoolCardId) {
//        this.schoolCardId = schoolCardId;
//    }
}
