package com.tanveer.userservice.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ApiResponse {

    private String success;
    private String message;
    private Object data;
}
