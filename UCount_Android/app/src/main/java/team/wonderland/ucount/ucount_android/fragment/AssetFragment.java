package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/21.
 */


public class AssetFragment extends Fragment {
    private String context;
    private TextView mTextView;

    public AssetFragment(){

    }

    public  AssetFragment(String context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_fragment,container,false);
        mTextView = (TextView)view.findViewById(R.id.txt_content);
        //mTextView = (TextView)getActivity().findViewById(R.id.txt_content);
        mTextView.setText(context);
        return view;
    }

}
