import java.util.ArrayList;

// Stores an arraylist of arrays which determine the monsters' spawns, round number, any special events, etc... for a given round
public class Round {
	private int roundNumber;
	private ArrayList<Integer> spawnTimes; // millis
	private ArrayList<ArrayList<Integer>> spawnPacks; // the inner arraylist holds ids for different monsters to spawn at the next "spawnTimes" element
	private ArrayList<ArrayList<Integer>> spawnPacksX;
	private ArrayList<ArrayList<Integer>> spawnPacksY;
	private boolean roundOver;
	private boolean roundStarted;
	private Timer roundTimer;
	private int totRoundMonsters;
	private int roundWinTime;
	
	/**
	 * @param rn - Round Number: The number of the given round
	 * @param st - Spawn Times: Contains the times for when to spawn the next pack
	 * @param sp - Spawn Packs: The packs to be spawned at specific spawn times
	 * @param spx - Spawn Packs X: The x values of the packs
	 * @param spy - Spawn Packs Y: The y values of the packs
	 * @param rwt - Round Win Time: The amount of time required to elapse in order to win the round
	 */
	
	public Round(int rn, ArrayList<Integer> st, ArrayList<ArrayList<Integer>> sp, ArrayList<ArrayList<Integer>> spx, ArrayList<ArrayList<Integer>> spy, int rwt){
		roundNumber = rn;
		spawnTimes = st;
		spawnPacks = sp;
		spawnPacksX = spx;
		spawnPacksY = spy;
		roundOver = false;
		roundStarted = false;
		roundWinTime = rwt;
		roundTimer = new Timer();
		calcTotRoundMonsters();
		
	}
	
	public int getRoundNumber(){
		return roundNumber;
	}
	public void setRoundNumber(int n){
		roundNumber = n;
	}
	public ArrayList<Integer> getSpawnTimes(){
		return spawnTimes;
	}
	public void setSpawnTimes(ArrayList<Integer> st){
		spawnTimes = st;
	}
	public ArrayList<ArrayList<Integer>> getSpawnPacks(){
		return spawnPacks;
	}
	public void setSpawnPacks(ArrayList<ArrayList<Integer>> sp){
		spawnPacks = sp;
	}
	public ArrayList<ArrayList<Integer>> getSpawnPacksX(){
		return spawnPacksX;
	}
	public void setSpawnPacksX(ArrayList<ArrayList<Integer>> spx){
		spawnPacksX = spx;
	}
	public ArrayList<ArrayList<Integer>> getSpawnPacksY(){
		return spawnPacksY;
	}
	public void setSpawnPacksY(ArrayList<ArrayList<Integer>> spy){
		spawnPacksY = spy;
	}
	public boolean getRoundOver(){
		return roundOver;
	}
	public void setRoundOverTrue(){
		roundOver = true;
	}
	public boolean getRoundStarted(){
		return roundStarted;
	}
	public void start(){
		roundStarted = true;
	}
	public Timer getRoundTimer(){
		return roundTimer;
	}
	private void calcTotRoundMonsters(){
		for(ArrayList<Integer> k : spawnPacks){
			totRoundMonsters += k.size();
		}
	}
	public int getTotRoundMonsters(){
		return totRoundMonsters;
	}
	public int getRoundWinTime(){
		return roundWinTime;
	}

}
