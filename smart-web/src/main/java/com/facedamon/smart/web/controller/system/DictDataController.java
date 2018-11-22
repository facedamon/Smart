package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ExcelUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.domain.DictData;
import com.facedamon.smart.system.service.IDictDataService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 字典数据controller
 * @Author: facedamon
 * @CreateDate: 2018/11/18 下午2:30
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/18 下午2:30
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/dict/data")
public class DictDataController extends BaseController {

    private String prefix = "system/dict/data";

    @Autowired
    public IDictDataService dictDataService;

    @RequiresPermissions("system:dict:view")
    @GetMapping
    public String data() {
        return prefix + "/data";
    }

    @RequiresPermissions("system:dict:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DictData dictData) {
        startPage();
        List<DictData> dictDatas = dictDataService.selectDictDataList(dictData);
        return getDataTable(dictDatas);
    }

    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, ModelMap map) {
        map.put("dictType", dictType);
        return prefix + "/add";
    }

    @Log(model = "新增字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(DictData dictData) {
        dictData.setCreateBy(ShiroUtils.getLoginName());
        return isSuccess(dictDataService.insertDictData(dictData));
    }

    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, ModelMap map) {
        map.put("dict", dictDataService.selectDictDataById(dictCode));
        return prefix + "/edit";
    }

    @Log(model = "更新字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(DictData dictData) {
        dictData.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(dictDataService.updateDictData(dictData));
    }

    @Log(model = "删除字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(String ids) {
        return isSuccess(dictDataService.deleteDictDataByIds(ids));
    }

    @Log(model = "导出字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response export(DictData dictData) {
        List<DictData> dictDataList = dictDataService.selectDictDataList(dictData);
        ExcelUtils utils = new ExcelUtils<DictData>(DictData.class);
        return utils.export(dictDataList, "dictData");
    }
}
