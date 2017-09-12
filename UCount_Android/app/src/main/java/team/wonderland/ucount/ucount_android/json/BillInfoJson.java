package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 账目信息
 * Created by CLL on 17/8/18.
 */
public class BillInfoJson implements Serializable {
    public Long billId;
    public String type ; 			//交易类型
    public String trader ; 			//交易对象
    public double amount ; 			//交易金额
    public String time;				//交易时间

    public BillInfoJson() {
    }

    public BillInfoJson(Long billId,String type, String trader, double amount, String time) {
        this.billId = billId;
        this.type = type;
        this.trader = trader;
        this.amount = amount;
        this.time = time;
    }

    @Override
    public String toString() {
        return "BillInfoJson{" +
                "type='" + type + '\'' +
                ", trader='" + trader + '\'' +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                '}';
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
