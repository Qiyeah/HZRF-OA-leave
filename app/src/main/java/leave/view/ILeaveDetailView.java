package leave.view;

/**
 * Created by Administrator on 2018-1-2.
 */

public interface ILeaveDetailView extends ILeaveView {
    void setApplyDate(String date);
    void setBeginDate(String date);
    void setEndDate(String date);
    void setBackDate(String date);
}
