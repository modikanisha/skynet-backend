package com.skynet.initdata.service;

import com.skynet.initdata.dto.request.NetworkAddRequest;
import com.skynet.commons.constants.ErrorConstants;
import com.skynet.commons.dto.response.SettingCommonListResponse;
import com.skynet.commons.exceptionHandlers.SkyNetException;
import com.skynet.commons.models.Address;
import com.skynet.commons.models.Network;
import com.skynet.commons.repository.NetworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.skynet.commons.enums.Status.*;


@AllArgsConstructor
@Service
public class NetworkServiceImpl implements NetworkService {

    private final NetworkRepository networkRepository;

    @Override
    public Network create(NetworkAddRequest networkAddRequest) {
        Network network = new Network();
        BeanUtils.copyProperties(networkAddRequest, network);

        network.setStatus(ACTIVE.getName());
        //TODO waiting for country city state details
        if (networkAddRequest.getAddress() != null) {
            Address address = new Address();
            address.setAddress(networkAddRequest.getAddress());
            network.setAddressId(address);
        }
        networkRepository.save(network);
        network.setCreatedAt(network.getCreatedAtDate().getTime());
        network.setUpdatedAt(network.getUpdatedAtDate().getTime());
        return network;
    }

    @Override
    public Network update(Network network) {
        Network dbNetwork = networkRepository.findByNetworkSeqId(network.getNetworkSeqId());
        if (dbNetwork == null)
            throw new SkyNetException(ErrorConstants.ErrorCode.NETWORK_ERROR, ErrorConstants.SubErrorCode.NETWORK_ID_NOT_FOUND, ErrorConstants.ErrorMessage.NETWORK_ID_NOT_FOUND);

        BeanUtils.copyProperties(network, dbNetwork);

        dbNetwork.setStatus(getStatus(network.getStatus()) != null ? getStatus(network.getStatus()).getName() : INACTIVE.getName());
        dbNetwork.setUpdatedAtDate(new Timestamp(System.currentTimeMillis()));

        networkRepository.save(dbNetwork);
        dbNetwork.setCreatedAt(dbNetwork.getCreatedAtDate().getTime());
        dbNetwork.setUpdatedAt(dbNetwork.getUpdatedAtDate().getTime());
        return dbNetwork;
    }

    @Override
    public SettingCommonListResponse list() {
        List<Network> networks = networkRepository.findAllByOrderByUpdatedAtDateDesc();
        SettingCommonListResponse settingCommonListResponse = new SettingCommonListResponse();

        SettingCommonListResponse.MainTableConfigs mainTableConfigs = new SettingCommonListResponse.MainTableConfigs();

        mainTableConfigs.setSortableColumns(new String[]{"networkName", "status"});
        mainTableConfigs.setFilterableColumns(new String[]{"networkName", "emailId", "contactNumber", "status"});
        mainTableConfigs.setDateColumns(new String[]{"createdAt", "updatedAt"});
        mainTableConfigs.setBooleanColumns(new String[]{});

        SettingCommonListResponse.Title mainTableTitle = new SettingCommonListResponse.Title();
        mainTableTitle.setName("Network Name");
        mainTableTitle.setEmailId("Email Id");
        mainTableTitle.setContactNumber("Contact Number");
        mainTableTitle.setAddress("Address");
        mainTableTitle.setStatus("Status");
        mainTableTitle.setCreatedAt("Created At");
        mainTableTitle.setCreatedBy("Created By");
        mainTableTitle.setUpdatedAt("Updated At");
        mainTableTitle.setUpdatedBy("Updated By");
        mainTableConfigs.setTitles(mainTableTitle);
        settingCommonListResponse.setMainTableConfigs(mainTableConfigs);

        if (networks != null && !networks.isEmpty()) {
            for (Network network : networks) {
                network.setCreatedAt(network.getCreatedAtDate().getTime());
                network.setUpdatedAt(network.getUpdatedAtDate().getTime());
                if (network.getAddressId() != null)
                    network.setAddress(network.getAddressId().getAddress());
            }
        }
        settingCommonListResponse.setRecords(networks);
        return settingCommonListResponse;
    }
}
