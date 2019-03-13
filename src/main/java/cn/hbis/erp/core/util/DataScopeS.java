package cn.hbis.erp.core.util;

import java.util.List;

/**
 * @author zhangb
 * @Date 2019-03-11
 **/
public class DataScopeS {
    private String scopeName = "deptid";
    private List<String> deptIds;

    public DataScopeS() {
    }

    public DataScopeS(List<String> deptIds) {
        this.deptIds = deptIds;
    }

    public DataScopeS(String scopeName, List<String> deptIds) {
        this.scopeName = scopeName;
        this.deptIds = deptIds;
    }

    public List<String> getDeptIds() {
        return this.deptIds;
    }

    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }

    public String getScopeName() {
        return this.scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}
