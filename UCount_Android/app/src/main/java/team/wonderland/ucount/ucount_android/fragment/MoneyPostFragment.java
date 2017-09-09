package team.wonderland.ucount.ucount_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.EFragment;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/31.
 */

@EFragment(R.layout.money_post_fragment)
public class MoneyPostFragment extends Fragment {
    Button confirm;
    EditText mTitle;
    EditText mContent;
    String title;
    String context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_post_fragment, container,false);

        confirm = (Button) view.findViewById(R.id.money_post_confirm);
        mTitle = (EditText) view.findViewById(R.id.money_post_title);
        mContent = (EditText) view.findViewById(R.id.money_post_content);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = mTitle.getText().toString();
                context = mContent.getText().toString();

                if(title.equals("")){
                    Toast.makeText(getContext(), "标题不能为空",
                        Toast.LENGTH_SHORT).show();
                }else if(context.equals("")){
                    Toast.makeText(getContext(), "内容不能为空",
                            Toast.LENGTH_SHORT).show();
                }else{
                    mTitle.setText("");
                    mContent.setText("");
                    //TODO  PostService.addPost  发表帖子
                    Toast.makeText(getContext(), "保存成功",
                            Toast.LENGTH_SHORT).show();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });
        return view;
    }
}
