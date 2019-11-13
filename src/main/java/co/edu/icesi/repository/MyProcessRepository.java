package co.edu.icesi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.model.MyProcess;

/**
 * Respository to handle the list of process
 * @author Juan David
 *
 */
@Repository
public class MyProcessRepository {
	
	/**
	 * List that contains the process 
	 */
	private List<MyProcess> list;
	
	
	public MyProcessRepository() {
		list = new ArrayList<MyProcess>();
	}
	
	public List<MyProcess> getValues() {
		return list;
	}
	
	public void removeAll() {
		list = new ArrayList<MyProcess>();
	}
	
	public void createProcess(MyProcess myProcess) {
		if(myProcess != null) {
			list.add(myProcess);
		}
	}
	
	public MyProcess getProcessById(String id) {
		MyProcess process = null;		
		boolean finded = false;
		
		for (int i = 0; i < list.size() && !finded; i++) {
			if(list.get(i).getId().equals(id)) {
				process = list.get(i);
				finded = true;
			}
		}
		return process;
	}

	

}
