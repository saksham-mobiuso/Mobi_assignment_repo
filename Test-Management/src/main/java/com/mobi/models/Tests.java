package com.mobi.models;

import java.util.List;

public class Tests {
	
	private Integer testId;
	private Object testQuestion;
	private List<Optionss> testOptions;
	
	
		
	public Tests() {
	}

	public Tests(Integer testId, Object question, List<Optionss> options) {
		super();
		this.testId = testId;
		this.testQuestion = question;
		this.testOptions = options;
	}
	
	public Integer getTestId() {
		return testId;
	}
	
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	public Object getQuestion() {
		return testQuestion;
	}
	
	public void setQuestion(Object question) {
		this.testQuestion = question;
	}
	
	public List<Optionss> getOptions() {
		return testOptions;
	}
	
	public void setOptions(List<Optionss> options) {
		this.testOptions = options;
	}
		
}
