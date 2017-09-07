package team.wonderland.ucount.ucount_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.UiThread;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/3.
 */

public class ForgetPasswordActivity extends AppCompatActivity {
    private ImageView iv_back;
    private TextView tv_verify;
    private int time = 60;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        tv_verify = (TextView)findViewById(R.id.forgetpassword_verify);
        tv_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 发送验证码

                //验证码发送成功
                reminderText();
            }
        });

        iv_back = (ImageView) findViewById(R.id.forgetpassword_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForgetPasswordActivity.this,LoginActivity_.class);
                startActivity(intent);
                finish();
            }
        });


        confirm = (Button) findViewById(R.id.forgetpassword_btn_revise);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: UserInfoService.modifyUserInfo 修改用户密码

                //TODO:手机号错误
                Toast.makeText(getApplicationContext(),"手机号错误",Toast.LENGTH_SHORT).show();
                //TODO: 验证码错误
                Toast.makeText(getApplicationContext(),"验证码错误",Toast.LENGTH_SHORT).show();
                //TODO: 修改成功
                Intent intent=new Intent(ForgetPasswordActivity.this,MainActivity.class);
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

                    tv_verify.setText(" "+time+"秒后可重发 ");

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