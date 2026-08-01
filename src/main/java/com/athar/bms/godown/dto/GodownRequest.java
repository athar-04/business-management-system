package com.athar.bms.godown.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GodownRequest {

    @NotBlank(message = "Godown name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 255)
    private String location;

    @Size(max = 255)
    private String description;
}