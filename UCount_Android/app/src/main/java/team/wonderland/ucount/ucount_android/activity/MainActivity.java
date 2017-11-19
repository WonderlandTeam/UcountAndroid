package team.wonderland.ucount.ucount_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.fragment.*;

import static team.wonderland.ucount.ucount_android.R.id.fragment_container;
import static team.wonderland.ucount.ucount_android.R.layout.activity_main;

/**
 * Created by liuyu on 2017/8/19.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static String PACKAGE_NAME;
    public static Resources resources;

    private DrawerLayout mDrawerLayout = null;
    private TextView tabAsset;
    private TextView tabReport;
    private TextView tabHome;
    private TextView tabPlan;
    private TextView tabMoney;

    private LinearLayout ll_quit;
    private CardView cv_modifyPassword;
    private CardView cv_post;
    private CardView cv_reply;
    private CardView cv_praise;
    private CardView cv_collection;
    private CardView cv_message;
    private TextView username;

    private SharedPreferences sp;

    private FrameLayout ly_content;

    private AssetFragment assetFragment;
    private ReportFragment reportFragment;
    private HomeFragment homeFragment;
    private PlanFragment planFragment;
    private MoneyFragment moneyFragment;
    private FragmentTransaction transaction;

    private ModifyPasswordFragment modifyPasswordFragment;
    private MyPostsFragment myPostsFragment;
    private MyMessageFragment myMessageFragment;
    private MyCollectionFragment myCollectionFragment;
    private MyReplyFragment myReplyFragment;
    private MyPraiseFragment myPraiseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        sp = this.getSharedPreferences("userInfo",0);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            /**
             * 当抽屉滑动状态改变的时候被调用
             * 状态值是STATE_IDLE（闲置--0）, STATE_DRAGGING（拖拽的--1）, STATE_SETTLING（固定--2）中之一。
             * 抽屉打开的时候，点击抽屉，drawer的状态就会变成STATE_DRAGGING，然后变成STATE_IDLE
             */
            @Override
            public void onDrawerStateChanged(int arg0) {
                Log.i("drawer", "drawer的状态：" + arg0);
            }

            /**
             * 当抽屉被滑动的时候调用此方法
             * arg1 表示 滑动的幅度（0-1）
             */
            @Override
            public void onDrawerSlide(View arg0, float arg1) {
                Log.i("drawer", arg1 + "");
            }

            /**
             * 当一个抽屉被完全打开的时候被调用
             */
            @Override
            public void onDrawerOpened(View arg0) {
                Log.i("drawer", "抽屉被完全打开了！");
            }

            /**
             * 当一个抽屉完全关闭的时候调用此方法
             */
            @Override
            public void onDrawerClosed(View arg0) {
                Log.i("drawer", "抽屉被完全关闭了！");
            }
        });


        bindView();

        tabHome.performClick();
        
        username= (TextView) findViewById(R.id.username);
        SharedPreferences preferences = getSharedPreferences("user", 0);
        String userName = preferences.getString("USERNAME", "sigma");
        username.setText("Username："+userName);

        //个人中心按钮
        ll_quit = (LinearLayout) findViewById(R.id.main_center_quit);
        ll_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳回到登录界面
                Intent intent=new Intent(MainActivity.this,LoginActivity_.class);
                startActivity(intent);

                sp=getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USERNAME","");
                editor.putString("PASSWORD","");
                editor.putBoolean("HAVELOGINED",false);
                editor.commit();
            }
        });


        cv_modifyPassword=  (CardView) findViewById(R.id.main_center_password);
        cv_modifyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                modifyPasswordFragment = new ModifyPasswordFragment();
                transaction.add(fragment_container, modifyPasswordFragment);
                transaction.commit();
            }
        });

        cv_post= (CardView) findViewById(R.id.main_center_post);
        cv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myPostsFragment=new MyPostsFragment_();
                transaction.add(fragment_container, myPostsFragment);
                transaction.commit();
            }
        });

        cv_collection= (CardView) findViewById(R.id.main_center_collection);
        cv_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myCollectionFragment=new MyCollectionFragment_();
                transaction.add(fragment_container, myCollectionFragment);
                transaction.commit();
            }
        });

        cv_praise= (CardView) findViewById(R.id.main_Center_praise);
        cv_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myPraiseFragment=new MyPraiseFragment_();
                transaction.add(fragment_container, myPraiseFragment);
                transaction.commit();
            }
        });

        cv_message= (CardView) findViewById(R.id.main_center_message);
        cv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myMessageFragment=new MyMessageFragment();
                transaction.add(fragment_container, myMessageFragment);
                transaction.commit();
            }
        });

        cv_reply= (CardView) findViewById(R.id.main_center_reply);
        cv_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myReplyFragment=new MyReplyFragment_();
                transaction.add(fragment_container, myReplyFragment);
                transaction.commit();
            }
        });

        // 获得包名和资源，方便后面的程序使用
        PACKAGE_NAME = getApplicationContext().getPackageName();
        resources = getResources();


        int id = getIntent().getIntExtra("jump", 0);
        if (id == 1||id==2||id==3 || id ==4) {
            tabAsset.setSelected(true);
            tabHome.setSelected(false);
            Fragment toFragment = new AssetFragment_();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,toFragment)
                    .commit();
        }else if(id == 5||id==6||id==7|| id ==8){
            tabReport.setSelected(true);
            tabHome.setSelected(false);
            Fragment toFragment = new ReportFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,toFragment)
                    .commit();
        }else if(id == 9||id==10 |id==11){
            tabPlan.setSelected(true);
            tabHome.setSelected(false);
            Fragment toFragment = new PlanFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,toFragment)
                    .commit();
        }else if(id == 12){
            tabMoney.setSelected(true);
            tabHome.setSelected(false);
            Fragment toFragment = new MoneyFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,toFragment)
                    .commit();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDrawerLayout!=null){
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                mDrawerLayout.closeDrawers();
            }else super.onBackPressed();
        }
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        tabAsset = (TextView)this.findViewById(R.id.txt_asset);
        tabReport = (TextView)this.findViewById(R.id.txt_report);
        tabHome = (TextView)this.findViewById(R.id.txt_home);
        tabPlan = (TextView)this.findViewById(R.id.txt_plan);
        tabMoney = (TextView)this.findViewById(R.id.txt_money);
        ly_content = (FrameLayout) findViewById(fragment_container);

        tabAsset.setOnClickListener(this);
        tabReport.setOnClickListener(this);
        tabHome.setOnClickListener(this);
        tabPlan.setOnClickListener(this);
        tabMoney.setOnClickListener(this);

    }

    //重置所有文本的选中状态
    public void selected(){
        tabAsset.setSelected(false);
        tabReport.setSelected(false);
        tabHome.setSelected(false);
        tabPlan.setSelected(false);
        tabMoney.setSelected(false);
    }


    @Override
    public void onClick(View v) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch(v.getId()){
            case R.id.txt_asset:
                selected();
                tabAsset.setSelected(true);
                assetFragment = new AssetFragment_();
                transaction.add(fragment_container, assetFragment);
                break;

            case R.id.txt_report:
                selected();
                tabReport.setSelected(true);
                reportFragment = new ReportFragment();
                transaction.add(fragment_container, reportFragment);
                break;

            case R.id.txt_plan:
                selected();
                tabPlan.setSelected(true);
                planFragment = new PlanFragment();
                transaction.add(fragment_container, planFragment);
                break;

            case R.id.txt_money:
                selected();
                tabMoney.setSelected(true);
                moneyFragment = new MoneyFragment();
                transaction.add(fragment_container, moneyFragment);
                break;

            case R.id.txt_home:
                selected();
                tabHome.setSelected(true);
                homeFragment = new HomeFragment_();
                transaction.add(fragment_container, homeFragment);
                break;
        }

        transaction.commit();
    }


}
