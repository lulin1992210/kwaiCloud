package com.itmonster.kwai.cloud.auth.biz;

import com.itmonster.kwai.cloud.auth.entity.ResourceAuthority;
import com.itmonster.kwai.cloud.auth.mapper.ResourceAuthorityMapper;
import com.itmonster.kwai.cloud.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ace on 2017/6/19.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceAuthorityBiz extends BaseBiz<ResourceAuthorityMapper, ResourceAuthority> {
}
