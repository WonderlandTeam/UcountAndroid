package team.wonderland.ucount.ucount_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/30.
 */

public class AssetAddDescriptionFragment extends Fragment {
    ImageView back;
    CircleButton save;
    EditText inputTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.asset_add_description_fragment, container, false);

        inputTxt = (EditText) view.findViewById(R.id.asset_add_description_et);

        back = (ImageView) view.findViewById(R.id.asset_add_description_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        save = (CircleButton) view.findViewById(R.id.asset_add_description_save);
        if(!GlobalVariables.getmDescription().equals("")){
            inputTxt.setText(GlobalVariables.getmDescription());
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);

                GlobalVariables.setmDescription(inputTxt.getText().toString());
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }
}
