/*package com.juniorro.servicecompany.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Services {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Name is required")
	private String description;

	private String status;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate = new Date();

	@DateTimeFormat(pattern = "MM/dd/yyyy h:m a")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "Requested date is required")
	private Date serviceDate;

	@ManyToOne
	@JoinColumn(name = "sustemUser_id")
	private SystemUser sustemUser;

	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Services(String name, String description, String status, Date requestDate, Date serviceDate,
			SystemUser sustemUser) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
		this.requestDate = requestDate;
		this.serviceDate = serviceDate;
		this.sustemUser = sustemUser;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public SystemUser getSustemUser() {
		return sustemUser;
	}

	public void setSustemUser(SystemUser sustemUser) {
		this.sustemUser = sustemUser;
	}

}
*/