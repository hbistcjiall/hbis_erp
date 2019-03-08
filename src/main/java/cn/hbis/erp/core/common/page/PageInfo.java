
package cn.hbis.erp.core.common.page;

import lombok.Data;

import java.util.List;

/**
 * 分页结果的封装(for Table)
 *
 *
 */
@Data
public class PageInfo {

    private Integer code = 0;

    private String msg = "请求成功";

    private List data;

    private long count;

    private long pageSize;

    private long pageNo;

}
