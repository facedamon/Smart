package com.facedamon.smart.system.service;

import com.facedamon.smart.system.domain.Post;

import java.util.List;

/**
 * @Description: 岗位信息 service
 * @Author: facedamon
 * @CreateDate: 2018/10/30 17:29
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/30 17:29
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IPostService {

    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    List<Post> selectPostList(Post post);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<Post> selectPostAll();

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    List<Post> selectPostsByUserId(Long userId);

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    Post selectPostById(Long postId);

    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    int deletePostByIds(String ids) throws Exception;

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    int insertPost(Post post);

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    int updatePost(Post post);

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    int countUserPostById(Long postId);

    /**
     * 校验岗位名称
     *
     * @param post 岗位信息
     * @return 结果
     */
    String checkPostNameUnique(Post post);

    /**
     * 校验岗位编码
     *
     * @param post 岗位信息
     * @return 结果
     */
    String checkPostCodeUnique(Post post);
}
