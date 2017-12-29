package leave.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeaveBean implements Serializable {
    private static final long serialVersionUID = 4987190517935993160L;
    private int applyId;//
    private int userId;//用户ID
    private int departId;//部门ID
    private String uname;//用户姓名主（申请人）
    private String dname;//部门名称（所在科室）
    private boolean married;//婚姻状态
    private int dayt;//请休假天数
    private String approvedate;//申请时间(applydate)
    private String begindate;//起时间
    private String enddate;//止时间
    private String reason;//请休假事由
    private List<T_FJList> fjLists;//附件列表
    private String opinion1;//科室意见
    private String opinion2;//副主任意见
    private String opinion3;//主任意见
    private String backdate;//销假时间
    private String days;//实际天数
    private String type;//假类型

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public boolean getMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getDayt() {
        return dayt;
    }

    public void setDayt(int dayt) {
        this.dayt = dayt;
    }

    public String getApprovedate() {
        return approvedate;
    }

    public void setApprovedate(String approvedate) {
        this.approvedate = approvedate;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<T_FJList> getFjLists() {
        return fjLists;
    }

    public void setFjLists(List<T_FJList> fjLists) {
        this.fjLists = fjLists;
    }

    public String getOpinion1() {
        return opinion1;
    }

    public void setOpinion1(String opinion1) {
        this.opinion1 = opinion1;
    }

    public String getOpinion2() {
        return opinion2;
    }

    public void setOpinion2(String opinion2) {
        this.opinion2 = opinion2;
    }

    public String getOpinion3() {
        return opinion3;
    }

    public void setOpinion3(String opinion3) {
        this.opinion3 = opinion3;
    }

    public String getBackdate() {
        return backdate;
    }

    public void setBackdate(String backdate) {
        this.backdate = backdate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
