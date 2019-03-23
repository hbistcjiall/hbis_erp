package cn.hbis.erp.core.common.constant.state;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EnumSummaryType
 * @Description  客户兑现率汇总方式
 * @author zhangry12988
 * @date 2017-09-19 15:32
 * @Copyright 2017 hundsun Inc. All rights reserved.
 * 注意：本内容仅限于限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public enum EnumSummaryType {
    WITH_ZERO("0","不去0统计"),
    WITHOUT_ZERO("1","去0统计");

    private EnumSummaryType(String code, String name) {
        this.code = code;
        this.name = name;
    }
    private String code;
    private String name;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static Map<String, String> toMap() {
        Map<String, String> enumDataMap = new HashMap<String, String>();
        for (EnumSummaryType type : values()) {
            enumDataMap.put(type.getCode(), type.getName());
        }
        return enumDataMap;
    }

    public static String getByCode(String code) {
        for (EnumSummaryType item : EnumSummaryType.values()) {
            if (item.getCode().equals(code)) {
                return item.getName();
            }
        }
        return null;
    }
}
