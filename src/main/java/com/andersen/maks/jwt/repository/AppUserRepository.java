package com.andersen.maks.jwt.repository;

import com.andersen.maks.jwt.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findOneByUsername(String username);
}
