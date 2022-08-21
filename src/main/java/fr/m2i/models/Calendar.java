package fr.m2i.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Calendar {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int _id;
	
	/*@OneToMany(targetEntity = Event.class, mappedBy= "idCalendar")
	private List<Event> _events = new ArrayList<>();*/

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	/*public List<Event> get_events() {
		return _events;
	}

	public void set_events(List<Event> _events) {
		this._events = _events;
	}*/
	
	/*@OneToOne
	@JoinColumn(name="user_id")
	private User _idUser;*/
	
	
	//De l'aute coté:(User) 	
	//@OneToOne(targetEntity = Calendar.class, mappedBy="_idUser")
	
	//private Calendar calendar = new ArrayList<>();
}//@OneToOne(targetEntity = Calendar.class, mappedBy="_idUser")
//private Calendar calendar;
