package com.itmonster.kwai.cloud.gateway.feign;

import com.itmonster.kwai.cloud.api.vo.log.LogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ${DESCRIPTION}
 *
 * @author ITMonster Kwai
 * @create 2017-07-01 15:16
 */
@FeignClient("kwai-admin")
public interface ILogService {
  @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
  public void saveLog(LogInfo info);
}
