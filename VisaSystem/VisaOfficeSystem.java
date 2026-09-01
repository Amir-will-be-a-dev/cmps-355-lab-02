package VisaSystem;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The main system class that have everything Uses a DataLoader to load data on
 * startup and save it on exit.
 *
 * @author all grp membwes
 * @version 1.8
 */

public class VisaOfficeSystem {
	private ArrayList<Application> applications;
	private ArrayList<Officer> officers;
	private ArrayList<Applicant> applicants;

	private static int application_counter = 1000;
	private static int officer_counter = 2000;

	private DataLoader dataL;

	/**
	 * @author mohamed sherbini 202406405 loads all data from the text files.
	 *         applicants and officers are loaded before applications because
	 *         applications contains them.
	 */

	public VisaOfficeSystem() {
		applications = new ArrayList<Application>();
		officers = new ArrayList<Officer>();
		applicants = new ArrayList<Applicant>();

		dataL = new DataLoader(this);
		applicants = dataL.loadApplicants();
		officers = dataL.loadOfficers();
		applications = dataL.loadApplications();
	}

	// Add methods
	/**
	 * @author amir chaaben 202404677 add visa officer to system.
	 *
	 * @param officer
	 */

	public void addVisaOfficer(Officer officer) {
		officers.add(officer);
		System.out.println("Officer  " + officer.getName() + "  is successfully added to the system.");
	}

	/**
	 * @author amir chaaben 202404677 add an application to the system.
	 *
	 * @param application
	 */

	public void addApplication(Application application) {
		applications.add(application);
	}

	/**
	 * @author amir chaaben 202404677 add applicant to the system.
	 *
	 * @param applicant
	 */

	public void addApplicant(Applicant applicant) {
		applicants.add(applicant);
	}

	// Find methods

	/**
	 * @author amir chaaben 202404677 find an officer by id.
	 *
	 * @param id
	 * @return the officer or nulk uf not found
	 */

	public Officer findOfficerByID(int id) {
		for (Officer officer : officers) {
			if (officer.getId() == id)
				return officer;
		}
		return null;
	}

	/**
	 * @author amir chaaben 202404677 find application by its number.
	 *
	 * @param number
	 * @return application or null if not found
	 */

	public Application findApplicationByNo(int number) {
		for (Application app : applications) {
			if (app.getApplicationNo() == number)
				return app;
		}
		return null;
	}

	/**
	 * @author amir chaaben 202404677 find applicant by his passport number.
	 *
	 * @param passportNo
	 * @return the applicant or null if not found
	 */

	public Applicant findApplicantByPassportNo(String passportNo) {
		for (Applicant applicant : applicants) {
			if (applicant.getPassportNo().equals(passportNo))
				return applicant;
		}
		return null;
	}

	// ===== Remove methods

	/**
	 * @author mohamed sherbini 202406405 Removes an application by its number.
	 *
	 * @param appNo
	 */

	public void removeApplication(int appNo) {
		Application application = findApplicationByNo(appNo);
		if (application != null) {
			applications.remove(application);
			System.out.println("aplication " + appNo + " removed succesfuly.");
		} else {
			System.out.println("application " + appNo + " notfound.");
		}
	}

	// ===== Display methods
	/**
	 * @author abdulrahman mousa 202406104
	 * 
	 *         show all officers in the system.
	 */
	public void displayAllOfficers() {
		System.out.println("- all officers-");
		for (Officer officer : officers) {
			System.out.println(officer);
		}
	}

	/**
	 * @author abdulrahman mousa 202406104 show all applications in the system.
	 */

	public void displayAllApplications() {
		displayApplicationsTabular(applications);
	}

	/**
	 * @author abdulrahman mousa 202406104 show only one application as one row in
	 *         table
	 *
	 * @param application to disp
	 */

