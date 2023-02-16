package com.mobi.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobi.models.Tests;

@Service
public class TestService {
	
	@Autowired
	QuestionsService questionsService;
		
	Integer[] selectedQuestionsArr= {1,4}; //Get from app
	
	int arrLength = selectedQuestionsArr.length;
	ArrayList<Tests> generatedTest = new ArrayList<>();
	
	public ArrayList<Tests> populateTests() {
		Tests t=null;
		for(int i=0; i<arrLength ;i++) {
			t = new Tests();
			t.setTestId(101);
			t.setQuestion(questionsService.getQuestionById(selectedQuestionsArr[i]).getQuestion());
			t.setOptions(questionsService.getQuestionById(selectedQuestionsArr[i]).getOptionss());
			generatedTest.add(t);
		}	
		return generatedTest;
	}

}
	
