package com.facedamon.smart.project.system.dept.service;

import com.facedamon.smart.project.system.dept.domain.Dept;
import com.facedamon.smart.project.system.role.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * @Description: dept service
 * @Author: facedamon
 * @CreateDate: 2018/10/9 16:09
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/9 16:09
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IDeptService {

    /**
     * 查询部门管理数据
     *
     * @param dept
     * @return
     */
    List<Dept> selectDeptList(Dept dept);

    /**
     * 查询部门树
     *
     * @return
     */
    List<Map<String, Object>> selectDeptTree();

    /**
     * 根据角色ID查询菜单
     *
     * @param role
     * @return
     */
    List<Map<String, Object>> roleDeptTreeData(Role role);

    /**
     * 查询部门人数
     *
     * @param parentId
     * @return
     */
    int selectDeptCount(Long parentId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId
     * @return
     */
    boolean checkDeptExistUser(Long deptId);

    /**
     * 删除部门关系信息
     *
     * @param deptId
     * @return
     */
    int deleteDeptById(Long deptId);

    /**
     * 新增保存部门信息
     *
     * @param dept
     * @return
     */
    int insertDept(Dept dept);

    /**
     * 修改保存部门信息
     *
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId
     * @return
     */
    Dept selectDeptById(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param dept
     * @return
     */
    String checkDeptNameUnique(Dept dept);
}
