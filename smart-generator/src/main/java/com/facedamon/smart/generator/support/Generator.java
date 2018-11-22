package com.facedamon.smart.generator.support;


import com.facedamon.smart.common.config.SmartConfig;
import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.utils.DateUtils;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.generator.constants.vm.TemplateVM;
import com.facedamon.smart.generator.dialect.convert.Dialect;
import com.facedamon.smart.generator.domain.ColumnInfo;
import com.facedamon.smart.generator.domain.TableInfo;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:    generator工具
 * @Author:         facedamon
 * @CreateDate:     2018/11/20 10:55
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/20 10:55
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Component
public class Generator {

    @Autowired
    private Dialect dialect;

    private static Generator generator;

    @PostConstruct
    public void init(){
        generator = this;
    }



    /**
     * 转换列信息
     * @param columnInfos
     * @return
     */
    public static List<ColumnInfo> transColumns(List<ColumnInfo> columnInfos){
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        for (ColumnInfo columnInfo : columnInfos){
            String attrName = StringUtils.under2Camel2UpperCase(columnInfo.getColumnName());
            columnInfo.setAttrName(attrName);
            columnInfo.setAttrNameLower(StringUtils.uncapitalize(attrName));

            String javaType = generator.dialect.getJavaType(columnInfo.getDataType());
            columnInfo.setJavaType(javaType);
            columnInfoList.add(columnInfo);
        }
        return columnInfoList;
    }

    /**
     * 表名转换
     * @param tableName
     * @return
     */
    public static String table2Java(String tableName){
        if (Constants.AUTO_REMOVE_PRE.getValue().equals(SmartConfig.INSTANCE.getAutoRemovePre())){
            tableName = tableName.substring(tableName.indexOf("_") + 1);
        }
        if (StringUtils.isNotBlank(SmartConfig.INSTANCE.getTablePrefix())){
            tableName = tableName.replace(SmartConfig.INSTANCE.getTablePrefix(),"");
        }
        return StringUtils.under2Camel2UpperCase(tableName);
    }

    public static List<String> getTemplates(){
        return new ArrayList<>(Arrays.asList(
                TemplateVM.TemplateDir.DOMAIN.getValue(),
                TemplateVM.TemplateDir.MAPPER.getValue(),
                TemplateVM.TemplateDir.SERVICE.getValue(),
                TemplateVM.TemplateDir.SERVICEIMPL.getValue(),
                TemplateVM.TemplateDir.CONTROLLER.getValue(),
                TemplateVM.TemplateDir.MAPPER_XML.getValue(),
                TemplateVM.TemplateDir.HTML_ADD.getValue(),
                TemplateVM.TemplateDir.HTML_LIST.getValue(),
                TemplateVM.TemplateDir.HTML_EDIT.getValue()
        ));
    }

    /**
     * 获取文件路径+名
     * @param template
     * @param tableInfo
     * @param moduleName
     * @return
     */
    public static String getFileName(String template, TableInfo tableInfo,String moduleName){
        String classname = tableInfo.getClassNameLower();
        String className = tableInfo.getClassName();
        String javaPath = SmartConfig.INSTANCE.getProjectPath() + "/" + moduleName + "/";
        String mybatisPath = SmartConfig.INSTANCE.getMybatisPath() + "/" + moduleName + "/" + className;
        String htmlPath = SmartConfig.INSTANCE.getTemplatesPath() + "/" + moduleName + "/" + classname;

        if (template.contains(TemplateVM.TemplateFile.DOMAIN.getValue())) {
            return javaPath + "domain/" + className + ".java";
        }
        if (template.contains(TemplateVM.TemplateFile.MAPPER.getValue())) {
            return javaPath + "mapper/" + className + "Mapper.java";
        }
        if (template.contains(TemplateVM.TemplateFile.SERVICE.getValue())) {
            return javaPath + "service/I" + className + "Service.java";
        }
        if (template.contains(TemplateVM.TemplateFile.SERVICEIMPL.getValue())) {
            return javaPath + "service/" + className + "ServiceImpl.java";
        }
        if (template.contains(TemplateVM.TemplateFile.CONTROLLER.getValue())) {
            return javaPath + "controller/" + className + "Controller.java";
        }
        if (template.contains(TemplateVM.TemplateFile.MAPPER_XML.getValue())) {
            return mybatisPath + "Mapper.xml";
        }

        if (template.contains(TemplateVM.TemplateFile.HTML_LIST.getValue())) {
            return htmlPath + "/" + classname + ".html";
        }
        if (template.contains(TemplateVM.TemplateFile.HTML_ADD.getValue())) {
            return htmlPath + "/" + "add.html";
        }
        if (template.contains(TemplateVM.TemplateFile.HTML_EDIT.getValue())) {
            return htmlPath + "/" + "edit.html";
        }
        return null;
    }

    /**
     * 获取模块名
     * @param packageName
     * @return
     */
    public static String getModuleName(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        String moduleName = StringUtils.substring(packageName, lastIndex + 1, nameLength);
        return moduleName;
    }

    /**
     * 获取模板信息
     * Java对象数据传递到模板文件vm
     * @param tableInfo
     * @return
     */
    public static VelocityContext getVelocityContext(TableInfo tableInfo){
        VelocityContext velocityContext = new VelocityContext();
        String packageName = SmartConfig.INSTANCE.getPackageName();
        velocityContext.put("tableName",tableInfo.getTableName());
        velocityContext.put("tableComment",tableInfo.getTableComment());
        velocityContext.put("primaryKey",tableInfo.getPrimaryKey());
        velocityContext.put("className",tableInfo.getClassName());
        velocityContext.put("classNameLower",tableInfo.getClassNameLower());
        velocityContext.put("moduleName",getModuleName(packageName));
        velocityContext.put("columns",tableInfo.getColumnInfos());
        velocityContext.put("package",packageName);
        velocityContext.put("author",SmartConfig.INSTANCE.getAuthor());
        velocityContext.put("datetime",DateUtils.getDate());
        return velocityContext;
    }

}
