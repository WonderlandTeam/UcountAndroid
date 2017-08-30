package team.wonderland.ucount.ucount_android.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/30.
 */

public class AssetAddFragment extends Fragment{
    private static final String TAG = "AddItemActivity";

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private Button addCostBtn;
    private Button addEarnBtn;
    private Button clearBtn;
    private ImageButton addFinishBtn;
    private ImageButton addDescription;


    private ImageView bannerImage;
    private TextView bannerText;

    private TextView moneyText;

    private TextView words;

    private SimpleDateFormat formatItem = new SimpleDateFormat("yyyy年MM月dd日");
    private SimpleDateFormat formatSum  = new SimpleDateFormat("yyyy年MM月");
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.asset_add_fragment,container,false);

        addCostBtn = (Button) view.findViewById(R.id.add_cost_button);
        addEarnBtn = (Button) view.findViewById(R.id.add_earn_button);
        addFinishBtn   = (ImageButton) view.findViewById(R.id.add_finish);
        addDescription = (ImageButton) view.findViewById(R.id.add_description);
        clearBtn = (Button) view.findViewById(R.id.clear);
        words = (TextView) view.findViewById(R.id.anime_words);
//        // 设置字体颜色
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/chinese_character.ttf");
//        clearBtn.setTypeface(typeface);
//        words.setTypeface(typeface);
        // 设置按钮监听
        addCostBtn.setOnClickListener(new ButtonListener());
        addEarnBtn.setOnClickListener(new ButtonListener());
        addFinishBtn.setOnClickListener(new ButtonListener());
        addDescription.setOnClickListener(new ButtonListener());
        clearBtn.setOnClickListener(new ButtonListener());


        bannerText = (TextView) view.findViewById(R.id.chosen_type);
        bannerImage = (ImageView) view.findViewById(R.id.chosen_image);

        moneyText = (TextView) view.findViewById(R.id.input_money_text);
        // 及时清零
        moneyText.setText("0.00");

        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.item_fragment, new AssetCostFragment());
        transaction.commit();

        return view;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            transaction = manager.beginTransaction();

            switch (view.getId()) {
                case R.id.add_cost_button:
                    addCostBtn.setTextColor(getActivity().getResources().getColor(R.color.text_gray)); // 设置“支出“按钮为灰色
                    addEarnBtn.setTextColor(getActivity().getResources().getColor(R.color.text_green)); // 设置“收入”按钮为绿色
                    transaction.replace(R.id.item_fragment, new AssetCostFragment());
                    Log.d(TAG, "onClick: add_cost_button");

                    break;
                case R.id.add_earn_button:
                    addEarnBtn.setTextColor(getActivity().getResources().getColor(R.color.text_gray)); // 设置“收入“按钮为灰色
                    addCostBtn.setTextColor(getActivity().getResources().getColor(R.color.text_green)); // 设置“支出”按钮为绿色
                    transaction.replace(R.id.item_fragment, new AssetEarnFragment());
                    Log.d(TAG, "onClick: add_earn_button");

                    break;
                case R.id.add_finish:
                    String moneyString =  moneyText.getText().toString();
                    if (moneyString.equals("0.00") || GlobalVariables.getmInputMoney().equals(""))
                        Toast.makeText(getActivity(),"你还没输入金额",Toast.LENGTH_SHORT).show();
                    else {
                        putItemInData(Double.parseDouble(moneyText.getText().toString()));
                        calculatorClear();
                        //TODO: 返回上一个fragment
                        getActivity().finish();
                    }
                    break;
                case R.id.clear:
                    calculatorClear();
                    moneyText.setText("0.00");
                    break;
                case R.id.add_description:
                    getFragmentManager().beginTransaction()
                            .addToBackStack(null)  //将当前fragment加入到返回栈中
                            .replace(R.id.fragment_container, new AssetAddDescriptionFragment()).commit();
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

        // 存储完之后及时清空备注
        GlobalVariables.setmDescription("");

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
            GlobalVariables.setmInputMoney(GlobalVariables.getmInputMoney()+".");
            GlobalVariables.setHasDot(true);
        }
    }


}
