package com.facedamon.smart.framework.web.page;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.framework.util.ServletUtils;

/**
 * @Description:
 * @Author: facedamon
 * @CreateDate: 2018/8/15 14:04
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/15 14:04
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class TableSupport {

    private static PageDomain getPageDomain() {
        PageDomain pageDomain = PageDomain.builder()
                .pageNum(Integer.valueOf(ServletUtils.getParameter(Constants.PAGE_NUM.getValue())))
                .pageSize(Integer.valueOf(ServletUtils.getParameter(Constants.PAGE_SIZE.getValue())))
                .orderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN.getValue()))
                .isAsc(ServletUtils.getParameter(Constants.IS_ASC.getValue()))
                .build();
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}