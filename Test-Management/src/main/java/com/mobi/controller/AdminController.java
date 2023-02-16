package com.mobi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.models.Optionss;
import com.mobi.models.Questions;
import com.mobi.models.Tests;
import com.mobi.service.OptionsService;
//import com.mobi.service.QuestionsService;
import com.mobi.service.QuestionsService;
import com.mobi.service.TestService;

@RestController
public class AdminController {
	
	@Autowired
	private QuestionsService questionsService;
	
//	@Autowired
//	private OptionsService optionsService;
	
	@Autowired
	TestService testService;
	

	@GetMapping("/questions")
	public List<Questions> getAllQuestions() {
		return questionsService.getAllQuestions();
	}
	
	@GetMapping("/test")
	public ArrayList<Tests> home() {
		//return optionsService.getAllOptions();
		return testService.populateTests();
	}
	
	@GetMapping("/question/{id}")
	public Questions getQuesionById(@PathVariable Integer id) {
		return questionsService.getQuestionById(id);
	}
		
	
	@PostMapping("/question")
	public String addQuestion(@RequestBody Questions questions) {
		for(Optionss q : questions.getOptionss()) {
			if(q.getOptionValue().equals(questions.getCorrectOption())) {
				questionsService.addQuestion(questions);
				return "Question added";
			}
		}
		return "No correct option!!!!";
	}
	
	
	@DeleteMapping("/question/{id}")
	public void deleteQuestion(@PathVariable Integer id) {
		questionsService.deleteQuestion(id);
	}
}
