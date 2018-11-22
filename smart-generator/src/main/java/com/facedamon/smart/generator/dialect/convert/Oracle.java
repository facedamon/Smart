package com.facedamon.smart.generator.dialect.convert;

import java.util.HashMap;

/**
 * @Description:    
 * @Author:         facedamon
 * @CreateDate:     2018/11/20 15:31
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/20 15:31
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class Oracle extends HashMap<String,String> implements Dialect {

    public Oracle(){
        //TODO
    }

    @Override
    public String getJavaType(String dbType) {
        return this.get(dbType);
    }
}
