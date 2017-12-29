package leave.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.sd.leave.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leave.XPopupWindow;
import leave.base.BaseActivity;
import leave.base.BaseFragment;
import leave.base.RequestCode;
import leave.bean.LeaveBean;
import leave.bean.LeaveRoot;
import leave.presenter.LeavePresenter;
import leave.util.CacheUtil;
import leave.util.LogUtil;
import leave.util.WindowUtil;

/**
 * Created by Administrator on 2017-12-28.
 */

/**
 * 工作台---请休假管理
 */
public class LeaveManagerActivity extends BaseActivity implements ILeaveView {

    @BindView(R.id.vp_container)
    ViewPager mVpContainer;
    @BindView(R.id.tv_draft)
    TextView mTvDraft;
    @BindView(R.id.tv_approved)
    TextView mTvApproved;
    @BindView(R.id.bt_apply)
    Button mBtApply;
    @BindView(R.id.linearLayout2)
    RelativeLayout mLinearLayout2;
    @BindView(R.id.lt_container)
    LinearLayout mLtContainer;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;


    private String queryAppliesName = "";
    private String cacheApplyFileName = "apply.xml";
    private String queryApprovesName = "";

    private LeavePresenter mPresenter = null;

    private List<LeaveBean> mLeaveList;// 待批列表数据

    private List<LeaveBean> mApproveList;//已批列表数据

    private LeaveBean mCacheApply;//用户缓存的请休假申请
    private FragmentPagerAdapter mAdapter;
    private List<BaseFragment> mTabs = new ArrayList<BaseFragment>();
    private ViewPager.OnPageChangeListener mPageChangeListener;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode){
            switch (requestCode) {
                case RequestCode.NEW:
                    mCacheApply = (LeaveBean) data.getSerializableExtra("leave");
                    if (null != mCacheApply)
                        mLeaveList.add(mCacheApply);
                    changeTabs(0);
                    break;
                case RequestCode.DRAFT:
                    changeTabs(0);
                    break;
                case RequestCode.APPROVE:
                    changeTabs(1);
                    break;
            }
        }
    }

    /**
     * 初始化页面数据
     */
    @Override
    public void initData() {
        mPresenter = new LeavePresenter(this);
        mPresenter.initViewData(queryAppliesName, queryApprovesName);
        mCacheApply = new CacheUtil(getApplicationContext(), cacheApplyFileName).getObject("key", new LeaveBean());
    }

    /**
     * 加载布局文件
     *
     * @return
     */
    @Override
    protected int layoutRes() {
        return R.layout.activity_leave;
    }

    /**
     *
     */
    @Override
    protected void initView() {
        mTabs.add(new AppliesFragment());
        mTabs.add(new ApprovedFragment());

        if (mTabs != null && mTabs.size() > 0) {
            mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mTabs.get(position);
                }

                @Override
                public int getCount() {
                    return mTabs.size();
                }
            };
            LogUtil.e("" + (mVpContainer == null));
            mVpContainer.setAdapter(mAdapter);
            mPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        mTvDraft.setBackgroundColor(Color.parseColor("#99CCFF"));
                        mTvApproved.setBackgroundColor(0xffffffff);
                    } else if (position == 1) {
                        mTvApproved.setBackgroundColor(Color.parseColor("#99CCFF"));
                        mTvDraft.setBackgroundColor(0xffffffff);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };
            changeTabs(0);
        }
    }

    /**
     * +
     * 清理资源引用
     */
    @Override
    protected void clearRes() {

    }

    /**
     * 成功获取待批列表数据
     *
     * @param root
     */
    @Override
    public void onInitAppliesSuccess(LeaveRoot root) {
        mLeaveList = root.getRoot();
        mLeaveList.add(mCacheApply);
    }

    /**
     * 成功获取已批列表数据
     *
     * @param root
     */
    @Override
    public void onInitApprovesSuccess(LeaveRoot root) {
        mApproveList = root.getRoot();
    }

    /**
     * 监听返回按键
     */
    @OnClick(R.id.iv_back)
    public void onMIvBackClicked() {
    }

    @OnClick(R.id.bt_apply)
    public void onMBtApplyClicked() {
        Intent intent = new Intent(this, LeaveDetailActivity.class);
        if (null == mCacheApply) {
            mCacheApply = new LeaveBean();
            mCacheApply.setUname("xxxx");
            mCacheApply.setDname("dddd");
            mCacheApply.setMarried(true);
        }
        intent.putExtra("leave", mCacheApply);
        startActivityForResult(intent, RequestCode.NEW);
    }

    /**
     * 监听搜索按键
     */
    @OnClick(R.id.iv_search)
    public void onMIvSearchClicked() {
    }

    /**
     * 监听待批列表按键
     */
    @OnClick(R.id.tv_draft)
    public void onMTvDraftClicked() {
        changeTabs(0);
    }

    /**
     * 监听已批列表按键
     */
    @OnClick(R.id.tv_approved)
    public void onMTvApprovedClicked() {
        changeTabs(1);
    }

    // 清除掉所有的选中状态
    private void clearSelection() {
        mTvDraft.setBackgroundColor(0xffffffff);
        mTvApproved.setBackgroundColor(0xffffffff);
    }

    private void changeTabs(int index) {
        clearSelection();
        mVpContainer.setCurrentItem(index, false);
        if (0 == index)
            mTvDraft.setBackgroundColor(Color.parseColor("#99CCFF"));
        else
            mTvApproved.setBackgroundColor(Color.parseColor("#99CCFF"));
    }

}
