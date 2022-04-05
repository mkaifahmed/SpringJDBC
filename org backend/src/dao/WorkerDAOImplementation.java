package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import model.Worker;
import java.sql.Statement;
import util.DatabaseConnection;

public class WorkerDAOImplementation implements WorkerDAO {
    Connection connection;

    public WorkerDAOImplementation() throws SQLException, ClassNotFoundException {
        this.connection = DatabaseConnection.getConnection();

    }

    public int add(Worker worker) throws SQLException {
        int workerId = worker.getWorkerId();
        String firstName = worker.getFirstName();
        String lastName = worker.getLastName();
        int salary = worker.getSalary();
        Date date = worker.getJoiningDate();
        String department = worker.getDepartment();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String joiningDate = sdf.format(date);
        String email = worker.getEmail();
        String query = String.format("INSERT INTO worker VALUES(%d,'%s','%s',%d,'%s','%s','%s');", workerId,
                firstName,
                lastName, salary, joiningDate, department, email);
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        }

    }

    public void delete(int workerId) throws SQLException {
        String query = String.format("DELETE FROM worker WHERE worker_Id=%d", workerId);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    public Worker getWorker(int workerId) throws SQLException {
        String query = "SELECT * FROM worker WHERE worker_id=?";
        Worker res = null;
        try (PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setInt(1, workerId);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int workerId1 = result.getInt("worker_id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                int salary = result.getInt("salary");
                Date date = result.getDate("joining_date");
                String department = result.getString("department");
                String email = result.getString("email");

                res = new Worker(workerId1, firstName, lastName, salary, date, department, email);
            }
        }
        return res;
    }

    public List<Worker> getWorkers() throws SQLException {
        String query = "SELECT * FROM worker";
        List<Worker> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int workerId = result.getInt("worker_Id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                int salary = result.getInt("salary");
                Date date = result.getDate("joining_Date");
                String department = result.getString("department");
                String email = result.getString("email");

                list.add(new Worker(workerId, firstName, lastName, salary, date, department, email));
            }
        }

        return list;
    }

    public void update(Worker emp) throws SQLException {
        String updateQuery = """
                UPDATE TABLE Worker SET
                worker_id =?,
                first_name = ?,
                last_name =?,
                salary =?,
                joining_date = ?,
                department = ?,
                WHERE worker_id = ?""";

        PreparedStatement ps = connection.prepareStatement(updateQuery);
        ps.setInt(1, emp.getWorkerId());
        ps.setString(2, emp.getFirstName());
        ps.setString(3, emp.getLastName());
        ps.setInt(4, emp.getSalary());
        ps.setDate(5, (java.sql.Date) emp.getJoiningDate());
        ps.setString(6, emp.getDepartment());
        ps.setInt(7, emp.getWorkerId());
        int r = ps.executeUpdate();
        System.out.println(r);
    }
}
