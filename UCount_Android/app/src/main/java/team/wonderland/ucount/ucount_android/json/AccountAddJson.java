package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 账户添加信息
 * Created by CLL on 17/8/18.
 */
public class AccountAddJson implements Serializable {
    public String username;         // 用户名
    public String accountType;      // 账户类型
    public String cardId;           // 账户id
    public double balance;          // 初始余额（手动账户须填写）

    public AccountAddJson() {
    }

    public AccountAddJson(String username, String accountType, String cardID, double balance) {
        this.username = username;
        this.accountType = accountType;
        this.cardId = cardID;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return username + " " + accountType + " " + cardId + " " + balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardID) {
        this.cardId = cardID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
