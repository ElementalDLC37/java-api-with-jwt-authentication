package com.vitrum.backend.dto;

import com.vitrum.backend.role.UserRole;

public record RegisterDTO(String name, String password, UserRole role) {
    
}
