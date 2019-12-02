package com.facedamon.smart.framework.web.service;

import com.facedamon.smart.system.domain.DictData;
import com.facedamon.smart.system.service.IDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: JS调用thymeleaf实现字典读取
 * @Author: facedamon
 * @CreateDate: 2018/10/29 17:19
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 17:19
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service("dict")
public class DictService {

    @Autowired
    private IDictDataService dictDataService;

    /**
     * 根据字典类型获取数据信息
     *
     * @param dictType
     * @return
     */
    public List<DictData> selectDataByType(String dictType) {
        return dictDataService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值获取字典数据信息
     *
     * @param dictType
     * @param dictValue
     * @return
     */
    public String selectLabel(String dictType, String dictValue) {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
