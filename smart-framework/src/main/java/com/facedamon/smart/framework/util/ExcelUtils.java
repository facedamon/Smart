package com.facedamon.smart.framework.util;

import com.facedamon.smart.common.annotation.Excel;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.config.SmartConfig;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.framework.web.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:    Excel 工具 包含数据库链接
 * @Author:         facedamon
 * @CreateDate:     2018/11/8 12:39
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/8 12:39
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Slf4j
public class ExcelUtils<T> {

    private Class<T> clazz;

    public ExcelUtils(Class<T> clazz){
        this.clazz = clazz;
    }


    /**
     * 获取下载路径
     * @param fileName
     * @return
     */
    public String getAbsouluteFile(String fileName){
        String downloadPath = SmartConfig.INSTANCE.getDownloadPath() + fileName;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()){
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 编码文件名
     * @param fileName
     * @return
     */
    public String encodingFileName(String fileName){
        return fileName + ".xls";
    }

    /**
     * 导出数据
     * @param list  数据集合
     * @param sheetName 工作表name
     * @return
     */
    public Response export(List<T> list,String sheetName){
        OutputStream out = null;
        HSSFWorkbook workbook = null;

        try {
            /**
             * 得到所有预定义字段
             */
            Field[] allFields = clazz.getDeclaredFields();
            List<Field> fields = new ArrayList<>();

            /**
             * 将声明@Excel的字段放入list中
             */
            for (Field field : allFields){
                if (field.isAnnotationPresent(Excel.class)){
                    fields.add(field);
                }
            }

            workbook = new HSSFWorkbook();
            /**
             * 每个sheet最多有65536行数据
             */
            int sheetSize = 65536;
            /**
             * sheet number
             */
            double sheetNo = Math.ceil(list.size() / sheetSize);

            for (int index = 0; index <= sheetNo; index++){
                HSSFSheet sheet = workbook.createSheet();
                if (0 == sheetNo){
                    workbook.setSheetName(index,sheetName);
                }else{
                    workbook.setSheetName(index,sheetName + index);
                }

                /**
                 * 行对象
                 */
                HSSFRow row;
                /**
                 * 单元格对象
                 */
                HSSFCell cell;

                /*---------------------开始写入标题-----------------*/

                row = sheet.createRow(0);
                for (int col = 0; col < fields.size(); col++){
                    Field field = fields.get(col);
                    Excel attr = field.getAnnotation(Excel.class);
                    cell = row.createCell(col);
                    cell.setCellType(CellType.STRING);
                    /**
                     * 设置 水平左右居中
                     */
                    HSSFCellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                    /**
                     * 警告
                     */
                    if (attr.name().indexOf("注:") >= 0){
                        HSSFFont font = workbook.createFont();
                        font.setColor(HSSFFont.COLOR_RED);
                        cellStyle.setFont(font);
                        /**
                         * 设置背景色
                         */
                        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
                        /**
                         * 设置列宽
                         */
                        sheet.setColumnWidth(col,6000);
                    }else{
                        HSSFFont font = workbook.createFont();
                        /**
                         * 粗体
                         */
                        font.setBold(true);
                        cellStyle.setFont(font);
                        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
                        sheet.setColumnWidth(col,3766);
                    }

                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cellStyle.setWrapText(true);
                    cell.setCellStyle(cellStyle);

                    cell.setCellValue(attr.name());
                    /**
                     * 设置提示信息
                     */
                    if (StringUtils.isNotBlank(attr.prompt())){

                    }
                    /**
                     * 设置只读模式
                     */
                    if (attr.combo().length > 0){

                    }
                }
                /*---------------------开始写入数据-----------------*/
                /**
                 * 每页显示sheetSize条记录，当前第index页(从0开始)的索引就是index * sheetSize
                 */
                int startNo = index * sheetSize;
                int endNo = Math.min(list.size(),startNo + sheetSize);
                HSSFCellStyle style = workbook.createCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER);
                style.setVerticalAlignment(VerticalAlignment.CENTER);

                for (int i = startNo; i < endNo; i++){
                    /**
                     * 一次创建一行
                     */
                    row = sheet.createRow(i - startNo + 1);
                    T vo = list.get(i);
                    for (int f = 0;  f < fields.size(); f++){
                        Field field = fields.get(f);
                        field.setAccessible(true);
                        Excel attr = field.getAnnotation(Excel.class);

                        try {
                            if (attr.containsData()){
                                cell = row.createCell(f);
                                cell.setCellStyle(style);

                                if (attr.isDict()){
                                    DictService dictService = ApplicationHolder.getBean(DictService.class);
                                    String dictValue = dictService.selectLabel(attr.dictType(),String.valueOf(field.get(vo)));
                                    field.set(vo,dictValue);
                                }

                                try {
                                    if (String.valueOf(field.get(vo)).length() > 15){
                                        throw new Exception("长度超过15位就不转为数字");
                                    }

                                    BigDecimal decimal = new BigDecimal(String.valueOf(field.get(vo)));
                                    cell.setCellType(CellType.NUMERIC);
                                    cell.setCellValue(decimal.doubleValue());

                                }catch (Exception e){
                                    cell.setCellType(CellType.STRING);
                                    if (null == vo || null == field.get(vo)){
                                        cell.setCellValue("");
                                    }else{
                                        cell.setCellValue(String.valueOf(field.get(vo)));
                                    }
                                }
                            }
                        }catch(Exception e){
                            log.error("导出Excel失败,{}",e.getMessage());
                        }
                    }
                }
            }
            String fileName = encodingFileName(sheetName);
            out = new FileOutputStream(getAbsouluteFile(fileName));
            workbook.write(out);
            return Response.success(fileName);
        }catch(Exception e){
            log.error("导出excel失败，{}",e.getMessage());
            return Response.error("导出Excel失败,请联系管理员");
        }finally {
            if (null != workbook){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
