package co.edu.icesi.model;

/**
 * Class that represent a active process from a computer
 * @author Juan David y Bayron
 *
 */
public class MyProcess {
	
//Attributes	

	/**
	 * Name of the process
	 */
	private String name;
	
	/**
	 * Id of the process
	 */
	private String id;
	
	/**
	 * Amount of cpu the process is consuming
	 */
	private String cpu;
	
	/**
	 * Attribute used to determine if the process is selected to be killed
	 */
	private boolean selected;
	
	
/**
 * Empty constructor
 */
	public MyProcess() {

	}
	
//Getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "MyProcess [name=" + name + ", id=" + id + ", cpu=" + cpu + ", selected=" + selected + "]";
	}
	
	
	
}
