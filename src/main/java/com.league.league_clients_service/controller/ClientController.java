package com.league.league_clients_service.controller;

import com.league.league_clients_service.domain.Client;
import com.league.league_clients_service.dto.ClientCreateRequest;
import com.league.league_clients_service.dto.ClientResponse;
import com.league.league_clients_service.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@Validated
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientCreateRequest request) {
        Client createdEntity = clientService.createClient(request.getFullName(), request.getGender());
        ClientResponse response = toResponse(createdEntity);
        return ResponseEntity.created(URI.create("/api/clients/" + response.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> list() {
        List<Client> clients = clientService.getAllClients();
        List<ClientResponse> responses = clients.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> get(@PathVariable Long id) {
        Client c = clientService.getClientById(id);
        return ResponseEntity.ok(toResponse(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @Valid @RequestBody ClientCreateRequest request) {
        Client updatedEntity = clientService.updateClient(id, request.getFullName(), request.getGender());
        return ResponseEntity.ok(toResponse(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    private ClientResponse toResponse(Client c) {
        ClientResponse r = new ClientResponse();
        r.setId(c.getId());
        r.setFullName(c.getFullName());
        r.setGender(c.getGender());
        r.setStatus(c.getStatus());
        return r;
    }
}
