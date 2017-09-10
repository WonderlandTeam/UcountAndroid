package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by CLL on 17/9/9.
 */
public class IncomeStatementJson implements Serializable{
    /************************** 收入部分 **************************/

    public double salary;               // 工资收入
    public double managementIncome;     // 理财收入
    public double alimony;              // 家庭补助
    public double otherIncome;          // 其他收入

    /* 收入合计 */
    public double totalIncome;          // 收入合计

    /************************** 支出部分 **************************/

    /* 生活必需 */
    public double commodity;            // 日用品
    public double utilities;            // 水电费（包括洗澡费用）
    public double communication;        // 通讯和网费
    public double diet;                 // 饮食（含校内外）
    public double electronic;           // 电子设备（手机、电脑等）
    public double traffic;              // 交通
    public double necessityTotal;       // 生活必需合计

    /* 妆服费 */
    public double clothing;             // 衣帽鞋包（含运动装备）
    public double cream;                // 护肤品
    public double cosmetics;            // 彩妆
    public double jewelry;              // 首饰
    public double adornTotal;           // 妆服费合计

    /* 学习 */
    public double training;             // 培训、考证费用
    public double book;                 // 书（一切实体书籍）
    public double stationery;           // 文具
    public double print;                // 打印资料、图像影印消费
    public double activity;             // 组织活动
    public double learningTotal;        // 学习费用合计

    /* 娱乐 */
    public double entertainment;        // 娱乐

    /* 理财支出 */
    public double managementExpenditure;// 理财支出

    /* 捐赠 */
    public double donation;             // 捐款
    public double otherDonation;        // 其他捐赠
    public double donationTotal;        // 捐赠合计

    /* 其他支出 */
    public double otherExpenditure;     // 其他支出

    /* 支出合计 */
    public double totalExpenditure;     // 支出合计

    public IncomeStatementJson() {
    }

    /**
     * 根据字段名获取字段
     */
    public static Field getFieldByFieldName(String fieldName) {
        try {
            return IncomeStatementJson.class.getField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getManagementIncome() {
        return managementIncome;
    }

    public void setManagementIncome(double managementIncome) {
        this.managementIncome = managementIncome;
    }

    public double getAlimony() {
        return alimony;
    }

    public void setAlimony(double alimony) {
        this.alimony = alimony;
    }

    public double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getCommodity() {
        return commodity;
    }

    public void setCommodity(double commodity) {
        this.commodity = commodity;
    }

    public double getUtilities() {
        return utilities;
    }

    public void setUtilities(double utilities) {
        this.utilities = utilities;
    }

    public double getCommunication() {
        return communication;
    }

    public void setCommunication(double communication) {
        this.communication = communication;
    }

    public double getDiet() {
        return diet;
    }

    public void setDiet(double diet) {
        this.diet = diet;
    }

    public double getElectronic() {
        return electronic;
    }

    public void setElectronic(double electronic) {
        this.electronic = electronic;
    }

    public double getTraffic() {
        return traffic;
    }

    public void setTraffic(double traffic) {
        this.traffic = traffic;
    }

    public double getNecessityTotal() {
        return necessityTotal;
    }

    public void setNecessityTotal(double necessityTotal) {
        this.necessityTotal = necessityTotal;
    }

    public double getClothing() {
        return clothing;
    }

    public void setClothing(double clothing) {
        this.clothing = clothing;
    }

    public double getCream() {
        return cream;
    }

    public void setCream(double cream) {
        this.cream = cream;
    }

    public double getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(double cosmetics) {
        this.cosmetics = cosmetics;
    }

    public double getJewelry() {
        return jewelry;
    }

    public void setJewelry(double jewelry) {
        this.jewelry = jewelry;
    }

    public double getAdornTotal() {
        return adornTotal;
    }

    public void setAdornTotal(double adornTotal) {
        this.adornTotal = adornTotal;
    }

    public double getTraining() {
        return training;
    }

    public void setTraining(double training) {
        this.training = training;
    }

    public double getBook() {
        return book;
    }

    public void setBook(double book) {
        this.book = book;
    }

    public double getStationery() {
        return stationery;
    }

    public void setStationery(double stationery) {
        this.stationery = stationery;
    }

    public double getPrint() {
        return print;
    }

    public void setPrint(double print) {
        this.print = print;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public double getLearningTotal() {
        return learningTotal;
    }

    public void setLearningTotal(double learningTotal) {
        this.learningTotal = learningTotal;
    }

    public double getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(double entertainment) {
        this.entertainment = entertainment;
    }

    public double getManagementExpenditure() {
        return managementExpenditure;
    }

    public void setManagementExpenditure(double managementExpenditure) {
        this.managementExpenditure = managementExpenditure;
    }

    public double getDonation() {
        return donation;
    }

    public void setDonation(double donation) {
        this.donation = donation;
    }

    public double getOtherDonation() {
        return otherDonation;
    }

    public void setOtherDonation(double otherDonation) {
        this.otherDonation = otherDonation;
    }

    public double getDonationTotal() {
        return donationTotal;
    }

    public void setDonationTotal(double donationTotal) {
        this.donationTotal = donationTotal;
    }

    public double getOtherExpenditure() {
        return otherExpenditure;
    }

    public void setOtherExpenditure(double otherExpenditure) {
        this.otherExpenditure = otherExpenditure;
    }

    public double getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }
}
