package com.tanveer.userservice;

import com.tanveer.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<User,Integer> {
}
