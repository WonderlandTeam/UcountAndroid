package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * Created by CLL on 17/9/9.
 */
public class CashFlowJson implements Serializable{
    private String accountType;
    private String cardId;
    private String billType;
    private double money;
    private String time;

    public CashFlowJson() {
    }

    public CashFlowJson(String accountType, String cardId, String billType, double money, String time) {
        this.accountType = accountType;
        this.cardId = cardId;
        this.billType = billType;
        this.money = money;
        this.time = time;
    }

    @Override
    public String toString(){
        String s="accountType="+accountType+", cardId="+cardId+", billType= "+billType+", money="+money+", time="+time;
        return s;
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

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
