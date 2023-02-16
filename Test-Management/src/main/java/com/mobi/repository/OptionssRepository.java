package com.mobi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mobi.models.Optionss;

@Service
public interface OptionssRepository extends CrudRepository<Optionss, String>{
	
}
