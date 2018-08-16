package com.facedamon.smart.project.system.user.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserPost")
public class UserPost{
    private Long userId;

    private Long  postId;
}
