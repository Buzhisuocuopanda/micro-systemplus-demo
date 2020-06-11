package com.mkst.mini.systemplus.framework.web.exception.user;

import com.mkst.mini.systemplus.framework.web.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author admin
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
