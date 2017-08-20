package team.wonderland.ucount.ucount_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import team.wonderland.ucount.ucount_android.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btn_login;
    private String usernameValue,passwordValue;
    private SharedPreferences sp;
    private boolean haveLogined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.login_edtId);
        password = (EditText)findViewById(R.id.login_edtPwd);
        btn_login = (Button)findViewById(R.id.login_btnLogin);
        sp = this.getSharedPreferences("userInfo",0);
        usernameValue = sp.getString("USERNAME","");
        passwordValue = sp.getString("PASSWORD","");
        haveLogined = sp.getBoolean("HAVELOGINED",false);

        if(haveLogined){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameValue = username.getText().toString();
                passwordValue = password.getText().toString();

                //TODO:如果登录成功
                if(true){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("USERNAME",usernameValue);
                    editor.putString("PASSWORD",passwordValue);
                    editor.putBoolean("HAVELOGINED",true);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
