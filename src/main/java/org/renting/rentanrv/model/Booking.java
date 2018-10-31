package org.renting.rentanrv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.renting.rentanrv.model.helper.BookingListener;
import org.renting.rentanrv.model.validation.ValidGuestCount;
import org.renting.rentanrv.model.validation.ValidReservationDates;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@ValidGuestCount
@ValidReservationDates
@EntityListeners(BookingListener.class)
public class Booking {
	
	// -- fields --
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Min(1)
	private int guestCount;//can not be more than the capacity of the vehicle
	
	//@NotNull
	//@Min(1)
	private int totalPrice;
	
	
	//by default it is false
	@Column(columnDefinition="boolean default false")
	private boolean completed=false;
		
	@NotNull
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date checkIn;
	
	@NotNull
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date checkOut;//must be after checkIn + minStay defined in vehicle
	
	@ManyToOne
	//@NotNull
	@Valid
	private User user;
	
	@ManyToOne
	@NotNull
	@Valid
	private Vehicle vehicle;
	
	// -- constructors --
	
	public Booking() {}

	public Booking(int guestCount, int totalPrice, Date checkIn, Date checkOut, 
			User user, Vehicle vehicle) {
		this.guestCount = guestCount;
		this.totalPrice = totalPrice;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.user = user;
		this.vehicle = vehicle;
	}
	
	// -- getters, setters and toString()

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		// can only be set from false to true
		if(completed == true && this.completed == false) {
			this.completed = completed;
		}
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Booking [guestCount=" + guestCount + ", totalPrice=" + totalPrice + ", completed=" + completed
				+ ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", user=" + user + ", vehicle=" + vehicle + "]";
	}	
}
