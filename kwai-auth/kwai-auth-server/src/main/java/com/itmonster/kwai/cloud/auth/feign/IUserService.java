package com.itmonster.kwai.cloud.auth.feign;

import com.itmonster.kwai.cloud.api.vo.user.UserInfo;
import com.itmonster.kwai.cloud.auth.configuration.FeignConfiguration;
import com.itmonster.kwai.cloud.auth.util.user.JwtAuthenticationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * ${DESCRIPTION}
 *
 * @author ITMonster Kwai
 * @create 2017-06-21 8:11
 */
@FeignClient(value = "kwai-admin",configuration = FeignConfiguration.class)
public interface IUserService {
  @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
  public UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}
