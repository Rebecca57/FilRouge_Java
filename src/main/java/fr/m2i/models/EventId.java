package fr.m2i.models;

public class EventId {



private Event event ;
private Number idUser;

public EventId() {
	
}

public EventId(Event event, Number idUser) {
this.setEvent(event);
this.setIdUser(idUser);	
//this.event = event;
	//this.idUser = idUser;
}
public Event getEvent() {
	return event;
}
public void setEvent(Event event) {
	this.event = event;
}
public Number getIdUser() {
	return idUser;
}
public void setIdUser(Number idUser) {
	this.idUser = idUser;
}







}