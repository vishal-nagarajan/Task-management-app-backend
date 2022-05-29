package com.example.TaskApp.controller;

import com.example.TaskApp.dto.ClientDTO;
import com.example.TaskApp.entity.Client;
import com.example.TaskApp.model.TaskResponseObject;
import com.example.TaskApp.model.TaskStatusCode;
import com.example.TaskApp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping(value = "/{id}")
    public Client get(@PathVariable(name = "id") Long id){
        return clientService.get(id);
    }

    @PostMapping
    public TaskResponseObject<ClientDTO> save(@RequestBody ClientDTO clientDTO){
        return clientService.save(clientDTO);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id")Long id){
        clientService.delete(id);
    }

    @GetMapping("/list")
    public TaskResponseObject<List<ClientDTO>> getAllClientsForUser(@RequestHeader(name = "UserId") Long userId) {
        List<ClientDTO> clientDTOList = new ArrayList<>();
        List<Client> clientList =clientService.getAllClientsForUser(userId);
        for(Client client:clientList){
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setClient(client.getClientName());
            clientDTO.setClientId(client.getId());
            clientDTOList.add(clientDTO);
        }
        return new TaskResponseObject<>(clientDTOList,TaskStatusCode.SUCCESS);
    }

}
