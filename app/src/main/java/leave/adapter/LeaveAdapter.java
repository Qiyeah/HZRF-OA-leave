package leave.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.sd.leave.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import leave.bean.LeaveBean;
import leave.util.LogUtil;

/**
 * Created by Administrator on 2018-1-2.
 */

public class LeaveAdapter extends BaseAdapter {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_person)
    TextView mTvPerson;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_unit)
    TextView mTvUnit;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    private List<LeaveBean> data;
    private Context mContext;

    public LeaveAdapter(List<LeaveBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LeaveBean leave = data.get(i);
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.leave_item, viewGroup, false);
            ButterKnife.bind(this, view);
        }
        mTvPerson.setText(leave.getUname());
        mTvDate.setText(leave.getApprovedate());
        mTvType.setText(leave.getType());
        mTvUnit.setText(leave.getDname());
        return view;
    }
}
