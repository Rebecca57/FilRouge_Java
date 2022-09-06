package fr.m2i.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="events")
public class Event {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Basic
	@Column(name="name")
	private String nameEvent;
	
	@Basic
	@Column(name="date")
	private Date dateEvent;
	//private Timestamp dateEvent;
	
	@Basic
	@Column(name="start_time")
	private Time startTimeEvent;
	
	@Basic
	@Column(name="end_time")
	private Time endTimeEvent;
	
	@Basic
	@Column(name="description")
	private String description;
	
	
	//TEMPORAIRE
	/*@Basic
	@Column(name="calendar_id")
	private int idCalendar;*/

	@ManyToOne
	@JsonBackReference 
	@JoinColumn(name = "calendar_id")//, nullable=false
	private Calendars idCalendar;
	
	
	




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}




	public Date getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	public Time getStartTimeEvent() {
		return startTimeEvent;
	}

	public void setStartTimeEvent(Time startTimeEvent) {
		this.startTimeEvent = startTimeEvent;
	}

	public Time getEndTimeEvent() {
		return endTimeEvent;
	}

	public void setEndTimeEvent(Time endTimeEvent) {
		this.endTimeEvent = endTimeEvent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendars getIdCalendar() {
		return idCalendar;
	}

	public void setIdCalendar(Calendars idCalendar) {
		this.idCalendar = idCalendar;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", nameEvent=" + nameEvent + ", dateEvent=" + dateEvent + ", startTimeEvent="
				+ startTimeEvent + ", endTimeEvent=" + endTimeEvent + ", description=" + description + ", idCalendar="
				+ idCalendar + "]";
	}
	

	
}
