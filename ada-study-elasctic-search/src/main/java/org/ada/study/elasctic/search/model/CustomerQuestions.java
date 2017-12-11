package org.ada.study.elasctic.search.model;

import static org.springframework.data.elasticsearch.annotations.FieldType.text;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
/**
 * 
 * @author CZD
 * @MultiField 注解详解：https://www.elastic.co/guide/en/elasticsearch/guide/current/multi-fields.html
 * 大意：以mainField定义的为主要信息
 * 		 otherFields 定义的其他字段，可以通过不同分词器分词
 */
@Document(indexName = "miduo_questions",type = "customer_questions")
public class CustomerQuestions {
	
	@Field(type = FieldType.Auto)
	private String id;
	
	/**
	 * 类别
	 */
	@Field(type = FieldType.Integer)
	private Integer categoryId;
	
	/**
	 * 问题
	 */
	@MultiField(mainField = @Field(type = text, index = false),
		otherFields = {
			@InnerField(suffix = "ik", type = text, indexAnalyzer = "ik",store=true),
		}
	)
	private String question;
	
	/**
	 * 答案 
	 */
	@MultiField(mainField = @Field(type = text, index = false),
	otherFields = {
			@InnerField(suffix = "ik", type = text, indexAnalyzer = "ik",store=true),
		}
	)
	private String answer;
	
	/**
	 * 问题状态
	 */
	@Field(type = FieldType.Integer)
	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
