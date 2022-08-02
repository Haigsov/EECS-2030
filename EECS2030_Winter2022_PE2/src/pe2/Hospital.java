package pe2;

import java.util.*;


public class Hospital {
	private Director hospDirector;
	private List<Physician> physicians = new ArrayList<Physician>();
	private List<Patient> patients = new ArrayList<Patient>();
	private List<Volunteer> volunteers = new ArrayList<Volunteer>();

	public Hospital(Director hospDirector) {
		super();
		this.hospDirector = hospDirector;
	}

	/**
	 * @return the hospDirector
	 */
	public Director getHospDirector() {
		return hospDirector;
	}

	/**
	 * @param hospDirector the hospDirector to set
	 */
	public void setHospDirector(Director hospDirector) {
		this.hospDirector = hospDirector;
	}
	
	/**
	 * 
	 * @param admin is the PhysicianAdministrator variable
	 * @return a boolean 
	 */
	public boolean addAdministrator(PhysicianAdministrator admin) {
		return this.hospDirector.assignAdministrator(admin);
	}

	public boolean hirePhysician(Physician physician) {
		try {
			if (this.physicians.size() > 70) throw new NoSpaceException(); //throws NoSpaceException if there is already 70 physicians when trying to hire a new one.
			if (this.physicians.contains(physician)) {                     //if physician is already hired then don't hire them
				return false;
			}
			
			//adds Physician under the administrator of their specialty.
			for (int i = 0; i < this.hospDirector.extractPhysicianAdmins().size(); i++) {
				if (this.hospDirector.extractPhysicianAdmins().get(i).getAdminSpecialtyType().equals(physician.getSpecialty())) {
					this.hospDirector.extractPhysicianAdmins().get(i).assignPhysician(physician);
				}
			}
			return this.physicians.add(physician);
		} catch (NoSpaceException e) {  //returns false where there is no space left.
			return false;
		}
	}
	
	/**
	 * 
	 * @return the list of physicians working in the hospital.
	 */
	public List<Physician> extractAllPhysicianDetails() {
		return this.physicians;
	}

	/**
	 * 
	 * @param physician stores the physician that is resigning
	 * @return boolean 
	 * @throws NoSpecialtyException is the exception that gets thrown if the current physician is the only one with a certain specialty.
	 */
	public boolean resignPhysician(Physician physician) throws NoSpecialtyException{
		for (int i = 0; i < getHospDirector().extractPhysicianAdmins().size(); i++) {
			if (physician.getSpecialty() == getHospDirector().extractPhysicianAdmins().get(i).getAdminSpecialtyType()) {
				if (getHospDirector().extractPhysicianAdmins().get(i).extractPhysician().size() == 1) throw new NoSpecialtyException();
			}
		}
		
		
		
		List<Patient> tempPatientList = physician.getPatients();
		List<Volunteer> tempVolList = physician.getVolunteers();
		boolean bool = this.physicians.remove(physician);
		int tempSize = tempPatientList.size();
		
		int j = 0;
		
		for (int i = 0; i < tempSize; i++) {
			if (this.physicians.get(j).hasMaximumpatient() == true) j++;
			
			this.physicians.get(j).assignPatient(tempPatientList.get(i));
			
		}
		
		tempSize = tempVolList.size();
		j = 0;
		for (int i = 0; i < tempSize; i++) {
			if (this.physicians.get(j).hasMaxVolunteers() == true) j++;
			
			this.physicians.get(j).assignVolunteer(tempVolList.get(i));
			
		}
		
		return bool;
		
		/*
		int j = this.physicians.indexOf(physician) - 1;  
		int tempSize = physician.getPatients().size();
		for (int i = 0; i < tempSize; i++) {
			
			while (this.physicians.get(j).hasMaximumpatient() == true) {
				
				if (j == 0) {
					j = this.physicians.indexOf(physician) + 1;
				}
				
				else if (j >= this.physicians.indexOf(physician)){
					j++;
				}
				
				else {
					j--;
				}
				
				
			}
			
			this.physicians.get(j).assignPatient(physician.getPatients().get(i));
			
		}
		
		
		j = this.physicians.indexOf(physician) - 1;
		tempSize = physician.getVolunteers().size();
		for (int i = 0; i < tempSize; i++) {
			
			while (this.physicians.get(j).hasMaxVolunteers() == true) {
				
				if (j == 0) {
					j = this.physicians.indexOf(physician) + 1;
				}
				
				else if (j >= this.physicians.indexOf(physician)){
					j++;
				}
				
				else {
					j--;
				}
				
				
			}
			this.physicians.get(j).assignVolunteer(physician.getVolunteers().get(0));
			physician.getVolunteers().remove(0);
		}
		*/
		//return this.physicians.remove(physician);
		
		
	}

