package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ExcelUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.domain.Post;
import com.facedamon.smart.system.service.IPostService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 岗位controller
 * @Author: facedamon
 * @CreateDate: 2018/11/17 下午11:44
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/17 下午11:44
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/post")
public class PostController extends BaseController {

    private String prefix = "system/post";

    @Autowired
    private IPostService postService;

    @RequiresPermissions("system:post:view")
    @GetMapping
    public String post() {
        return prefix + "/post";
    }

    @RequiresPermissions("system:post:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Post post) {
        startPage();
        List<Post> posts = postService.selectPostList(post);
        return getDataTable(posts);
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @Log(model = "新增岗位", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:post:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(Post post) {
        post.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return isSuccess(postService.insertPost(post));
    }

    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, ModelMap map) {
        map.put("post", postService.selectPostById(postId));
        return prefix + "/edit";
    }

    @Log(model = "更新岗位", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:post:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(Post post) {
        post.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(postService.updatePost(post));
    }

    @Log(model = "删除岗位", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:post:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(String ids) {
        try {
            return isSuccess(postService.deletePostByIds(ids));
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    @Log(model = "导出岗位信息", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:post:export")
    @PostMapping("/export")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response export(Post post) {
        List<Post> posts = postService.selectPostList(post);
        ExcelUtils utils = new ExcelUtils<Post>(Post.class);
        return utils.export(posts, "post");
    }

    @PostMapping("/checkPostNameUnique")
    @ResponseBody
    public String checkPostNameUnique(Post post) {
        return postService.checkPostNameUnique(post);
    }

    @PostMapping("/checkPostCodeUnique")
    @ResponseBody
    public String checkPostCodeUnique(Post post) {
        return postService.checkPostCodeUnique(post);
    }

}
