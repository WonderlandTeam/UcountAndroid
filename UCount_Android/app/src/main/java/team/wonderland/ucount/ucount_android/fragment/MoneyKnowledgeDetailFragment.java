package team.wonderland.ucount.ucount_android.fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/1.
 */

public class MoneyKnowledgeDetailFragment extends Fragment {

    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_knowledge_detail_fragment, container, false);
        PDFView mPdfView = (PDFView) view.findViewById(R.id.pdf_fragment);

        back = (ImageView) view.findViewById(R.id.money_knowledge_detail_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        String num = String.valueOf(getArguments().get("num"));
        mPdfView.fromAsset("pdf"+num+".pdf")
                .defaultPage(0)//默认页
                .enableSwipe(true)//允许拖动
                .swipeHorizontal(false)//默认方向是竖轴的，改为true变为横轴
                .enableDoubletap(true)//允许双击放缩
                .load();
        return view;
    }
}
