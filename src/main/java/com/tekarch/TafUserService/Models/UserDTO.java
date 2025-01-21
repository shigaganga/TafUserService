package com.tekarch.TafUserService.Models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long user_id;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
