package team.wonderland.ucount.ucount_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.UiThread;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/3.
 */

public class ForgetPasswordActivity extends AppCompatActivity {
    private ImageView iv_back;
    private TextView tv_verify;
    private int time = 60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        iv_back = (ImageView) findViewById(R.id.forgetpassword_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForgetPasswordActivity.this,LoginActivity_.class);
                startActivity(intent);
                finish();
            }
        });

    }


    //验证码送成功后提示文字

    private void reminderText() {

        handlerText.sendEmptyMessageDelayed(1, 1000);

    }

    Handler handlerText =new Handler(){

        public void handleMessage(Message msg) {

            if(msg.what==1){

                if(time>0){

                    tv_verify.setText(" "+(60-time)+"秒可重发 ");

                    time--;

                    handlerText.sendEmptyMessageDelayed(1, 1000);

                }else{
                    tv_verify.setText(" 获取验证码 ");
                    time = 60;
                }

            }else{
                tv_verify.setText(" 获取验证码 ");
                time = 60;
            }

        };

    };
}