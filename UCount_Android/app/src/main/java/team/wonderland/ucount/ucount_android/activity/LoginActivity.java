package team.wonderland.ucount.ucount_android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Map;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.json.LoginJson;
import team.wonderland.ucount.ucount_android.service.UserBasicService;

/**
 * Created by CLL on 17/8/16.
 */
@EActivity
public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btn_login;
    private String usernameValue,passwordValue;
    private TextView forgetPass,register;
    private SharedPreferences sp;
    private boolean haveLogined;

    @RestService
    UserBasicService userBasicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.login_edtId);
        password = (EditText)findViewById(R.id.login_edtPwd);
        btn_login = (Button)findViewById(R.id.login_btnLogin);
        forgetPass = (TextView)findViewById(R.id.login_txtForgotPwd);
        register = (TextView)findViewById(R.id.login_txtRegister);
        sp = this.getSharedPreferences("userInfo",0);
        usernameValue = sp.getString("USERNAME","");
        passwordValue = sp.getString("PASSWORD","");
        haveLogined = sp.getBoolean("HAVELOGINED",false);

        if(haveLogined){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
            forgetPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("tag","clicked!!!!!!!!!!!!!!!!!!");
                    Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


    }

    @Click(R.id.login_btnLogin)
    public void loginClick(View view){
        usernameValue = username.getText().toString();
        passwordValue = password.getText().toString();

        LoginJson loginJson = new LoginJson();
        loginJson.setUsername(usernameValue);
        loginJson.setPassword(passwordValue);
        loginAsync(loginJson);

//        Log.i("login",(String)result.get("content"));
//        //TODO:如果登录成功
//        if(true) {
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("USERNAME",usernameValue);
//            editor.putString("PASSWORD",passwordValue);
//            editor.putBoolean("HAVELOGINED",true);
//            editor.commit();
//
//            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

    @Background
    void loginAsync(LoginJson loginJson) {
        Map<String, Object> result = userBasicService.login(loginJson);
        if (result.containsKey("content")) {
            System.out.println(result.get("content"));
            loginSuccess();
        } else {
            showErrorInfo((String) result.get("error"));
        }

    }

    @UiThread
    void loginSuccess() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USERNAME",usernameValue);
        editor.putString("PASSWORD",passwordValue);
        editor.putBoolean("HAVELOGINED",true);
        editor.commit();

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @UiThread
    void showErrorInfo(String error) {
        System.out.println(error);
        Log.i("login", error);
    }

    @Click(R.id.login_txtRegister)
    public void registerClick(View view){
        RegisterActivity_.intent(getApplication()).start();
        finish();
    }

}
