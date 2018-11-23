package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Excel;
import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.domain.Notice;
import com.facedamon.smart.system.service.INoticeService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:    通知公告
 * @Author:         facedamon
 * @CreateDate:     2018/11/23 14:34
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/23 14:34
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping("/system/notice")
public class NoticeController extends BaseController {

    private String prefix = "system/notice";

    @Autowired
    private INoticeService noticeService;

    @RequiresPermissions("system:notice:view")
    @GetMapping
    public String notice(){
        return prefix + "/notice";
    }

    @RequiresPermissions("system:notice:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(Notice notice){
        startPage();
        List<Notice> notices = noticeService.selectNoticeList(notice);
        return getDataTable(notices);
    }

    @GetMapping("/add")
    public String add(){
        return prefix + "/add";
    }

    @Log(model = "新增公告",businessType = BusinessType.INSERT)
    @RequiresPermissions("system:notice:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(Notice notice){
        notice.setCreateBy(ShiroUtils.getLoginName());
        return isSuccess(noticeService.insertNotice(notice));
    }

    @GetMapping("/edit/{noticeId}")
    public String edit(@PathVariable("noticeId") Long noticeId, ModelMap map){
        map.put("notice",noticeService.selectNoticeById(noticeId));
        return prefix + "/edit";
    }

    @Log(model = "更新公告",businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:notice:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(Notice notice){
        notice.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(noticeService.updateNotice(notice));
    }

    @Log(model = "删除公告",businessType = BusinessType.DELETE)
    @RequiresPermissions("system:notice:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(String ids){
        return isSuccess(noticeService.deleteNoticeByIds(ids));
    }
}
