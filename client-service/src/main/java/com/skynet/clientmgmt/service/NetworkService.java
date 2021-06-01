package com.skynet.clientmgmt.service;

import com.skynet.clientmgmt.dto.request.NetworkAddRequest;
import com.skynet.commons.dto.response.SettingCommonListResponse;
import com.skynet.commons.models.Network;

public interface NetworkService {

    Network create(NetworkAddRequest networkAddRequest);
    Network update(Network network);
    SettingCommonListResponse list();

}