	/**
	 * 
	 * @return the list of patients
	 */
	public List<Patient> extractAllPatientDetails() {
		return this.patients;
	}

	public boolean admitPatient(Patient patient) throws NoSpaceException{
		if (this.patients.size() / this.physicians.size() == 8) throw new NoSpaceException();
		if (this.patients.contains(patient)) {
			return false;
		}
		for (int i = 0; i < this.physicians.size() ; i++) {
			if(this.physicians.get(i).hasMaximumpatient() != true) {
				this.patients.add(patient);
				return patient.setAssignedPhysician(this.physicians.get(i));
			}
		}
		return false;
	}

	/**
	 * 
	 * @param patient stores the patient that is being discharged
	 * @return a boolean
	 */
	public boolean dischargePatient(Patient patient) {
		patient.getAssignedPhysician().getPatients().remove(patient);  //gets rid of the patient from the physician's list
		boolean bool = this.patients.remove(patient);
		patient = null;
		return bool;
	}

	/**
	 * 
	 * @param volunteer stores the volunteer that is being hired
	 * @return a boolean
	 */
	public boolean hireVolunteer(Volunteer volunteer) {
		
		//loops through every physician to see which one has space for a volunteer.
		for (int i = 0; i < this.physicians.size(); i++) {
			if (this.physicians.get(i).hasMaxVolunteers() != true) {
				this.volunteers.add(volunteer);
				volunteer.setAssignedPhys(this.physicians.get(i));         //assigns volunteer to the physician.
				return this.physicians.get(i).assignVolunteer(volunteer);
			}
		}
		
		return false;
		
	}

	/**
	 * 
	 * @param volunteer stores the volunteer that is resigning
	 * @return a boolean
	 * @throws NoVolunteersException gets thrown if volunteer is the only one in the hospital.
	 */
	public boolean resignVolunteer(Volunteer volunteer) throws NoVolunteersException{ 
		if (this.volunteers.size() <= 1) throw new NoVolunteersException();     //throws NoVolunteersException if it's the only volunteer.
		volunteer.getAssignedPhys().extractValunterDetail().remove(volunteer);  //removes volunteer from assigned physician.
		boolean bool = this.volunteers.remove(volunteer);                       //removes volunteer from hospital records.
		volunteer = null;
		return bool;
	}

	/**
	 * 
	 * @return the list of volunteers.
	 */
	public List<Volunteer> extractAllVolunteerDetails() {
		return this.volunteers;
	}
	
	
	
	
	
}

abstract class Person {
	private String firstName;   //a String variable that stores someone's first name.
	private String lastName;    //a String variable that stores someone's last name.
	private int age;            //an int variable that stores someone's age.
	private String gender;      //a String variable that stores someone's gender.
	private String Address;     //a String variable that stores someone's Address.
	
	/**
	 * The default constructor for the Person class.
	 */
	public Person() {
		this.firstName = "";
		this.lastName  = "";
		this.age       = 0;
		this.gender    = "";
		this.Address   = "";
	}
	
	/**
	 * The overloaded constructor for the Person class.
	 * @param firstName a String variable that stores someone's first name.
	 * @param lastName a String variable that stores someone's last name.
	 * @param age an int variable that stores someone's age.
	 * @param gender a String variable that stores someone's gender.
	 * @param Address a String variable that stores someone's Address.
	 */
	public Person(String firstName, String lastName, int age, String gender, String Address) {
		this.firstName = firstName;
		this.lastName  = lastName;
		this.age       = age;
		this.gender    = gender;
		this.Address   = Address;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName() {
		return this.firstName + ", " + this.lastName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}
	
	
}

class Patient extends Person implements Comparable<Object>{
	private static int patientNum = 1000;  //a static int variable that starts from 1000 and keeps incrementing whenever a new Patient object gets created.	
	private int patientID;   //a String variable that stores a patient's id.
	protected Physician assignedPhysician;

