package com.mobi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobi.models.Authority;

@S
public interface AuthorityRepository extends JpaRepository<Authority, String>{

}
