package Lab6;

import java.util.*;
import java.io.*;
/**
 * This class is a container that holds an unlimited number of 
 * String objects. It is able to remove objects and add objects.
 */

public class Container {
	// No instance variable should be defined for this class. 
	
	
	/**
	 * Reads the given file and add it into a list. 
	 * Each element of the list contains one line of the file. 
	 * @param fileName is the name of the file that is read in this method. 
	 * @throws FileNotFoundException 
	 */

	public static final List<String> readFile(String fileName)  {
		//Initialize local variable.
		Scanner scannerObj = null;
		try {
			scannerObj = new Scanner(new File(fileName));  //Takes a file input from the user.
		} catch (FileNotFoundException e) {                //Makes sure there is a file being inputed.
			e.printStackTrace();
		}
		
		List<String> list = new ArrayList<String>();       //Creates a list.
		while (scannerObj.hasNextLine()) {                 //loops through every line and adds it in the list until there aren't anymore.
			list.add(scannerObj.nextLine());
		}
		scannerObj.close();                                //Closes the input.             
		return list;
	}
		
	/**
	 * This method adds the <code> obj </code> to the container.
	 * @param obj is the object that is added to the container.
	 */
	
	void add(Object object) {
	
	}

	/**
	 * This method removes the object from the container
	 * @return returns the removed object.
	 */
	Object remove() {
		// insert your code here. You may want to change the return value. 
		return null;
	}

	/**
	 * @return It returns the number of elements in the container.
	 */
	int getSize() {
		// insert your code here. You may need to change the return value. 
		return 0;
	}

}

/**
 * 
 * This class simulates a Queue, which is a data structure that insert and remove data 
 * by FIFO (first-in, first-out) rule
 *
 */
class Queue extends Container{
	ArrayList<String> queue; 
	
	/**
	 * This is the constructor that initializes the <code> queue </code>
	 * with all the strings in the <code> fileName </code> that is labeled 
	 * by "Queue"
	 * @param fileName is the name of the file that is read.  
	 * @throws FileNotFoundException 
	 */
	public Queue(String fileName) {
		this.queue = new ArrayList<String>();             //Creating queue.
		
		Scanner scannerObj = null;
		try {                                             
			scannerObj = new Scanner(new File(fileName)); //Accepts input from user.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String temp;
		while (scannerObj.hasNextLine()) {                //loops through every line and adds it in the list until there aren't anymore.
			temp = scannerObj.nextLine();
			if (temp.contains("Queue")) {                 //Only adds lines that have the term Queue written in them.
				temp = temp.substring(5).trim();          //Gets rid of the undesired parts.
				this.queue.add(temp);
			}
		}
		scannerObj.close();                               //Closes the input.
	}
	
	/**
	 * This method adds the object into the Queue. 
	 * Please note that the rule of the queue insertion/removal is 
	 * First in, First out. 
	 * @param obj is the object that is added to the queue. 
	 */
	@Override
	public void add(Object obj) {
		this.queue.add((String) obj);
	}
	/**
	 * This method removes an object from the Queue. 
	 * Please note that the rule of the queue insertion/removal is 
	 * First in, First out. 
	 */
	@Override
	public Object remove() {
		Object temp = this.queue.remove(0);
		return temp; 
	}
	/**
	 * @return returns the object which is in front of the queue.
	 */
	public Object top() {
		// insert your code here. You may want to change the return value. 
		return this.queue.get(0);
	}
	
	/**
	 * Returns the number of items in the queue.
	 */
	@Override 
	public int getSize(){
		// insert your code here. You may want to change the return value. 
		return this.queue.size();
	}

	@Override
	public String toString() {
		String temp = "";
		for (int i = 0; i < getSize(); i++) {
			temp = temp + "[" + this.queue.get(i) + "] ";
		}
		return temp;
	}
	
	
}

/**
 * 
 * This class simulates a Stack, which is a data structure that insert and remove data 
 * by FILO (first-in, last-out) rule
 *
 */
class Stack extends Container{
	ArrayList<String> stack; 
	
	/**
	 * This is the constructor that initializes the <code> stack </code>
	 * with all the strings in the <code> fileName </code> that is labeled 
	 * by "Stack"
	 * @param fileName is the name of the file that is read.  
	 */
	public Stack(String fileName) {
		this.stack = new ArrayList<String>();              //Creating Stack.
		Scanner scannerObj = null;
		try {
			scannerObj = new Scanner(new File(fileName));  //Accepts input from user.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String temp;
		while (scannerObj.hasNextLine()) {                 //loops through every line and adds it in the list until there aren't anymore.
			temp = scannerObj.nextLine();                  //Only adds lines that have the term Stack written in them.
			if (temp.contains("Stack")) {
				temp = temp.substring(5).trim();           //Gets rid of the undesired parts.
				this.stack.add(temp);
			}
		}
		scannerObj.close();                                //Closes the input.
	}
	/**
	 * This method removes an object from the stack. 
	 * Please note that the rule of the stack insertion/removal is 
	 * First in, Last out. 
	 */

	@Override
	public void add(Object obj) {
		this.stack.add((String) obj);
	}
	
	/**
	 * This method removes an object from the stack. 
	 * Please note that the rule of the stack insertion/removal is 
	 * First in, Last out. 
	 */

	@Override
	public Object remove() {
		Object temp = this.stack.remove(getSize() - 1); 
		return temp; 	
	}
	/**
	 * @return returns the object which is on top of the stack.
	 */
	
	public Object top() { 
		Object temp = this.stack.get(getSize() - 1);
		return temp;
	}
	/**
	 * Returns the number of items in the stack.
	 */
	@Override 
	public int getSize() {
		return this.stack.size();
	}
	
	@Override
	public String toString() {
		String temp = "";
		for (int i = 0; i < getSize(); i++) {
			temp = temp + "[" + this.stack.get(i) + "] ";
		}
		return temp;
	}
	
}