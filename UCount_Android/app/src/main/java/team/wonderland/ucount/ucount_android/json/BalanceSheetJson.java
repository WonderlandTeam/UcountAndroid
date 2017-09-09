package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by CLL on 17/9/9.
 */
public class BalanceSheetJson implements Serializable{
    /************************** 资产项目 **************************/

    /* 流动性资产 */
    public Map<String, Double> cash;                    // 现金
    public Map<String, Double> deposit;                 // 活存
    public Map<String, Double> currentAssets;           // 流动性资产

    /* 投资性资产 */
    public Map<String, Double> foreignDeposit;          // 外币存款
    public Map<String, Double> stock;                   // 股票
    public Map<String, Double> fund;                    // 基金
    public Map<String, Double> bond;                    // 债券
    public Map<String, Double> investmentInsurance;     // 投资型保险
    public Map<String, Double> investmentAssets;        // 投资性资产

    /* 自用性资产 */
    public Map<String, Double> computer;                // 自用电脑
    public Map<String, Double> mobilePhone;             // 自用手机
    public Map<String, Double> personalAssets;          // 自用性资产

    /* 总资产 */
    public Map<String, Double> totalAssets;

    /************************** 负债项目 **************************/

    public double creditCardLiabilities;                // 信用卡负债
    public double consumerLiabilities;                  // 消费负债
    public double investmentLiabilities;                // 投资负债
    public double personalLiabilities;                  // 自用负债
    /* 总负债 */
    public double totalLiabilities;                     // 总负债

    /************************** 净值项目 **************************/

    public Map<String, Double> currentNetValue;         // 流动净值
    public Map<String, Double> investmentNetValue;      // 投资净值
    public Map<String, Double> personalNetValue;        // 自用净值
    /* 总净值 */
    public Map<String, Double> totalNetValue;

    public Map<String, Double> getCash() {
        return cash;
    }

    public void setCash(Map<String, Double> cash) {
        this.cash = cash;
    }

    public Map<String, Double> getDeposit() {
        return deposit;
    }

    public void setDeposit(Map<String, Double> deposit) {
        this.deposit = deposit;
    }

    public Map<String, Double> getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(Map<String, Double> currentAssets) {
        this.currentAssets = currentAssets;
    }

    public Map<String, Double> getForeignDeposit() {
        return foreignDeposit;
    }

    public void setForeignDeposit(Map<String, Double> foreignDeposit) {
        this.foreignDeposit = foreignDeposit;
    }

    public Map<String, Double> getStock() {
        return stock;
    }

    public void setStock(Map<String, Double> stock) {
        this.stock = stock;
    }

    public Map<String, Double> getFund() {
        return fund;
    }

    public void setFund(Map<String, Double> fund) {
        this.fund = fund;
    }

    public Map<String, Double> getBond() {
        return bond;
    }

    public void setBond(Map<String, Double> bond) {
        this.bond = bond;
    }

    public Map<String, Double> getInvestmentInsurance() {
        return investmentInsurance;
    }

    public void setInvestmentInsurance(Map<String, Double> investmentInsurance) {
        this.investmentInsurance = investmentInsurance;
    }

    public Map<String, Double> getInvestmentAssets() {
        return investmentAssets;
    }

    public void setInvestmentAssets(Map<String, Double> investmentAssets) {
        this.investmentAssets = investmentAssets;
    }

    public Map<String, Double> getComputer() {
        return computer;
    }

    public void setComputer(Map<String, Double> computer) {
        this.computer = computer;
    }

    public Map<String, Double> getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Map<String, Double> mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Map<String, Double> getPersonalAssets() {
        return personalAssets;
    }

    public void setPersonalAssets(Map<String, Double> personalAssets) {
        this.personalAssets = personalAssets;
    }

    public Map<String, Double> getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Map<String, Double> totalAssets) {
        this.totalAssets = totalAssets;
    }

    public double getCreditCardLiabilities() {
        return creditCardLiabilities;
    }

    public void setCreditCardLiabilities(double creditCardLiabilities) {
        this.creditCardLiabilities = creditCardLiabilities;
    }

    public double getConsumerLiabilities() {
        return consumerLiabilities;
    }

    public void setConsumerLiabilities(double consumerLiabilities) {
        this.consumerLiabilities = consumerLiabilities;
    }

    public double getInvestmentLiabilities() {
        return investmentLiabilities;
    }

    public void setInvestmentLiabilities(double investmentLiabilities) {
        this.investmentLiabilities = investmentLiabilities;
    }

    public double getPersonalLiabilities() {
        return personalLiabilities;
    }

    public void setPersonalLiabilities(double personalLiabilities) {
        this.personalLiabilities = personalLiabilities;
    }

    public double getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public Map<String, Double> getCurrentNetValue() {
        return currentNetValue;
    }

    public void setCurrentNetValue(Map<String, Double> currentNetValue) {
        this.currentNetValue = currentNetValue;
    }

    public Map<String, Double> getInvestmentNetValue() {
        return investmentNetValue;
    }

    public void setInvestmentNetValue(Map<String, Double> investmentNetValue) {
        this.investmentNetValue = investmentNetValue;
    }

    public Map<String, Double> getPersonalNetValue() {
        return personalNetValue;
    }

    public void setPersonalNetValue(Map<String, Double> personalNetValue) {
        this.personalNetValue = personalNetValue;
    }

    public Map<String, Double> getTotalNetValue() {
        return totalNetValue;
    }

    public void setTotalNetValue(Map<String, Double> totalNetValue) {
        this.totalNetValue = totalNetValue;
    }
}
