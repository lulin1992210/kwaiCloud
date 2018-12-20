package com.itmonster.kwai.cloud.common.msg.auth;

import com.itmonster.kwai.cloud.common.constant.RestCodeConstants;
import com.itmonster.kwai.cloud.common.msg.BaseResponse;

/**
 * Created by ace on 2017/8/23.
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
