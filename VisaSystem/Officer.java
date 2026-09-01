package VisaSystem;

/**
 * the visa officer.
 *
 * @author tamim abdalah 202308213
 * @version 1.0
 */
public class Officer {
	private int id;
	private String name;

	/** nonparamtiez consteuctor */
	public Officer() {
	}

	/**
	 * create officer with received id and name
	 *
	 * @param id
	 * @param name
	 */
	public Officer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/** @return the officer id */
	public int getId() {
		return id;
	}

	/** @param id */
	public void setId(int id) {
		this.id = id;
	}

	/** @return the officer name */
	public String getName() {
		return name;
	}

	/** @param name */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns officer info as a string.
	 *
	 * @return formatted officer details
	 */
	@Override
	public String toString() {
		return "Officer " + name + " [ID: " + id + "]";
	}
}
