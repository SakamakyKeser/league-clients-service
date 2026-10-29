package com.league.league_clients_service.dto;

import com.league.league_clients_service.domain.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientCreateRequest {
    @NotBlank(message = "fullName обязательно")
    private String fullName;

    @NotNull(message = "gender обязателен")
    private Gender gender;
}
