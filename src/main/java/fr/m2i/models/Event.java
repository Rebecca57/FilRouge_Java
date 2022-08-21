package fr.m2i.models;

import java.sql.Time;
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

@Entity
@Table(name="events")
public class Event {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int _id;
	
	@Basic
	@Column(name="name")
	private String nameEvent;
	
	@Basic
	@Column(name="date")
	private Date dateEvent;
	
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
	@Basic
	@Column(name="calendar_id")
	private int idCalendar;
	
	/*@ManyToOne
	@JoinColumn(name = "calendar_id")
	private Calendar idCalendar;*/
	
		public int getIdCalendar() {
		return idCalendar;
	}

	public void setIdCalendar(int idCalendar) {
		this.idCalendar = idCalendar;
	}

	public int get_id() {
		return _id;
	}



	public void set_id(int _id) {
		this._id = _id;
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

	/*public Calendar getIdCalendar() {
		return idCalendar;
	}

	public void setIdCalendar(Calendar idCalendar) {
		this.idCalendar = idCalendar;
	}*/
	
	
	//Participants???
	

	
}
