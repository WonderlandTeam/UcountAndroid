package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 账目信息
 * Created by CLL on 17/8/18.
 */
public class BillInfoJson implements Serializable {
    private Long billID;//账目id
    private String username;//用户名
    private Calendar time; //时间
    private Double incomeExpenditure;//收／支金额
    private String commodity;//消费项目
    private String consumeType;//消费类型
    private String remark;//备注
    private String accountType;//账户类型
    private String cardType;//卡类型
    private String cardId;//卡号

    public Long getBillID() {
        return billID;
    }

    public void setBillID(Long billID) {
        this.billID = billID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
