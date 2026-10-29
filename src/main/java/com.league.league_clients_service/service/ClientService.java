package com.league.league_clients_service.service;

import com.league.league_clients_service.domain.*;
import com.league.league_clients_service.exception.ClientNotFoundException;
import com.league.league_clients_service.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client createClient(String fullName, Gender gender) {
        Client client = new Client(null, fullName, gender, ClientStatus.ACTIVE);
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
    }

    @Transactional
    public Client updateClient(Long id, String fullName, Gender gender) {
        Client existing = getClientById(id);
        existing.setFullName(fullName);
        existing.setGender(gender);
        // статус не меняем, если не требуется по ТЗ
        return clientRepository.save(existing);
    }

    @Transactional
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }
}
