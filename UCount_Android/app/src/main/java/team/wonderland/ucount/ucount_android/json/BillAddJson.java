package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 手动记账信息
 * Created by CLL on 17/8/18.
 */
public class BillAddJson implements Serializable{
    public String time;                 //交易时间
    public Double incomeExpenditure;    //交易金额
    public String commodity;            //商品名称
    public String consumeType;          //交易类型
    public String remark;

    public BillAddJson(String time, Double incomeExpenditure, String commodity, String consumeType, String remark) {
        this.time = time;
        this.incomeExpenditure = incomeExpenditure;
        this.commodity = commodity;
        this.consumeType = consumeType;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BillAddJson{" +
                "time='" + time + '\'' +
                ", incomeExpenditure=" + incomeExpenditure +
                ", commodity='" + commodity + '\'' +
                ", consumeType='" + consumeType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getIncomeExpenditure() {
        return incomeExpenditure;
    }

    public void setIncomeExpenditure(Double incomeExpenditure) {
        this.incomeExpenditure = incomeExpenditure;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
