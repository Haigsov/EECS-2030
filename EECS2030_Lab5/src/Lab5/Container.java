package Lab5;

//Parent Class
public class Container {
	// Do not change the value of the following constants.
	protected final int ORIGINAL_SIZE = 10; 
	protected Object[] list; // is a container that stores the element of MyList
	protected Object[] set; // is a container that stores the element of MySet
	protected int size; // this variable holds the actual number of elements that are stored in either of the containers (i.e. MyList or MySet).
	/**
	 * This method adds the <code> obj </code> to the end of the container.
	 * @param obj is the object that is added to the container.
	 */
	void add(Object obj) {
		
		System.out.println("The object was added to the contianer");
	}
	/**
	 * This method removes the <code> obj </code> from the container. 
	 * It shifts all the elements to make sure that removal of the element
	 * does not create a whole in the container.
	 * @param obj is the object that is removed from the container. 
	 * @return It returns the object that was removed.
	 */
	Object remove(Object obj) {
		System.out.println("The object was removed from the container.");
		return obj;
	}
	/**
	 * This method returns true if the container is empty.
	 * @return It returns true if the container is empty, otherwise false.
	 */
	boolean isEmpty() {
		return this.size == 0;
	}
	/**
	 * This method returns the number of elements stored in the container.
	 * @return It returns the number of elements in the container.
	 */
	int getSize() {
		return this.size;
	}
	

}

/**
 * 
 * This class simulates an ArrayList, where you can add unlimited number of 
 * elements to the list.
 *
 */
class MyList extends Container{
	/**
	 * This is the default constructor that sets all the instance variables to their default value. 
	 */
	public MyList () {
		list= new Object[ORIGINAL_SIZE];
		size = 0; 
	}
	
	/**
	 * This method returns the element that is stored at index <code> index </code>.
	 * @param index is the<code> index </code> at which the element is accessed and returned. 
	 * @return it returns the element stored at the given <code> index </code>.
	 */
	public Object get(int index) {
		return this.list[index];
		//Container tempContainer = new Container();
		//return tempContainer.list[index];
	}
	/**
	 * This method overrides the <code> add </code> method defined in class <code> container</code>. By
	 * adding the <code> obj </code> to the back of the <code> list </code> array. 
	 * The original size of the <code> array </code> is defined by <code> ORIGINAL_SIZE </code>, however, it is possible that 
	 * more elements is added to this array. In case the array does not have enough capacity to add one more element, it grows itself 
	 * by doubling the size of <code> list </code> array.     
	 */
	@Override
	void add(Object obj) {
		//Checks if list's size needs to get doubled
		if ((this.list.length > 0) && (this.list.length % ORIGINAL_SIZE == 0)) {  //Checks to see if list is full.
			Object[] tempList = new Object[(this.list.length * 2)];               //Creates a tempList that has double the size of list.
			for (int i = 0; i < this.list.length; i++) {
				tempList[i] = list[i];
			}
			this.list = tempList;
		}
		this.list[size] = obj;
		size++;
	}

	
	/**
	 * This method removes the first occurrence of <code> obj </code>
	 * from <code> list </code>
	 * @pre <code> obj </code> exists in the <code>list</code> array.
	 * 
	 */
	@Override
	Object remove(Object obj) {
		Object[] tempList = new Object[size];   
		int j = 0;
		//Makes the first occurence null.
		for (int i = 0; i < size; i++) {
			if (this.list[i].equals(obj)) {
				this.list[i] = null;
				break;
			}
		}
		//Doesn't add null.
		for (int i = 0; i < size; i++) {
			if(this.list[i] != null) {
				tempList[j] = this.list[i];
				j++;
			}
		}
		//tempList[size] = null;
		this.list = tempList;
		size--;
		return this.list;
	}
	/**
	 * This method returns the elements of the MyList in a form of 
	 * [obj1 obj2 obj3 ...]
	 */
	@Override
	public String toString() {
		String output = "[";
		
		for (int i = 0; i < size - 1; i++) {
			output = output + this.list[i] + " ";
		}
		output = output + this.list[size - 1] + "]";
		return output;
	}


}

class MySet extends Container{
	public MySet() {
		set = new Object[ORIGINAL_SIZE];
		size = 0; 
	}
	
	/**
	 * This method overrrides the <code> add </code> method defined in class <code> container</code>. By
	 * adding the <code> obj </code> to the back of the <code> set </code> array. 
	 * The original size of the <code> set </code> is defined by <code> ORIGINAL_SIZE </code>, however, it is possible that 
	 * more elements is added to this set. In case the set does not have enough capacity to add one more element, it grows itself 
	 * by doubling the size of <code> set </code> array.      
	 */
	
	@Override
	void add(Object obj) {
		//Checks if it's already there.
		boolean available = false;
		for (int i = 0; i < size; i++) {
			if (this.set[i].equals(obj)) {
				available = true;
			}
		}
		//Adds it in the set if it's alreaady there.
		if(!available) {
			if ((this.set.length > 0) && (this.set.length % ORIGINAL_SIZE == 0)) {  //Checks to see if set is full.
				Object[] tempSet = new Object[(this.set.length * 2)];               //Creates a tempSet that has double the size of list.
				for (int i = 0; i < this.set.length; i++) {
					tempSet[i] = this.set[i];
				}
				this.set = tempSet;
			}
			this.set[size] = obj;
			size++;
		}
	}
	
	
	/**
	 * This method removes the first occurrence of <code> obj </code>
	 * from <code> set </code>
	 * @pre <code> obj </code> exists in the <code>set</code> array.
	 * 
	 */
	@Override
	Object remove(Object obj) {
		Object[] tempSet = new Object[size];   
		int j = 0;
		//Finds the obj we want to remove.
		for (int i = 0; i < size; i++) {
			if (this.set[i].equals(obj)) {
				this.set[i] = null;
				break;
			}
		}
		//Removes the obj.
		for (int i = 0; i < size; i++) {
			if(this.set[i] != null) {
				tempSet[j] = this.set[i];
				j++;
			}
		}
		//tempList[size] = null;
		this.set = tempSet;
		size--;
		return this.set;
	}
	
	/**
	 * This method returns the elements of the MySet in a form of 
	 * [obj1 obj2 obj3 ...]
	 */
	
	@Override
	public String toString() {
        String output = "[";
		
		for (int i = 0; i < size - 1; i++) {
			output = output + this.set[i] + " ";
		}
		output = output + this.set[size - 1] + "]";
		return output;
	}
	
}
