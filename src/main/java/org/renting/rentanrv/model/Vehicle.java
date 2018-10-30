package org.renting.rentanrv.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(indexes = {@Index(name="VEHICLE_DETAILS_IDX", columnList = "localisation, pricePerNight")})
public class Vehicle {
	
	// -- fields --
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Size(min=3, max=40)
	@Column(unique = true)
	private String name;
	
	@NotNull
	@Min(1)
	@Max(20)
	private int numberOfGuests;
	
	@NotNull
	@Size(min=5, max=80)
	private String localisation;
	
	//kind
	
	@NotNull
	@Min(1)
	@Max(20)
	private int numberOfBeds;
	
	@NotNull
	@Min(1)
	private BigDecimal pricePerNight;
	
	@Min(1)
	private int minStay;
	
	@ManyToOne
	@Valid
	@NotNull //rv must belong to a user
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "VEHICLE_ID")
	@Valid
	private List <Booking> bookings = new ArrayList<>(); 
	
	// -- constructors --
	
	public Vehicle() {}

	public Vehicle(String name, int numberOfGuests, String localisation, int numberOfBeds,
			BigDecimal pricePerNight, int minStay, User user) {
		this.name = name;
		this.numberOfGuests = numberOfGuests;
		this.localisation = localisation;
		this.numberOfBeds = numberOfBeds;
		this.pricePerNight = pricePerNight;
		this.minStay = minStay;
		this.user = user;
	}
	
	// -- getters, setters, toString --

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public BigDecimal getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public int getMinStay() {
		return minStay;
	}

	public void setMinStay(int minStay) {
		this.minStay = minStay;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Vehicle [name=" + name + ", NumberOfGuests=" + numberOfGuests + ", localisation=" + localisation
				+ ", NumberOfBeds=" + numberOfBeds + ", PricePerNight=" + pricePerNight + ", MinStay=" + minStay + "]";
	}
	
}
