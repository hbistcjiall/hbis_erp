/**
 * FileName: Allocation
 * Author:   zhangb
 * Date:     2019/3/21 15:13
 */
package cn.hbis.erp.modular.system.entity;

import lombok.Data;

@Data
public class Allocation {
    private Integer sort;
    private String pzName;
    private Double yield;
    private Double planNum;
    private Double schedule;
    private String cxName;
    private String companyName;
    private String flName;

}

