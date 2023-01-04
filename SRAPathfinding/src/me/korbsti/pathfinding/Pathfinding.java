package me.korbsti.pathfinding;

import org.bukkit.plugin.java.JavaPlugin;

import me.korbsti.pathfinding.api.ShortestPathAvoidMat;
import me.korbsti.pathfinding.api.ShortestPathFollow;
import me.korbsti.pathfinding.api.ShortestPath;
import me.korbsti.pathfinding.utils.Distance;
import me.korbsti.pathfinding.utils.ShortestDistance;
import me.korbsti.pathfinding.utils.ShortestDistanceFollow;
import me.korbsti.pathfinding.utils.ShortestDistanceIgnore;

public class Pathfinding extends JavaPlugin {
	
	
	
	
	
	public Distance dis = new Distance(this);
	public ShortestDistance shortestDistance = new ShortestDistance(this);
	public ShortestPathAvoidMat shortestPathAvoidMat = new ShortestPathAvoidMat(this);
	public ShortestPath shortestPath = new ShortestPath(this);
	public ShortestPathFollow shortestPathFollow = new ShortestPathFollow(this);
	public ShortestDistanceIgnore shortestDistanceIgnore = new ShortestDistanceIgnore(this);
	public ShortestDistanceFollow shortestDistanceFollow = new ShortestDistanceFollow(this);

	public static Pathfinding pathfinding;

	
	@Override
	public void onEnable() {
		pathfinding = this;
	}
	
	
	@Override
	public void onDisable() {
		
	}
	
	
	public Pathfinding getInstance(Pathfinding instance) {
		return this;
	}
	
	
}
