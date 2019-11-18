package co.edu.icesi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.MyProcess;
import co.edu.icesi.repository.MyProcessRepository;

@Service()
@Qualifier("linuxService")
public class MyProcessServiceLinux implements MyProcessService {

	private MyProcessRepository processRepository;
	
	
	@Autowired
	public MyProcessServiceLinux(MyProcessRepository processRepository) {
		super();
		this.processRepository = processRepository;
	}

	@Override
	public List<MyProcess> findAll() {
		return processRepository.getValues();
	}

	@Override
	public MyProcess findById(String id) {
		return processRepository.getProcessById(id);
	}

	@Override
	public void initProcesses() {
		processRepository.removeAll();
		try {
			String cmd = "ps -e -o %c; -o %p; -o %C";
			Process process = Runtime.getRuntime().exec(cmd);
			InputStream in = process.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inputReader);
			br.readLine();
			String line;
			String[] arg = new String[] {};
			
			line = br.readLine();
			MyProcess temp = null;
			while ((line = br.readLine()) != null) {
				arg = line.split(";");
				temp = new MyProcess();
				temp.setName(arg[0]);
				temp.setId(arg[1]);
				temp.setCpu(arg[2]);
				temp.setSelected(false);
				processRepository.createProcess(temp);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void killProcess(String id) {
		String cmd = "kill -9 "+ id;
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