	public void displayApplicationTabular(Application application) {
		String officerName;
		if (application.getOfficer() == null)
			officerName = "n/a";
		else
			officerName = application.getOfficer().getName();

		String applicantName, passport, nationality;
		if (application.getApplicant() == null) {
			applicantName = "N/A";
			passport = "N/A";
			nationality = "N/A";
		} else {
			applicantName = application.getApplicant().getName();
			passport = application.getApplicant().getPassportNo();
			nationality = application.getApplicant().getNationality();
		}

		String visaType = application.getClass().getSimpleName();

		System.out.printf("%-15d %-20s %-15s %-15s %-10s %-15s %-15s %-15s%n", application.getApplicationNo(),
				applicantName, passport, nationality, application.getStatus(), application.getSubmissionDate(),
				visaType, officerName);
	}

	/**
	 * @author abdulrahman mousa 202406104 show applications in tabular format.
	 *
	 * @param applications to display
	 */
	public void displayApplicationsTabular(ArrayList<Application> applications) {
		System.out.printf("%-15s %-20s %-15s %-15s %-10s %-15s %-15s %-15s%n", "App No", "Applicant Name",
				"Passport No.", "Nationality", "Status", "Submission", "Visa Type", "Officer");
		for (Application application : applications) {
			displayApplicationTabular(application);
		}
	}

	public void displayApplicationsDETAILED(ArrayList<Application> applications) {
		System.out.printf("%-15s %-20s %-15s %-15s %-10s %-15s %-15s %-15s%n", "App No", "Applicant Name",
				"Passport No.", "Nationality", "Status", "Submission", "Visa Type", "Officer");

		
		
		for (Application application : applications) {
			displayApplicationTabular(application);
		}
	}

	// ===== assign and process

	/**
	 * @author mohamed sherbini 202406405 assign officer to an application.
	 *
	 * @param application
	 * @param officer
	 */

	public void assignOfficerToApplication(Application application, Officer officer) {
		application.setOfficer(officer);
	}

	/**
	 * @author mohamed sherbini 202406405 makes an application approved or refused
	 *         canot change the status of an already processed application.
	 *
	 * @param application
	 * @param status
	 */

	public void processApplication(Application application, Status status) {

		System.out.println("status will be updadet.");
		application.setStatus(status);

	}

	/**
	 * @author abdulrahman mousa 202406104 shows applications assigned to a specific
	 *         officer.
	 *
	 * @param officerid
	 */

	public void displayAssignedApplications(int officerID) {
		ArrayList<Application> assigned = new ArrayList<Application>();
		for (Application application : applications) {
			if (application.getOfficer() != null && application.getOfficer().getId() == officerID)
				assigned.add(application);
		}
		if (assigned.isEmpty())
			System.out.println("No applications for this officer.");
		else
			displayApplicationsTabular(assigned);
	}

	
	
	
	
	// Reports methods
	/**
	 * @author tamim abdullah 202308213 showas all applications with pending status.
	 */
	public void displayPendingApplications() {
		ArrayList<Application> pending = new ArrayList<Application>();
		for (Application appllication : applications) {
			if (appllication.getStatus() == Status.PENDING)
				pending.add(appllication);
		}
		if (pending.isEmpty())
			System.out.println("No pending applications.");
		else
			displayApplicationsTabular(pending);
	}

	/**
	 * @author tamim abdullah 202308213 calcs the total money from all applications.
	 *         polymorphism use each visa type returns its own fee.
	 */

	public void calculateTotalRevenue() {
		double total = 0;
		for (Application application : applications) {
			total += application.calculateProcessingFees();
		}
		System.out.println("total revenue is qar " + total);
	}

	/**
	 * @author tamim abdullah 202308213 find and displays the applicant with the
	 *         most applications.
	 */
	public void applicantWithMostApplications() {
		Applicant top = null;
		int maxCount = 0;
		for (Applicant applicant : applicants) {
			int count = 0;
			for (Application application : applications) {
				if (application.getApplicant().getPassportNo().equals(applicant.getPassportNo()))
					count++;
			}
			if (count > maxCount) {
				maxCount = count;
				top = applicant;
			}
		}
		if (top == null)
			System.out.println("no found aplications");
		else
			System.out.println("applkicant with most applications is " + top.getName() + " [" + top.getPassportNo()
					+ "], with " + maxCount + " applicationss.");
	}

