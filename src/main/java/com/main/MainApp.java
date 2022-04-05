package com.main;

import java.util.Date;
import java.util.List;

import com.dao.WorkerDAO;
import com.model.Worker;
import com.repository.WorkerRepository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class MainApp {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		WorkerRepository workerRepo = (WorkerRepository) context.getBean("workerRepository");
		
		List<Worker> wl = workerRepo.getWorkers();
		WorkerRepository.printAllWorkers(wl);
		
    }
}
