package VisaSystem;

import java.time.LocalDate;

/**
 * the student visa application.
 *
 * @author amir chaaben 202404677
 * @version 1.0
 */
public class StudentVisa extends Application {

	/** student visa fee */
	public static final double STUDENT_FEE = 350;

	private String universityName;
	private int studyDurationYears;
	private boolean scholarship;

	/** non paramtized constructor . */
	public StudentVisa() {
	}

	/**
	 * creates student visa application.
	 *
	 * @param applicationNo
	 * @param applicant
	 * @param status
	 * @param submissionDate
	 * @param officer
	 * @param universityName
	 * @param studyDurationYears
	 * @param scholarship        ( true or falsse )
	 */
	public StudentVisa(int applicationNo, Applicant applicant, Status status, LocalDate submissionDate, Officer officer,
			String universityName, int studyDurationYears, boolean scholarship) {
		super(applicationNo, applicant, status, submissionDate, officer);
		this.universityName = universityName;
		this.studyDurationYears = studyDurationYears;
		this.scholarship = scholarship;
	}

	/** @return university name */
	public String getUniversityName() {
		return universityName;
	}

	/** @param universityName */
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	/** @return study duration in years */
	public int getStudyDurationYears() {
		return studyDurationYears;
	}

	/** @param studyDurationYears */
	public void setStudyDurationYears(int studyDurationYears) {
		this.studyDurationYears = studyDurationYears;
	}

	/** @return true if the applicant has scholarship */
	public boolean isScholarship() {
		return scholarship;
	}

	/** @param scholarship */
	public void setScholarship(boolean scholarship) {
		this.scholarship = scholarship;
	}

	/**
	 * calculates total fee for a student visa.
	 *
	 * @return STUDENT_FEE + OFFICE_FEE
	 */
	@Override
	public double calculateProcessingFees() {
		return STUDENT_FEE + OFFICE_FEE;
	}

	/**
	 * gives the student visa info as a string.
	 *
	 * @return student visa details
	 */
	@Override
	public String toString() {
		return super.toString() + "\nVisa Type: StudentVisa" + "\nUniversity: " + universityName + "\nStudy Duration: "
				+ studyDurationYears + " years" + "\nScholarship: " + scholarship;
	}
}
