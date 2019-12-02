package com.facedamon.smart.web.controller.monitor;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.quartz.domain.Job;
import com.facedamon.smart.quartz.service.IJobService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 调度任务controller
 * @Author: facedamon
 * @CreateDate: 2018/11/27 11:41
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/27 11:41
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/monitor/job")
public class JobController extends BaseController {

    private String prefix = "monitor/job";

    @Autowired
    private IJobService jobService;

    @RequiresPermissions("monitor:job:view")
    @GetMapping
    public String job() {
        return prefix + "/job";
    }

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Job job) {
        startPage();
        List<Job> jobs = jobService.selectJobList(job);
        return getDataTable(jobs);
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @Log(model = "增加定时任务", businessType = BusinessType.INSERT)
    @RequiresPermissions("monitor:job:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(Job job) {
        job.setCreateBy(ShiroUtils.getLoginName());
        return isSuccess(jobService.insertJobCron(job));
    }

    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap map) {
        map.put("job", jobService.selectJobById(jobId));
        return prefix + "/edit";
    }

    @Log(model = "修改定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(Job job) {
        job.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(jobService.updateJobCron(job));
    }

    @Log(model = "删除定时任务", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(String ids) {
        try {
            jobService.deleteJobByIds(ids);
            return Response.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }

    }

    @Log(model = "修改定时任务状态", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/changeStatus")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response changeStatus(Job job) {
        job.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(jobService.changeStatus(job));
    }

    @Log(model = "运行定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:run")
    @PostMapping("/run")
    @ResponseBody
    public Response run(Job job) {
        return isSuccess(jobService.run(job));
    }
}
