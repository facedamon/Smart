package com.facedamon.smart.core.web.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
* @Description:    表格分页数据
* @Author:         facedamon
* @CreateDate:     2018/8/15 15:18
* @UpdateUser:     facedamon
* @UpdateDate:     2018/8/15 15:18
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Data
@Builder
public class TableDataInfo {
    private long total;
    private List<?> rows;
    private int code;


}
