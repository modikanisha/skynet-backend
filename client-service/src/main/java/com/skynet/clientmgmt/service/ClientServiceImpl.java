package com.skynet.clientmgmt.service;

import com.skynet.clientmgmt.models.Client;
import com.skynet.clientmgmt.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
