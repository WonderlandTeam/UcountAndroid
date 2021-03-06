package team.wonderland.ucount.ucount_android.fragment;

import android.content.res.Resources;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.activity.MainActivity;

/**
 * Created by yuukidach on 17-3-21.
 */

public class GlobalVariables {
    private static String mDate = "";
    private static boolean mHasDot = false;
    private static String mInputMoney = "";
    private static String mDescription = "";
    private static String[] cost_titles;
    private static String[] earn_titles;
    private static String[] accounts = {"现金","银行卡","校园卡","支付宝","工行卡"};

    public static void setmDate(String date)      { mDate = date;     }
    public static void setHasDot(boolean hasDot)  { mHasDot = hasDot; }
    public static void setmInputMoney(String a)   { mInputMoney = a;  }
    public static void setmDescription(String a ) { mDescription = a; }

    public static String getmDate()        { return mDate;        }
    public static boolean getmHasDot()     { return mHasDot;      }
    public static String getmInputMoney()  { return mInputMoney;  }
    public static String getmDescription() { return mDescription; }

    public static int getSrcID(String type){

            Resources resources = MainActivity.resources;
            cost_titles = resources.getStringArray(R.array.cost);
            earn_titles = resources.getStringArray(R.array.earn);

            String srcName = "";
            for(int i=0;i<cost_titles.length;i++){
                if(type.equals(cost_titles[i])){
                    srcName = "type_cost_"+String.valueOf((i+1));
                    return resources.getIdentifier(srcName, "drawable", MainActivity.PACKAGE_NAME);
                }
            }
            for(int i=0;i<earn_titles.length;i++){
                if(type.equals(earn_titles[i])){
                    srcName = "type_get_"+String.valueOf((i+1));
                    return resources.getIdentifier(srcName, "drawable", MainActivity.PACKAGE_NAME);
                }
            }
            if(type.equals(accounts[0])){
                return resources.getIdentifier("xianjin", "mipmap", MainActivity.PACKAGE_NAME);
            }
            if(type.equals(accounts[1])){
                return resources.getIdentifier("yinhangka", "mipmap", MainActivity.PACKAGE_NAME);
            }
            if(type.equals(accounts[2])){
                return resources.getIdentifier("xiaoyuan", "mipmap", MainActivity.PACKAGE_NAME);
            }
            if(type.equals(accounts[3])){
                return resources.getIdentifier("zhifubao", "mipmap", MainActivity.PACKAGE_NAME);
            }
            if(type.equals(accounts[4])){
            return resources.getIdentifier("gonghang", "mipmap", MainActivity.PACKAGE_NAME);
            }


            return resources.getIdentifier(srcName, "drawable", MainActivity.PACKAGE_NAME);
    }
}
