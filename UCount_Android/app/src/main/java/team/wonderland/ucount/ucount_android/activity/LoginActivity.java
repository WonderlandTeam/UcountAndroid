package team.wonderland.ucount.ucount_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.UserInfoJson;
import team.wonderland.ucount.ucount_android.service.UserBasicService;

import java.util.Map;

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
        sp = this.getSharedPreferences("user",0);
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

//        LinkedMultiValueMap<String, String> loginData = new LinkedMultiValueMap<>();
//        loginData.add("username", usernameValue);
//        loginData.add("password", passwordValue);
//        loginAsync(loginData);

        loginAsync();

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
    void loginAsync() {
        try {
            String brand = Build.BRAND;
            String model = Build.MODEL;
            UserInfoJson userInfo = userBasicService.login(usernameValue, passwordValue, brand + " " + model);
            Log.i("login", "brand:" + brand + " model:" + model);
            loginSuccess(userInfo);
        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void loginSuccess(UserInfoJson userInfo) {
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("USERNAME", userInfo.userName);
        editor.putString("PASSWORD", userInfo.password);
        editor.putBoolean("HAVELOGINED", true);
        editor.commit();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.login_txtRegister)
    public void registerClick(View view){
        RegisterActivity_.intent(getApplication()).start();
        finish();
    }

}
