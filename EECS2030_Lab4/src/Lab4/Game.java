package Lab4;

/**
 * This class implements a game like 'among us'
 * Author: Haig Souvalian
 * Student ID: 218186510
 */
public class Game {

	//Declares variables.
	public Player[] player;
	public Map map;
	
	
	/**
	 * This is the default constructors. 
	 * At most 10 player can play this game. 
	 * At most there are 4 areas that players can play in.
	 */

	private Game() {
		this.player = new Player[10];
		this.map    = null;
	}
	
	
	/**
	 * This is the overloaded constructor for this class
	 * @param player is an array containing all the players who entered the game.
	 * @param map is the area that is available to the players to play.
	 */
	

	private Game(Player[] player, Map map) {
		//Declares temporary variables.
		String tempName;
		Role tempRole;
		String tempMap1 = new String(map.theSkeld);
		String tempMap2 = new String(map.miraHq);
		String tempMap3 = new String(map.polus);
		String tempMap4 = new String(map.airShip);
		
		//The loop goes through each array element and deep copies.
		this.player = new Player[player.length];
		for (int i = 0; i < player.length; i++) {
			tempName = new String(player[i].name);        //Makes a copy by creating a new string.
			tempRole = Role.getInstance(player[i].role);  //Makes a copy by calling for the getInstance role.
			
			this.player[i] = Player.getInstance(tempName, tempRole);
		}
		this.map = Map.getInstance(tempMap1, tempMap2, tempMap3, tempMap4);
	}
	
	
	
	/**
	 * This the copy constructor for this class
	 * @param game is an object of Game, whose component is deeply copied into 
	 * the component of this object.
	 */

	
	private Game(Game other) {
		//Declares temporary variables.
		String tempName;
		Role tempRole;
		String tempMap1 = new String(other.map.theSkeld);
		String tempMap2 = new String(other.map.miraHq);
		String tempMap3 = new String(other.map.polus);
		String tempMap4 = new String(other.map.airShip);
		
		//The loop goes through each array element and deep copies.
		this.player = new Player[other.player.length];
		for (int i = 0; i < other.player.length; i++) {
			tempName = new String(other.player[i].name);        //Makes a copy by creating a new string.
			tempRole = Role.getInstance(other.player[i].role);  //Makes a copy by calling for the getInstance role.
			
			this.player[i] = Player.getInstance(tempName, tempRole);
		}
		this.map = Map.getInstance(tempMap1, tempMap2, tempMap3, tempMap4);
	}
	
	
	
	/**
	 * This is a static factory method
	 * @return IT returns an object of Game
	 */

	public static Game getInstance() {
		Game temp = new Game();
		return temp;
	}
	
	/**
	 * This is a static factory method
	 * @param player is an array that contains players.
	 * @param map is a map of the game
	 * @return It returns an object of Game made using the input parameters.
	 */

	public static Game getInstance(Player[] player, Map map) {
		Game temp = new Game(player, map);
		return temp;
	}
	
	/**
	 * This is a static factory method
	 * @param game is an object of Game
	 * @return it returns an object of Game made using the input parameter. 
	 */

	public static Game getInstance(Game other) {
		Game temp = new Game(other.player, other.map);
		return temp;
	}
	
	/**
	 * This is the getter method for the player list.
	 * @return returns an array containing all the players of this game. 
	 */

	public Player[] getPlayer() {
		return this.player;
	}
	
	
	
	/**
	 * This is the getter method for the map attribute.
	 * @return Returns an object of map containing all the components of this game's map.
	 */


	public Map getMap() {
		return this.map;
	}
	
	
	/**
	 * This is the setter method for the player attribute, which deeply copies 
	 * the input parameter into the player attribute of this object. 
	 * @param player is an array of Player, whose elements are copied in the player attribute of this object.
	 */
	

	public void setPlayer(Player[] player) {
		//Declares temporary variables.
		String tempName;
		Role tempRole;
		
		//The loop goes through each array element and deep copies.
		this.player = new Player[player.length];
		for (int i = 0; i < player.length; i++) {
			tempName = new String(player[i].name);        //Makes a copy by creating a new string.
			tempRole = Role.getInstance(player[i].role);  //Makes a copy by calling for the getInstance role.
			
			this.player[i] = Player.getInstance(tempName, tempRole);
		}
	}
	
	
	
	/**
	 * This is the setter method for the map attribute, which deeply copies 
	 * the input parameter into the map attribute of this object.
	 * @param map is an object of Map, whose attributes are copied in the map attribute of this object.
	 */

	public void setMap(Map map) {
		String tempMap1 = new String(map.theSkeld);
		String tempMap2 = new String(map.miraHq);
		String tempMap3 = new String(map.polus);
		String tempMap4 = new String(map.airShip);
		
		this.map = Map.getInstance(tempMap1, tempMap2, tempMap3, tempMap4);
		
}
}



/**
 * 
 * This class implements all a player requires to play in this game. 
 *
 */
class Player {
	
	public String name;
	public Role role;
	
	/**
	 * This is the overloaded constructor for this class
	 * @param name
	 * @param role
	 */

