package com.facedamon.smart.project.system.user.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserRole")
public class UserRole{
    private Long userId;

    private Long roleId;
}
