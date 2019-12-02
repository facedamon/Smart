package com.facedamon.smart.system.service;

import com.facedamon.smart.system.domain.User;

import java.util.List;

/**
 * @Description: user service
 * @Author: facedamon
 * @CreateDate: 2018/10/2 下午2:41
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/2 下午2:41
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IUserService {

    /**
     * 根据条件分页查询用户
     *
     * @param user
     * @return
     */
    List<User> selectUserList(User user);

    /**
     * 通过登录名查询用户
     *
     * @param loginName
     * @return
     */
    User selectUserByLoginName(String loginName);

    /**
     * 通过电话查询用户
     *
     * @param phoneNumber
     * @return
     */
    User selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过email查询用户
     *
     * @param email
     * @return
     */
    User selectUserByEmail(String email);

    /**
     * 通过Id查询用户
     *
     * @param userId
     * @return
     */
    User selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     *
     * @param userId
     * @return
     */
    int deleteUserById(Long userId);

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     * @throws Exception
     */
    int deleteUserByIds(String ids) throws Exception;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 修改用户详细信息
     *
     * @param user
     * @return
     */
    int updateUserInfo(User user);

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    int resetUserPwd(User user);

    /**
     * 校验用户登录名唯一
     *
     * @param loginName
     * @return
     */
    String checkLoginNameUnique(String loginName);

    /**
     * 校验电话唯一
     *
     * @param user
     * @return
     */
    String checkPhoneUnique(User user);

    /**
     * 校验email唯一
     *
     * @param user
     * @return
     */
    String checkEmailUnique(User user);

    /**
     * 查询用户所属角色组
     *
     * @param userId
     * @return
     */
    String selectUserRoleGroup(Long userId);

    /**
     * 查询用户所属岗位组
     *
     * @param userId
     * @return
     */
    String selectUserPostGroup(Long userId);
}