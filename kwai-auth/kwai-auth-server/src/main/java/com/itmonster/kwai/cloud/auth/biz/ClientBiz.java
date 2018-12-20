package com.itmonster.kwai.cloud.auth.biz;

import com.itmonster.kwai.cloud.auth.entity.Client;
import com.itmonster.kwai.cloud.auth.entity.ClientService;
import com.itmonster.kwai.cloud.auth.mapper.ClientMapper;
import com.itmonster.kwai.cloud.auth.mapper.ClientServiceMapper;
import com.itmonster.kwai.cloud.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 
 *
 * @author ITMonster Kwai
 * @email loqelia_s@sina.com
 * @date 2017-12-26 19:43:46
 */
@Service
public class ClientBiz extends BaseBiz<ClientMapper, Client> {
    @Autowired
    private ClientServiceMapper clientServiceMapper;
    @Autowired
    private ClientServiceBiz clientServiceBiz;

    public List<Client> getClientServices(String id) {
        return mapper.selectAuthorityServiceInfo(id);
    }

    public void modifyClientServices(String id, String clients) {
        clientServiceMapper.deleteByServiceId(id);
        if (!StringUtils.isEmpty(clients)) {
            String[] mem = clients.split(",");
            for (String m : mem) {
                ClientService clientService = new ClientService();
                clientService.setServiceId(m);
                clientService.setClientId(id+"");
                clientServiceBiz.insertSelective(clientService);
            }
        }
    }
}