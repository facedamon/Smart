package com.facedamon.smart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description:    工程启动入口
 * @Author:         facedamon
 * @CreateDate:     2018/9/30 下午9:11
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/9/30 下午9:11
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.facedamon.smart.project.*.*.mapper")
public class SmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartApplication.class, args);
    }

    public static String builder() {
        StringBuffer sb = new StringBuffer()
                .append("                            _ooOoo_                     \n")
                .append("                           o8888888o                    \n")
                .append("                           88  .  88                    \n")
                .append("                           (| -_- |)                    \n")
                .append("                            O\\ = /O                    \n")
                .append("                        ____/`---'\\____                \n")
                .append("                      .   ' \\| |// `.                  \n")
                .append("                       / \\||| : |||// \\               \n")
                .append("                     / _||||| -:- |||||- \\\\             \n")
                .append("                       | | \\\\\\ - /// | |             \n")
                .append("                     | \\_| ''\\---/'' | |              \n")
                .append("                      \\ .-\\__ `-` ___/-. /            \n")
                .append("                   ___`. .' /--.--\\ `. . __            \n")
                .append("                .\"\" '< `.___\\_<|>_/___.' >'\"\".         \n")
                .append("               | | : `- \\`.;`\\ _ /`;.`/ - ` : | |     \n")
                .append("                 \\ \\ `-. \\_ __\\ /__ _/ .-` / /      \n")
                .append("         ======`-.____`-.___\\_____/___.-`____.-'====== \n")
                .append("                            `=---=' \n")
                .append("                                                        \n")
                .append("         .............................................  \n")
                .append("                  佛祖镇楼             BUG辟邪          ");
        return sb.toString();
    }
}
