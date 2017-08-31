package team.wonderland.ucount.ucount_android.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import team.wonderland.ucount.ucount_android.R;

/**
 * 修改密码
 * Created by CLL on 17/8/30.
 */
public class ModifyPasswordFragment extends Fragment {
    private Button cancelPassword;
    private Button submitPassword;
    private EditText oldPassword;
    private EditText newPassword1;
    private EditText newPassword2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.modify_password_fragment,container,false);

        oldPassword=view.findViewById(R.id.old_password);
        newPassword1=view.findViewById(R.id.new_password1);
        newPassword2=view.findViewById(R.id.new_password2);

        submitPassword=view.findViewById(R.id.password_submit_bt);
        submitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPasswordText=oldPassword.getText().toString();
                String newPasswordText1=newPassword1.getText().toString();
                String newPasswordText2=newPassword2.getText().toString();
                //TODO
            }
        });

        cancelPassword=view.findViewById(R.id.password_cancel_bt);
        cancelPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }
}
