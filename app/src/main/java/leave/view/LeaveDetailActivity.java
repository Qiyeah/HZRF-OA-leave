package leave.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhilian.sd.leave.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leave.NoScrollListView;
import leave.adapter.TextArrayAdapter;
import leave.base.BaseActivity;
import leave.bean.LeaveBean;
import leave.bean.LeaveRoot;
import leave.constant.Constants;
import leave.constant.RequestCode;
import leave.util.CacheUtil;
import leave.util.DateUtil;
import leave.util.LogUtil;
import leave.util.StrKit;
import leave.weidget.DatePickerFragment;

/**
 * Created by Administrator on 2017-12-29.
 */

public class LeaveDetailActivity extends BaseActivity implements ILeaveDetailView {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.linearLayout2)
    RelativeLayout mLinearLayout2;
    @BindView(R.id.uname)
    TextView mUname;
    @BindView(R.id.dname)
    TextView mDname;
    @BindView(R.id.type)
    Spinner mType;
    @BindView(R.id.married)
    TextView mMarried;
    @BindView(R.id.approvedate)
    TextView mApprovedate;
    @BindView(R.id.dayt)
    TextView mDayt;
    @BindView(R.id.begindate)
    TextView mBegindate;
    @BindView(R.id.enddate)
    TextView mEnddate;
    @BindView(R.id.reason)
    EditText mReason;
    @BindView(R.id.fj_list)
    NoScrollListView mFjList;
    @BindView(R.id.opinion1)
    TextView mOpinion1;
    @BindView(R.id.opinion2)
    TextView mOpinion2;
    @BindView(R.id.opinion3)
    TextView mOpinion3;
    @BindView(R.id.backdate)
    TextView mBackdate;
    @BindView(R.id.days)
    TextView mDays;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;
    @BindView(R.id.bt_save)
    Button mBtSave;
    @BindView(R.id.bt_cancel)
    Button mBtCancel;
    @BindView(R.id.bt_return)
    Button mBtReturn;
    @BindView(R.id.bt_update)
    Button mBtUpdate;
    @BindView(R.id.bt_opinion1)
    Button mBtOpinion1;
    @BindView(R.id.bt_opinion2)
    Button mBtOpinion2;
    @BindView(R.id.bt_opinion3)
    Button mBtOpinion3;

    private int result_code = 0;

    private LeaveBean leave;
    private List<String> types;
    private String[] typeRes;

    @Override
    public void initData() {
        typeRes = getResources().getStringArray(R.array.languages);
        types = Arrays.asList(typeRes);
        leave = (LeaveBean) getIntent().getSerializableExtra("leave");
        //LogUtil.e("(LeaveBean) getIntent().getSerializableExtra(\"leave\") :\n"+leave.toString());
        result_code = getIntent().getIntExtra("request_code", 0);
    }

    @Override
    public void onInitAppliesSuccess(LeaveRoot root) {

    }

    @Override
    public void onInitApprovesSuccess(LeaveRoot root) {

    }

    @Override
    public void setApplyDate(String date) {
        mApprovedate.setText(date);
        leave.setApprovedate(date);
    }

    @Override
    public void setBeginDate(String date) {
        mBegindate.setText(date);
        leave.setBegindate(date);
        updateDayt();
    }

    private void updateDayt() {
        if (StrKit.notBlank(leave.getBegindate())&& StrKit.notBlank(leave.getEnddate())){
            int dayt = DateUtil.getInstance().diffDays(leave.getEnddate(),leave.getBegindate());
            mDayt.setText(""+dayt);
        }
    }

    @Override
    public void setEndDate(String date) {
        mEnddate.setText(date);
        leave.setEnddate(date);
        updateDayt();
    }

    @Override
    public void setBackDate(String date) {
        mBackdate.setText(date);
        leave.setBackdate(date);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_leave_detail;
    }

    @Override
    protected void initView() {
        mType.setSelection(types.indexOf(leave.getType()));
        mType.setAdapter(new TextArrayAdapter(this,typeRes));
        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                leave.setType(types.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mUname.setText(leave.getUname());
        mDname.setText(leave.getDname());
        mType.setSelection(types.indexOf(leave.getType()));
        mMarried.setText(leave.getMarried());

        mApprovedate.setText(leave.getApprovedate());


        mDayt.setText(leave.getDayt());
        mBegindate.setText(leave.getBegindate());

        mEnddate.setText(leave.getEnddate());

        mReason.setText(leave.getReason());

        //mFjList.setAdapter();
        mOpinion1.setText(leave.getOpinion1());
        mOpinion2.setText(leave.getOpinion2());
        mOpinion3.setText(leave.getOpinion3());

        mBackdate.setText(leave.getBackdate());

        mDays.setText(leave.getDays());

        switch (result_code) {
            case RequestCode.NEW:
                break;
            case RequestCode.DRAFT:
                mBtOpinion1.setVisibility(View.VISIBLE);
                break;
            case RequestCode.APPROVE:
                mBtOpinion2.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void clearRes() {

    }


    @OnClick(R.id.iv_back)
    public void onMIvBackClicked() {
        Intent intent = new Intent(this, LeaveManagerActivity.class);
        intent.putExtra(Constants.cacheApplyKey, leave);
        setResult(result_code, intent);
        finish();
    }

    @OnClick(R.id.bt_submit)
    public void onMBtSubmitClicked() {

    }

    @OnClick(R.id.bt_save)
    public void onMBtSaveClicked() {

       // String applyDate = mApprovedate.getText().toString().trim();
        String dayt = mDayt.getText().toString().trim();
      //  String beginDate = mBegindate.getText().toString().trim();
       // String endDate = mEnddate.getText().toString().trim();
        String reason = mReason.getText().toString().trim();

//        if (StrKit.notBlank(applyDate)) {
//            leave.setApprovedate(applyDate);
//        }
        if (StrKit.notBlank(dayt)) {
            leave.setDayt(dayt);
        }
//        if (StrKit.notBlank(beginDate)) {
//            leave.setBegindate(beginDate);
//        }
//        if (StrKit.notBlank(endDate)) {
//            leave.setEnddate(endDate);
//        }
        if (StrKit.notBlank(reason)) {
            leave.setReason(reason);
        }
       //LogUtil.e(leave.getEnddate());
        new CacheUtil(this, Constants.cacheApplyFileName).saveObject(Constants.cacheApplyKey, leave);

    }

    @OnClick(R.id.bt_cancel)
    public void onMBtCancelClicked() {
    }

    private void saveLeave() {

    }


    @OnClick({R.id.approvedate, R.id.begindate, R.id.enddate})
    public void onViewClicked(View view) {
        String tempStr = "";
        switch (view.getId()){
            case R.id.approvedate:
                tempStr = leave.getApprovedate();
                break;
            case R.id.begindate:
                tempStr = leave.getBegindate();
                break;
            case R.id.enddate:
                tempStr = leave.getEnddate();
                break;
        }
        DatePickerFragment dialog = new DatePickerFragment(this, view.getId(),tempStr);
        dialog.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this, LeaveManagerActivity.class);
            intent.putExtra(Constants.cacheApplyKey, leave);
            setResult(result_code, intent);
            finish();
        }
        return true;
    }
}
