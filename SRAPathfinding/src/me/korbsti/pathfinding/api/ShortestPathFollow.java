package me.korbsti.pathfinding.api;

import java.time.Instant;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import me.korbsti.pathfinding.Pathfinding;

public class ShortestPathFollow {
	Pathfinding plugin;
	
	public ShortestPathFollow(Pathfinding instance) {
		this.plugin = instance;
	}
	
	public Instant start;

	
	
	public ArrayList<Location> returnPathFollow(Location from, Location to, double precision, ArrayList<Material> followMats, int timeout) {
		ArrayList<Location> blockedPath = new ArrayList<Location>();
		ArrayList<Location> currentPath = new ArrayList<Location>();
		start = Instant.now();
		currentPath.add(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
		int i = 0;
		int d = 0;
		double dis = plugin.dis.distance(from, to);
		while (dis > precision) {
			if (i > timeout) {
				return currentPath;
			}
			if (d == i + 1) {
				d++;
			}
			
			if (d > timeout) {
				return currentPath;
			}
			ArrayList<Location> l = plugin.shortestDistanceFollow.nextShortestDistance(currentPath.get(i), to,
			        precision, blockedPath, followMats);
			int size = l.size();
			if (size == 2) {
				blockedPath.add(new Location(l.get(0).getWorld(), l.get(0).getX(), l.get(0).getY(), l.get(0).getZ()));
				continue;
			}
			if (size == 3) {
				blockedPath.add(new Location(l.get(0).getWorld(), l.get(0).getX(), l.get(0).getY(), l.get(0).getZ()));
				currentPath.remove(i);
				i -= 1;
				continue;
			}
			blockedPath.add(new Location(l.get(0).getWorld(), l.get(0).getX(), l.get(0).getY(), l.get(0).getZ()));
			currentPath.add(new Location(l.get(0).getWorld(), l.get(0).getX(), l.get(0).getY(), l.get(0).getZ()));
			dis = plugin.dis.distance(currentPath.get(i), to);
			i++;
			
		}
		Bukkit.broadcastMessage("MS Time: " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
		return currentPath;
	}
	
}
