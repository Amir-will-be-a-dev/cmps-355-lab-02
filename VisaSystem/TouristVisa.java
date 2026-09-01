package VisaSystem;

import java.time.LocalDate;

/**
 * tourist visa application.
 *
 * @author abdulrahman mousa 202406104
 * @version 1.0
 */
public class TouristVisa extends Application {

	/** fee for tourist visa. */
	public static final double TOURIST_FEE = 350;
	private int durationDays;
	private boolean hotelReservation;

	/** default constructor. */
	public TouristVisa() {
	}

	/**
	 * makes a tourist visa application.
	 *
	 * @param applicationNo
	 * @param applicant
	 * @param status
	 * @param submissionDate
	 * @param officer
	 * @param durationDays
	 * @param hotelReservation true if the applicant has a hotel reservation
	 */
	public TouristVisa(int applicationNo, Applicant applicant, Status status, LocalDate submissionDate, Officer officer,
			int durationDays, boolean hotelReservation) {
		super(applicationNo, applicant, status, submissionDate, officer);
		this.durationDays = durationDays;
		this.hotelReservation = hotelReservation;
	}

	/** @return the duration in days */
	public int getDurationDays() {
		return durationDays;
	}

	/** @param durationDays */
	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	/** @return true if the applicant has a hotel reservation */
	public boolean isHotelReservation() {
		return hotelReservation;
	}

	/** @param hotelReservation */
	public void setHotelReservation(boolean hotelReservation) {
		this.hotelReservation = hotelReservation;
	}

	/**
	 * calcs the total fee for a tourist visa.
	 *
	 * @return TOURIST_FEE + OFFICE_FEE
	 */
	@Override
	public double calculateProcessingFees() {
		return TOURIST_FEE + OFFICE_FEE;
	}

	/**
	 * gives the tourist visa details as a string.
	 *
	 * @return gives the tourist visa details as a string.
	 */
	@Override
	public String toString() {
		return super.toString() + "\nVisa Type: TouristVisa" + "\nDuration: " + durationDays + " days"
				+ "\nHotel Reservation: " + hotelReservation;
	}
}
