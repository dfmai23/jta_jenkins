package com.rev.model;

import java.time.LocalDateTime;
import static com.rev.util.Constants.PENDING;
import static com.rev.util.Constants.ACCEPTED;
import static com.rev.util.Constants.REJECTED;

public class Reimbursement {
	private int id;
	private int eid;	//employee id
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int status;
	private double amount;
	private String description;
	private int resolverId;
	
	//new, open reimbursement
	public Reimbursement(int id, int eid, LocalDateTime startDate, double amount, String description) {
		this.id = id;
		this.eid = eid;
		this.startDate = startDate;
		this.amount = amount;
		this.description = description;
		
		this.endDate = null;
		this.status = PENDING;
		this.resolverId = -1;
	}
	
	public Reimbursement(int id, int eid, LocalDateTime startDate, double amount, String description,
			LocalDateTime endDate, int status, int resolverId) {
		this(id, eid, startDate, amount, description);
		this.endDate = endDate;
		this.status = status;
		this.resolverId = resolverId;
	}

	public int getId() {
		return id;
	}
	public int getEid() {
		return eid;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public int getStatus() {
		return status;
	}
	public double getAmount() {
		return amount;
	}
	public String getDescription() {
		return description;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setAmount(double amt) {
		this.amount = amt;
	}
	public void setDescription(String details) {
		this.description = details;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", eid=" + eid + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", amount=" + amount + ", description=" + description + ", resolverId="
				+ resolverId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + eid;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + resolverId;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eid != other.eid)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
	
}
