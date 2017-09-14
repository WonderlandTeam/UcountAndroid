package team.wonderland.ucount.ucount_android.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.BillAddJson;
import team.wonderland.ucount.ucount_android.json.BillInfoJson;
import team.wonderland.ucount.ucount_android.service.BillService;

/**
 * Created by liuyu on 2017/8/30.
 */

@EFragment(R.layout.asset_add_fragment)
public class AssetAddFragment extends Fragment{
    private static final String TAG = "AddItemActivity";

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private Button addCostBtn;
    private Button addEarnBtn;
    private Button clearBtn;
    private ImageButton addDescription;
    private TextView tv_description;
    private Button zero,one,two,three,four,five,six,seven,eight,nine,dot;

    private ImageView bannerImage;
    private TextView bannerText;

    private TextView moneyText;

    private LinearLayout save;

    private SimpleDateFormat formatItem = new SimpleDateFormat("yyyy年MM月dd日");
    private SimpleDateFormat formatSum  = new SimpleDateFormat("yyyy年MM月");
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @RestService
    BillService billService;

    long accountID;
    BillAddJson billAddJson;

    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.asset_add_fragment,container,false);

        addCostBtn = (Button) view.findViewById(R.id.add_cost_button);
        addEarnBtn = (Button) view.findViewById(R.id.add_earn_button);
        addDescription = (ImageButton) view.findViewById(R.id.add_description);
        clearBtn = (Button) view.findViewById(R.id.clear);
        save = (LinearLayout)view.findViewById(R.id.calculator_sidebar);
        // 设置按钮监听
        addCostBtn.setOnClickListener(new ButtonListener());
        addEarnBtn.setOnClickListener(new ButtonListener());
        addDescription.setOnClickListener(new ButtonListener());
        clearBtn.setOnClickListener(new ButtonListener());
        save.setOnClickListener(new ButtonListener());

        bannerText = (TextView) view.findViewById(R.id.chosen_type);
        bannerImage = (ImageView) view.findViewById(R.id.chosen_image);

        moneyText = (TextView) view.findViewById(R.id.input_money_text);
        // 及时清零
        moneyText.setText("0.00");

        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.item_fragment, new AssetCostFragment());
        transaction.commit();

        zero = (Button) view.findViewById(R.id.zero);
        one = (Button) view.findViewById(R.id.one);
        two = (Button) view.findViewById(R.id.two);
        three = (Button) view.findViewById(R.id.three);
        four = (Button) view.findViewById(R.id.four);
        five = (Button) view.findViewById(R.id.five);
        six = (Button) view.findViewById(R.id.six);
        seven = (Button) view.findViewById(R.id.seven);
        eight = (Button) view.findViewById(R.id.eight);
        nine = (Button) view.findViewById(R.id.nine);
        dot = (Button) view.findViewById(R.id.dot);
        tv_description = (TextView)view.findViewById(R.id.txt_add_description);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorNumOnclick(view);
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorPushDot(view);
            }
        });

        if(!GlobalVariables.getmDescription().equals("")){
            tv_description.setText(GlobalVariables.getmDescription());
            //清空备注
            GlobalVariables.setmDescription("");
        }
        return view;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            transaction = manager.beginTransaction();

            switch (view.getId()) {
                case R.id.add_cost_button:
                    addCostBtn.setTextColor(getActivity().getResources().getColor(R.color.navigation)); // 设置“支出“按钮为灰色
                    addEarnBtn.setTextColor(getActivity().getResources().getColor(R.color.text_gray)); // 设置“收入”按钮为绿色
                    transaction.replace(R.id.item_fragment, new AssetCostFragment());

                    break;
                case R.id.add_earn_button:
                    addEarnBtn.setTextColor(getActivity().getResources().getColor(R.color.navigation)); // 设置“收入“按钮为灰色
                    addCostBtn.setTextColor(getActivity().getResources().getColor(R.color.text_gray)); // 设置“支出”按钮为绿色
                    transaction.replace(R.id.item_fragment, new AssetEarnFragment());

                    break;
                case R.id.calculator_sidebar:
                    String moneyString =  moneyText.getText().toString();
                    if (moneyString.equals("0.00") || GlobalVariables.getmInputMoney().equals(""))
                        Toast.makeText(getActivity(),"你还没输入金额",Toast.LENGTH_SHORT).show();
                    else {
                        putItemInData(Double.parseDouble(moneyText.getText().toString()));
                        calculatorClear();
                        addBill();
                    }
                    break;
                case R.id.clear:
                    calculatorClear();
                    moneyText.setText("0.00");
                    break;
                case R.id.add_description:
                    getFragmentManager().beginTransaction()
                            .addToBackStack(null)  //将当前fragment加入到返回栈中
                            .add(R.id.fragment_container, new AssetAddDescriptionFragment()).commit();
            }

            transaction.commit();
        }
    }

    public void putItemInData(double money) {
        IOItem ioItem = new IOItem();
        String tagName = (String) bannerText.getTag();
        int tagType = (int) bannerImage.getTag();

        if (tagType < 0) {
            ioItem.setType(ioItem.TYPE_COST);
        } else ioItem.setType(ioItem.TYPE_EARN);

//        ioItem.setName(bannerText.getText().toString());
//        ioItem.setSrcName(tagName);
//        ioItem.setMoney(money);
//        ioItem.setTimeStamp(formatItem.format(new Date()));         // 存储记账时间
//        ioItem.setDescription(GlobalVariables.getmDescription());
//        ioItem.save();

        int type = ioItem.getType();

        String sumDate = formatSum.format(new Date());
    }

    // 数字输入按钮
    public void calculatorNumOnclick(View v) {
        Button view = (Button) v;
        String digit = view.getText().toString();
        String money = GlobalVariables.getmInputMoney();
        if (GlobalVariables.getmHasDot() && GlobalVariables.getmInputMoney().length()>2) {
            String dot = money.substring(money.length() - 3, money.length() - 2);
            Log.d(TAG, "calculatorNumOnclick: " + dot);
            if (dot.equals(".")) {
                Toast.makeText(getActivity(), "已经不能继续输入了", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        GlobalVariables.setmInputMoney(money+digit);
        moneyText.setText(decimalFormat.format(Double.valueOf(GlobalVariables.getmInputMoney())));
    }

    // 清零按钮
    public void calculatorClear() {
        GlobalVariables.setmInputMoney("");
        GlobalVariables.setHasDot(false);
    }

    // 小数点处理工作
    public void calculatorPushDot(View view) {
        if (GlobalVariables.getmHasDot()) {
            Toast.makeText(getActivity(), "已经输入过小数点了", Toast.LENGTH_SHORT).show();
        } else {
            GlobalVariables.setmInputMoney(GlobalVariables.getmInputMoney() + ".");
            GlobalVariables.setHasDot(true);
        }

    }

    @Background
    void addBill(){
        accountID  = (Long)this.getArguments().get("account");

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);//time就是当前时间

        int tagType = (int) bannerImage.getTag();

        double incomeExpenditure = Double.parseDouble(moneyText.getText().toString());
        //tagType<0 cost
        if (tagType < 0) {
            incomeExpenditure = 0-incomeExpenditure;
        }
        String commity = "";

        String consumeType = bannerText.getText().toString();

        String remark = GlobalVariables.getmDescription();

        Log.i("assetAdd",accountID+" "+time+" "+incomeExpenditure+" "+commity+" "+consumeType+" "+remark);
        billAddJson = new BillAddJson(time, incomeExpenditure, commity, consumeType, remark);
        try {
            List<BillInfoJson> bills = billService.addBillManually(accountID, billAddJson);
//            System.out.println(result.get("content"));
            returnToFragment();
        }catch (ResponseException e){
            showErrorInfo(e.toString());
        }
    }



    //返回界面,
    @UiThread
    void returnToFragment(){
        Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
        //跳转
        getFragmentManager().popBackStack();
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
