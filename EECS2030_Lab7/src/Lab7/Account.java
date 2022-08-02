package Lab7;
import java.util.Date;
import java.util.Objects;


public abstract class Account implements Comparable {
	// define the instance variables based on the given UML, here.
	protected int accountNo;
	protected double balance;
	protected String fullName;
	protected Date dateOpened;
	protected double maxTransferable;
	
	/**
	 * This method deposit <code> amount </code> to this account.
	 * @param amount is the amount that is deposited to this account. 
	 * @throws NotEnoughMoneyException 
	 */
	public abstract void deposit(double amount);

	
	
	/**
	 * This method withdraw <code> amount </code> from this account.
	 * @param amount is the amount that should be withdrawn from this account
	 * @return It returns true if the transaction is done successfully. 
	 * @throws Exception This methods may throw either <code> NotEnoughMoneyException </code> or
	 * <code> TransferNotAllowedException </code>.
	 */
	public abstract boolean withdraw(double amount) throws TransferNotAllowedException, NotEnoughMoneyException;

	
	/**
	 * This method transfers money from this account to the given account.
	 * @param to is the destination account, where the money is deposited to.
	 * @param amount is the amount of money that is transfered. 
	 * @return It returns true if the transaction is successful. 
	 * @throws NotEnoughMoneyException 
	 * @throws TransferNotAllowedException 
	 * @throws Exception This methods may throw either <code> NotEnoughMoneyException </code> or
	 * <code> TransferNotAllowedException </code>.
	 */

	
	public boolean transferFrom(Account to, double amount) throws NotEnoughMoneyException, TransferNotAllowedException {
		
		if (amount > this.balance) throw new NotEnoughMoneyException();
		if (amount > this.maxTransferable) throw new TransferNotAllowedException();
		this.balance = this.balance - amount;
		to.balance = to.balance + amount;
		return true;
		
	}
	
	
	/**
	 * This is the accessor method for <code> accountNo </code>
	 * @return It returns the accountNo of this account.
	 */

	public int getAccountNo() {
		return this.accountNo;
	}
	
	/**
	 * This is the accessor method for <code> balance </code>
	 * @return It returns the balance of the account.
	 */

	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * This is the accessor method for <code> fullName </code>
	 * @return It returns the name of the holder of the account.
	 */

	public String getFullName() {
		return this.fullName;
	}
	
	/**
	 * This is the accessor method for <code> dateOpened </code>
	 * @return It returns the date at which the account was opened.
	 */

	public Date getDateOpened() {
		return this.dateOpened;
	}
	
	/**
	 * This is the accessor method for <code> maxTransferable </code>
	 * @return It returns the maximum allowed amount that can be withdrawn from this account in one transaction.
	 */

	public double getMaxTransferable() {
		return this.maxTransferable;
	}
	
	
	


	/**
	 * This method compares two accounts. If the two accounts have the same values
	 * for all the instance variables, they are considered, equal and this 
	 * method returns 0. If two objects were not equal, the account whose accountNo is less, 
	 * is the smaller object so this method returns -1. Otherwise it returns 1.
	 * @param object is an object of type account. 
	 * @return<pre> It returns 0, if the two objects are equal. 
	 * It returns -1, if this object is less than the object that is passed as a parameter into the method. 
	 * It returns 1, if this object is greater than the object that is passed as a parameter into the method<pre>. 
	 */
	public int compareTo(Object other) {
		Account temp = (Account) other;
		if (temp.accountNo == this.accountNo
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(temp.balance)
				&& Objects.equals(dateOpened, temp.dateOpened) && Objects.equals(fullName, temp.fullName)
				&& Double.doubleToLongBits(maxTransferable) == Double.doubleToLongBits(temp.maxTransferable)) {
			return 0;
		}
		
		else if (temp.accountNo > this.accountNo) {
			return -1;
		}
		
		else {
			return 1;
		}
	}


}

	
// Implement class Current based on the given UML in the description. 
	
class Current extends Account {
	
	/**
	 * This is the overloaded constructor for this class.
	 * @param accountNo is an int that represents the account's number.
	 * @param balance is an int that represents the account's balance.
	 * @param fullName is a String that represents the account holder's name.
	 * @param dateOpened is a Date data type that shows the date when the account was opened.
	 * @param maxTransferable is an that represents the max amount of money that can be transferred.
	 */
	public Current(int accountNo, double balance, String fullName, Date dateOpened, double maxTransferable) {
		this.accountNo       = accountNo;
		this.balance         = balance;
		this.fullName        = fullName;
		this.dateOpened      = dateOpened;
		this.maxTransferable = maxTransferable;
	}
	
	
	/**
	 * makes hashCode.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(accountNo, balance, dateOpened, fullName, maxTransferable);
	}


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;
		return accountNo == other.accountNo
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(dateOpened, other.dateOpened) && Objects.equals(fullName, other.fullName)
				&& Double.doubleToLongBits(maxTransferable) == Double.doubleToLongBits(other.maxTransferable);
	}
	
		
	/**
	 * This method withdraw <code> amount </code> from this account.
	 * @param amount is the amount that should be withdrawn from this account
	 * @return It returns true if the transaction is done successfully. 
	 * @throws Exception This methods may throw either <code> NotEnoughMoneyException </code> or
	 * <code> TransferNotAllowedException </code>.
	 */
	
	public void deposit(double amount)  {
		this.balance = this.balance + amount; 		
	}
	
	/**
	 * This method transfers money from this account to the given account.
	 * @param to is the destination account, where the money is deposited to.
	 * @param amount is the amount of money that is transfered. 
	 * @return It returns true if the transaction is successful. 
	 * @throws NotEnoughMoneyException 
	 * @throws TransferNotAllowedException 
	 * @throws Exception This methods may throw either <code> NotEnoughMoneyException </code> or
	 * <code> TransferNotAllowedException </code>.
	 */
	
	public boolean withdraw(double amount) throws TransferNotAllowedException, NotEnoughMoneyException{
		
			if (amount > this.balance) throw new NotEnoughMoneyException();
			if (amount > this.maxTransferable) throw new TransferNotAllowedException();
			this.balance = this.balance - amount;
			return true;
		
	}
	
	
	
	
}
//Define the Exception classes here

class TransferNotAllowedException extends Exception {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransferNotAllowedException() {
		super();
	}
	
	public TransferNotAllowedException(String message) {
		super(message);
	}
	
}


class NotEnoughMoneyException extends Exception {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughMoneyException() {
		super();
	}
	
	public NotEnoughMoneyException(String message) {
		super(message);
	}
	
}

