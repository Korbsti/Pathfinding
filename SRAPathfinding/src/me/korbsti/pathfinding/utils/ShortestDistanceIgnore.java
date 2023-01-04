package me.korbsti.pathfinding.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import me.korbsti.pathfinding.Pathfinding;

public class ShortestDistanceIgnore {
	
	Pathfinding plugin;
	
	public ShortestDistanceIgnore(Pathfinding instance) {
		this.plugin = instance;
	}
	
	public ArrayList<Location> nextShortestDistance(Location from, Location to, double precision, ArrayList<Location> blockedLocations, ArrayList<Material> blockedMats) {
		ArrayList<Double> distance;
		distance = new ArrayList<Double>();
		ArrayList<Integer> placeholder;
		placeholder = new ArrayList<Integer>();
		int x;
		x = 0;
		ArrayList<Location> returning = new ArrayList<Location>();
		////
		from.add(precision, 0, 0);
		if (!blockedLocations.contains(from)) {
			if (locationBlocked(from, blockedMats)) {
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(-precision, 0, 0);
				return returning;
			}
		}
		if (blockedLocations.contains(from)) {
			++x;
		}  else {
			distance.add(plugin.dis.distance(from, to));
			placeholder.add(0);			
		}
		from.add(-precision, 0, 0);
		from.add(-precision, 0, 0);
		if (!blockedLocations.contains(from)) {
			if (locationBlocked(from, blockedMats)) {
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(precision, 0, 0);
				return returning;
			}
		}

		if (blockedLocations.contains(from)) {
			++x;
		}  else {
			distance.add(plugin.dis.distance(from, to));
			placeholder.add(1);
		}
		from.add(precision, 0, 0);
		from.add(0, precision, 0);
		if (!blockedLocations.contains(from)) {
			if (locationBlocked(from, blockedMats)) {
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(0, -precision, 0);
				return returning;
			}
		}
		if (blockedLocations.contains(from)) {
			x++;
		}  else {
			distance.add(plugin.dis.distance(from, to));
			placeholder.add(2);
		}
		from.add(0, -precision, 0);
		from.add(0, -precision, 0);
		if (!blockedLocations.contains(from)) {
			if (locationBlocked(from, blockedMats)) {
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(0, precision, 0);
				return returning;
			}
		}
		if (blockedLocations.contains(from)) {
			x++;
		}  else {
			distance.add(plugin.dis.distance(from, to));
			placeholder.add(3);
		}
		from.add(0, precision, 0);
		from.add(0, 0, precision);
		if (!blockedLocations.contains(from)) {

			if (locationBlocked(from, blockedMats)) {
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(0, 0, -precision);
				return returning;
			}
		}

		if (blockedLocations.contains(from)) {
			x++;
		}  else {
			distance.add(plugin.dis.distance(from, to));
			placeholder.add(4);
		}
		from.add(0, 0, -precision);
		from.add(0, 0, -precision);
		if (!blockedLocations.contains(from)) {
			if (locationBlocked(from, blockedMats)) {
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(0, 0, precision);
				return returning;
			}
		}
		if (blockedLocations.contains(from)) {
			x++;
		} else {
			distance.add(plugin.dis.distance(from, to));
			placeholder.add(5);
		}
		if(x == 6) {
			returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
			returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
			returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
			from.add(0, 0, precision);
			return returning;
		}
		from.add(0, 0, precision);
		for(int i = 0; i < placeholder.size() - 1; i++) {
			if(distance.get(i) > distance.get(i + 1)) {
				distance.add(distance.get(i));
				placeholder.add(placeholder.get(i));
				distance.remove(i);
				placeholder.remove(i);
				i = -1;
			}
		}		
		switch (placeholder.get(0)) {
			case 0:
				from.add(precision, 0, 0);
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(-precision, 0, 0);
				return returning;
			case 1:
				
				from.add(-precision, 0, 0);
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(precision, 0, 0);
				return returning;
			case 2:
				from.add(0, precision, 0);
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(0, -precision, 0);
				return returning;
			case 3:
				
				from.add(0, -precision, 0);
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(0, precision, 0);
				return returning;
			case 4:
				
				from.add(0, 0, precision);
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(0, 0, -precision);
				return returning;
			case 5:
				
				from.add(0, 0, -precision);
				returning.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
				from.add(0, 0, precision);
				return returning;
			default:
				return null;
		}
		
	}
	
	public boolean locationBlocked(Location loc, ArrayList<Material> mat) {
		return mat.contains(loc.getBlock().getType());
		
	}
	
}
