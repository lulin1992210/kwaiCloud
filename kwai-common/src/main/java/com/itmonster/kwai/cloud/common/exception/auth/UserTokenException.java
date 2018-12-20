package com.itmonster.kwai.cloud.common.exception.auth;


import com.itmonster.kwai.cloud.common.constant.CommonConstants;
import com.itmonster.kwai.cloud.common.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
