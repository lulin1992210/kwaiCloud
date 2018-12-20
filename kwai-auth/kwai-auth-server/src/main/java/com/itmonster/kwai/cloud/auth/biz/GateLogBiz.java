package com.itmonster.kwai.cloud.auth.biz;

import com.itmonster.kwai.cloud.auth.entity.GateLog;
import com.itmonster.kwai.cloud.auth.mapper.GateLogMapper;
import com.itmonster.kwai.cloud.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author ITMonster Kwai
 * @create 2017-07-01 14:36
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GateLogBiz extends BaseBiz<GateLogMapper, GateLog> {

    @Override
    public void insert(GateLog entity) {
        mapper.insert(entity);
    }

    @Override
    public void insertSelective(GateLog entity) {
        mapper.insertSelective(entity);
    }
}
