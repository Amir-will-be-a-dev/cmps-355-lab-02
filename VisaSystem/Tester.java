package VisaSystem;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * test class have the main method with all menus : Admin, Officer, and
 * Applicant.
 *
 * @author all grp members
 * @version 1.9
 * 
 */

public class Tester {
	private static Scanner sc = new Scanner(System.in);
	private static VisaOfficeSystem sys = new VisaOfficeSystem();

	/**
	 * Shows the main menu
	 * 
	 * @author mohamed sherbini 202406405
	 * @param args (unused)
	 */

	public static void main(String[] args) {

		int choice;
		do {
			try {
				System.out.println();
				System.out.println("=== Welcome to Qatar Visa Processing System ===");
				System.out.println("1/ Admin");
				System.out.println("2/ Officer");
				System.out.println("3/ Applicant");
				System.out.println("0/ save and exit ");
				System.out.println("Enter menu option : (0-3)");
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
				case 1:
					showAdminMenu();
					break;
				case 2:
					showOfficerMenu();
					break;
				case 3:
					showApplicantMenu();
					break;
				case 0:
					sys.save();
					System.out.println("leaving system and saving !");
					System.exit(0);
				default:
					System.out.println("invalid option");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry type, please enter a number.");
				sc.nextLine();
			}
		} while (true);

	}// end of ,ain method

	// Admin menu

	/**
	 * @author amir chaaben 202404677 displays the admin menu with adding officers,
	 *         viewing all oficers , assigning officers, removing applications, and
	 *         generating reports.
	 */

