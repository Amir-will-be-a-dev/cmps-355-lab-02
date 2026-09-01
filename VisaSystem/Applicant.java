package VisaSystem;

import java.time.LocalDate;

/**
 * Represents the applicant who can submit the visa applications
 * 
 * @author tamim abdallah 202308213
 * @version 1.2
 */
public class Applicant {
	private String name;
	private String passportNo;
	private String nationality;
	private LocalDate birthdate;

	/**
	 * non paramtized constructor
	 */
	public Applicant() {
	}

	/**
	 * fully paramatized construcotr
	 * 
	 * @param name        applicant name
	 * @param passportNo  pass numbr
	 * @param nationality the nationality
	 * @param birthdate   date of birth
	 */
	public Applicant(String name, String passportNo, String nationality, LocalDate birthdate) {
		this.name = name;
		this.passportNo = passportNo;
		this.nationality = nationality;
		this.birthdate = birthdate;
	}

	/** @return applicant name */
	public String getName() {
		return name;
	}

	/** @param name sets name */
	public void setName(String name) {
		this.name = name;
	}

	/** @return passport numbr */
	public String getPassportNo() {
		return passportNo;
	}

	/** @param passportNo sets passport number */
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	/** @return nationality */
	public String getNationality() {
		return nationality;
	}

	/** @param nationality */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/** @return date of birth */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/** @param birthdate */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Returns the applicant info as string.
	 *
	 * @return Returns the applicant info as string.
	 */
	@Override
	public String toString() {
		return name + ", " + passportNo + ", " + nationality + ", " + birthdate;
	}
}
