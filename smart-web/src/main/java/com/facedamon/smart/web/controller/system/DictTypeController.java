package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ExcelUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.doamin.DictType;
import com.facedamon.smart.system.service.IDictTypeService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 字典类型controller
 * @Author: facedamon
 * @CreateDate: 2018/11/18 下午2:03
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/18 下午2:03
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/dict")
public class DictTypeController extends BaseController {

    private String prefix = "system/dict/type";

    @Autowired
    private IDictTypeService dictTypeService;

    @RequiresPermissions("system:dict:view")
    @GetMapping
    public String type() {
        return prefix + "/type";
    }

    @RequiresPermissions("system:dict:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DictType dictType) {
        startPage();
        List<DictType> dictTypes = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(dictTypes);
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @Log(model = "新增字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(DictType dictType) {
        dictType.setCreateBy(ShiroUtils.getLoginName());
        return isSuccess(dictTypeService.insertDictType(dictType));
    }

    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Long dictId, ModelMap map) {
        map.put("dict", dictTypeService.selectDictTypeById(dictId));
        return prefix + "/edit";
    }

    @Log(model = "修改字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(DictType dictType) {
        dictType.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(dictTypeService.updateDictType(dictType));
    }

    @Log(model = "删除字典类型", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(String ids) {
        try {
            return isSuccess(dictTypeService.deleteDictTypeByIds(ids));
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    @Log(model = "导出字典类型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response export(DictType dictType) {
        List<DictType> dictTypes = dictTypeService.selectDictTypeList(dictType);
        ExcelUtils utils = new ExcelUtils<DictType>(DictType.class);
        return utils.export(dictTypes, "dictType");
    }

    @PostMapping("/checkDictTypeUnique")
    @ResponseBody
    public String checkDictTypeUnique(DictType dictType) {
        return dictTypeService.checkDictTypeUnique(dictType);
    }

    /**
     * 查询字典类型数据
     *
     * @param dictId
     * @param map
     * @return
     */
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") Long dictId, ModelMap map) {
        map.put("dict", dictTypeService.selectDictTypeById(dictId));
        map.put("dictList", dictTypeService.selectDictTypeAll());
        return "system/dict/data/data";
    }
}
