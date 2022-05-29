package com.example.TaskApp.service;

import com.example.TaskApp.dto.ClientDTO;
import com.example.TaskApp.entity.Client;
import com.example.TaskApp.model.TaskResponseObject;

import java.util.List;

public interface ClientService {
    public Client get(Long id);
    public TaskResponseObject<ClientDTO> save(ClientDTO clientDTO);
    public Client update(Client Client);
    public void delete(Long id);
    Boolean isUniqueClient(String name);
    List<Client> getAllClientsForUser(Long userId);
    Client findByName(String name);
}