	/**
	 * The default constructor for the Patient class.
	 */
	public Patient() {
		super();
	}

	/**
	 * The overloaded constructor for the Patient class.
	 * @param firstName a String variable that stores the patient's first name.
	 * @param lastName a String variable that stores the patient's last name.
	 * @param age an int variable that stores the patient's age.
	 * @param gender a String variable that stores the patient's gender.
	 * @param Address a String variable that stores the patient's Address.
	 */
	public Patient(String firstName, String lastName, int age, String gender, String Address) {
		super(firstName, lastName, age, gender, Address);
		this.patientID = patientNum++;
	}

	
	
	
	
	/**
	 * @return the patientID
	 */
	public int getPatientID() {
		return patientID;
	}

	/**
	 * @param patientID the patientID to set
	 */
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}


	@Override
	public int compareTo(Object o) {
		return 0;
	}

	/**
	 * 
	 * @return patient's physician.
	 */
	public Physician getAssignedPhysician() {
		return this.assignedPhysician;
	}

	/**
	 * 
	 * @param physician stores the physician that is being appointed to the patient.
	 * @return boolean
	 */
	public boolean setAssignedPhysician(Physician physician) {
		if(physician.hasMaximumpatient() == false) {      //checks if physician has space.
			this.assignedPhysician = physician;           //assigns physician to patient.
			return physician.assignPatient(this);         //assigns patient to physician.
		}
		else {
			return false;
		}
		
	}

	/**
	 * cleans patient record
	 * @return  boolean
	 */
	public boolean clearPatientRecord() {
		
		if (this.assignedPhysician == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Patient [" + this.getPatientID() + ", [" + this.getFirstName() + ", "+ this.getLastName() + ", " + this.getAge() + ", " + this.getGender() + ", " + this.getAddress() + "]]";
	}
	
}

abstract class Employee extends Person {
	private static int employeeNum = 100;  //a static int variable that starts from 100 and keeps incrementing whenever a new Employee object gets created.
	private int employeeID;        //an int variable that stores the employee's id.

	/**
	 * The default constructor for the Employee class.
	 */
	public Employee() {
		super();
		this.employeeID = employeeNum++;
	}

	/**
	 * The overloaded constructor for the Employee class.
	 * @param firstName a String variable that stores the employee's first name.
	 * @param lastName a String variable that stores the employee's last name.
	 * @param age an int variable that stores the employee's age.
	 * @param gender a String variable that stores the employee's gender.
	 * @param Address a String variable that stores the employee's Address.
	 */
	public Employee(String firstName, String lastName, int age, String gender, String Address) {
		super(firstName, lastName, age, gender, Address);
		this.employeeID = employeeNum++;
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	
	
}

class Volunteer extends Employee {
	private Physician assignedPhysician;

	/**
	 * The default constructor for the Volunteer class.
	 */
	public Volunteer() {
		super();
	}

	/**
	 * The overloaded constructor for the Volunteer class.
	 * @param firstName a String variable that stores the volunteer's first name.
	 * @param lastName a String variable that stores the volunteer's last name.
	 * @param age an int variable that stores the volunteer's age.
	 * @param gender a String variable that stores the volunteer's gender.
	 * @param Address a String variable that stores the volunteer's Address.
	 */
	public Volunteer(String firstName, String lastName, int age, String gender, String Address) {
		super(firstName, lastName, age, gender, Address);
	}
	
	
	
	
	/**
	 * @return the assignedPhys
	 */
	public Physician getAssignedPhys() {
		return assignedPhysician;
	}

	/**
	 * @param assignedPhys the assignedPhys to set
	 */
	public void setAssignedPhys(Physician assignedPhys) {
		this.assignedPhysician = assignedPhys;
	}

	@Override
	public String toString() {
		return "Volunteer [[" + this.getEmployeeID() + ",[" + this.getFirstName() + ", "+ this.getLastName() + ", " + this.getAge() + ", " + this.getGender() + ", " + this.getAddress() + "]]]" ;
	}
	
}

abstract class SalariedEmployee extends Employee {
	private double salary;

