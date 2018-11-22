package com.facedamon.smart.system.mapper;

import com.facedamon.smart.system.domain.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 部门 mapper
 * @Author: facedamon
 * @CreateDate: 2018/10/9 15:54
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/9 15:54
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface DeptMapper {

    /**
     * 查询部门人数
     *
     * @param dept
     * @return
     */
    int selectDeptCount(Dept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId
     * @return
     */
    int checkDeptExistUser(Long deptId);

    /**
     * 查询部门管理数据
     *
     * @param dept
     * @return
     */
    List<Dept> selectDeptList(Dept dept);

    /**
     * 删除部门管理信息
     *
     * @param deptId
     * @return
     */
    int deleteDeptById(Long deptId);

    /**
     * 新增部门信息
     *
     * @param dept
     * @return
     */
    int insertDept(Dept dept);

    /**
     * 修改部门信息
     *
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 修改子元素关系
     *
     * @param depts
     * @return
     */
    int updateDeptChildren(@Param("depts") List<Dept> depts);

    /**
     * 根据部门Id查询信息
     *
     * @param deptId
     * @return
     */
    Dept selectDeptById(Long deptId);

    /**
     * 检查部门名称是否唯一
     *
     * @param deptName
     * @return
     */
    Dept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId
     * @return
     */
    List<String> selectRoleDeptTree(Long roleId);
}