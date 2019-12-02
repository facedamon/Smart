package com.facedamon.smart.system.mapper;

import com.facedamon.smart.system.domain.Notice;

import java.util.List;

/**
 * @Description: 通知公告mapper
 * @Author: facedamon
 * @CreateDate: 2018/11/23 14:44
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/23 14:44
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface NoticeMapper {

    /**
     * 根据ID查询公告信息
     *
     * @param noticeId
     * @return
     */
    Notice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     *
     * @param notice
     * @return
     */
    List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增公告
     *
     * @param notice
     * @return
     */
    int insertNotice(Notice notice);

    /**
     * 更新公告
     *
     * @param notice
     * @return
     */
    int updateNotice(Notice notice);

    /**
     * 批量删除公告
     *
     * @param noticeIds
     * @return
     */
    int deleteNoticeByIds(String[] noticeIds);

}
