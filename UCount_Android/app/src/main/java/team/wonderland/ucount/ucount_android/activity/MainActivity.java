package team.wonderland.ucount.ucount_android.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private int[] mImgIds = new int[]{
            R.drawable.guide_image1,R.drawable.guide_image2,R.drawable.guide_image3
    };
    private List<ImageView> mImages = new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager)findViewById(R.id.id_viewPager);
        //为viewPager设置动画效果
        //mViewPager.setPageTransformer(true,new DepthPageTransformer());
        mViewPager.setAdapter(new PagerAdapter(){
            @Override
            public Object instantiateItem(ViewGroup container, int position){
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(mImgIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mImages.add(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object){
                container.removeView(mImages.get(position));
                //container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return mImgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });

    }
}
