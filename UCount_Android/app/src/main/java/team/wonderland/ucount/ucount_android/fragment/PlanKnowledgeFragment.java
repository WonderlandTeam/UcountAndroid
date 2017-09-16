package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/11.
 */

public class PlanKnowledgeFragment extends Fragment {

    TextView level;
    TextView feature;
    TextView advice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_knowledge_fragment, container, false);


        level = view.findViewById(R.id.plan_knowledge_tv_level);

        feature = view.findViewById(R.id.plan_knowledge_tv_feature);

        advice =view.findViewById(R.id.plan_knowledge_tv_advice);

        return view;
    }
}
