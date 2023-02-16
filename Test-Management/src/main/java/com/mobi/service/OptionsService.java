package com.mobi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobi.models.Optionss;
import com.mobi.repository.OptionssRepository;

@Service
public class OptionsService{
	
	@Autowired
	OptionssRepository optionssRepository;
	
	public List<Optionss> getAllOptions() {
		List<Optionss> optionsses = new ArrayList<>();
		return (List<Optionss>) optionssRepository.findAll();
		//return optionsses;
		
	}
	
}
