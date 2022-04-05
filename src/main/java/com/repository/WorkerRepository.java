package com.repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.*;

import com.dao.WorkerDAO;
import com.mapper.WorkerMapper;
import com.model.Worker;

public class WorkerRepository implements WorkerDAO {
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int add(Worker worker) throws SQLException {
		 int workerId = worker.getWorkerId();
	        String firstName = worker.getFirstName();
	        String lastName = worker.getLastName();
	        Integer salary = worker.getSalary();
	        Date date = worker.getJoiningDate();
	        String department = worker.getDepartment();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String joiningDate = sdf.format(date);
	        String email = worker.getEmail();
	        String query = String.format("INSERT INTO worker VALUES(%d,'%s','%s',%d,'%s','%s','%s');", workerId,
	                firstName,
	                lastName, salary, joiningDate, department, email);
		return jdbcTemplateObject.update(query);
	}

	@Override
	public void delete(Integer workerId) throws SQLException {
		String sql = "DELETE FROM worker WHERE worker_id = ?";
		jdbcTemplateObject.update(sql, workerId);
		System.out.println("Record #" + workerId + " deleted");
		return;

	}

	@Override
	public Worker getWorker(Integer workerId) throws SQLException {
		String sql = "SELECT * FROM students WHERE id = ?";
		Worker worker = jdbcTemplateObject.queryForObject(
				sql, 
				new Object[] {workerId}, 
				new WorkerMapper()
				);
		
		return worker;
	}

	@Override
	public List<Worker> getWorkers() throws SQLException {
		String sql = "SELECT * FROM worker";
		List<Worker> students = jdbcTemplateObject.query(
				sql,
				new WorkerMapper());

		return students;
	}

	@Override
	public void update(Worker emp) throws SQLException {
		String sql = """
                UPDATE TABLE Worker SET
                worker_id =?,
                first_name = ?,
                last_name =?,
                salary =?,
                joining_date = ?,
                department = ?,
                WHERE worker_id = ?""";
		
		jdbcTemplateObject.update(sql,emp.getWorkerId(),emp.getFirstName(),emp.getLastName(), emp.getSalary(), emp.getJoiningDate(), emp.getDepartment(), emp.getEmail());

	}
	
	public static void printAllWorkers(List<Worker> workers) {
		for(Worker worker: workers) {
			System.out.println("ID: " + worker.getWorkerId());
			System.out.println("FIRST NAME: " + worker.getFirstName());
			System.out.println("LAST NAME: " + worker.getLastName());
			System.out.println("SALARY: " + worker.getSalary());
			System.out.println("JOINING DATE: " + worker.getJoiningDate());
			System.out.println("DEPARTMENT: " + worker.getDepartment());
			System.out.println("EMAIL: " + worker.getEmail());
			System.out.println();
		}
	}

}
