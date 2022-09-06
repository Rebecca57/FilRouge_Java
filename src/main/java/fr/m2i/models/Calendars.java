package fr.m2i.models;



import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="calendars")
public class Calendars {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Basic
	@Column(name="user_id")
	private Integer userId;
	
	@Basic
	@Column(name="editable_by_other")
	private boolean editableByOther;
	
	
	public Calendars() {
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isEditableByOther() {
		return editableByOther;
	}

	public void setEditableByOther(boolean editableByOther) {
		this.editableByOther = editableByOther;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
