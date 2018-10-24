package com.facedamon.smart.core.web.page;

import com.facedamon.smart.common.utils.StringUtils;
import lombok.Builder;
import lombok.Data;

/**
 * @Description: 分页对象
 * @Author: facedamon
 * @CreateDate: 2018/8/15 13:01
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/15 13:01
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
public class PageDomain {
    /**
     * 当前记录起始索引
     */
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序方式
     */
    private String isAsc;

    public String getOrderBy() {
        if (StringUtils.isBlank(orderByColumn)) {
            return StringUtils.EMPTY;
        }
        return StringUtils.camel2Under(orderByColumn) + " " + isAsc;
    }
}
