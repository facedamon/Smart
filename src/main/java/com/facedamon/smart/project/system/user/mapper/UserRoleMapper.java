package com.facedamon.smart.project.system.user.mapper;

import com.facedamon.smart.project.system.user.domain.UserRole;

import java.util.List;

/**
 * @Description:    用户-角色关系mapper N->1
 * @Author:         facedamon
 * @CreateDate:     2018/10/1 上午5:51
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/1 上午5:51
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface UserRoleMapper {

    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserRole(Long[] ids);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int countUserRoleByRoleId(Long roleId);

    /**
     * 批量新增用户角色信息
     *
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    int batchUserRole(List<UserRole> userRoleList);
}
