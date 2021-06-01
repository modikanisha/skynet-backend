package com.skynet.clientmgmt.service;


import com.skynet.commons.models.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);
    List<Client> clientList();
}
