package leave.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.sd.leave.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import leave.base.BaseActivity;
import leave.base.BaseFragment;
import leave.constant.Constants;
import leave.constant.RequestCode;
import leave.bean.LeaveBean;
import leave.bean.LeaveRoot;
import leave.presenter.LeavePresenter;
import leave.util.CacheUtil;
import leave.util.LogUtil;

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

    private List<LeaveBean> mDraftList = new ArrayList<>();// 待批列表数据

    private List<LeaveBean> mApproveList = new ArrayList<>();//已批列表数据

    private LeaveBean mCacheApply;//用户缓存的请休假申请
    private FragmentPagerAdapter mAdapter;
    private List<BaseFragment> mTabs = new ArrayList<BaseFragment>();
    private ViewPager.OnPageChangeListener mPageChangeListener;
    private int index = 0;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (RESULT_OK == resultCode){
        LogUtil.e("resultCode = " + resultCode);
        mDraftList.remove(mDraftList.indexOf(mCacheApply));
        switch (resultCode) {
            case RequestCode.NEW:
                mCacheApply = (LeaveBean) data.getSerializableExtra(Constants.cacheApplyKey);
                mDraftList.add(mCacheApply);
                mTabs.get(index).notifyDataChange(mDraftList);
                break;
            case RequestCode.DRAFT:
                mCacheApply = (LeaveBean) data.getSerializableExtra(Constants.cacheApplyKey);
                mDraftList.add(mCacheApply);
                mTabs.get(index).notifyDataChange(mDraftList);
                break;
            case RequestCode.APPROVE:
                mTabs.get(index).notifyDataChange(mApproveList);
                break;
        }
       // changeTabs();


//        }
    }

    /**
     * 初始化页面数据
     */
    @Override
    public void initData() {
        mPresenter = new LeavePresenter(this);
        mPresenter.initViewData(queryAppliesName, queryApprovesName);
        mCacheApply = new CacheUtil(getApplicationContext(), cacheApplyFileName).getObject(Constants.cacheApplyKey, new LeaveBean());
        if (null != mCacheApply) {
            mDraftList.add(mCacheApply);
        }
//        LogUtil.e(" size = "+mDraftList.size());
//        for (LeaveBean bean : mDraftList) {
//            LogUtil.e(" reason = "+bean.getReason());
//        }
        // LogUtil.e(mCacheApply.toString());
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

        mTabs.add(new AppliesFragment(mDraftList));
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
            mVpContainer.setAdapter(mAdapter);
            mPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    index = position;
                    changeTabs();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };
            mVpContainer.setOnPageChangeListener(mPageChangeListener);
            changeTabs();
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
        mDraftList = root.getRoot();
        mDraftList.add(mCacheApply);
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
            mCacheApply.setApplyId(0x1);
            mCacheApply.setUname("uname");
            mCacheApply.setDname("dname");
            mCacheApply.setMarried("is married");
        }
        intent.putExtra(Constants.cacheApplyKey, mCacheApply);
        intent.putExtra("request_code", RequestCode.NEW);
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
        index = 0;
        changeTabs();
    }

    /**
     * 监听已批列表按键
     */
    @OnClick(R.id.tv_approved)
    public void onMTvApprovedClicked() {
        index = 1;
        changeTabs();
    }

    // 清除掉所有的选中状态
    private void clearSelection() {
        mTvDraft.setBackgroundColor(0xffffffff);
        mTvApproved.setBackgroundColor(0xffffffff);
    }

    private void changeTabs() {
        clearSelection();

        mVpContainer.setCurrentItem(index, false);
        if (0 == index)
            mTvDraft.setBackgroundColor(Color.parseColor("#99CCFF"));
        else
            mTvApproved.setBackgroundColor(Color.parseColor("#99CCFF"));
    }

}
