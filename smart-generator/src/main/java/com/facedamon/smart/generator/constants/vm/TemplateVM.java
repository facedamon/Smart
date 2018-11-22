package com.facedamon.smart.generator.constants.vm;

/**
 * @Description:    模板常量
 * @Author:         facedamon
 * @CreateDate:     2018/11/20 14:43
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/20 14:43
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface TemplateVM {

    /**
     * 模板文件目录
     */
    enum TemplateDir implements TemplateVM{
        /**
         *
         */
        DOMAIN("vm/java/" + TemplateFile.DOMAIN.value),
        MAPPER("vm/java/" + TemplateFile.MAPPER.value),
        SERVICE("vm/java/" + TemplateFile.SERVICE.value),
        SERVICEIMPL("vm/java/" + TemplateFile.SERVICEIMPL.value),
        CONTROLLER("vm/java/" + TemplateFile.CONTROLLER.value),
        MAPPER_XML("vm/xml/" + TemplateFile.MAPPER_XML.value),
        HTML_ADD("vm/html/" + TemplateFile.HTML_ADD.value),
        HTML_LIST("vm/html/" + TemplateFile.HTML_LIST.value),
        HTML_EDIT("vm/html/" + TemplateFile.HTML_EDIT.value);

        private String value;

        public String getValue() {
            return value;
        }

        TemplateDir(String value) {
            this.value = value;
        }
    }

    /**
     * 模板文件
     */
    enum TemplateFile implements TemplateVM{
        /**
         *
         */
        DOMAIN("domain.java.vm"),
        MAPPER("Mapper.java.vm"),
        SERVICE("Service.java.vm"),
        SERVICEIMPL("ServiceImpl.java.vm"),
        CONTROLLER("Controller.java.vm"),
        MAPPER_XML("Mapper.xml.vm"),
        HTML_ADD("add.html.vm"),
        HTML_LIST("list.html.vm"),
        HTML_EDIT("edit.html.vm");

        private String value;

        public String getValue() {
            return value;
        }

        TemplateFile(String value) {
            this.value = value;
        }
    }
}
