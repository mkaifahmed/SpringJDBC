package com.dao;

import java.sql.*;
import java.util.List;
import com.model.Worker;

public interface WorkerDAO {
    public int add(Worker worker)
            throws SQLException;

    public void delete(Integer workerId)
            throws SQLException;

    public Worker getWorker(Integer workerId)
            throws SQLException;

    public List<Worker> getWorkers()
            throws SQLException;

    public void update(Worker emp)
            throws SQLException;
}