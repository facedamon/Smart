package com.facedamon.smart.generator.service.impl;

import com.facedamon.smart.common.config.SmartConfig;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.generator.domain.ColumnInfo;
import com.facedamon.smart.generator.domain.TableInfo;
import com.facedamon.smart.generator.mapper.GeneratorMapper;
import com.facedamon.smart.generator.service.IGeneratorService;
import com.facedamon.smart.generator.support.Generator;
import com.facedamon.smart.generator.support.VeloctivyInitializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description:    生成代码service
 * @Author:         facedamon
 * @CreateDate:     2018/11/20 10:35
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/20 10:35
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Service
@Slf4j
public class GeneratorServiceImpl implements IGeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;

    @Override
    public List<TableInfo> selectTableList(TableInfo tableInfo) {
        return generatorMapper.selectTableList(tableInfo);
    }

    /**
     * 生成代码
     * @param tableInfo
     * @param columnInfos
     * @param zip
     */
    protected void generatorCode(TableInfo tableInfo,List<ColumnInfo> columnInfos, ZipOutputStream zip){
        String className = Generator.table2Java(tableInfo.getTableName());
        tableInfo.setClassName(className);
        tableInfo.setClassNameLower(StringUtils.uncapitalize(className));

        tableInfo.setColumnInfos(Generator.transColumns(columnInfos));
        tableInfo.setPrimaryKey(tableInfo.getColumnsLast());

        VeloctivyInitializer.initVelocity();

        String packageName = SmartConfig.INSTANCE.getPackageName();
        String moduleName = Generator.getModuleName(packageName);

        VelocityContext context = Generator.getVelocityContext(tableInfo);

        /**
         * 获取模板列表
         */
        List<String> templates = Generator.getTemplates();
        for (String template : templates){
            /**
             * 渲染模板
             */
            StringWriter sw = new StringWriter();
            Template template1 = Velocity.getTemplate(template,StandardCharsets.UTF_8.displayName());
            template1.merge(context,sw);
            try {
                /**
                 * 添加到zip
                 */
                zip.putNextEntry(new ZipEntry(Generator.getFileName(template,tableInfo,moduleName)));
                IOUtils.write(sw.toString(),zip,StandardCharsets.UTF_8.displayName());
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            }catch (IOException e){
                log.error("渲染模板失败，表名: " + tableInfo.getTableName(),e);
            }
        }
    }

    /**
     * 生成代码字节码
     * @param tableName
     * @return
     */
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        TableInfo tableInfo = generatorMapper.selectTableByName(tableName);
        List<ColumnInfo> columnInfos = generatorMapper.selectTableColumnsByName(tableName);
        generatorCode(tableInfo,columnInfos,zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码字节码
     * @param tableNames
     * @return
     */
    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName: tableNames){
            TableInfo tableInfo = generatorMapper.selectTableByName(tableName);
            List<ColumnInfo> columnInfos = generatorMapper.selectTableColumnsByName(tableName);
            generatorCode(tableInfo,columnInfos,zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
