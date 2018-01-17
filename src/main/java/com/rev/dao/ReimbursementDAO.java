package com.rev.dao;

import java.util.List;

import com.rev.model.*;

public interface ReimbursementDAO {
	public List<Reimbursement> getPending(int id);
	public List<Reimbursement> getResolved(int id);
	
	public List<Reimbursement> getAllPending();
	public List<Reimbursement> getAllResolved();
	
	public List<Reimbursement> getEmployeeReimbursements(int id);
	
	public void moveReimbursement(Reimbursement reimbursement, int acceptOrDeny, int resolverid);
	public Reimbursement getOpenReimbursement(int id);
	public void addReimbursement(Reimbursement reimbursement);
	
}
