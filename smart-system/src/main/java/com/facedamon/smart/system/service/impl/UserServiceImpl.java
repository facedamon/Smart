package com.facedamon.smart.system.service.impl;

import com.facedamon.smart.common.annotation.DataScope;
import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.support.Convert;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.system.domain.*;
import com.facedamon.smart.system.mapper.*;
import com.facedamon.smart.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: user service
 * @Author: facedamon
 * @CreateDate: 2018/10/2 下午2:56
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/2 下午2:56
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserPostMapper userPostMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 根据条件分页查询用户
     *
     * @param user
     * @return
     */
    @Override
    @DataScope(tableAlias = "u")
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过登录名查询用户
     *
     * @param loginName
     * @return
     */
    @Override
    public User selectUserByLoginName(String loginName) {
        User user = userMapper.selectUserByLoginName(loginName);
        if (null != user && user.getDeptId() != null) {
            user.setDept(deptMapper.selectDeptById(user.getDeptId()));
        }
        return user;
    }

    /**
     * 通过电话查询用户
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public User selectUserByPhoneNumber(String phoneNumber) {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过email查询用户
     *
     * @param email
     * @return
     */
    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    /**
     * 通过userId查询用户
     * @param userId
     * @return
     */
    @Override
    public User selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public int deleteUserById(Long userId) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    @Override
    public int deleteUserByIds(String ids) throws Exception {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long id : userIds) {
            if (User.isAdmin(id)) {
                throw new Exception("不允许删除管理员账户");
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {

        int rows = userMapper.insertUser(user);
        insertUserPost(user);
        insertUserRole(user);

        return rows;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        Long userId = user.getUserId();

        userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(user);

        userPostMapper.deleteUserPostByUserId(userId);
        insertUserPost(user);
        return userMapper.updateUser(user);
    }

    /**
     * 更新用户详细信息
     *
     * @param user
     * @return
     */
    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @Override
    public int resetUserPwd(User user) {
        return updateUserInfo(user);
    }

    /**
     * 新增用户角色信息
     *
     * @param user
     * @return
     */
    public void insertUserRole(User user) {
        List<UserRole> userRoles = new ArrayList<>();
        for (Long roleId : user.getRoleIds()) {
            userRoles.add(UserRole.builder().roleId(roleId).userId(user.getUserId()).build());
        }
        if (!userRoles.isEmpty()) {
            userRoleMapper.batchUserRole(userRoles);
        }
    }

    /**
     * 新增用户岗位信息
     *
     * @param user
     * @return
     */
    public void insertUserPost(User user) {
        List<UserPost> userPosts = new ArrayList<>();
        for (Long postId : user.getPostIds()) {
            userPosts.add(UserPost.builder().postId(postId).userId(user.getUserId()).build());
        }
        if (!userPosts.isEmpty()) {
            userPostMapper.batchUserPost(userPosts);
        }
    }

    /**
     * 校验登录名是否唯一
     *
     * @param loginName
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName) {
        return userMapper.checkLoginNameUnique(loginName) > 0 ? Constants.USER_NAME_NOT_UNIQUE.getValue()
                : Constants.USER_NAME_UNIQUE.getValue();
    }

    /**
     * 校验用户电话是否唯一
     *
     * @param user
     * @return
     */
    @Override
    public String checkPhoneUnique(User user) {
        Long userId = user.getUserId() == null ? -1L : user.getUserId();
        User next = userMapper.checkPhoneUnique(user.getPhonenumber());
        return (null != next && next.getUserId().longValue() != userId.longValue()) ? Constants.USER_PHONE_NOT_UNIQUE.getValue()
                : Constants.USER_PHONE_UNIQUE.getValue();
    }

    /**
     * 校验用户email唯一
     *
     * @param user
     * @return
     */
    @Override
    public String checkEmailUnique(User user) {
        Long userId = user.getUserId() == null ? -1L : user.getUserId();
        User next = userMapper.checkEmailUnique(user.getEmail());
        return (null != next && next.getUserId().longValue() != userId.longValue()) ? Constants.USER_EMAIL_NOT_UNIQUE.getValue()
                : Constants.USER_EMAIl_UNIQUE.getValue();
    }

    /**
     * 查询用户所属角色组
     *
     * @param userId
     * @return
     */
    @Override
    public String selectUserRoleGroup(Long userId) {
        List<Role> roles = roleMapper.selectRolesByUserId(userId);
        StringBuffer sb = new StringBuffer();
        for (Role role : roles) {
            sb.append(role.getRoleName() + ",");
        }
        if (StringUtils.isNotBlank(sb.toString())) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userId
     * @return
     */
    @Override
    public String selectUserPostGroup(Long userId) {
        List<Post> posts = postMapper.selectPostsByUserId(userId);
        StringBuffer sb = new StringBuffer();
        for (Post post : posts){
            sb.append(post.getPostName() + ",");
        }
        if (StringUtils.isNotBlank(sb.toString())){
            return sb.substring(0,sb.length() - 1);
        }
        return sb.toString();
    }
}