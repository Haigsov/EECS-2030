package Lab8;
import java.util.List;
import java.util.ArrayList;

/**
 * This class contains a number of methods that 
 * works on a wide range of data types. 
 */

/* This is a parameterized class. You should decide 
* what the parameter should look like in order to 
* pass all the test cases. 
*/
public class Utility <T> {  //Change this line to create a parameterized class
	List<T> list;   //Create class variable list.
	
	
	/**
	 * The default Constructor for class Utility.
	 */
	public Utility() {
		this.list = new ArrayList<T>();
	}
	
	/**
	 * The copy constructor for class Utility.
	 * @param otherList is the list that the constructor is deep copying.
	 */
	@SuppressWarnings("unchecked")
	public Utility(List<T> otherList) {
		this.list = (List<T>) new ArrayList<T>(otherList).clone();
		
	}
	
	/**
	 * Searches for a certain item in a list.
	 * @param <T> accepts any type of input.
	 * @param item is the item that the method is trying to search for.
	 * @return returns item's position if found in the list. Otherwise returns -1.
	 */
	@SuppressWarnings({ "unchecked", "hiding", "rawtypes" })
	public <T extends Comparable> int linearSearch(T item)   {
		int position = -1;
			for (int i = 0; i <list.size(); i++) {
				if (item.compareTo( list.get(i)) == 0) {
					position = i;
					break;
				}
			}
		return position;
	}
	
	
	/**
	 * Gets a list of some type and adds all the items in the instance variable at the end of list.
	 * @param list is the list with the items that the method is adding to the end of list.
	 */
	public void mergeList(List<? super T> list) {
		for (T obj: this.list) {
			list.add(obj);
		}
	}
	
	
	/**
	 * If the instance variable list has the same elements as the input variable list then it adds all the elements
	 * to the instance variable and return true. Else it returns false.
	 * @param list is the input variable that the method is trying to check if all it's elements are in the instance
	 * variable.
	 * @return true or false.
	 */
	public boolean containList(List<T> list) {
		if (this.list.containsAll(list)) {
				this.list = new ArrayList<T>();
				for (T obj: list) {
					this.list.add(obj);
				}
				return true;
			}
		else {
			return false;
		}
	}
	
	
	/**
	 * Checks if there is a zero in the input variable myList by looping through it and if there is to remove it.
	 * Otherwise do nothing.
	 * @param <T> Lets the method accept any type of input.
	 * @param myList the input variable for the list that the method is looping through to remove a zero if there is
	 * any.
	 */
	@SuppressWarnings("hiding")
	public <T> void removeZero(List<T> myList) {
		for (int i = 0; i < myList.size(); i++) {
			if (MyInteger.isZero( (MyInteger) myList.get(i))) {
				myList.remove(i);
				i--;
			}
		}
	}
	
	
	
	
	
	
	
}
/**
 * This class implements a 2D points. 
 */
class Point implements Comparable<Point>{
	double x; 
	double y; 
	public Point(double x, double y) {
		this.x = x; 
		this.y = y;
	}
	@Override 
	public int compareTo(Point p) {
		if (this.x == p.x && this.y == p.y) return 0;
		else if (this.x == p.x) return 1;
		else return -1;	
	}
}

/**
 * This class represents an Integer number. 
 */
class MyInteger implements Comparable<MyInteger>{
	int x; 
	public MyInteger() {
		this.x = 0; 
	}
	public MyInteger(int x) {
		this.x = x; 
	}
	public static boolean isZero(MyInteger input) {
		return input.x == 0; 
	}
	@Override
	public int compareTo(MyInteger input) {
		if (this.x == input.x ) return 0;
		else if (this.x < input.x) return -1;
		else return 1;	
	}
}
/**
 * This class represents a subset of integer numbers that are natural numbers. 
 */
class NaturalNumber extends MyInteger{
	public NaturalNumber(int x) {
		super(x);
	}
}
/**
 * This class represents the odd natural numbers. 
 */

class OddNumber extends NaturalNumber{
	public OddNumber(int x) {
		super(x);
	}
	
}
/**
 * This class represents the even natural numbers. 
 */
class EvenNumber extends NaturalNumber{
	public EvenNumber(int x) {
		super(x);
	}
}