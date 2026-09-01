package VisaSystem;

import java.time.LocalDate;

/**
 * Abstract class for all visa applications. Holds shared info by Tourist,
 * Student, and Work
 *
 * @author amir chaaben 202404677
 * @version 1.0
 */
public abstract class Application {

	/** thr static office fee */
	public static final double OFFICE_FEE = 250;

	private int applicationNo;
	private Applicant applicant;
	private Status status;
	private LocalDate submissionDate;
	private Officer officer;

	/** non paramrtiez constructr */
	public Application() {
	}

	/**
	 * fully oaramtiez constructor
	 *
	 * @param applicationNo
	 * @param applicant
	 * @param status
	 * @param submissionDate
	 * @param officer        ((can be null)
	 */
	public Application(int applicationNo, Applicant applicant, Status status, LocalDate submissionDate,
			Officer officer) {
		this.applicationNo = applicationNo;
		this.applicant = applicant;
		this.status = status;
		this.submissionDate = submissionDate;
		this.officer = officer;
	}

	/** @return application number */
	public int getApplicationNo() {
		return applicationNo;
	}

	/** @param application number */
	public void setApplicationNo(int applicationNo) {
		this.applicationNo = applicationNo;
	}

	/** @return applicant */
	public Applicant getApplicant() {
		return applicant;
	}

	/**
	 * @param applicant
	 */
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	/** @return curent status */
	public Status getStatus() {
		return status;
	}

	/** @param status */
	public void setStatus(Status status) {
		this.status = status;
	}

	/** @return submission date */
	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	/** @param submissionDate */
	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	/** @return the officer */
	public Officer getOfficer() {
		return officer;
	}

	/** @param officer */
	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	/** Sets the application status to approved. */
	public void approveApplication() {
		this.status = Status.APPROVED;
	}

	/** Sets the application status to rejected. */
	public void rejectApplication() {
		this.status = Status.REJECTED;
	}

	/**
	 * Calculates the total fee for this application. Each visa child type add its
	 * own fee to the office fee.
	 *
	 * @return the total fee
	 */
	public abstract double calculateProcessingFees();

	/**
	 * Returns a string info of the application.
	 *
	 * @return application details
	 */
	@Override
	public String toString() {
		String officerInfo;
		if (officer == null)
			officerInfo = "N/A";
		else
			officerInfo = officer.toString();

		return "Application No: " + applicationNo + "\nApplicant: " + applicant + "\nStatus: " + status
				+ "\nSubmission Date: " + submissionDate + "\nOfficer: " + officerInfo;
	}
}
