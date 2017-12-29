package leave.presenter;


import leave.bean.LeaveRoot;
import leave.model.ILeaveModel;
import leave.model.LeaveModel;
import leave.view.ILeaveView;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeavePresenter implements ILeavePresenter,ILeaveModel.CallBack1<LeaveRoot>,ILeaveModel.CallBack2<LeaveRoot>{
    private ILeaveModel mModel;
    private ILeaveView mView;

    public LeavePresenter(ILeaveView view) {
        mView = view;
        mModel = new LeaveModel();
    }

    @Override
    public void initViewData(String queryName1, String queryName2) {
        mModel.loadServerApplies(queryName1,this);
        mModel.loadServerApproves(queryName2,this);
    }

    @Override
    public void loadApplies(LeaveRoot root) {
        mView.onInitAppliesSuccess(root);
    }

    @Override
    public void loadApproves(LeaveRoot root) {
        mView.onInitApprovesSuccess(root);
    }
}