	public static void showAdminMenu() {
		int choice;
		do {
			try {
				System.out.println();
				System.out.println("==== Admin Menu ===");
				System.out.println("1. Add Visa Officer");
				System.out.println("2. Display All Officers");
				System.out.println("3. Assign Officer to Application");
				System.out.println("4. View All Applications");
				System.out.println("5. Remove Application");
				System.out.println("6. Generate Reports");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option: (0 - 6) ");
				choice = sc.nextInt();
				sc.nextLine();

				if (choice == 1) {
					System.out.println("Enter Officer Name:");
					String name = sc.nextLine();
					int newId = VisaOfficeSystem.getOfficerCounter();
					Officer officer = new Officer(newId, name);
					sys.addVisaOfficer(officer);
					VisaOfficeSystem.setOfficerCounter(newId + 1);
				}

				else if (choice == 2) {
					sys.displayAllOfficers();
				}

				else if (choice == 3) {
					System.out.println("Enter Officer ID:");
					int officerId = sc.nextInt();
					sc.nextLine();
					Officer officer = sys.findOfficerByID(officerId);
					if (officer == null) {
						System.out.println("Officer not found.");
					} else {
						sys.displayAllApplications();
						System.out.println("Enter Application Number:");
						int appNo = sc.nextInt();
						sc.nextLine();
						Application application = sys.findApplicationByNo(appNo);
						if (application == null) {
							System.out.println("Application not found.");
						} else {
							sys.assignOfficerToApplication(application, officer);
							System.out
									.println("ApplicationNumber " + appNo + " assigned to " + officer + "successfully");
						}
					}
				}

				else if (choice == 4) {
					sys.displayAllApplications();
				}

				else if (choice == 5) {
					sys.displayAllApplications();
					System.out.println("Enter Application Number:");
					int appNo = sc.nextInt();
					sc.nextLine();
					sys.removeApplication(appNo);
				}

				else if (choice == 6) {
					showReportsMenu();
				}

				else if (choice == 0) {
					return;
				}

				else {
					System.out.println("invalid option");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry type, please enter a number.");
				sc.nextLine();
			}
		} while (true);
	}

	/**
	 * @author amir chaaben 202404677 Displays the reports menu for the admin menu
	 *         option 6 it have pending applications, total money, highest
	 *         applicant, and applications before date.
	 */

	public static void showReportsMenu() {
		int choice;
		do {
			try {
				System.out.println();
				System.out.println("==== Reports ====");
				System.out.println("1. Display pending applications");
				System.out.println("2. Calculate total revenue");
				System.out.println("3. Find applicant with most applications");
				System.out.println("4. Display applications before a date");
				System.out.println("5. DetailedVisaPerformanceReport");
				System.out.println("0. Back");
				System.out.println("Enter option:");
				choice = sc.nextInt();
				sc.nextLine();

				if (choice == 1) {
					sys.displayPendingApplications();
				} else if (choice == 2) {
					sys.calculateTotalRevenue();
				} else if (choice == 3) {
					sys.applicantWithMostApplications();
				} else if (choice == 4) {
					System.out.println("Enter date [yyyy-mm-dd]: EXAMPLE ( 2026-03-15) ");
					LocalDate date = LocalDate.parse(sc.nextLine());
					sys.displayApplicationsBeforeDate(date);
				}

				else if (choice == 5) {
					System.out.println("enter start date [yyyy-mm-dd] ");
					System.out.println("enter end date [yyyy-mm-dd] ");
					LocalDate date0 = LocalDate.parse(sc.nextLine());
					LocalDate date1 = LocalDate.parse(sc.nextLine());
					System.out.println(" visa reports from "+ date0 + " to " +date1 );
					sys.DetailedVisaPerformanceReport(date0, date1);

				}

				else if (choice == 0) {
					return;
				} else {
					System.out.println("Invalid option");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry type, please enter a number.");
				sc.nextLine();
			}
		} while (true);
	}

	// Officer menu
	/**
	 * @author abdulrahman mousa 202406104 shows the officer menu after officer sign
	 *         in options : see assigned applications, see details, process
	 *         applications, and calculate fees.
	 */

	public static void showOfficerMenu() {
		System.out.println("=== Officer Login ==");
		for (Officer officer : sys.getOfficers()) {
			System.out.println(officer.getName() + " [" + officer.getId() + "]");
		}
		System.out.println("Enter Officer ID to login:");
		int id;
		try {
			id = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid entry type, please enter a number.");
			sc.nextLine();
			return;
		}

		Officer currentOf = sys.findOfficerByID(id);
		if (currentOf == null) {
			System.out.println("Officer not found.");
			return;
		}

		System.out.println(" Welcome Officer  **" + currentOf.getName() + "**");

		int choice;
		do {
			try {
				System.out.println();
				System.out.println("1. View Assigned Applications");
				System.out.println("2. Review Application Details");
				System.out.println("3. Process Application");
				System.out.println("4. Calculate Processing Fee");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option:");
				choice = sc.nextInt();
				sc.nextLine();

				if (choice == 1) {
					sys.displayAssignedApplications(currentOf.getId());
				}

				else if (choice == 2) {
					System.out.println("Enter Application No:");
					int appNo = sc.nextInt();
					sc.nextLine();
					Application application = sys.findApplicationByNo(appNo);
					if (application == null)
						System.out.println("Application not found.");
					else if (!isAssignedToOfficer(application, currentOf)) {
						System.out.println("This application is not assigned to you.");
					}

					else
						System.out.println(application);
				}

				else if (choice == 3) {
					System.out.println("Enter Application No:");
					int appNo = sc.nextInt();
					sc.nextLine();
					Application application = sys.findApplicationByNo(appNo);
					if (application == null) {
						System.out.println("Application not found.");
					} else if (!isAssignedToOfficer(application, currentOf)) {
						System.out.println("This application is not assigned to you.");
					}

					else if (application.getStatus() != Status.PENDING) {

						System.out
								.println("Application  " + application.getApplicationNo() + "  is already processed ( "
										+ application.getStatus() + " ) . and status can not be changed");
					} else {
						System.out.println(
								"status of application  " + application.getApplicationNo() + " is currently pending");
						System.out.println("Select new Status:");
						System.out.println("1. Approved");
						System.out.println("2. Rejected");
						int stat = sc.nextInt();
						sc.nextLine();
						if (stat == 1)
							sys.processApplication(application, Status.APPROVED);
						else if (stat == 2)
							sys.processApplication(application, Status.REJECTED);
						else
							System.out.println("Invalid choice.");
					}
				} else if (choice == 4) {
					System.out.println("Enter Application No:");
					int appNo = sc.nextInt();
					sc.nextLine();
					Application application = sys.findApplicationByNo(appNo);
					if (application == null) {
						System.out.println("Application not found.");
					}

					else if (!isAssignedToOfficer(application, currentOf)) {
						System.out.println("This application is not assigned to you.");
					} else {
						System.out.println(application);
						System.out.println("application " + application.getApplicationNo() + " Fees: qar "
								+ application.calculateProcessingFees());
					}
				} else if (choice == 0) {
					return;
				} else {
					System.out.println("Invalid option");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry type, please enter a number.");
				sc.nextLine();
			}
		} while (true);
	}

	/**
	 * @author amir chaaben 202404677 (helper method check if an application is
	 *         assigned to the signed in officer.
	 *
	 * @param app
	 * @param currentofficer
	 * @return true if the application is for this current officer
	 */
	public static boolean isAssignedToOfficer(Application application, Officer currentOf) {
		if (application.getOfficer() == null)
			return false;
		return application.getOfficer().getId() == currentOf.getId();
	}

	/**
	 * @author amir chaaben 202404677
	 * 
	 *         also a helper method
	 * @param application
	 * 
	 * 
	 * @param currentApplicant
	 * @return true if the application is assignes to this applicant
	 */
	public static boolean belongsToApplicant(Application application, Applicant current) {
		if (application.getApplicant() == null)
			return false;
		return application.getApplicant().getPassportNo().equals(current.getPassportNo());
	}

	// Applicant menu =

	/**
	 * @author tamim abdullah 202308213 shows the applicant menu after signin in or
	 *         creating new account . and after that can create new applications,
	 *         see their own, check status, or withdraw a pending application.
	 */

	public static void showApplicantMenu() {
		System.out.println("-= Applicant Login ==");
		for (Applicant applicant : sys.getApplicants()) {
			System.out.println(applicant.getName() + " [" + applicant.getPassportNo() + "]");
		}
		System.out.println("Enter passport number to login or 0 to create new informations:");
		String input = sc.nextLine();

		Applicant current;
		if (input.equals("0")) {
			System.out.println("Enter name:");
			String name = sc.nextLine();
			System.out.println("Enter Passport number:");
			String passport = sc.nextLine();
			System.out.println("Enter nationality:");
			String nationality = sc.nextLine();
			System.out.println("Enter Date of birth [yyyy-mm-dd]: EXAMPLE (2026-03-15)");
			LocalDate dob = LocalDate.parse(sc.nextLine());
			current = new Applicant(name, passport, nationality, dob);
			sys.addApplicant(current);
		} else {
			current = sys.findApplicantByPassportNo(input);
			if (current == null) {
				System.out.println("Applicant not found.");
				return;
			}
		}

		System.out.println("-- Welcome " + current.getName() + " --");

		int choice;
		do {
			try {
				System.out.println();
				System.out.println("1. Create New Visa Application");
				System.out.println("2. View My Applications");
				System.out.println("3. Check Application Status");
				System.out.println("4. Withdraw Application");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option:");
				choice = sc.nextInt();
				sc.nextLine();

				if (choice == 1) {
					createNewApplication(current);
				} else if (choice == 2) {
					sys.displayApplicationsByPassportNo(current.getPassportNo());
				}

				else if (choice == 3) {
					System.out.println("Enter Application No:");
					int appNo = sc.nextInt();
					sc.nextLine();
					Application application = sys.findApplicationByNo(appNo);
					if (application == null)
						System.out.println("Application not found.");
					else if (!belongsToApplicant(application, current)) {
						System.out.println("This application does not belong to you.");
					}

					else
						System.out.println("Status: " + application.getStatus());
				}

				else if (choice == 4) {
					System.out.println("Enter Application No:");
					int appNo = sc.nextInt();
					sc.nextLine();
					Application app = sys.findApplicationByNo(appNo);
					if (app == null) {
						System.out.println("Application not found.");
					}

					else if (!belongsToApplicant(app, current)) {
						System.out.println("This application does not belong to you.");
					} else if (app.getStatus() != Status.PENDING) {
						System.out.println("Already processed. Cannot withdraw.");
					} else {
						sys.removeApplication(appNo);
					}
				} else if (choice == 0) {
					return;
				} else {
					System.out.println("Invalid option");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry type, please enter a number.");
				sc.nextLine();
			}
		} while (true);
	}

	/**
	 * @author tamim abdulah 202308213 makes a new visa application for the current
	 *         applicant. ask for the visa type and their details
	 *
	 * @param applicant
	 */

	public static void createNewApplication(Applicant applicant) {
		System.out.println("Select Visa type:");
		System.out.println("1. Tourist Visa");
		System.out.println("2. Student Visa");
		System.out.println("3. Work Visa");
		int type = sc.nextInt();
		sc.nextLine();

		int appNo = VisaOfficeSystem.getApplicationCounter();
		LocalDate today = LocalDate.now();
		Application newApp = null;

		if (type == 1) {
			System.out.println("Enter Duration (in days):");
			int days = sc.nextInt();
			sc.nextLine();
			System.out.println("Hotel Reservation (true/false):");
			boolean hotel = sc.nextBoolean();
			sc.nextLine();
			newApp = new TouristVisa(appNo, applicant, Status.PENDING, today, null, days, hotel);
		} else if (type == 2) {
			System.out.println("Enter University Name:");
			String uni = sc.nextLine();
			System.out.println("Enter Study Duration (years):");
			int years = sc.nextInt();
			sc.nextLine();
			System.out.println("Scholarship (true/false):");
			boolean scholar = sc.nextBoolean();
			sc.nextLine();
			newApp = new StudentVisa(appNo, applicant, Status.PENDING, today, null, uni, years, scholar);
		} else if (type == 3) {
			System.out.println("Enter Company Name:");
			String company = sc.nextLine();
			System.out.println("Enter Salary:");
			double salary = sc.nextDouble();
			sc.nextLine();
			System.out.println("Enter Contract Years:");
			int years = sc.nextInt();
			sc.nextLine();
			newApp = new WorkVisa(appNo, applicant, Status.PENDING, today, null, company, salary, years);
		} else {
			System.out.println("Invalid visa type.");
			return;
		}

		sys.addApplication(newApp);
		VisaOfficeSystem.setApplicationCounter(appNo + 1);
		System.out.println("New application added:");
		System.out.println(newApp);
	}
}