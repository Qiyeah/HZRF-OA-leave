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
import leave.bean.LeaveBean;
import leave.bean.LeaveRoot;

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
    EditText mOpinion1;
    @BindView(R.id.opinion2)
    EditText mOpinion2;
    @BindView(R.id.opinion3)
    EditText mOpinion3;
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
    private LeaveBean leave;

    private void returnResult() {
        Intent intent = new Intent(this, LeaveManagerActivity.class);
        intent.putExtra("leave", leave);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void initData() {
        leave = (LeaveBean) getIntent().getSerializableExtra("leave");
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
        mBtReturn.setVisibility(View.INVISIBLE);
        mUname.setText(leave.getUname());
        mDname.setText(leave.getDname());
    }

    @Override
    protected void clearRes() {

    }



    @OnClick(R.id.iv_back)
    public void onMIvBackClicked() {
        returnResult();
    }

    @OnClick(R.id.bt_submit)
    public void onMBtSubmitClicked() {

    }

    @OnClick(R.id.bt_save)
    public void onMBtSaveClicked() {

    }

    @OnClick(R.id.bt_cancel)
    public void onMBtCancelClicked() {
    }
}
