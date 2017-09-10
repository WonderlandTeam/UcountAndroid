package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

import static android.R.attr.id;

/**
 * 用户账户信息
 * Created by CLL on 17/8/18.
 */
public class AccountInfoJson implements Serializable{
    public long   accountId;
    public double balance; 		//余额
    public String type; 		//类型
    public String username; 	//用户名
    public String cardID; 		//账户id
    public double income; 		//账户总收入
    public double expend; 		//账户总支出

    public AccountInfoJson() {
    }

    public AccountInfoJson(long accountId, double balance, String type, String username, String cardID, double income, double expend) {
        this.accountId = accountId;
        this.balance = balance;
        this.type = type;
        this.username = username;
        this.cardID = cardID;
        this.income = income;
        this.expend = expend;
    }

    public String toString(){
        String s=accountId+" "+balance+" "+type+" "+username+" "+cardID+" "+income+" "+expend+" ";
        return s;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpend() {
        return expend;
    }

    public void setExpend(double expend) {
        this.expend = expend;
    }
}
