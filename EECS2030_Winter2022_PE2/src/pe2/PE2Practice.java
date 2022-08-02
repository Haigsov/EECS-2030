package pe2;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PE2Practice {

	public static void main(String[] args) {
		Director director = new Director("Rebert", "Jack", 59, "Male", "Birchmount Road");
		Hospital hospital = new Hospital(director);
		PhysicianAdministrator admin1 = new PhysicianAdministrator("Elizabeth", "Smith", 53, "Female",
				"Lawrence Avenue East");
		admin1.setAdminSpecialtyType("Immunology");
		PhysicianAdministrator admin2 = new PhysicianAdministrator("Jeremy", "Dave", 45, "Male", "Guildwood Parkway");
		admin2.setAdminSpecialtyType("Dermatology");
		PhysicianAdministrator admin3 = new PhysicianAdministrator("Adam", "Tom", 55, "Male", "Danforth Road");
		admin3.setAdminSpecialtyType("Neurology");

		hospital.addAdministrator(admin1);
		hospital.addAdministrator(admin2);
		hospital.addAdministrator(admin3);

		Physician physician1 = new Physician("Ryan", "Mark", 35, "Male", "Canlish Road");
		physician1.setSalary(6000);
		physician1.setSpecialty("Dermatology");
		Physician physician2 = new Physician("George", "Hardy", 45, "Male", "Rockwood Drive");
		physician2.setSalary(4050);
		physician2.setSpecialty("Immunology");

		Physician physician3 = new Physician("FirstNameA", "LastNameA", 45, "Female", "AddressA");
		physician3.setSalary(4100);
		physician3.setSpecialty("Neurology");

		Physician physician4 = new Physician("FirstNameB", "LastNameB", 45, "Male", "AddressB");
		physician4.setSpecialty("Neurology");
		physician4.setSalary(4000);
		hospital.hirePhysician(physician1);
		hospital.hirePhysician(physician2);
		hospital.hirePhysician(physician3);
		hospital.hirePhysician(physician4);

		Random rnd = new Random();
		List<Patient> alist = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			alist.add(new Patient("PFirstName " + i + ((char) (rnd.nextInt(25) + 65)),
					"PLastName " + ((char) (rnd.nextInt(26) + 65)), rnd.nextInt(100), " ",
					"Paddress" + ((char) (rnd.nextInt(26) + 65))));
			

		}

		for (int i = 0; i < 24; i++) {
			try {
				hospital.admitPatient(alist.get(i));
				
			} catch (NoSpaceException e) {
				// no exception
				fail();
			}
		}
		for (int i = 0; i < 20; i++) {
			if (i % 3 == 0)
				hospital.dischargePatient(alist.get(i));
		}
		
		

		try {
			hospital.resignPhysician(physician3);
			// you can verify that by uncomment the below line
			// fail();
		} catch (Exception e) {
			// no exception
			fail();
		}
		

	}

}
