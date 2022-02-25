package fr.formation.inti.entity;
// Generated 18 févr. 2022 é 08:19:10 by Hibernate Tools 3.6.0.Final

/**
 * Stades generated by hbm2java
 */

public class Venue implements java.io.Serializable {

	private Integer id;
	private String name;
	private String city;
	private String address;
	private Integer capacity;
	private String logo;
	
	

	public Venue() {
	}

	

	public Venue(Integer id, String name, String city, String address, Integer capacity, String logo) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.capacity = capacity;
		this.logo=logo;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}
	
	



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Integer getCapacity() {
		return capacity;
	}



	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}



	public String getLogo() {
		return logo;
	}



	public void setLogo(String logo) {
		this.logo = logo;
	}



	@Override
	public String toString() {
		return "Venue [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
	
	

}