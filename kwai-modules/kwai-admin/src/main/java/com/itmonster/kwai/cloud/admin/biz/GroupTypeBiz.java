package com.itmonster.kwai.cloud.admin.biz;

import com.itmonster.kwai.cloud.admin.entity.GroupType;
import com.itmonster.kwai.cloud.admin.mapper.GroupTypeMapper;
import org.springframework.stereotype.Service;

import com.itmonster.kwai.cloud.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author ITMonster Kwai
 * @create 2017-06-12 8:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupTypeBiz extends BaseBiz<GroupTypeMapper, GroupType> {
}
