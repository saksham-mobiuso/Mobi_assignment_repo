package com.mobi.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {
	
	@Id
	@Column(name = "question_id")
	private Integer questionId;
	@Column(name = "question")
	private String question;
	@Column(name = "correct_option")
	private String correctOption;
	@OneToMany(mappedBy = "questionId",cascade = CascadeType.ALL)
	//@JsonIgnore
	private List<Optionss> Optionss;
	
		
	public Questions() {

	}

	public Questions(Integer questionId, String question, String correctOption,
			List<Optionss> optionss) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.correctOption = correctOption;
		Optionss = optionss;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	public List<Optionss> getOptionss() {
		return Optionss;
	}

	public void setOptionss(List<Optionss> optionss) {
		Optionss = optionss;
	}

		
}
