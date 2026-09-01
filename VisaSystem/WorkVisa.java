package VisaSystem;

import java.time.LocalDate;

/**
 * work visa application.
 *
 * @author abdulrahman mousa 202406104
 * @version 1.0
 */
public class WorkVisa extends Application {

	/** Visa-specific fee for work visas. */
	public static final double WORK_FEE = 350;

	private String companyName;
	private double salary;
	private int contractYears;

	/** Default constructor. or non paramtized constructor */
	public WorkVisa() {
	}

	/**
	 * creates work visa application.
	 *
	 * @param applicationNo
	 * @param applicant
	 * @param status
	 * @param submissionDate
	 * @param officer
	 * @param companyName
	 * @param salary
	 * @param contractYears
	 */
	public WorkVisa(int applicationNo, Applicant applicant, Status status, LocalDate submissionDate, Officer officer,
			String companyName, double salary, int contractYears) {
		super(applicationNo, applicant, status, submissionDate, officer);
		this.companyName = companyName;
		this.salary = salary;
		this.contractYears = contractYears;
	}

	/** @return company name */
	public String getCompanyName() {
		return companyName;
	}

	/** @param companyName */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/** @return salary */
	public double getSalary() {
		return salary;
	}

	/** @param salary */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/** @return contract */
	public int getContractYears() {
		return contractYears;
	}

	/** @param contractYears */
	public void setContractYears(int contractYears) {
		this.contractYears = contractYears;
	}

	/**
	 * calc total fee for a work visa.
	 *
	 * @return WORK_FEE + OFFICE_FEE
	 */
	@Override
	public double calculateProcessingFees() {
		return WORK_FEE + OFFICE_FEE;
	}

	/**
	 * Returns the work visa details as a string.
	 *
	 * @return Returns the work visa details as a string.
	 */
	@Override
	public String toString() {
		return super.toString() + "\nVisa Type: WorkVisa" + "\nCompany: " + companyName + "\nSalary: " + salary
				+ "\nContract Years: " + contractYears;
	}
}
