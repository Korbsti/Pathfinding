package me.korbsti.pathfinding.utils;

import org.bukkit.Location;

import me.korbsti.pathfinding.Pathfinding;

public class Distance {
	
	Pathfinding plugin;
	
	public Distance(Pathfinding instance) {		
		this.plugin = instance;
	}
	
	public Double distance(Location from, Location to) {
		return Math.sqrt(Math.pow(to.getX() - from.getX(), 2) + Math.pow(to.getY() - from.getY(), 2) + Math.pow(to.getZ() - from.getZ(),2));
	}
	
	
	
}
