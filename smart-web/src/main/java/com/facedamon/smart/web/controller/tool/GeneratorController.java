package com.facedamon.smart.web.controller.tool;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.common.support.Convert;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.generator.domain.TableInfo;
import com.facedamon.smart.generator.service.IGeneratorService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 逆向工程controller
 * @Author: facedamon
 * @CreateDate: 2018/11/21 14:37
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/21 14:37
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/tool/gen")
public class GeneratorController extends BaseController {

    private String prefix = "tool/gen";

    @Autowired
    private IGeneratorService generatorService;

    @RequiresPermissions("tool:gen:view")
    @GetMapping
    public String gen() {
        return prefix + "/gen";
    }

    @RequiresPermissions("tool:gen:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TableInfo tableInfo) {
        startPage();
        List<TableInfo> tableInfos = generatorService.selectTableList(tableInfo);
        return getDataTable(tableInfos);
    }

    @Log(model = "代码生成", businessType = BusinessType.GENCODE)
    @RequiresPermissions("tool:gen:code")
    @GetMapping("/genCode/{tableName}")
    public void genCode(@PathVariable("tableName") String tableName, HttpServletResponse response) throws IOException {
        byte[] codes = generatorService.generatorCode(tableName);
        setStream(response, codes.length);
        IOUtils.write(codes, response.getOutputStream());
    }

    @Log(model = "批量代码生成", businessType = BusinessType.GENCODE)
    @RequiresPermissions("tool:gen:code")
    @GetMapping("/batchGenCode")
    public void genCode(HttpServletResponse response, String tableNames) throws IOException {
        String[] tables = Convert.toStrArray(tableNames);
        byte[] codes = generatorService.generatorCode(tables);

        setStream(response, codes.length);
        IOUtils.write(codes, response.getOutputStream());
    }

    protected void setStream(HttpServletResponse response, int len) {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=\"smart.zip\"");
        response.addHeader("Content-Length", "" + len);
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