	/**
	 * The default constructor for the SalariedEmployee class.
	 */
	public SalariedEmployee() {
		super();
	}

	/**
	 *The overloaded constructor for the SalariedEmployee class.
	 * @param firstName a String variable that stores the salaried employee's first name.
	 * @param lastName a String variable that stores the salaried employee's last name.
	 * @param age an int variable that stores the salaried employee's age.
	 * @param gender a String variable that stores the salaried employee's gender.
	 * @param Address a String variable that stores the salaried employee's Address.
	 */
	public SalariedEmployee(String firstName, String lastName, int age, String gender, String Address) {
		super(firstName, lastName, age, gender, Address);
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	
	
}

class Physician extends SalariedEmployee implements Comparable<Object> {
	private String specialty;                                        //Stores a Physician's specialty.
	private List<Patient> patients = new ArrayList<Patient>();       //Stores the assigned number of patients that a physician has.
	private List<Volunteer> volunteers = new ArrayList<Volunteer>(); //Stores the assigned number of volunteers that a physician has.
	
	
	

	/**
	 * The default constructor for the Physician class
	 */
	public Physician() {
		super();
	}



	/**
	 * The overloaded constructor for the Physician class.
	 * @param firstName a String variable that stores the physician's first name.
	 * @param lastName a String variable that stores the physician's last name.
	 * @param age an int variable that stores the physician's age.
	 * @param gender a String variable that stores the physician's gender.
	 * @param Address a String variable that stores the physician's Address.
	 */
	public Physician(String firstName, String lastName, int age, String gender, String Address) {
		super(firstName, lastName, age, gender, Address);
	}
	

	/**
	 * @return the specialty
	 */
	public String getSpecialty() {
		return specialty;
	}



	/**
	 * @param specialty the specialty to set
	 */
	public void setSpecialty(String specialty) {
		
		if(!specialty.equals("Immunology") && !specialty.equals("Dermatology") && !specialty.equals("Neurology")) throw new IllegalArgumentException();
		this.specialty = specialty;
		
	}



	@Override
	public int compareTo(Object o) {
		return 0;
	}



	/**
	 * 
	 * @return patient list
	 */
	public List<Patient> extractPatientDetail() {
		return this.patients;
	}
	
	/**
	 * 
	 * @return Volunteer details.
	 */
	public List<Volunteer> extractValunterDetail() {
		return this.volunteers;
	}



	/**
	 * 
	 * @param volunteer stores the volunteer we want to assign to physician.
	 * @return boolean
	 */
	public boolean assignVolunteer(Employee volunteer) {
		if (!hasMaxVolunteers()) {                               //checks if the physician has space for volunteer.
			return this.volunteers.add((Volunteer) volunteer);
		}
		return false;
	}
	
	/**
	 * 
	 * @param patient stores the patient we want to assign to physician.
	 * @return 
	 */
	public boolean assignPatient(Patient patient) {
		if (!hasMaximumpatient()) {                               //checks if the physician has space for patient.
			patient.assignedPhysician = this;
			return this.patients.add(patient);
		}
		return false;
	}
	
	



	/**
	 * @return the patients
	 */
	public List<Patient> getPatients() {
		return patients;
	}



	/**
	 * @param patients the patients to set
	 */
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}



	/**
	 * @return the volunteers
	 */
	public List<Volunteer> getVolunteers() {
		return volunteers;
	}



	/**
	 * @param volunteers the volunteers to set
	 */
	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}



	/**
	 * checks if physician has the max number of volunteers.
	 * @return boolean
	 */
	public boolean hasMaxVolunteers() {
		if (this.volunteers.size() >= 5) {
			return true;
		}
		else {
			return false;
		}
	}



	/**
	 * checks if physician has the max number of patients.
	 * @return booleans
	 */
	public boolean hasMaximumpatient() {
		if (this.patients.size() >= 8) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Physician [[[" + this.getEmployeeID() + ",[" + this.getFirstName() + ", "+ this.getLastName() + ", " + this.getAge() + ", " + this.getGender() + ", " + this.getAddress() + "]], " + this.getSalary() + "]]" ;
	}
	
}

abstract class Administrator extends SalariedEmployee {

