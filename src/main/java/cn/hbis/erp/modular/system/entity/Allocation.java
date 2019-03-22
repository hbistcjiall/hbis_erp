/**
 * FileName: Allocation
 * Author:   zhangb
 * Date:     2019/3/21 15:13
 */
package cn.hbis.erp.modular.system.entity;

public class Allocation {
    private String pzName;
    private Integer yield;
    private Integer planNum;
    private Double schedule;
    private String cxName;

    public String getPzName() {
        return pzName;
    }

    public void setPzName(String pzName) {
        this.pzName = pzName;
    }

    public Integer getYield() {
        return yield;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    public Double getSchedule() {
        return schedule;
    }

    public void setSchedule(Double schedule) {
        this.schedule = schedule;
    }

    public String getCxName() {
        return cxName;
    }

    public void setCxName(String cxName) {
        this.cxName = cxName;
    }
}

