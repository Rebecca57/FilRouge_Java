package fr.m2i.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import fr.m2i.models.Calendars;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="users")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Basic
	@Column(name="firstname")
	private String firstname;
	
	//@Basic(fetch = FetchType.LAZY)
	@Column(name="lastname")
	private String lastname;
	
	@Basic
	@Column(name="email")
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar birthday;
	
	@Basic
	@Column(name="password")
	private String password;
	
	@Basic
	@Column(name="phone")
	private String phone;
	
	@Basic
	@Column(name="picture_url")
	private String pictureUrl;
	
	@Basic
	@Column(name="work_area")
	private String workArea;
	
	@Basic
	@Column(name="address")
	private String address;
	
	@Basic
	@Column(name="city")
	private String city;
	
	@Basic
	@Column(name="postal_code")
	private String postalCode;
	
	@Basic
	@Column(name="access_right")
	private String accessRight;
	
	@Basic
	@Column(name="super_admin")
	private boolean superAdmin;
	
	@Basic
	@Column(name="can_share")
	private boolean canShare;
	
	@Basic
	@Column(name="active")
	private boolean active;

	/*
	@OneToMany( targetEntity=Shares.class, mappedBy="user")
    private List<Shares> shares = new ArrayList<>();
	*/
	
	//@JsonManagedReference 
	@OneToOne(targetEntity = Calendars.class , mappedBy="user_id", fetch = FetchType.EAGER, cascade=CascadeType.ALL)//
	private Calendars calendar;
	

		

	public Calendars getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendars calendar) {
		this.calendar = calendar;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Calendar getBirthday() {
		return this.birthday;
	}
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	
	public String getWorkArea() {
		return workArea;
	}
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	public String getAccessRight() {
		return this.accessRight;
	}
	public void setAccessRight(String accessRight) {
		this.accessRight = accessRight;
	}
	
	
	public boolean getSuperAdmin() {
		return this.superAdmin;
	}
	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}	
	public boolean getCanShare() {
		return this.canShare;
	}
	public void setCanShare(boolean canShare) {
		this.canShare = canShare;
	}
	public boolean getActive() {
		return this.active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}