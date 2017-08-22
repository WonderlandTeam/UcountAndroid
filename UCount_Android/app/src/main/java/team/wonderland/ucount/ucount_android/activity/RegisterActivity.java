package team.wonderland.ucount.ucount_android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/20.
 */

public class RegisterActivity extends AppCompatActivity{
    private EditText phone,username, password;
    private Button btn_register;
    private String phoneValue,usernameValue,passwordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phone = (EditText)findViewById(R.id.et_reg_phone);
        username = (EditText)findViewById(R.id.et_reg_username);
        password = (EditText)findViewById(R.id.et_reg_pass);
        btn_register = (Button)findViewById(R.id.btn_register);
    }

}
