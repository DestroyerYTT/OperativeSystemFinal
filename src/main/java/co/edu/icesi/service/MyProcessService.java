package co.edu.icesi.service;

import java.util.List;

import co.edu.icesi.model.MyProcess;

public interface MyProcessService {
	
	public List<MyProcess> findAll();
	public MyProcess findById(String id);
	public void initProcesses();
	public void killProcess(String id);
}
