package VisaSystem;

import java.util.ArrayList;

/**
 * load and save methodss that will be used in the data loader class
 *
 * @author mohamed sherbini 202406405
 * @version 1.0
 */
public interface DataManager {

	/**
	 * Loads applicants.
	 *
	 * @return a list of applicants
	 */
	ArrayList<Applicant> loadApplicants();

	/**
	 * Loads applications
	 *
	 * @return a list of applications
	 */
	ArrayList<Application> loadApplications();

	/**
	 * Loads officers
	 *
	 * @return a list of officers
	 */
	ArrayList<Officer> loadOfficers();

	/** Saves all applicants */
	void saveApplicants();

	/** Saves all applications */
	void saveApplications();

	/** Saves all officers. */
	void saveOfficers();
}
