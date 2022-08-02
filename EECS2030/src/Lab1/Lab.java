package Lab1;

/**
 * This class is meant 
 * @author Haig Souvalian
 *
 */

public class Lab {

    /**
     * this method takes no input. It just returns Haig Souvalian's student ID
     * @return studentId
     */
    public static String getMyID() {
	
    String studentId = "218186510";
	
    return studentId;
	
    }
    
    /**
     * this method accepts a double value and returns 
     * @param grade
     * @return a grade letter equivalent to the input
     * @pre The input grade is a double number between zero and 100 inclusive.
     */
    public static String getLetterGrade(double grade) {
    	if (grade >= 90) {
    		return "A+";
    	}
    	
    	else if (grade >= 80){
    		return "A";
    	}
    	
    	else if (grade >= 80){
    		return "A";
    	}
    	
    	else if (grade >= 75){
    		return "B+";
    	}
    	
    	else if (grade >= 70){
    		return "B";
    	}
    	
    	else if (grade >= 65){
    		return "C+";
    	}
    	
    	else if (grade >= 60){
    		return "C";
    	}
    	
    	else if (grade >= 55){
    		return "D+";
    	}
    	
    	else if (grade >= 50){
    		return "D";
    	}
    	
    	else if (grade >= 45){
    		return "E";
    	}
    	
    	else {
    		return "F";
    	}
    }
    

}
