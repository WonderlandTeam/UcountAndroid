package team.wonderland.ucount.ucount_android.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import team.wonderland.ucount.ucount_android.R;

/**
 * 修改密码
 * Created by CLL on 17/8/30.
 */
public class ModifyPasswordFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.modify_password_fragment,container,false);

        return view;
    }
}
