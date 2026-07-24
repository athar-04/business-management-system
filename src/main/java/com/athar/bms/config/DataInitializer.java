package com.athar.bms.config;

import com.athar.bms.role.entity.Role;
import com.athar.bms.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRoleIfNotExists("OWNER", "Business Owner");
        createRoleIfNotExists("ADMIN", "Business Administrator");
        createRoleIfNotExists("MANAGER", "Business Manager");
        createRoleIfNotExists("ACCOUNTANT", "Business Accountant");
        createRoleIfNotExists("STAFF", "Business Staff");
    }

    private void createRoleIfNotExists(String name, String description) {

        if (!roleRepository.existsByName(name)) {

            Role role = Role.builder()
                    .name(name)
                    .description(description)
                    .build();

            roleRepository.save(role);
        }
    }
}