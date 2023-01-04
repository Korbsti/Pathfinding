package me.korbsti.pathfinding.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import me.korbsti.pathfinding.Pathfinding;

public class ShortestPath {
	Pathfinding plugin;
	
	public ShortestPath(Pathfinding instance) {
		this.plugin = instance;
	}
	
	public ArrayList<Location> returnShortestPath(Location from, Location to, double precision) {
		ArrayList<Location> currentPath = new ArrayList<Location>();
		currentPath.add(from);
		int i = 0;
		double dis = plugin.dis.distance(from, to);		
		while(dis > precision) {
			Location l = plugin.shortestDistance.nextShortestDistance(currentPath.get(i), to, precision);
			currentPath.add(new Location(l.getWorld(), l.getX(), l.getY(), l.getZ()));
			dis = plugin.dis.distance(currentPath.get(i), to);
			i++;
		}
		return currentPath;
	}
	
}
