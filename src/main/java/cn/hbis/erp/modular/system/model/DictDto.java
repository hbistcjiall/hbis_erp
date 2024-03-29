package cn.hbis.erp.modular.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典信息
 *
 *
 */
@Data
public class DictDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    private String dictTypeId;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 备注
     */
    private String description;
    /**
     * 序号
     */
    private Integer sort;
}
