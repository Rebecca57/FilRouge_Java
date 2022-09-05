package fr.m2i.models;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="shares")
public class Shares {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	//@ManyToOne
	//@JoinColumn(name="user_id")
	@Column(name="user_id")
	private Integer userId;

	/*
	@OneToOne(mappedBy = "share")
	@Column(name="share")
	private Calendar calendar;*/
	
	@Basic
	@Column(name="calendar_id")
	private Integer calendarId;
	
	@Basic
	@Column(name="readonly")
	private boolean readonly;
	
	public Shares() {	
	}

	public Shares(Integer userId, Integer calendarId, boolean readonly) {	
		this.setUserId(userId);
		this.setCalendarId(calendarId);
		this.setReadonly(readonly);
	}
	
	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

}
