package com.rev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rev.model.Reimbursement;
import static com.rev.util.Constants.*;

import org.apache.logging.log4j.*;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private static Logger log = LogManager.getLogger(ReimbursementDAOImpl.class.getName());
	
	public List<Reimbursement> getPending(int id) {	//gets user pending reimbursements
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
				
		try (Connection connection = DAOUtils.getConnection()) { //try w/ resources
			String sqlselect = "select * from openreimbursements where eid = ?";
			PreparedStatement statement = connection.prepareStatement(sqlselect);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Reimbursement re = new Reimbursement(rs.getInt("rid"),
						rs.getInt("eid"), 
						rs.getTimestamp("startdate").toLocalDateTime(),
						rs.getDouble("amount"), 
						rs.getString("description"));
				reimbursements.add(re);
				//System.out.println(re.toString());
				log.debug(re.toString());
			}
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return reimbursements;
	}
	
	public List<Reimbursement> getResolved(int id) {	//gets user pending reimbursements
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
				
		try (Connection connection = DAOUtils.getConnection()) { //try w/ resources
			String sqlselect = "select * from closedreimbursements where eid = ?";
			PreparedStatement statement = connection.prepareStatement(sqlselect);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Reimbursement re = new Reimbursement(rs.getInt("rid"),
						rs.getInt("eid"), 
						rs.getTimestamp("startdate").toLocalDateTime(),
						rs.getDouble("amount"), 
						rs.getString("description"),
						rs.getTimestamp("enddate").toLocalDateTime(),
						rs.getInt("status"),
						rs.getInt("resolverid"));
				reimbursements.add(re);
				//System.out.println("t1 " + re.toString());
			}
			statement.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return reimbursements;
	}
	
	public List<Reimbursement> getAllPending() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try (Connection connection = DAOUtils.getConnection()) { //try w/ resources
			String sqlselect = "select * from openreimbursements";
			PreparedStatement statement = connection.prepareStatement(sqlselect);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Reimbursement re = new Reimbursement(rs.getInt("rid"),
						rs.getInt("eid"), 
						rs.getTimestamp("startdate").toLocalDateTime(),
						rs.getDouble("amount"), 
						rs.getString("description"));
				reimbursements.add(re);
				//System.out.println(re.toString());
			}
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return reimbursements;
	}
	
	public List<Reimbursement> getAllResolved() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try (Connection connection = DAOUtils.getConnection()) { //try w/ resources
			String sqlselect = "select * from closedreimbursements";
			PreparedStatement statement = connection.prepareStatement(sqlselect);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Reimbursement re = new Reimbursement(rs.getInt("rid"),
						rs.getInt("eid"), 
						rs.getTimestamp("startdate").toLocalDateTime(),
						rs.getDouble("amount"), 
						rs.getString("description"),
						rs.getTimestamp("enddate").toLocalDateTime(),
						rs.getInt("status"),
						rs.getInt("resolverid"));
				reimbursements.add(re);
				//System.out.println(re.toString());
			}
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return reimbursements;
	}
	
	public List<Reimbursement> getEmployeeReimbursements(int id) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		// employee's pending reimbursements
		try (Connection connection = DAOUtils.getConnection()) { //try w/ resources
			String sqlselect1 = "select * from openreimbursements where eid = ?";
			PreparedStatement statement = connection.prepareStatement(sqlselect1);
			statement.setInt(1, id);
			ResultSet rs1 = statement.executeQuery();
			while (rs1.next()) {
				Reimbursement re = new Reimbursement(rs1.getInt("rid"),
						rs1.getInt("eid"), 
						rs1.getTimestamp("startdate").toLocalDateTime(),
						rs1.getDouble("amount"), 
						rs1.getString("description"));
				reimbursements.add(re);
				//System.out.println(re.toString());
			}
			statement.close();
			rs1.close();
			
			//employee's resolved reimbursement
			String sqlselect2 = "select * from closedreimbursements where eid = ?";
			statement = connection.prepareStatement(sqlselect2);
			statement.setInt(1, id);
			ResultSet rs2 = statement.executeQuery();
			while (rs2.next()) {
				Reimbursement re = new Reimbursement(rs2.getInt("rid"),
						rs2.getInt("eid"), 
						rs2.getTimestamp("startdate").toLocalDateTime(),
						rs2.getDouble("amount"), 
						rs2.getString("description"),
						rs2.getTimestamp("enddate").toLocalDateTime(),
						rs2.getInt("status"),
						rs2.getInt("resolverid"));
				reimbursements.add(re);
				//System.out.println(re.toString());
			}
			statement.close();
			rs2.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return reimbursements;
	}
	
	public void moveReimbursement(Reimbursement reimbursement, int acceptOrDeny, int resolverid) {
		try (Connection connection = DAOUtils.getConnection()) { //try w/ resources
			String sqlinsert = "insert into closedreimbursements (rid, eid, startdate, amount, description, enddate, status, resolverid) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			LocalDateTime timestamp = LocalDateTime.now();
			PreparedStatement statement = connection.prepareStatement(sqlinsert);
			statement.setInt(1, reimbursement.getId());
			statement.setInt(2, reimbursement.getEid());
			statement.setTimestamp(3, Timestamp.valueOf(reimbursement.getStartDate()));
			statement.setDouble(4, reimbursement.getAmount());
			statement.setString(5, reimbursement.getDescription());
			statement.setTimestamp(6, Timestamp.valueOf(timestamp));
			if (acceptOrDeny==ACCEPTED) { 
				statement.setInt(7, ACCEPTED);
			}
			else {
				statement.setInt(7, REJECTED);
			}
			statement.setInt(8, resolverid);
			statement.executeUpdate();
			statement.close();
			
			
			String sqldelete = "delete from openreimbursements " 
					+ "where rid = ?";
			statement = connection.prepareStatement(sqldelete);
			statement.setInt(1, reimbursement.getId());
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
	}
	
	public Reimbursement getOpenReimbursement(int id) {	
		try {
			Connection connection = DAOUtils.getConnection();
			String sqlget = "select * from openreimbursements where rid = ?";
			PreparedStatement stmt = connection.prepareStatement(sqlget);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			Reimbursement re = new Reimbursement(rs.getInt("rid"),
					rs.getInt("eid"), 
					rs.getTimestamp("startdate").toLocalDateTime(),
					rs.getDouble("amount"), 
					rs.getString("description"));
			stmt.close();
			return re;
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
			return null;
		}
	}
	
	public void addReimbursement(Reimbursement reimbursement) {
		try (Connection connection = DAOUtils.getConnection()) {
			String insert_re = "insert into openreimbursements (rid, eid, startdate, amount, description) "
					+ "values (re_id_seq.nextval, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert_re);
			statement.setInt(1, reimbursement.getEid());
			statement.setTimestamp(2, Timestamp.valueOf(reimbursement.getStartDate()));
			statement.setDouble(3, reimbursement.getAmount());
			statement.setString(4, reimbursement.getDescription());
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
	}
}
