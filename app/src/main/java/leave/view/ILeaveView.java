package leave.view;


import leave.base.IBaseView;
import leave.bean.LeaveRoot;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeaveView extends IBaseView {
    void onInitAppliesSuccess(LeaveRoot root);
    void onInitApprovesSuccess(LeaveRoot root);
}
