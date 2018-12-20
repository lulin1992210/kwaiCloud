package com.itmonster.kwai.cloud.auth.mapper;

import com.itmonster.kwai.cloud.auth.entity.ClientService;
import tk.mybatis.mapper.common.Mapper;

public interface ClientServiceMapper extends Mapper<ClientService> {
    void deleteByServiceId(String id);
}