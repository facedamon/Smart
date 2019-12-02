package com.facedamon.smart.common.exception.file;

import lombok.Getter;
import org.apache.commons.fileupload.FileUploadException;

/**
 * @Description: 文件名超长异常类
 * @Author: facedamon
 * @CreateDate: 2018/10/29 15:03
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 15:03
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Getter
public class FileNameLengthLimitExceededException extends FileUploadException {

    private int length;
    private int maxLength;
    private String fileName;

    public FileNameLengthLimitExceededException(String filename, int length, int maxLength) {
        super("file name : [" + filename + "], length : [" + length + "], max length : [" + maxLength + "]");
        this.length = length;
        this.maxLength = maxLength;
        this.fileName = filename;
    }
}
