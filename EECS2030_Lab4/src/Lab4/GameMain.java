package Lab4;

public class GameMain {

	public static void main(String[] args) {
		Role role1 = Role.getInstance('C');
		Player player1 = Player.getInstance("Jane", role1); 
		Role role2 = Role.getInstance('I'); 
		Player player2 = Player.getInstance("John", role2); 
		Player [] player = new Player[2]; 
		player[0] = player1; 
		player[1] = player2;
		Map map = Map.getInstance("theSkeld", "miraHq", "polus", "airShip"); 
		
		Game game = Game.getInstance();
		game.setMap(map);
		game.setPlayer(player);
		
		System.out.println(game.getMap().airShip);
		System.out.println(map.airShip);

	}

}
