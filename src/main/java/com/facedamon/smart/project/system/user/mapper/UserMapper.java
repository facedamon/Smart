package com.facedamon.smart.project.system.user.mapper;

import com.facedamon.smart.project.system.user.domain.User;

import java.util.List;

/**
* @Description:    用户mapper
* @Author:         facedamon
* @CreateDate:     2018/8/16 10:28
* @UpdateUser:     facedamon
* @UpdateDate:     2018/8/16 10:28
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface UserMapper {
    /**
     * 根据条件分页查询用户
     * @param user 用户信息
     * @return 用户列表
     */
    public List<User> selectUserList(User user);

    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    public User selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询
     * @param phoneNumber
     * @return
     */
    public User selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过email查询
     * @param email
     * @return
     */
    public User selectUserByEmail(String email);

    /**
     * 通过ID查询用户
     * @param userId
     * @return
     */
    public User selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     * @param userId
     * @return
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    public int deleteUserByIds(Long[] ids);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 校验用户名称是否唯一
     * @param loginName
     * @return
     */
    public int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     * @param phoneNumber
     * @return
     */
    public int checkPhoneNumberUnique(String phoneNumber);

    /**
     * 校验email是否唯一
     * @param email
     * @return
     */
    public int checkEmailUnique(String email);
}
