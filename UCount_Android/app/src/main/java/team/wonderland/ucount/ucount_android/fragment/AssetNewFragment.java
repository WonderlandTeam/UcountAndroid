package team.wonderland.ucount.ucount_android.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;
import org.w3c.dom.Text;

import java.util.Map;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.AccountAddJson;
import team.wonderland.ucount.ucount_android.json.BudgetAddJson;
import team.wonderland.ucount.ucount_android.service.AccountService;

/**
 * Created by liuyu on 2017/8/29.
 */

@EFragment(R.layout.asset_new_fragment)
public class AssetNewFragment extends Fragment{
    ImageView im_back;
    CardView cv_name;
    CardView cv_type;
    CardView cv_balance;
    CircleButton bt_save;
    TextView tv_name;
    TextView tv_type;
    TextView tv_balance;
    EditText et_name;
    EditText et_balance;
    String[] titles = {"现金","银行卡","校园卡","支付宝"};

    public String username;         // 用户名
    public String accountType;      // 账户类型
    public String cardID;        // 账户id
    public double balance;          // 初始余额（手动账户须填写）
    AccountAddJson accountAddJson;

    @RestService
    AccountService accountService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_new_fragment, container, false);
        im_back = (ImageView) view.findViewById(R.id.asset_new_back);
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        cv_name = (CardView)view.findViewById(R.id.asset_new_cardview_name);
        cv_type = (CardView)view.findViewById(R.id.asset_new_cardview_type);
        cv_balance = (CardView)view.findViewById(R.id.asset_new_cardview_balance);
        bt_save = (CircleButton)view.findViewById(R.id.asset_new_bt_save);
        tv_name = (TextView)view.findViewById(R.id.asset_new_tv_name);
        tv_type = (TextView)view.findViewById(R.id.asset_new_tv_type);
        tv_balance = (TextView)view.findViewById(R.id.asset_new_tv_balance);

        cv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
                View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
                localBuilder.setView(dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
                et_name = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
                tv.setText("请输入账户名称");
                localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {
                        tv_name.setText(et_name.getText());
                        paramAnonymousDialogInterface.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {
                        paramAnonymousDialogInterface.dismiss();
                    }
                }).create().show();
            }
        });

        cv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("账户类型")
                        //设置单选框监听
                        .setSingleChoiceItems(titles, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //根据which决定选择了哪一个子项
                                tv_type.setText(titles[which]);
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });


        cv_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
                final View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
                localBuilder.setView(dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
                et_balance = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
                tv.setText("请输入账户余额");
                localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {
                        tv_balance.setText(et_balance.getText());
                        paramAnonymousDialogInterface.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {
                        paramAnonymousDialogInterface.dismiss();
                    }
                }).create().show();
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=getActivity().getSharedPreferences("user",0).getString("USERNAME","");
                accountType=tv_type.getText().toString();
                cardID=tv_name.getText().toString();
                balance=Double.parseDouble(tv_balance.getText().toString());
                accountAddJson=new AccountAddJson(username,accountType,cardID,balance);
                addAccount();
            }
        });

        return view;
    }

    @Background
    void addAccount(){
        try {
            Map<String,Object> result=accountService.addAccount(accountAddJson);
//            System.out.println(result.get("content"));
            returnToAssetFragment();
        }catch (ResponseException e){
            showErrorInfo(e.toString());
        }
    }

    //返回到资产主界面
    @UiThread
    void returnToAssetFragment(){

        Looper.prepare();
        Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
        Looper.loop();

        //保存成功跳转到资产主界面
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, new AssetFragment_()).commit();
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Looper.prepare();
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        Looper.loop();

    }
}
