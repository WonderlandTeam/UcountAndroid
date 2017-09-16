package team.wonderland.ucount.ucount_android.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import team.wonderland.ucount.ucount_android.json.BalanceSheetJson;

import java.util.ArrayList;

/**
 * Created by CLL on 17/9/3.
 */
public class ChartTableView extends View {
    private int width;//控件宽
    private int height;//控件高
    private int dataNum;//数据量

    private Paint mPaintText;//用于绘制文本
    private Paint mPaintWhiteBg;//用于绘制白色背景
    private Paint mPaintGreyBg;//用于绘制灰色背景
    private Paint mPaintLightGrey;//用于绘制浅灰色背景
    private Paint mPaintLine;//用于画表格的列线
    private Paint mPaintHead;
    private Paint mPaintHead2;
    private Paint mPaintHead3;
    private Paint mPaintWhiteText;
    private Paint mPaintNumText;

    private int textSize = SizeConvert.dip2px(getContext(), 13);//文本尺寸，dp转px

    private static final String[] assetType = {"   流动资产", "   投资资产", "   自用资产"};
    private static final String[] asset1 = {"现金", "活存"};//流动性资产
    private static final String[] asset2 = {"外币", "股票", "基金", "债权", "  投资保单"};//投资型资产
    private static final String[] asset3 = {"   自用电脑", "   自用手机"};//自用型资产

    private static final String[] debtType = {"消费负债", "投资负债", "自用负债"};
    private static final String[] debt1 = {"信用卡负债", ""};
    private static final String[] debt2 = {"", "", "", "", ""};
    private static final String[] debt3 = {"", "自用贷款"};

    private static final String[] clearType = {"流动净值", "投资净值", "自用净值"};

    private static final ArrayList<String[]> assets = new ArrayList<>();
    private static final ArrayList<String[]> debts = new ArrayList<>();

    private ArrayList<Double> assetCost;//资产成本一列数据
    private ArrayList<Double> assetPrice;//资产市价一列
    private ArrayList<Double> debtMoney;//负债金额一列
    private ArrayList<Double> clearCost;
    private ArrayList<Double> clearPrice;

    private BalanceSheetJson balanceSheetJson;

    private Context context;


    public ChartTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        mPaintLine = new Paint();
        mPaintLine.setColor(Color.LTGRAY);
        mPaintLine.setStrokeWidth(4);
        mPaintLine.setAntiAlias(true);

        mPaintText = new Paint();
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(textSize);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setAntiAlias(true);

        mPaintWhiteBg = new Paint();
        mPaintWhiteBg.setColor(Color.WHITE);
        mPaintWhiteBg.setStyle(Paint.Style.FILL);
        mPaintWhiteBg.setAntiAlias(true);

        mPaintWhiteText = new Paint();
        mPaintWhiteText.setColor(Color.WHITE);
        mPaintWhiteText.setTextSize(textSize);
        mPaintWhiteText.setTextAlign(Paint.Align.CENTER);
        mPaintWhiteText.setAntiAlias(true);

        mPaintGreyBg = new Paint();
        mPaintGreyBg.setColor(Color.argb(255, 240, 240, 240));
        mPaintGreyBg.setStyle(Paint.Style.FILL);
        mPaintGreyBg.setAntiAlias(true);

        mPaintLightGrey = new Paint();
        mPaintLightGrey.setColor(Color.argb(255, 250, 250, 250));
        mPaintLightGrey.setStyle(Paint.Style.FILL);
        mPaintLightGrey.setAntiAlias(true);

        mPaintHead = new Paint();
        mPaintHead.setStyle(Paint.Style.FILL);
        mPaintHead.setAntiAlias(true);
        mPaintHead.setColor(Color.parseColor("#bedebd"));

        mPaintHead2 = new Paint();
        mPaintHead2.setStyle(Paint.Style.FILL);
        mPaintHead2.setColor(Color.parseColor("#9bca99"));
        mPaintHead2.setAntiAlias(true);

        mPaintHead3 = new Paint();
        mPaintHead3.setStyle(Paint.Style.FILL);
        mPaintHead3.setColor(Color.parseColor("#fff45c"));
        mPaintHead3.setAntiAlias(true);

        dataNum = 14;

