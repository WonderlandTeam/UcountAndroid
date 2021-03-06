package team.wonderland.ucount.ucount_android.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import team.wonderland.ucount.ucount_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CLL on 17/9/2.
 */
public class TimePickerDialog {
    private int tag;
    private Context mContext;
    private AlertDialog.Builder mAlertDialog;
    private int mHour, mMinute;
    private TimePickerDialogInterface timePickerDialogInterface;
    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private int mYear, mDay, mMonth;

    public TimePickerDialog(Context context,Fragment fragment,int tag) {
        super();
        mContext = context;
        timePickerDialogInterface = (TimePickerDialogInterface) fragment;
        this.tag=tag;
    }

    /**
     * 初始化DatePicker
     *
     * @return
     */
    private View initDatePicker() {
        View inflate = LayoutInflater.from(mContext).inflate(
                R.layout.datepicker_layout, null);
        mDatePicker = (DatePicker) inflate
                .findViewById(R.id.datePicker);
        resizePikcer(mDatePicker);
        return inflate;
    }

    /**
     * 创建dialog
     *
     * @param view
     */
    private void initDialog(View view) {
        mAlertDialog.setPositiveButton("确定",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();

                        getDatePickerValue();
                        timePickerDialogInterface.positiveListener(tag);

                    }
                });
        mAlertDialog.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        timePickerDialogInterface.negativeListener(tag);
                        dialog.dismiss();
                    }
                });
        mAlertDialog.setView(view);
    }


    /**
     * 显示日期选择器
     */
    public void showDatePickerDialog() {
        View view = initDatePicker();
        mAlertDialog = new AlertDialog.Builder(mContext);
        //mAlertDialog.setTitle("选择时间");
        initDialog(view);
        mAlertDialog.show();
    }
    /*
    * 调整numberpicker大小
    */
    private void resizeNumberPicker(NumberPicker np) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 0, 10, 0);
        np.setLayoutParams(params);
    }

    /**
     * 调整FrameLayout大小
     *
     * @param tp
     */
    private void resizePikcer(FrameLayout tp) {
        List<NumberPicker> npList = findNumberPicker(tp);
        for (NumberPicker np : npList) {
            resizeNumberPicker(np);
        }
    }

    /**
     * 得到viewGroup里面的numberpicker组件
     *
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    public int getYear() {
        return mYear;
    }

    public int getDay() {
        return mDay;
    }

    public int getMonth() {
        //返回的时间是0-11
        return mMonth+1;
    }

    public int getMinute() {
        return mMinute;
    }

    public int getHour() {
        return mHour;
    }

    /**
     * 获取日期选择的值
     */
    private void getDatePickerValue() {
        mYear = mDatePicker.getYear();
        mMonth = mDatePicker.getMonth();
        mDay= mDatePicker.getDayOfMonth();
    }

    /**
     * 获取时间选择的值
     */
    private void getTimePickerValue() {
        // api23这两个方法过时
        mHour = mTimePicker.getCurrentHour();// timePicker.getHour();
        mMinute = mTimePicker.getCurrentMinute();// timePicker.getMinute();
    }



    public interface TimePickerDialogInterface {
        public void positiveListener(int tag);
        public void negativeListener(int tag);
    }

}
