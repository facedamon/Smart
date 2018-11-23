package com.facedamon.smart.system.service.impl;

import com.facedamon.smart.common.support.Convert;
import com.facedamon.smart.system.domain.Notice;
import com.facedamon.smart.system.mapper.NoticeMapper;
import com.facedamon.smart.system.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:    公告service
 * @Author:         facedamon
 * @CreateDate:     2018/11/23 14:55
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/23 14:55
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Service
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 根据ID查询公告
     * @param noticeId
     * @return
     */
    @Override
    public Notice selectNoticeById(Long noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * @param notice
     * @return
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     * @param notice
     * @return
     */
    @Override
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 更新公告
     * @param notice
     * @return
     */
    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 批量删除公告
     * @param ids
     * @return
     */
    @Override
    public int deleteNoticeByIds(String ids) {
        return noticeMapper.deleteNoticeByIds(Convert.toStrArray(ids));
    }
}
