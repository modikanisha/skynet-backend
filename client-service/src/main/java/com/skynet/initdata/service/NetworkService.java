package com.skynet.initdata.service;

import com.skynet.initdata.dto.request.NetworkAddRequest;
import com.skynet.commons.dto.response.SettingCommonListResponse;
import com.skynet.commons.models.Network;

public interface NetworkService {

    Network create(NetworkAddRequest  networkAddRequest);
    Network update(Network network);
    SettingCommonListResponse list();

}