	private Player(String name, Role role) {
		this.name = name;
		this.role = role;
	}
	
	
	
	/**
	 * This is the copy constructor for this class
	 * @param player
	 */

	private Player(Player other) {
		this.name = other.name;
		this.role = other.role;
	}
	
	
	
	/**
	 * This is a static factory method.
	 * @return It returns an object of Player
	 */

	public static Player getInstance() {
		Player temp = new Player("", null);
		return temp;
	}
	
	
	/**
	 * This is a static factory method
	 * @param name is the name of player
	 * @param role is the role of the palyer in the game
	 * @return It returns an object of player, which is made by the two input variables. 
	 */

	public static Player getInstance(String name, Role role) {
		Player temp = new Player(name, role);
		return temp;
	}
	
	
	/**
	 * This is a static factory method
	 * @param player is an object of player
	 * @return it returns an object of player that is made using the input parameter. 
	 */

	public static Player getInstance(Player player) {
		Player temp = new Player(player.name, player.role);
		return temp;
	}
	
	
	/**
	 * This is the getter method for attribute name
	 * @return returns the value of attribute name
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * This is the getter method for attribute role
	 * @return returns the reference to attribute role.
	 */

	public Role getRole(){
		return role;
	}
	
	/**
	 * This is the setter method for attribute name
	 * @param name  is the value that is used to initialize name attribute
	 */

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This is the setter method for attribute role
	 * @param role is the object, whose reference is used to initialize attribute role.
	 */

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}


/**
 * This class implements the areas in which players can play.
 *
 */
class Map{

	public String theSkeld;
	public String miraHq;
	public String polus;
	public String airShip;
	

	/**
	 * This is the overloaded constructor.
	 * @param theSkeld is the first area in which player can play.
	 * @param miraHq is the second area in which player can play.
	 * @param polus is the third area in which player can play.
	 * @param airShip  is the fourth area in which player can play.
	 */

	private Map(String theSkeld, String miraHq, String polus, String airShip) {
		this.theSkeld = theSkeld;
		this.miraHq = miraHq;
		this.polus = polus;
		this.airShip = airShip;
	}
	
	/**
	 * This is a static factory method that initializes the attributes to their default values. 
	 * @return It returns an object of MAP
	 */

	public static Map getInstance() {
		Map temp = new Map("", "", "", "");
		return temp;
	}
	
	/**
	 * This is a static factory method
	 * @param theSkeld is the first area in which player can play.
	 * @param miraHq is the second area in which player can play.
	 * @param polus is the third area in which player can play.
	 * @param airShip  is the fourth area in which player can play.
	 * @return it returns an object of MAP.
	 */

	public static Map getInstance(String theSkeld, String miraHq, String polus, String airShip) {
		 Map temp = new Map(theSkeld, miraHq, polus, airShip);
		 return temp;
	}
	
	/**
	 * This is a static factory method
	 * @param other is a Map object type
	 * @return it return an object of MAP
	 */
	public static Map getInstance(Map other) {
		 Map temp = new Map(other.theSkeld, other.miraHq, other.polus, other.airShip);
		 return temp;
	}
	
	/**
	 * This is the copy constructor.
	 * @param map is an object of Map that is used to initialize this object.
	 */
	
	private Map(Map other) {
		this.theSkeld = other.theSkeld;
		this.miraHq = other.miraHq;
		this.polus = other.polus;
		this.airShip = other.airShip;
	}

}



/**
 * 
 * This class presents the role of the players, 
 * which can be either imposter or crewmate. 
 *  Imposter role, gets the value of 'i'and 
 *  Cremate gets the value of 'c'
 *
 */
class Role{
	public char role;

	
	/**
	 * This is the overloaded constructor.
	 * @param role is used as an initial value of the instance variable role
	 */
	private Role(char role) {
		this.role = role;
	}

	
	
	/**
	 * This is a static factory method.
	 * @param role is the initial value of the role
	 * @return it returns a reference to an object that is created in this factory method.
	 */

	public static Role getInstance() {
		Role temp = new Role(' ');
		return temp;
	}
	
	/**
	 * This is a static factory method.
	 * @param role is the initial value of the role
	 * @return it returns a reference to an object that is created in this factory method.
	 */

	public static Role getInstance(char role) {
		Role temp = new Role(role);
		return temp;
	}
	
	 /**
	 * This is the copy constructor. 
	 * @param role is an object of Role that is used to initialize the instance variable role.
	 */

	private Role(Role other) {
		this.role = other.role;
	}
	
	/**
	 * This is a static factory method
	 * @param role is a variable of type Role
	 * @return it returns an object of Role.
	 */

	public static Role getInstance(Role role) {
		Role temp = new Role(role);
		return temp;
	}
	
	/**
	 * This is the getter method for the role
	 * @return It returns the role of the player
	 */

	public char getRole() {
		return this.role;
	}
	
	
	/**
	 * This is the setter method for the role
	 * @param role is the initial value for attribute role
	 */
	public void setRole(char role) {
		this.role = role;
	}
}
