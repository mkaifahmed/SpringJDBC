package dao;

import java.util.Date;
import java.util.List;

import model.Worker;

public class App {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        WorkerDAO workerDao = new WorkerDAOImplementation();
        Worker worker1 = new Worker(9, "Nezuko", "Kammado", 100000, new Date(System.currentTimeMillis()),
                "Admin", "xyz@gmail.com");
        System.out.println(workerDao.add(worker1));
        workerDao.delete(9);
        List<Worker> list = workerDao.getWorkers();
        list.forEach(System.out::println);
        System.out.println(workerDao.getWorker(2));
    }
}
