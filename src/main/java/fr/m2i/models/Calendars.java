package fr.m2i.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
//@JsonIgnoreProperties
//@JsonIgnore(false)
//@JsonIgnoreProperties("field")
@Table(name="calendars")
public class Calendars {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	

	
	@JsonManagedReference 
	@OneToMany(targetEntity = Event.class, mappedBy= "idCalendar", fetch = FetchType.EAGER )//
	private List<Event> _events = new ArrayList<>();
	
	@OneToOne
	@JsonBackReference
	@JoinColumn(name="user_id")//, nullable=false
	private User user_id;
	
  	@Basic
	@Column(name="editable_by_other")
	private boolean editableByOther;
	
		public boolean isEditableByOther() {
		return editableByOther;
	}

	public void setEditableByOther(boolean editableByOther) {
		this.editableByOther = editableByOther;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Event> get_events() {
		return _events;
	}

	public void set_events(List<Event> _events) {
		this._events = _events;
	}

	/*public List<Event> get_events() {
		return _events;
	}

	public void set_events(List<Event> _events) {
		this._events = _events;
	}*/
	

	
	
	//private Calendar calendar = new ArrayList<>();
}//@OneToOne(targetEntity = Calendar.class, mappedBy="_idUser")
//private Calendar calendar;
