package com.facedamon.smart.system.domain;

import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description: 通知公告
 * @Author: facedamon
 * @CreateDate: 2018/11/23 14:38
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/23 14:38
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@Alias("Notice")
public class Notice extends BaseEntity {

    /**
     * 公告编号
     */
    private Long noticeId;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型(1通知 2公告)
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 状态 (0正常 1关闭)
     */
    private String status;
}