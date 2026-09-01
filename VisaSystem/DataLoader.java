package VisaSystem;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Implements DataManager interface Read and writes to applicants.txt,
 * officers.txt, and applications.txt.
 *
 * @author mohamed sherbini 202406405
 * @version 1.3
 */
public class DataLoader implements DataManager {
	private VisaOfficeSystem sys;

	/** non paramtiez constructor */
	public DataLoader() {
	}

	/**
	 * Creates a DataLoader linked to the system.
	 *
	 * @param the VisaOfficeSystem this loader works with( we will need later to
	 *            access some methods )
	 */
	public DataLoader(VisaOfficeSystem sys) {
		this.sys = sys;
	}

	/**
	 * Sets the system reference used by save and load methods.( same as the
	 * constructor )
	 *
	 * @param sys the VisaOfficeSystem instance
	 */
	public void setSystem(VisaOfficeSystem sys) {
		this.sys = sys;
	}

	/**
	 * Loads applicants from applicants.txt.
	 *
	 * @return a list of Applicant objects from the file
	 */
	@Override
	public ArrayList<Applicant> loadApplicants() {
		ArrayList<Applicant> list = new ArrayList<Applicant>();
		try {
			Scanner fileSc = new Scanner(new File("applicants.txt"));
			while (fileSc.hasNextLine()) {
				String line = fileSc.nextLine();
				String[] parts = line.split(",");
				String name = parts[0];
				String passport = parts[1];
				String nationality = parts[2];
				LocalDate dob = LocalDate.parse(parts[3]);
				list.add(new Applicant(name, passport, nationality, dob));
			}
			fileSc.close();
		} catch (IOException e) {
			System.out.println("file no opend , exeptiion : " + e);
		}
		return list;
	}

	/**
	 * Loads officers from officers.txt. The first line is the officer counter.
	 *
	 * @return a list of Officer objects read from the file
	 */
	@Override
	public ArrayList<Officer> loadOfficers() {
		ArrayList<Officer> list = new ArrayList<Officer>();
		try {
			Scanner fileSc = new Scanner(new File("officers.txt"));
			if (fileSc.hasNextLine()) {
				int counter = Integer.parseInt(fileSc.nextLine());
				VisaOfficeSystem.setOfficerCounter(counter);
			}
			while (fileSc.hasNextLine()) {
				String line = fileSc.nextLine();
				String[] parts = line.split(",");
				int id = Integer.parseInt(parts[0]);
				String name = parts[1];
				list.add(new Officer(id, name));
			}
			fileSc.close();
		} catch (IOException e) {
			System.out.println("file not opened , exeption :  " + e);
		}
		return list;
	}

	/**
	 * Loads applications from applications.txt. The first line is the application
	 * counter. Each application row is parsed based onthe vsa type.
	 *
	 * @return a list of aapplication objects read from the file
	 */
	@Override
	public ArrayList<Application> loadApplications() {
		ArrayList<Application> list = new ArrayList<Application>();
		try {
			Scanner fileSc = new Scanner(new File("applications.txt"));
			if (fileSc.hasNextLine()) {
				int counter = Integer.parseInt(fileSc.nextLine());
				VisaOfficeSystem.setApplicationCounter(counter);
			}
			while (fileSc.hasNextLine()) {
				String line = fileSc.nextLine();
				String[] p = line.split(",");
				String type = p[0];
				int appNo = Integer.parseInt(p[1]);
				String passport = p[2];
				LocalDate date = LocalDate.parse(p[3]);
				Status status = Status.valueOf(p[4]);
				String officerStr = p[5];

				Applicant applicant = sys.findApplicantByPassportNo(passport);
				Officer officer = null;
				if (!officerStr.equals("N/A")) {
					officer = sys.findOfficerByID(Integer.parseInt(officerStr));
				}

				if (type.equals("TOURIST")) {
					int days = Integer.parseInt(p[6]);
					boolean hotel = Boolean.parseBoolean(p[7]);
					list.add(new TouristVisa(appNo, applicant, status, date, officer, days, hotel));
				} else if (type.equals("STUDENT")) {
					String univ = p[6];
					int years = Integer.parseInt(p[7]);
					boolean scholar = Boolean.parseBoolean(p[8]);
					list.add(new StudentVisa(appNo, applicant, status, date, officer, univ, years, scholar));
				} else if (type.equals("WORK")) {
					String company = p[6];
					double salary = Double.parseDouble(p[7]);
					int years = Integer.parseInt(p[8]);
					list.add(new WorkVisa(appNo, applicant, status, date, officer, company, salary, years));
				}
			}
			fileSc.close();
		} catch (IOException e) {
			System.out.println("fille not opened  exeption  " + e);
		}
		return list;
	}

	/** Saves applicants to applicants.txt. */
	@Override
	public void saveApplicants() {
		try {
			Formatter out = new Formatter(new File("applicants.txt"));
			for (Applicant applicant : sys.getApplicants()) {
				out.format("%s,%s,%s,%s%n", applicant.getName(), applicant.getPassportNo(), applicant.getNationality(),
						applicant.getBirthdate());
			}
			out.close();
		} catch (IOException e) {
			System.out.println("file not opened  ,  exception: " + e);
		}
	}

	/** Saves all officers to officers.txt with the counter on the first line. */
	@Override
	public void saveOfficers() {
		try {
			Formatter out = new Formatter(new File("officers.txt"));
			out.format("%d%n", VisaOfficeSystem.getOfficerCounter());
			for (Officer officer : sys.getOfficers()) {
				out.format("%d,%s%n", officer.getId(), officer.getName());
			}
			out.close();
		} catch (IOException e) {
			System.out.println("file not opened  ,  exception: : " + e);
		}
	}

	/**
	 * Saves all applications to applications.txt with the counter on the first line
	 * also.
	 */
	@Override
	public void saveApplications() {
		try {
			Formatter out = new Formatter(new File("applications.txt"));
			out.format("%d%n", VisaOfficeSystem.getApplicationCounter());
			for (Application application : sys.getApplications()) {
				String officerId;
				if (application.getOfficer() == null)
					officerId = "N/A";
				else
					officerId = String.valueOf(application.getOfficer().getId());

				String passport;
				if (application.getApplicant() == null)
					passport = "N/A";
				else
					passport = application.getApplicant().getPassportNo();

				if (application instanceof TouristVisa) {
					TouristVisa t = (TouristVisa) application;
					out.format("TOURIST,%d,%s,%s,%s,%s,%d,%b%n", t.getApplicationNo(), passport, t.getSubmissionDate(),
							t.getStatus(), officerId, t.getDurationDays(), t.isHotelReservation());
				} else if (application instanceof StudentVisa) {
					StudentVisa s = (StudentVisa) application;
					out.format("STUDENT,%d,%s,%s,%s,%s,%s,%d,%b%n", s.getApplicationNo(), passport,
							s.getSubmissionDate(), s.getStatus(), officerId, s.getUniversityName(),
							s.getStudyDurationYears(), s.isScholarship());
				} else if (application instanceof WorkVisa) {
					WorkVisa w = (WorkVisa) application;
					out.format("WORK,%d,%s,%s,%s,%s,%s,%.1f,%d%n", w.getApplicationNo(), passport,
							w.getSubmissionDate(), w.getStatus(), officerId, w.getCompanyName(), w.getSalary(),
							w.getContractYears());
				}
			}
			out.close();
		} catch (IOException e) {
			System.out.println("file not opened. exception: " + e);
		}
	}
}
