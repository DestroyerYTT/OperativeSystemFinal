package co.edu.icesi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.MyProcess;
import co.edu.icesi.repository.MyProcessRepository;

@Service
public class MyProcessServiceImp implements MyProcessService{
	
	private MyProcessRepository processRepository;

	@Autowired
	public MyProcessServiceImp(MyProcessRepository processRepository) {
		super();
		this.processRepository = processRepository;
	}


	@Override
	public List<MyProcess> findAll() {
		initProcesses();
		return processRepository.getValues();
	}
	

	@Override
	public MyProcess findById(String id) {
	     return processRepository.getProcessById(id);
	}
	
	public void killProcess(String id) {
			String cmd = "powershell.exe Stop-Process -Id "+ id;
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void initProcesses() {
		processRepository.removeAll();
		try {
			String cmd = "powershell.exe Get-Process | select Name, Id, @{n='CPU'; e={[math]::Round($_.CPU,2)}} | sort -Property CPU -Descending | ConvertTo-Csv -delimiter ';'";
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
				line = line.replaceAll("\"", "");
				arg = line.split(";");
				temp = new MyProcess();
				temp.setName(arg[0]);
				temp.setId(arg[1]);
				temp.setCpu(arg[2]);
				temp.setSelected(false);
				processRepository.createProcess(temp);
			}
			
			
		} catch(Exception e) {
			
		}
	}


	
	
	
	

}
