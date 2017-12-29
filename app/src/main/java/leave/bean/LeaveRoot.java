package leave.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeaveRoot implements Serializable {
    private static final long serialVersionUID = -4118834997889241940L;
    private List<LeaveBean> root;

    public List<LeaveBean> getRoot() {
        return root;
    }
    public void setRoot(List<LeaveBean> root) {
        this.root = root;
    }
    public void add(List<LeaveBean> root){
        root.addAll(root);
    }
    public boolean isBlank(){
        return this == null || root.size() ==0;
    }
}
