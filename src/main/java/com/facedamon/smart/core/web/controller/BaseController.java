package com.facedamon.smart.core.web.controller;

import com.facedamon.smart.core.web.page.PageDomain;
import com.facedamon.smart.core.web.page.TableDataInfo;
import com.facedamon.smart.core.web.page.TableSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: web 通用controller
 * @Author: facedamon
 * @CreateDate: 2018/8/15 15:20
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/15 15:20
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class BaseController {

    /**
     * 封装分页数据
     */
    protected static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null != pageDomain) {
            Integer pageNum = pageDomain.getPageNum();
            Integer pageSize = pageDomain.getPageSize();
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 响应请求分页数据
     */
    protected TableDataInfo getDataTable(List<?> list) {
        return TableDataInfo.builder().code(0)
                .rows(list)
                .total(new PageInfo<>(list).getTotal())
                .build();
    }

    /**
     * Shiro
     */

}
