package com.mkst.mini.systemplus.common.exception.file;

/**
 * 文件信息异常类
 * 
 * @author ruoyi
 */
public class FileException extends FileBaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
