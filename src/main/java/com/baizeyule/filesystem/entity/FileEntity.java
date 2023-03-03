package com.baizeyule.filesystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FileEntity {

    /**
     * 文件名
     */
    private String fineName;

    /**
     * 路径 Base62编码
     */
    private String path;

    /**
     * 是否为目录
     */
    private Boolean isDirectory;

    /**
     * 创建时间
     */
    private Date lastModified;

    /**
     * 文件后缀
     */
    private String fileSuffix;
}
