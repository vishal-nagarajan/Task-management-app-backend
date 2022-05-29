package com.example.TaskApp.service.impl;

import com.example.TaskApp.dto.ClientDTO;
import com.example.TaskApp.entity.Client;
import com.example.TaskApp.entity.Client;
import com.example.TaskApp.entity.Project;
import com.example.TaskApp.entity.User;
import com.example.TaskApp.model.TaskResponseObject;
import com.example.TaskApp.model.TaskStatusCode;
import com.example.TaskApp.repository.ClientRepository;
import com.example.TaskApp.service.ClientService;
import com.example.TaskApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserService userService;

    @Override
    public Client get(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public TaskResponseObject<ClientDTO> save(ClientDTO clientDTO) {
        Client client = new Client();
        TaskResponseObject<ClientDTO> responseObject = new TaskResponseObject<>();
        if (!isUniqueClient(clientDTO.getClient())) {
            client.setClientName(clientDTO.getClient());
            client.setUser(userService.get(clientDTO.getUserId()));
            clientRepository.save(client);

            responseObject.setResponseObject(clientDTO);
            responseObject.setStatus(TaskStatusCode.SUCCESS);

        } else {
            responseObject.setResponseObject(clientDTO);
            responseObject.setStatus(TaskStatusCode.CLIENT_ALREADY_EXISTS);
        }
        return responseObject;
    }

    @Override
    public Boolean isUniqueClient(String name) {
        return clientRepository.existsByClientName(name);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getAllClientsForUser(Long userId) {
        User user = userService.get(userId);
        return clientRepository.findAllByUser(user);
    }

    @Override
    public Client findByName(String name) {
        return clientRepository.findByClientName(name);
    }
}
