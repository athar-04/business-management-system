package com.athar.bms.godown.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GodownResponse {

    private Long id;

    private String name;

    private String location;

    private String description;

    private Boolean isActive;
}