	/**
	 * The default constructor for Administrator.
	 */
	public Administrator() {
		super();
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param Address
	 */
	public Administrator(String firstName, String lastName, int age, String gender, String Address) {
		super(firstName, lastName, age, gender, Address);
	}
	
}

class Director extends Administrator {
	private List<PhysicianAdministrator> admins = new ArrayList<PhysicianAdministrator>();    //stores the physician admins.
	

	/**
	 * the default constructor for the Director class.
	 */
	public Director() {
		super();
	}

	/**
	 * The overloaded constructor for the Director class.
	 * @param firstName a String variable that stores the director's first name.
	 * @param lastName a String variable that stores the director's last name.
	 * @param age an int variable that stores the director's age.
	 * @param gender a String variable that stores the director's gender.
	 * @param Address a String variable that stores the director's Address.
	 */
	public Director(String firstName, String lastName, int age, String gender, String Address) {
		super(firstName, lastName, age, gender, Address);
	}
	
	/**
	 * 
	 * @param admin assigns administrator
	 * @return boolean
	 */
	public boolean assignAdministrator(PhysicianAdministrator admin) {
		
		
		try {
			if (this.admins.size() > 3) throw new NoSpaceException();
			
			if (this.admins.size() >= 3) {
				return false;
				
			}
			else {
				return this.admins.add(admin);
			}
			
			
		} catch (NoSpaceException e) {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @return the physician administrators
	 */
	public List<PhysicianAdministrator> extractPhysicianAdmins() {
		return this.admins;
	}
}

class PhysicianAdministrator extends Administrator {
	private String adminSpecialtyType;                                //stores administrator specialty.
	private List<Physician> physicians = new ArrayList<Physician>();  //stores the list of physicians under each administrator

	/**
	 * the default constructor for PhysicianAdministrator.
	 */
	public PhysicianAdministrator() {
		super();
	}

	/**
	 * The overloaded constructor for the PhysicianAdministrator class.
	 * @param firstName a String variable that stores the physician administrator's first name.
	 * @param lastName a String variable that stores the physician administrator's last name.
	 * @param age an int variable that stores the physician administrator's age.
	 * @param gender a String variable that stores the physician administrator's gender.
	 * @param Address a String variable that stores the physician administrator's Address.
	 */
	public PhysicianAdministrator(String firstName, String lastName, int age, String gender, String Address) {
		super(firstName, lastName, age, gender, Address);
	}

	/**
	 * @return the adminSpecialtyType
	 */
	public String getAdminSpecialtyType() {
		return adminSpecialtyType;
	}

	/**
	 * @param adminSpecialtyType the adminSpecialtyType to set
	 */
	public void setAdminSpecialtyType(String adminSpecialtyType) {
		
		if (!adminSpecialtyType.equals("Immunology") && !adminSpecialtyType.equals("Dermatology") && !adminSpecialtyType.equals("Neurology")) throw new IllegalArgumentException();
		this.adminSpecialtyType = adminSpecialtyType;
		
	}
	
	/**
	 * assigns physician to physician administrator
	 * @param physician stores the physician we want to assign
	 * @return boolean
	 */
	public boolean assignPhysician(Physician physician) {
		try {
			if(this.physicians.size() > 25) throw new NoSpaceException();
			
			if (physician.getSpecialty().equals(this.adminSpecialtyType)) {
				return this.physicians.add(physician);
			}
			
			else {
				return false;
			}
		} catch (NoSpaceException e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @return the list of physicians
	 */
	public List<Physician> extractPhysician() {
		return this.physicians;
	}

	@Override
	public String toString() {
		return "PhysicianAdministrator [[[" + this.getEmployeeID() + ",[" + this.getFirstName() + ", "+ this.getLastName() + ", " + this.getAge() + ", " + this.getGender()+ ", " + this.getAddress() + "]], " + this.getSalary() + "], " + this.getAdminSpecialtyType() + "]";
	}
	
	
	
}

class NoSpaceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoSpaceException() {
		super();
	}
	
	public NoSpaceException(String message) {
		super(message);
	}
	
}

class NoSpecialtyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoSpecialtyException() {
		super();
	}
	
	public NoSpecialtyException(String message) {
		super(message);
	}
			
}

class NoVolunteersException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoVolunteersException() {
		super();
	}
	
	public NoVolunteersException(String message) {
		super(message);
	}
}

