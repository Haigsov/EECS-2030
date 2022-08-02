package PE1;
import java.util.ArrayList;
/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Haig Souvalian
Student Number: 218186510
Course Section: M
*/




public class MinDominantSet {
	int [][] buildings; 
	ArrayList<Integer[]> combination; 
	private int size;

	
	/**
	 * Assigns combination to an empty 2D ArrayList and size to 0
	 * this is the default constructor
	 * @pre none
	 * @post none
	 */
	public MinDominantSet () {
		this.combination = new ArrayList<Integer[]>(0);
		this.size = 0;
	}
	
	
	/**
	 * Sets the number of buildings given. And sets the distance between each and every building.
	 * @param size the number of buildings.
	 * @param buildings the 2D that stores whether the distance between each building is more than 100 meters
	 * to zero and sets it to 1 if it's not.
	 * @pre Size can't be negative. buildings has to be a 2D int array.
	 * @post there is no output.
	 */
	public MinDominantSet (int size, int [][] buildings) {
		this.size = size;
		this.buildings = buildings;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.buildings[i][j] = buildings[i][j];
			}
		}
		
	}
	
	/**
	 * This is the helper method that creates the arrays for the Arraylist combination.
	 * @param n is the number of things to choose from.
	 * @param r the number of n we choose from in a an order.
	 * @param list temporary list to turn to array.
	 * @param k the saved number to put in an array.
	 * @pre none of the numbers are negative.
	 * @post none
	 */
	private void helper( int n, int r, ArrayList<Integer> list, int k){
        
		if(list.size() == r){
			//if the list is of size r, it means we've found another permutation, add it to combination
            Integer[] toAdd = new Integer[r];
            for(int i = 0; i < toAdd.length; i++){
                toAdd[i] = list.get(i);
            }
            combination.add(toAdd);
        }
        for(int i = k; i < n; i++){
        	//go through the numbers from k to n and try to find permutations with each number at the specified position
            if(list.isEmpty() || i > list.get(list.size()-1)){ //only recurse if the number we are looking at is greater then the previous element in the permutation
                list.add(i);                //add element to permutation
                helper(n, r, list, k+1);    //recurse to find the the rest of the permutations with this number at this potion
                list.remove(list.size()-1);                
            } 

        }
    } 
	
	
	/**
	 * Creates all possible combinations recursively of selecting r items from n items.
	 * There should be no repetition and order doesn't matter.
	 * @param n is the number of things to choose from.
	 * @param r the number of n we choose from in a an order.
	 * @pre none of the number are negative.
	 * @post none.
	 */

	void combination(int n, int r) {
		helper(n, r, new ArrayList<Integer>(), 0);
	}
	
	/**
	 * Sees whether or not a the number of vending machines can cover every building.
	 * @param numMachine the number of vending machines to put.
	 * @return true or false.
	 */
	public boolean isEnough(int numMachine) {
		//combinations(size, numMachine);
		return true;
	}

	
	/**
	 * This method prints the content of the combination.
	 * we don't need this method to solve this problem, 
	 * however it is there to help you see the content of 
	 * the list, when you check for the correctness of your
	 * code. 
	 */
	void print() {

		for (int i = 0; i < combination.size(); i++) {
			for (int j = 0; j < combination.get(i).length; j++) {
				System.out.print(combination.get(i)[j]+ "\t"); 
			}
			System.out.println(); 		
		}
	}
			 
} // end of MinDominantSet

