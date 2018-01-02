package leave.weidget;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import com.zhilian.sd.leave.R;

import java.util.Calendar;

import leave.util.DateUtil;
import leave.util.StrKit;
import leave.view.ILeaveDetailView;
import leave.view.ILeaveView;

/**
 * Created by Administrator on 2018-1-2.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private ILeaveDetailView mView;
    private int mResId;
    private String mDate;
    public DatePickerFragment(ILeaveDetailView view,int resId,String strDate) {
        mView = view;
        mResId = resId;
        mDate=strDate;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        switch (mResId){
            case R.id.approvedate:
                mView.setApplyDate(formatDate(i,i1,i2));
                break;
            case R.id.begindate:
                mView.setBeginDate(formatDate(i,i1,i2));
                break;
            case R.id.enddate:
                mView.setEndDate(formatDate(i,i1,i2));
                break;
            case R.id.backdate:
                mView.setBackDate(formatDate(i,i1,i2));
                break;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog dialog = null;
        if (StrKit.isBlank(mDate)){
            Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            dialog = new DatePickerDialog(getActivity(),this,year,month,day);
        }else {
            int[] arr = DateUtil.getInstance().splitDate(mDate);
            dialog = new DatePickerDialog(getActivity(),this,arr[0],arr[1]-1,arr[2]);
        }
        return dialog;
    }
    private String formatDate(int year,int month,int day){
        return year+" - "+(month+1)+" - "+day;
    }


}