	/**
	 * @author tamim abdullah 202308213 show applications before the given date.
	 *
	 * @param date
	 */

	public void displayApplicationsBeforeDate(LocalDate date) {
		ArrayList<Application> result = new ArrayList<Application>();
		for (Application appllication : applications) {
			if (appllication.getSubmissionDate().isBefore(date))
				result.add(appllication);
		}
		if (result.isEmpty())
			System.out.println("no applications before this date " + date);
		else
			displayApplicationsTabular(result);
	}

	/**
	 * @author tamim abdullah 202308213 disp applicationsby pass no
	 *
	 * @param passportNo
	 */

	public void displayApplicationsByPassportNo(String passportNo) {
		ArrayList<Application> result = new ArrayList<Application>();
		for (Application application : applications) {
			if (application.getApplicant().getPassportNo().equals(passportNo))
				result.add(application);
		}
		if (result.isEmpty())
			System.out.println("no application for this passport " + passportNo);
		else
			displayApplicationsTabular(result);
	}

	public void displayHOWMANYAssignedApplications(int officerID) {
		ArrayList<Application> assigned = new ArrayList<Application>();
		for (Application application : applications) {
			if (application.getOfficer() != null && application.getOfficer().getId() == officerID)
				assigned.add(application);
		}
		if (assigned.isEmpty())
			System.out.println("No applications for this officer.");
		else
			displayApplicationsTabular(assigned);
	}
	
	
	
	public void DetailedVisaPerformanceReport(LocalDate date0, LocalDate date1) {
		ArrayList<Application> apps = new ArrayList<Application>();
		int count = 0;
		double totalRevenue= 0;
		int rejected=0;
		int approved=0;
		int pending=0;
		for (Application appllication : applications) {
			if (appllication.getSubmissionDate().isBefore(date1) && appllication.getSubmissionDate().isAfter(date0))
				count++;
				System.out.println(appllication.getOfficer().getName());
				System.out.println("student { approved : {pending: } { regected : { }");
				System.out.println("tourist { approved : {pending: } { regected : { }");
				System.out.println("work { approved : {pending: } { regected : { }");
				int x =displayHOWMANYAssignedApplications()
				System.out.println("assigned apps : " + x );
			apps.add(appllication);
			for (Application app : apps) {
				totalRevenue += app.calculateProcessingFees();
				
				if  (app.getStatus().equals("APPROVED") )
					approved++;

				
				else if (app.getStatus().equals("REJECTED")) 
						rejected++;
				
				else if (app.getStatus().equals("PENDING")) 
					pending++;
			
			}
		
		}
	
	displayApplicationsDETAILED(apps);
	System.out.println("");
	
	System.out.println("ovorall approved "+ approved);
	System.out.println("ovorall approved "+ rejected);
	System.out.println("ovorall approved "+ pending);
	System.out.println("ovorall visas between period "+ count);
	System.out.println("ovorall revenue "+ totalRevenue);
	
	
	
	}
	// Save mthod
	/**
	 * @author mohamed sherbini 202406405 save all data to files.
	 */
	public void save() {
		dataL.saveApplicants();
		dataL.saveOfficers();
		dataL.saveApplications();
		System.out.println("all data saved successfully.");
	}

	// Getters and counter ( abdulrahman and tammim )
	/** @return applications */
	public ArrayList<Application> getApplications() {
		return applications;
	}

	/** @return officers */
	public ArrayList<Officer> getOfficers() {
		return officers;
	}

	/** @return applicants */
	public ArrayList<Applicant> getApplicants() {
		return applicants;
	}

	/** @return the next application number to be used */
	public static int getApplicationCounter() {
		return application_counter;
	}

	/** @param the new application counter value */
	public static void setApplicationCounter(int value) {
		application_counter = value;
	}

	/** @return the next officer id to be used */
	public static int getOfficerCounter() {
		return officer_counter;
	}

	/** @param the new officer counter value */
	public static void setOfficerCounter(int value) {
		officer_counter = value;
	}
}
