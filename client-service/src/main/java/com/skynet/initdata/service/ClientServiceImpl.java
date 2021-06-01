package com.skynet.initdata.service;

import com.skynet.commons.models.Client;
import com.skynet.initdata.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;


    @Override
    public Client createClient(Client client) {
        client.setAddress("Gulmohar");
        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> clientList() {
        return clientRepository.findAll();
    }
}
