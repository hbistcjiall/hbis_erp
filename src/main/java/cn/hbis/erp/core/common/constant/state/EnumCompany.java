package cn.hbis.erp.core.common.constant.state;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright © 2016 hundsun. All rights reserved.
 * @Package: com.hundsun.network.uc.core.enums
 * @Title: EnumCooperationSub.java
 * @Description: 合作子公司
 * @author: zhangry12988
 * @date: 2016-3-2 上午10:10:36
 * @version: V1.0
 */
public enum EnumCompany {
    TG("9580", "河钢唐钢"), HG("9727", "河钢邯钢"), XG("9193", "河钢宣钢"), CG("9196", "河钢承钢"), WG("1932", "河钢舞钢"), SG("8110",
            "河钢石钢"), HB("8493", "河钢衡板");
    private String code;
    private String name;

    private EnumCompany(String code, String name) {
        this.code = code;
        this.name = name;
    }
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
        Map<String, String> enumCooperationSub = new LinkedHashMap<String, String>();
        for (EnumCompany type : EnumCompany.values()) {
            enumCooperationSub.put(type.getCode(), type.getName());
        }
        return enumCooperationSub;
    }
    public static String getByValue(String value) {
        for (EnumCompany item : EnumCompany.values()) {
            if (item.getName().equals(value)) {
                return item.getCode();
            }
        }
        return null;
    }
    public static String getByCode(String code) {
        for (EnumCompany item : EnumCompany.values()) {
            if (item.getCode().equals(code)) {
                return item.getName();
            }else if("7778".equals(code)) {
                return "河钢邯宝";
            }
        }
        return "";
    }
}
