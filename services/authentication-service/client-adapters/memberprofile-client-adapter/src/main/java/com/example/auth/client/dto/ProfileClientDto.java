package com.example.auth.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public final class ProfileClientDto {
        private ProfileClientDto() {}

        public record CreateProfileClientRequestDto(
                @NotBlank
                @JsonProperty("accountId")
                String id,

                @NotBlank
                String username
        ) {}
}
