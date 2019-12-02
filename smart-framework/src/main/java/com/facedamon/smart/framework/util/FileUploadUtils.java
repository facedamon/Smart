package com.facedamon.smart.framework.util;

import com.facedamon.smart.common.config.SmartConfig;
import com.facedamon.smart.common.exception.file.FileNameLengthLimitExceededException;
import com.facedamon.smart.common.utils.Md5Utils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description: 文档上传工具类
 * @Author: facedamon
 * @CreateDate: 2018/10/29 14:55
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 14:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class FileUploadUtils {

    /**
     * 默认大小 50MB
     */
    private static final long DEFAULT_MAX_SIZE = 52428800;

    /**
     * 默认上传地址
     */
    private static String DEFAULT_DIR = SmartConfig.INSTANCE.getProfile();

    /**
     * 默认文件名最大长度
     */
    private static final int DEFAULT_FILE_NAME_LENGTH = 200;

    /**
     * 默认文件类型
     */
    private static final String IMAGE_JPG_EXTENSION = ".jpg";

    private static int counter = 0;

    public static String getDefaultDir() {
        return DEFAULT_DIR;
    }

    public static void setDefaultDir(String defaultDir) {
        DEFAULT_DIR = defaultDir;
    }

    /**
     * 文件上传
     *
     * @param baseDir   相对上传路径
     * @param file      上传文件
     * @param extension 上传文件扩展名
     * @return
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws IOException                          读写文件出错时
     * @throws FileNameLengthLimitExceededException 文件名太长
     */
    public static final String upload(String baseDir, MultipartFile file, String extension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException {
        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNameLength, DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file);

        String fileName = encodingFilename(file.getOriginalFilename(), extension);
        File desc = getAbsoluteFile(baseDir, baseDir + fileName);
        file.transferTo(desc);
        return fileName;
    }

    /**
     * 创建绝对路径同名空文件
     *
     * @param uploadDir
     * @param fileName
     * @return
     * @throws IOException
     */
    private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        /**
         * / 或者 \
         */
        File desc = new File(File.separator + fileName);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 文件大小校验
     *
     * @param file
     * @throws FileSizeLimitExceededException 超出最大文件大小
     */
    public static final void assertAllowed(MultipartFile file) throws FileSizeLimitExceededException {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && DEFAULT_MAX_SIZE < size) {
            throw new FileSizeLimitExceededException("not allowed upload", size, DEFAULT_MAX_SIZE);
        }
    }

    /**
     * 编码文件名
     *
     * @param filename
     * @param extension
     * @return
     */
    public static final String encodingFilename(String filename, String extension) {
        filename = filename.replace("_", "");
        filename = Md5Utils.hash(filename + System.nanoTime() + counter++) + extension;
        return filename;
    }
}
