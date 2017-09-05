package team.wonderland.ucount.ucount_android.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import lecho.lib.hellocharts.formatter.ColumnChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.util.TimePickerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 收支表
 * Created by CLL on 17/9/2.
 */
public class ReportOneFragment extends Fragment implements TimePickerDialog.TimePickerDialogInterface {
    private EditText beginDate;
    private EditText endDate;
    private TimePickerDialog beginPickerDialog;
    private TimePickerDialog endPickerDialog;
    private PieChartView incomePieChart;
    private PieChartView outputPieChart;
    private ColumnChartView outputColumnChart1;
    private ColumnChartView outputColumnChart2;
    private ColumnChartView outputColumnChart3;
    private final static String[] outputCategory1 = new String[]{"日用品", "水电费", "通讯和网费",
            "饮食", "电子设备", "交通"};//生活必需
    private final static String[] outputCategory2 = new String[]{"衣帽鞋包", "护肤品", "彩妆", "首饰"};//服饰
    private final static String[] outputCategory3 = new String[]{"培训、考证", "书", "文具", "打印", "组织活动"};//学习
    //娱乐
    //理财支出
    private final static String[] outputCategory4 = new String[]{"捐款", "其他捐赠"};//捐赠
    //其他支出

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_one, container, false);
        beginDate = view.findViewById(R.id.begin_date_text);
        beginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beginPickerDialog.showDatePickerDialog();
            }
        });
        endDate = view.findViewById(R.id.end_date_text);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endPickerDialog.showDatePickerDialog();
            }
        });

        beginPickerDialog = new TimePickerDialog(view.getContext(), this, 0);
        endPickerDialog = new TimePickerDialog(view.getContext(), this, 1);

        incomePieChart = view.findViewById(R.id.income_piechart);
        initPieChart(incomePieChart, getIncomeData());

        outputPieChart = view.findViewById(R.id.output_piechart);
        initPieChart(outputPieChart, getOutputData());

        /**生活必需**/
        outputColumnChart1 = view.findViewById(R.id.output_columnchart);

        //假的数据
        ArrayList<Float> list = new ArrayList<>();
        list.add(10.6f);
        list.add(12.2f);
        list.add(20.8f);
        list.add(10.6f);
        list.add(12.2f);
        list.add(20.8f);
        initColumnChart(outputColumnChart1, list, outputCategory1);

        /**服饰**/
        outputColumnChart2 = view.findViewById(R.id.output_columnchart2);

        //假的数据
        ArrayList<Float> list2 = new ArrayList<>();
        list2.add(10.6f);
        list2.add(12.2f);
        list2.add(20.8f);
        list2.add(10.6f);
        initColumnChart(outputColumnChart2, list2, outputCategory2);
        /**学习**/
        outputColumnChart3 = view.findViewById(R.id.output_columnchart3);

        //假的数据
        ArrayList<Float> list3 = new ArrayList<>();
        list3.add(10.6f);
        list3.add(12.2f);
        list3.add(20.8f);
        list3.add(10.6f);
        list3.add(12.2f);
        initColumnChart(outputColumnChart3, list3, outputCategory3);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 确定
     */
    @Override
    public void positiveListener(int tag) {
        switch (tag) {
            case 0:
                String year = String.valueOf(beginPickerDialog.getYear());
                String month = String.valueOf(beginPickerDialog.getMonth());
                String day = String.valueOf(beginPickerDialog.getDay());
                Log.i("=====", "=======year======" + beginPickerDialog.getYear());
                Log.i("=====", "=======getMonth======" + beginPickerDialog.getMonth());
                Log.i("=====", "=======getDay======" + beginPickerDialog.getDay());
                beginDate.setText(year + " - " + month + " - " + day);
            case 1:
                String year1 = String.valueOf(endPickerDialog.getYear());
                String month1 = String.valueOf(endPickerDialog.getMonth());
                String day1 = String.valueOf(endPickerDialog.getDay());
                Log.i("=====", "=======year======" + endPickerDialog.getYear());
                Log.i("=====", "=======getMonth======" + endPickerDialog.getMonth());
                Log.i("=====", "=======getDay======" + endPickerDialog.getDay());
                endDate.setText(year1 + " - " + month1 + " - " + day1);
        }

    }

    /**
     * 取消
     */
    @Override
    public void negativeListener(int tag) {

    }

    /**
     * 绘制饼状图
     *
     * @param pieChartView
     * @param pd
     */
    private void initPieChart(PieChartView pieChartView, PieChartData pd) {
        pieChartView.setViewportCalculationEnabled(true);//设置饼图自动适应大小
        pieChartView.setChartRotationEnabled(true);//设置饼图是否可以手动旋转
        pieChartView.setValueSelectionEnabled(true);//选择饼图某一块变大
        pieChartView.setValueTouchEnabled(true);
        pieChartView.setClickable(true);
        pieChartView.setAlpha(0.9f);//设置透明度
        pieChartView.setCircleFillRatio(1f);//设置饼图大小

        pd.setValueLabelsTextColor(Color.WHITE);//设置显示值的字体颜色
        pd.setValueLabelTextSize(15);
        pd.setHasLabels(true);//显示label
        pd.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pd.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pd.setHasCenterCircle(true);//是否是环形显示
        pd.setValueLabelBackgroundColor(Color.TRANSPARENT);
        pd.setCenterCircleColor(Color.TRANSPARENT);//设置环形中间的颜色
        pd.setValueLabelBackgroundEnabled(false);
        pd.setCenterCircleScale(0.45f);//设置环形的大小级别

        pieChartView.setPieChartData(pd);//设置数据
    }

    /**
     * 获得收入饼状图数据
     *
     * @return
     */
    private PieChartData getIncomeData() {
        PieChartData pd = new PieChartData();

        pd.setCenterText1("收入");//环形中间的文字1
        pd.setCenterText1Color(Color.BLACK);//文字颜色
        pd.setCenterText1FontSize(30);//文字大小

        List<SliceValue> sliceList = new ArrayList<SliceValue>();
        List<String> incomeLabels = new ArrayList<>();
        incomeLabels.add("工资 ");
        incomeLabels.add("理财 ");
        incomeLabels.add("其他 ");
        List<Float> incomes = new ArrayList<>();
        incomes.add((float) 10);
        incomes.add((float) 20);
        incomes.add((float) 5);
        for (int i = 0; i < incomeLabels.size(); i++) {
            sliceList.add(new SliceValue(incomes.get(i), ChartUtils.pickColor()).setLabel(incomeLabels.get(i) + incomes.get(i)));
        }
//        sliceList.add(new SliceValue(incomes.get(0),Color.parseColor("#3F51B5")).setLabel("工资 "+incomes.get(0)));
//        sliceList.add(new SliceValue(incomes.get(1),Color.parseColor("#1abc9c")).setLabel("理财 "+incomes.get(1)));
//        sliceList.add(new SliceValue(incomes.get(2),Color.parseColor("#bdc3c7")).setLabel("其他 "+incomes.get(2)));
        pd.setValues(sliceList);
        return pd;
    }

    /**
     * 获得支出饼状图数据
     *
     * @return
     */
    private PieChartData getOutputData() {
        PieChartData pd = new PieChartData();

        pd.setCenterText1("支出");//环形中间的文字1
        pd.setCenterText1Color(Color.BLACK);//文字颜色
        pd.setCenterText1FontSize(30);//文字大小

        List<String> outputLabels = new ArrayList<>();
        outputLabels.add("生活必需 ");
        outputLabels.add("服饰 ");
        outputLabels.add("学习 ");
        outputLabels.add("娱乐 ");
        outputLabels.add("理财 ");
        outputLabels.add("捐赠 ");
        outputLabels.add("其他 ");

        List<Float> outputs = new ArrayList<>();
        outputs.add((float) 10);
        outputs.add((float) 20);
        outputs.add((float) 25);
        outputs.add((float) 5);
        outputs.add((float) 5);
        outputs.add((float) 5);
        outputs.add((float) 5);

        List<SliceValue> sliceList = new ArrayList<SliceValue>();
        for (int i = 0; i < outputLabels.size(); i++) {
            sliceList.add(new SliceValue(outputs.get(i), ChartUtils.pickColor()).setLabel(outputLabels.get(i) + outputs.get(i)));
        }
        pd.setValues(sliceList);
        return pd;
    }

    private void initColumnChart(ColumnChartView columnChartView, List<Float> list, String[] labels) {
        columnChartView.setZoomEnabled(false);//禁止手势缩放
        ColumnChartData data;
        // 每列1个subcolumn。
        int numSubColumns = 1;
        int numColumns = list.size();
        //定义一个圆柱对象集合
        List<Column> columns = new ArrayList<Column>();
        //子列数据集合
        List<SubcolumnValue> values;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        //遍历列数numColumns
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            //遍历每一列的每一个子列
            for (int j = 0; j < numSubColumns; ++j) {
                //为每一柱图添加颜色和数值
                float f = list.get(i);
                values.add(new SubcolumnValue(f, ChartUtils.pickColor()));
            }
            //创建Column对象
            Column column = new Column(values);
            //这一步是能让圆柱标注数据显示带小数的重要一步 让我找了好久问题
            //作者回答https://github.com/lecho/hellocharts-android/issues/185
            ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(2);
            column.setFormatter(chartValueFormatter);
            //是否有数据标注
            column.setHasLabels(true);
            //是否是点击圆柱才显示数据标注
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
            //给x轴坐标设置描述
            axisValues.add(new AxisValue(i).setLabel(labels[i]));
        }
        //创建一个带有之前圆柱对象column集合的ColumnChartData
        data = new ColumnChartData(columns);

        //定义x轴y轴相应参数
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisY.setName("支出（元）");//轴名称
        axisY.hasLines();//是否显示网格线
        axisY.setTextColor(R.color.text_green);//颜色

        axisX.hasLines();
        axisX.setTextColor(R.color.text_green_darker);
        axisX.setValues(axisValues);
        //把X轴Y轴数据设置到ColumnChartData 对象中
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //给表填充数据，显示出来
        columnChartView.setColumnChartData(data);
    }

}
