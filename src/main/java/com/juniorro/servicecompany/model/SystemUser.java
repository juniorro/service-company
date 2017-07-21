package com.juniorro.servicecompany.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juniorro.servicecompany.model.security.Authority;
import com.juniorro.servicecompany.model.security.UserRole;

@Entity
public class SystemUser implements Serializable, UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Username is required")
	private String username;

	@NotEmpty(message = "Password is required")
	private String password;

	@NotEmpty(message = "First Name is required")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	private String lastName;

	@NotEmpty(message = "Email is required")
	private String email;

	private String phone;

	private String streetAddress;

	private String city;

	private String zipCode;

	private String gender;

	private boolean enabled;

	@Transient
	private MultipartFile profilePhoto;

	@OneToMany(mappedBy = "systemUser",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Services> services;

	@OneToMany(mappedBy = "systemUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> systemUserRoles = new HashSet<>();

	public SystemUser() {
		super();
	}

	public SystemUser(String username, String password, String firstName, String lastName, String email, String phone,
			String streetAddress, String city, String zipCode, String gender, boolean enabled,
			MultipartFile profilePhoto, List<Services> services, Set<UserRole> systemUserRoles) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.streetAddress = streetAddress;
		this.city = city;
		this.zipCode = zipCode;
		this.gender = gender;
		this.enabled = enabled;
		this.profilePhoto = profilePhoto;
		this.services = services;
		this.systemUserRoles = systemUserRoles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public MultipartFile getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(MultipartFile profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public List<Services> getServices() {
		return services;
	}

	public void setServices(List<Services> services) {
		this.services = services;
	}

	public Set<UserRole> getSystemUserRoles() {
		return systemUserRoles;
	}

	public void setSystemUserRoles(Set<UserRole> systemUserRoles) {
		this.systemUserRoles = systemUserRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		systemUserRoles.forEach(ur -> authorites.add(new Authority(ur.getRole().getName())));
		return authorites;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String toString() {
		return "SystemUser [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", streetAddress="
				+ streetAddress + ", city=" + city + ", zipCode=" + zipCode + ", gender=" + gender + ", enabled="
				+ enabled + ", profilePhoto=" + profilePhoto + ", services=" + services + ", systemUserRoles="
				+ systemUserRoles + "]";
	}
	
	

}
