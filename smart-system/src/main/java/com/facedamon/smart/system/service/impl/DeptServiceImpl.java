package com.facedamon.smart.system.service.impl;

import com.facedamon.smart.common.annotation.DataScope;
import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.system.Ancestor;
import com.facedamon.smart.system.domain.Dept;
import com.facedamon.smart.system.domain.Role;
import com.facedamon.smart.system.mapper.DeptMapper;
import com.facedamon.smart.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: dept service
 * @Author: facedamon
 * @CreateDate: 2018/10/9 16:17
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/9 16:17
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询部门管理数据
     *
     * @param dept
     * @return
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<Dept> selectDeptList(Dept dept) {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 查询所有部门树
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectDeptTree() {
        /**
         * 所有菜单
         */
        List<Dept> deptList = selectDeptList(Dept.builder().build());
        return getTrees(deptList, false, null);
    }

    /**
     * 根据角色ID查询部门信息
     *
     * @param role
     * @return
     */
    @Override
    public List<Map<String, Object>> roleDeptTreeData(Role role) {
        Long roleId = role.getRoleId();
        /**
         * 所有菜单
         */
        List<Dept> deptList = selectDeptList(Dept.builder().build());

        if (null != roleId) {
            /**
             * deptId + deptName
             */
            List<String> roleDeptList = deptMapper.selectRoleDeptTree(roleId);
            return getTrees(deptList, true, roleDeptList);
        } else {
            return getTrees(deptList, false, null);
        }
    }

    /**
     * 查询部门人数
     *
     * @param parentId
     * @return
     */
    @Override
    public int selectDeptCount(Long parentId) {
        return deptMapper.selectDeptCount(Dept.builder().parentId(parentId).build());
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId
     * @return
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return deptMapper.checkDeptExistUser(deptId) > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId
     * @return
     */
    @Override
    public int deleteDeptById(Long deptId) {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 新增保存部门信息
     *
     * @param dept
     * @return
     */
    @Override
    public int insertDept(Dept dept) {
        Dept parentDept = deptMapper.selectDeptById(dept.getParentId());
        dept.setAncestors(Ancestor.calculateAncestor(parentDept.getAncestors(), dept.getParentId()));
        return deptMapper.insertDept(dept);
    }

    /**
     * 修改保存部门信息
     *
     * @param dept
     * @return
     */
    @Override
    public int updateDept(Dept dept) {
        Dept parentDept = deptMapper.selectDeptById(dept.getParentId());
        if (null != parentDept) {
            String ancestors = Ancestor.calculateAncestor(parentDept.getAncestors(), dept.getParentId());
            dept.setAncestors(ancestors);
            updateDeptChildren(dept.getDeptId(), ancestors);
        }
        return deptMapper.updateDept(dept);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    部门ID
     * @param ancestors 元素列表
     */
    private void updateDeptChildren(Long deptId, String ancestors) {
        List<Dept> childrens = deptMapper.selectDeptList(Dept.builder().parentId(deptId).build());

        for (Dept children : childrens) {
            children.setAncestors(Ancestor.calculateAncestor(ancestors, deptId));
        }
        if (!childrens.isEmpty()) {
            deptMapper.updateDeptChildren(childrens);
        }
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId
     * @return
     */
    @Override
    public Dept selectDeptById(Long deptId) {
        return deptMapper.selectDeptById(deptId);
    }

    /**
     * 检查部门名称是否唯一
     *
     * @param dept
     * @return
     */
    @Override
    public String checkDeptNameUnique(Dept dept) {
        Long deptId = dept.getParentId() == null ? -1L : dept.getDeptId();
        Dept next = deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());

        if (null != next && next.getDeptId().longValue() != deptId.longValue()) {
            return Constants.DEPT_NAME_NOT_UNIQUE.getValue();
        }
        return Constants.DEPT_NAME_UNIQUE.getValue();
    }

    /**
     * 对象转部门树
     *
     * @param deptList     部门列表
     * @param isCheck      是否需要选中
     * @param roleDeptList 已存在的角色的菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<Dept> deptList, boolean isCheck, List<String> roleDeptList) {
        List<Map<String, Object>> trees = new ArrayList<>();
        for (Dept dept : deptList) {
            if (Constants.DEPT_NORMAL.getValue().equals(dept.getStatus())) {
                Map<String, Object> deptMap = new HashMap<>(5);
                deptMap.put("id", dept.getDeptId());
                deptMap.put("pId", dept.getParentId());
                deptMap.put("name", dept.getDeptName());
                deptMap.put("title", dept.getDeptName());

                if (isCheck) {
                    deptMap.put("checked", roleDeptList.contains(dept.getDeptId() + dept.getDeptName()));
                } else {
                    deptMap.put("checked", false);
                }
                trees.add(deptMap);
            }
        }
        return trees;
    }
}