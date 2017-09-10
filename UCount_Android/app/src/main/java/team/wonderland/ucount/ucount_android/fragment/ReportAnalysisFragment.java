package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.barteksc.pdfviewer.PDFView;
import org.androidannotations.annotations.EFragment;
import team.wonderland.ucount.ucount_android.R;

/**
 * Created by CLL on 17/9/10.
 */
@EFragment
public class ReportAnalysisFragment extends Fragment {
    PDFView mPdfView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.report_analysis_fragment, container, false);
        mPdfView=view.findViewById(R.id.report_pdf_fragment);
        int num=1;
        mPdfView.fromAsset("ReportAnalysis/analysis"+num+".pdf")
                .defaultPage(0)//默认页
                .enableSwipe(true)//允许拖动
                .swipeHorizontal(false)//默认方向是竖轴的，改为true变为横轴
                .enableDoubletap(true)//允许双击放缩
                .load();
        return view;
    }
}
