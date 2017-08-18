package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 账户添加信息
 * Created by CLL on 17/8/18.
 */
public class AccountAddJson implements Serializable {
    private String username;//用户名
    private String cardType;//卡类型
    private String cardId;//卡号

    public AccountAddJson(String username, String cardType, String cardId) {
        this.username = username;
        this.cardType = cardType;
        this.cardId = cardId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
