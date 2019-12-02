package com.facedamon.smart.system.mapper;

import com.facedamon.smart.system.domain.DictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 字典数据mapper
 * @Author: facedamon
 * @CreateDate: 2018/10/29 17:06
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 17:06
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface DictDataMapper {

    /**
     * 根据条件分页查询字典数据
     *
     * @param DictData 字典数据信息
     * @return 字典数据集合信息
     */
    List<DictData> selectDictDataList(DictData DictData);

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<DictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    DictData selectDictDataById(Long dictCode);

    /**
     * 查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据
     */
    int countDictDataByType(String dictType);

    /**
     * 通过字典ID删除字典数据信息
     *
     * @param dictCode 字典数据ID
     * @return 结果
     */
    int deleteDictDataById(Long dictCode);

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteDictDataByIds(String[] ids);

    /**
     * 新增字典数据信息
     *
     * @param DictData 字典数据信息
     * @return 结果
     */
    int insertDictData(DictData DictData);

    /**
     * 修改字典数据信息
     *
     * @param DictData 字典数据信息
     * @return 结果
     */
    int updateDictData(DictData DictData);

    /**
     * 同步修改字典类型
     *
     * @param oldDictType 旧字典类型
     * @param newDictType 新旧字典类型
     * @return 结果
     */
    int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}