        initData();


    }

    private int tableItemWidth;
    private int tableItemHeight = SizeConvert.dip2px(getContext(), 36);//表格单元高
    /**
     * 表格左上角的横纵坐标
     */
    private float startX;
    private float startY;

    private Canvas canvas;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        //根据数据数量来得到控件高
        if (dataNum != 0) {
            height = (dataNum + 1) * tableItemHeight;
        }
        //表格单元宽
        tableItemWidth = width / 8;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas=canvas;
        startX = (float) 5;
        assets.add(asset1);
        assets.add(asset2);
        assets.add(asset3);
        debts.add(debt1);
        debts.add(debt2);
        debts.add(debt3);

        super.onDraw(canvas);
        //绘制统计表表第一行
        canvas.drawRect(startX, startY, (float) (startX + tableItemWidth * (3 - 1.0 / 8)), startY + tableItemHeight,
                mPaintHead2);
        canvas.drawRect((float) (startX + tableItemWidth * (3 - 1.0 / 8)), startY, (float) (startX + tableItemWidth * (5 - 1.0 / 8)), startY + tableItemHeight, mPaintHead);
        canvas.drawRect((float) (startX + tableItemWidth * (5 - 1.0 / 8)), startY, width, startY + tableItemHeight, mPaintHead2);
        canvas.drawText("资产项目", startX + tableItemWidth / 2, startY + tableItemHeight / 2 + textSize / 2, mPaintWhiteText);
        canvas.drawText("成本", startX + tableItemWidth * 3 / 2, startY + tableItemHeight / 2 + textSize / 2, mPaintWhiteText);
        canvas.drawText("市价", startX + tableItemWidth * 5 / 2, startY + tableItemHeight / 2 + textSize / 2, mPaintWhiteText);
        canvas.drawText("负债项目", startX + tableItemWidth * 7 / 2, startY + tableItemHeight / 2 + textSize / 2, mPaintWhiteText);
        canvas.drawText("金额", startX + tableItemWidth * 9 / 2, startY + tableItemHeight / 2 + textSize / 2, mPaintWhiteText);
        canvas.drawText("净值项目", startX + tableItemWidth * 11 / 2, startY + tableItemHeight / 2 + textSize / 2, mPaintWhiteText);
        canvas.drawText("成本", startX + tableItemWidth * 13 / 2, startY + tableItemHeight / 2 + textSize / 2, mPaintWhiteText);
        canvas.drawText("市价", startX + tableItemWidth * 15 / 2, startY + tableItemHeight / 2 + textSize / 2, mPaintWhiteText);


        float now_height = tableItemHeight + startY;
        for (int i = 0; i < assetType.length; i++) {
            for (int j = 0; j < assets.get(i).length; j++) {
                canvas.drawRect(startX, now_height, width, now_height + tableItemHeight, mPaintWhiteBg);
                canvas.drawLine(startX, now_height + tableItemHeight, width, now_height + tableItemHeight, mPaintLine);
                //资产
                canvas.drawText(assets.get(i)[j], startX + tableItemWidth / 2, now_height + tableItemHeight / 2 + textSize / 2, mPaintText);
                //资产
                canvas.drawText(debts.get(i)[j], startX + tableItemWidth * 7 / 2, now_height + tableItemHeight / 2 + textSize / 2, mPaintText);

                now_height = now_height + tableItemHeight;
            }

            canvas.drawRect(startX, now_height, width, now_height + tableItemHeight, mPaintGreyBg);
            canvas.drawLine(startX, now_height + tableItemHeight, width, now_height + tableItemHeight, mPaintLine);
            //资产
            canvas.drawText(assetType[i], startX + tableItemWidth / 2, now_height + tableItemHeight / 2 + textSize / 2, mPaintText);
            //负债
            canvas.drawText(debtType[i], startX + tableItemWidth * 7 / 2, now_height + tableItemHeight / 2 + textSize / 2, mPaintText);
            //净值
            canvas.drawText(clearType[i], startX + tableItemWidth * 11 / 2, now_height + tableItemHeight / 2 + textSize / 2, mPaintText);
            now_height = now_height + tableItemHeight;
        }
        canvas.drawRect(startX, now_height, (float) (startX + tableItemWidth * (3 - 1.0 / 8)), now_height + tableItemHeight,mPaintHead2);
        canvas.drawRect((float) (startX + tableItemWidth * (3 - 1.0 / 8)), now_height, (float) (startX + tableItemWidth * (5 - 1.0 / 8)), now_height + tableItemHeight, mPaintHead);
        canvas.drawRect((float) (startX + tableItemWidth * (5 - 1.0 / 8)), now_height, width, now_height + tableItemHeight, mPaintHead2);
        canvas.drawText("总资产", startX + tableItemWidth / 2, now_height + tableItemHeight / 2 + textSize / 2, mPaintText);
        canvas.drawText("总负债", startX + tableItemWidth * 7 / 2, now_height + tableItemHeight / 2 + textSize / 2, mPaintText);
        canvas.drawText("总净值", startX + tableItemWidth * 11 / 2, now_height + tableItemHeight / 2 + textSize / 2, mPaintText);
        now_height = now_height + tableItemHeight;


        //分隔线
        canvas.drawLine((float) (startX + tableItemWidth * (3 - 1.0 / 8)), startY + tableItemHeight, (float) (startX + tableItemWidth
                * (3 - 1.0 / 8)), now_height, mPaintLine);
        //分隔线
        canvas.drawLine((float) (startX + tableItemWidth * (5 - 1.0 / 8)), startY + tableItemHeight, (float) (startX + tableItemWidth
                * (5 - 1.0 / 8)), now_height, mPaintLine);

        showData();

    }

    private void initData(){
        assetCost=new ArrayList<>();

        assetPrice=new ArrayList<>();

        debtMoney=new ArrayList<>();

        clearCost=new ArrayList<>();

        clearPrice=new ArrayList<>();
    }


    /**
     * 提供给fragment的接口
     * @param balanceSheetJson
     */
    public void setData(BalanceSheetJson balanceSheetJson){
        Log.i("tag","setData");


        //从上到下共13个数
        assetCost.add(balanceSheetJson.cash.get("cost"));
        assetCost.add(balanceSheetJson.deposit.get("cost"));
        assetCost.add(balanceSheetJson.currentAssets.get("cost"));
        assetCost.add(balanceSheetJson.foreignDeposit.get("cost"));
        assetCost.add(balanceSheetJson.stock.get("cost"));
        assetCost.add(balanceSheetJson.fund.get("cost"));
        assetCost.add(balanceSheetJson.bond.get("cost"));
        assetCost.add(balanceSheetJson.investmentInsurance.get("cost"));
        assetCost.add(balanceSheetJson.investmentAssets.get("cost"));
        assetCost.add(balanceSheetJson.computer.get("cost"));
        assetCost.add(balanceSheetJson.mobilePhone.get("cost"));
        assetCost.add(balanceSheetJson.personalAssets.get("cost"));
        assetCost.add(balanceSheetJson.totalAssets.get("cost"));

        // 13
        assetPrice.add(balanceSheetJson.cash.get("market"));
        assetPrice.add(balanceSheetJson.deposit.get("market"));
        assetPrice.add(balanceSheetJson.currentAssets.get("market"));
        assetPrice.add(balanceSheetJson.foreignDeposit.get("market"));
        assetPrice.add(balanceSheetJson.stock.get("market"));
        assetPrice.add(balanceSheetJson.fund.get("market"));
        assetPrice.add(balanceSheetJson.bond.get("market"));
        assetPrice.add(balanceSheetJson.investmentInsurance.get("market"));
        assetPrice.add(balanceSheetJson.investmentAssets.get("market"));
        assetPrice.add(balanceSheetJson.computer.get("market"));
        assetPrice.add(balanceSheetJson.mobilePhone.get("market"));
        assetPrice.add(balanceSheetJson.personalAssets.get("market"));
        assetPrice.add(balanceSheetJson.totalAssets.get("market"));

        //6,其余设为0
        debtMoney.add(balanceSheetJson.creditCardLiabilities);
        debtMoney.add(0.0);
        debtMoney.add(balanceSheetJson.consumerLiabilities);
        for(int i=0;i<5;i++){
            debtMoney.add(0.0);
        }
        debtMoney.add(balanceSheetJson.investmentLiabilities);
        debtMoney.add(0.0);
        debtMoney.add(0.0);
        debtMoney.add(balanceSheetJson.personalLiabilities);
        debtMoney.add(balanceSheetJson.totalLiabilities);

        //4
        clearCost.add(0.0);
        clearCost.add(0.0);
        clearCost.add(balanceSheetJson.currentNetValue.get("cost"));
        for(int i=0;i<5;i++){
            clearCost.add(0.0);
        }
        clearCost.add(balanceSheetJson.investmentNetValue.get("cost"));
        clearCost.add(0.0);
        clearCost.add(0.0);
        clearCost.add(balanceSheetJson.personalNetValue.get("cost"));
        clearCost.add(balanceSheetJson.totalNetValue.get("cost"));

        //4
        clearPrice.add(0.0);
        clearPrice.add(0.0);
        clearPrice.add(balanceSheetJson.currentNetValue.get("market"));
        for(int i=0;i<5;i++){
            clearPrice.add(0.0);
        }
        clearPrice.add(balanceSheetJson.investmentNetValue.get("market"));
        clearPrice.add(0.0);
        clearPrice.add(0.0);
        clearPrice.add(balanceSheetJson.personalNetValue.get("market"));
        clearPrice.add(balanceSheetJson.totalNetValue.get("market"));

        Log.i("tableSize",""+assetCost.size());

    }

    public void showData(){
        float now_height=startY + tableItemHeight / 2 + textSize / 2;
        for(int i=0;i<assetCost.size();i++){
            now_height=now_height+tableItemHeight;
            if(!assetCost.get(i).equals(0.0)){
                canvas.drawText(""+assetCost.get(i).intValue(),startX + tableItemWidth * 3 / 2,now_height,mPaintText);
            }
            if(!assetPrice.get(i).equals(0.0)){
                canvas.drawText(""+assetPrice.get(i).intValue(),startX + tableItemWidth * 5 / 2,now_height,mPaintText);
            }
            if(!debtMoney.get(i).equals(0.0)){
                canvas.drawText(""+debtMoney.get(i).intValue(),startX + tableItemWidth * 9 / 2,now_height,mPaintText);
            }
            if(!clearCost.get(i).equals(0.0)){
                canvas.drawText(""+clearCost.get(i).intValue(),startX + tableItemWidth * 13 / 2,now_height,mPaintText);
            }
            if(!clearPrice.get(i).equals(0.0)){
                canvas.drawText(""+clearPrice.get(i).intValue(),startX + tableItemWidth * 15 / 2,now_height,mPaintText);
            }
        }
        Log.i("tag","showData");
    }

}
