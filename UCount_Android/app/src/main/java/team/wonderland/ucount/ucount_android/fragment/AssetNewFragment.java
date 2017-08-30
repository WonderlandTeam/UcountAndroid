package team.wonderland.ucount.ucount_android.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
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

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/29.
 */

public class AssetNewFragment extends Fragment{
    ImageView im_back;
    CardView cv_name;
    CardView cv_type;
    CardView cv_balance;
    CircleButton bt_save;

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

        cv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
                View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
                localBuilder.setView(dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
                EditText et = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
                tv.setText("请输入账户名称");
                localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {

                    }
                }).create().show();
            }
        });

        cv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
                View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
                localBuilder.setView(dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
                EditText et = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
                tv.setText("请输入账户类型");
                localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {

                    }
                }).create().show();
            }
        });


        cv_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
                View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
                localBuilder.setView(dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
                EditText et = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
                tv.setText("请输入账户余额");
                localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {

                    }
                }).create().show();
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_container, new AssetFragment()).commit();
            }
        });

        return view;
    }
}
