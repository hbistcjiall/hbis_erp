package cn.hbis.erp.core.common.constant.dictmap;

import cn.hbis.erp.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 日志的字典
 *
 */
public class LogDict extends AbstractDictMap {

    @Override
    public void init() {
        put("description", "备注");
    }

    @Override
    protected void initBeWrapped() {

    }
}
