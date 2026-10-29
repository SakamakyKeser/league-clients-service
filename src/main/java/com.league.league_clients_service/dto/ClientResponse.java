package com.league.league_clients_service.dto;

import com.league.league_clients_service.domain.Gender;
import com.league.league_clients_service.domain.ClientStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private Long id;
    private String fullName;
    private Gender gender;
    private ClientStatus status;
}
