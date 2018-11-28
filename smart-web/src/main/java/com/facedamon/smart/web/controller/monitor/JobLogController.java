package com.facedamon.smart.web.controller.monitor;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ExcelUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.quartz.domain.JobLog;
import com.facedamon.smart.quartz.service.IJobLogService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.facedamon.smart.common.base.Response.success;

/**
 * @Description:    定时任务日志 controller
 * @Author:         facedamon
 * @CreateDate:     2018/11/28 14:03
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/28 14:03
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class JobLogController extends BaseController
{
    private String prefix = "monitor/job";

    @Autowired
    private IJobLogService jobLogService;

    @RequiresPermissions("monitor:job:view")
    @GetMapping()
    public String jobLog()
    {
        return prefix + "/jobLog";
    }

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JobLog jobLog)
    {
        startPage();
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
        return getDataTable(list);
    }

    @Log(model = "导出调度日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:job:export")
    @PostMapping("/export")
    @ResponseBody
    public Response export(JobLog jobLog)
    {
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
        ExcelUtils<JobLog> util = new ExcelUtils<JobLog>(JobLog.class);
        return util.export(list, "jobLog");
    }

    @Log(model = "删除调度日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Response remove(String ids)
    {
        return isSuccess(jobLogService.deleteJobLogByIds(ids));
    }
    
    @Log(model = "清空调度日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/clean")
    @ResponseBody
    public Response clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}