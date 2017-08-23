package team.wonderland.ucount.ucount_android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.json.UserSignUpJson;
import team.wonderland.ucount.ucount_android.service.UserBasicService;
import team.wonderland.ucount.ucount_android.service.servicestub.UserBasicService_Stub;

/**
 * Created by liuyu on 2017/8/20.
 */
@EActivity
public class RegisterActivity extends AppCompatActivity{
    private EditText phone,username, password;
    private Button btn_register;
    private String phoneValue,usernameValue,passwordValue;

    @RestService
    UserBasicService userBasicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //先用stub
        userBasicService=new UserBasicService_Stub();

        phone = (EditText)findViewById(R.id.et_reg_phone);
        username = (EditText)findViewById(R.id.et_reg_username);
        password = (EditText)findViewById(R.id.et_reg_pass);
        btn_register = (Button)findViewById(R.id.btn_register);
        
    }

    @Click(R.id.btn_register)
    public void registerClick(View view){
        if(username==null||username.getText().toString().equals("")) {
            return;
        }
        if(password==null||password.getText().toString().equals("")) {
            return;
        }
        if(phone==null||phone.getText().toString().equals("")) {
            return;
        }
        //邮箱可选，先空着
        //TODO
        UserSignUpJson userSignUpJson=new UserSignUpJson(username.getText().toString(),password.getText().toString(),
                phone.getText().toString(),null);
        if (userBasicService.signUp(userSignUpJson)==null){
            //error填入错误信息
            showErrorMessage("");
        }
        //返回用户id
        else{
            //进入登录界面
            showLoginActivity();
        }
    }

    @UiThread
    void showLoginActivity(){
        LoginActivity_.intent(getApplication()).start();
    }

    @UiThread
    void showErrorMessage(String context){
        Toast.makeText(getApplicationContext(),context,Toast.LENGTH_LONG).show();
    }
}
