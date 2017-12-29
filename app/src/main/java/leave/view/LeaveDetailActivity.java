package leave.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.sd.leave.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leave.NoScrollListView;
import leave.base.BaseActivity;
import leave.constant.Constants;
import leave.constant.RequestCode;
import leave.bean.LeaveBean;
import leave.bean.LeaveRoot;
import leave.util.CacheUtil;
import leave.util.LogUtil;
import leave.util.StrKit;

/**
 * Created by Administrator on 2017-12-29.
 */

public class LeaveDetailActivity extends BaseActivity implements ILeaveView {
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
    EditText mType;
    @BindView(R.id.married)
    EditText mMarried;
    @BindView(R.id.approvedate)
    EditText mApprovedate;
    @BindView(R.id.dayt)
    EditText mDayt;
    @BindView(R.id.begindate)
    EditText mBegindate;
    @BindView(R.id.enddate)
    EditText mEnddate;
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
    EditText mBackdate;
    @BindView(R.id.days)
    EditText mDays;
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


    private void returnResult() {
        getInputs();
        Intent intent = new Intent(this, LeaveManagerActivity.class);
        intent.putExtra("leave", leave);
        setResult(result_code, intent);
        finish();

    }

    @Override
    public void initData() {
        leave = (LeaveBean) getIntent().getSerializableExtra("leave");
        result_code = getIntent().getIntExtra("request_code", 0);
    }

    @Override
    public void onInitAppliesSuccess(LeaveRoot root) {

    }

    @Override
    public void onInitApprovesSuccess(LeaveRoot root) {

    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_leave_detail;
    }

    @Override
    protected void initView() {

        mUname.setText(leave.getUname());
        mDname.setText(leave.getDname());

        mType.setText("类型" + leave.getType());
        mMarried.setText("婚否" + leave.getMarried());
        mApprovedate.setText("申请时间" + leave.getApprovedate());
        //mDayt.setText(leave.getDayt());
        mBegindate.setText("开始时间" + leave.getBegindate());
        mEnddate.setText("结束时间" + leave.getEnddate());
        mReason.setText("请休假事由" + leave.getReason());

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
        LogUtil.e("back!");
        returnResult();
    }

    @OnClick(R.id.bt_submit)
    public void onMBtSubmitClicked() {

    }

    @OnClick(R.id.bt_save)
    public void onMBtSaveClicked() {
       getInputs();
        new CacheUtil(this, Constants.cacheApplyFileName).saveObject(Constants.cacheApplyKey,leave);
    }

    @OnClick(R.id.bt_cancel)
    public void onMBtCancelClicked() {
    }

    private void getInputs(){
        leave.setType(mType.getText().toString().trim());
        leave.setApprovedate(mApprovedate.getText().toString().trim());
        String dayt = mDayt.getText().toString().trim();
        if (StrKit.notBlank(dayt)){
            leave.setDayt(Integer.valueOf(dayt));
        }
        leave.setBegindate(mBegindate.getText().toString().trim());
        leave.setEnddate(mEnddate.getText().toString().trim());
        leave.setReason(mReason.getText().toString().trim());
    }

}
