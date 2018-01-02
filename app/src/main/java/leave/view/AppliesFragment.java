package leave.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhilian.sd.leave.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import leave.adapter.LeaveAdapter;
import leave.base.BaseFragment;
import leave.bean.LeaveBean;
import leave.constant.Constants;
import leave.constant.RequestCode;
import leave.util.LogUtil;

/**
 * Created by Administrator on 2017-12-29.
 */

public class AppliesFragment extends BaseFragment {
    @BindView(R.id.lv_draft)
    ListView mLvDraft;
    private LeaveAdapter mAdapter;
    private List<LeaveBean> mDraftList;

    public AppliesFragment(List<LeaveBean> draftList) {
        mDraftList = draftList;

    }



    @Override
    protected void initView() {
        mAdapter = new LeaveAdapter(mDraftList,getActivity());
        mLvDraft.setAdapter(mAdapter);
        mLvDraft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), LeaveDetailActivity.class);
                intent.putExtra(Constants.cacheApplyKey, mDraftList.get(i));
                intent.putExtra("request_code", RequestCode.DRAFT);
                startActivityForResult(intent, RequestCode.DRAFT);
            }
        });
    }

    @Override
    protected int layoutRes() {
        return R.layout.layout_draft;
    }

    @Override
    public void notifyDataChange(List<LeaveBean> data) {
        LogUtil.e("notify");
        mDraftList = data;
        for (LeaveBean leaveBean : mDraftList) {
            LogUtil.e(leaveBean.getReason());
        }
        mAdapter.notifyDataSetChanged();
    }


}
