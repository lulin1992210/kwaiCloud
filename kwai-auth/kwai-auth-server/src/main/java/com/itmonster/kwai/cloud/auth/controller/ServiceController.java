package com.itmonster.kwai.cloud.auth.controller;

import com.itmonster.kwai.cloud.auth.biz.ClientBiz;
import com.itmonster.kwai.cloud.auth.entity.Client;
import com.itmonster.kwai.cloud.auth.entity.ClientService;
import com.itmonster.kwai.cloud.common.msg.ObjectRestResponse;
import com.itmonster.kwai.cloud.common.rest.BaseController;
import org.springframework.web.bind.annotation.*;

/**
 * @author ITMonster Kwai
 * @create 2017/12/26.
 */
@RestController
@RequestMapping("service")
public class ServiceController extends BaseController<ClientBiz,Client>{

    @RequestMapping(value = "/{id}/client", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse modifyUsers(@PathVariable String id, String clients){
        baseBiz.modifyClientServices(id, clients);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/client", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<ClientService> getUsers(@PathVariable String id){
        return new ObjectRestResponse<ClientService>().rel(true).data(baseBiz.getClientServices(id));
    }
}
