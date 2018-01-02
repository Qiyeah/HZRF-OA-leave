package leave.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhilian.sd.leave.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import leave.base.BaseFragment;
import leave.bean.LeaveBean;

/**
 * Created by Administrator on 2017-12-29.
 */

public class ApprovedFragment extends BaseFragment {


    @Override
    protected void initView() {

    }

    @Override
    protected int layoutRes() {
        return R.layout.layout_apply;
    }

    @Override
    public void notifyDataChange(List<LeaveBean> data) {

    }


}
