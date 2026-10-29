package com.league.league_clients_service.repository;

import com.league.league_clients_service.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
