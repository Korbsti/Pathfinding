package me.korbsti.pathfinding.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.korbsti.pathfinding.Pathfinding;

public class ShortestDistance {
	
	Pathfinding plugin;
	
	public ShortestDistance(Pathfinding instance) {
		this.plugin = instance;
	}
	
	public Location nextShortestDistance(Location from, Location to, double precision) {
		double nextShort = plugin.dis.distance(from, to);
		from.add(precision, 0, 0);
		double distance = plugin.dis.distance(from, to);
		from.add(-precision, 0, 0);
		int type = 0;
		if (distance < nextShort) {
			nextShort = distance;
		}
		from.add(-precision, 0, 0);
		distance = plugin.dis.distance(from, to);
		from.add(precision, 0, 0);
		if (distance < nextShort) {
			nextShort = distance;
			type = 1;
		}
		from.add(0, precision, 0);

		distance = plugin.dis.distance(from, to);
		from.add(0, -precision, 0);
		if (distance < nextShort) {
			nextShort = distance;
			type = 2;
		}
		from.add(0, -precision, 0);

		distance = plugin.dis.distance(from, to);
		from.add(0, precision, 0);
		if (distance < nextShort) {
			nextShort = distance;
			type = 3;
		}
		from.add(0, 0, precision);

		distance = plugin.dis.distance(from, to);
		from.add(0, 0, -precision);
		if (distance < nextShort) {
			nextShort = distance;
			type = 4;
		}
		from.add(0, 0, -precision);

		distance = plugin.dis.distance(from, to);
		from.add(0, 0, precision);
		if (distance < nextShort) {
			nextShort = distance;
			type = 5;
		}

		switch (type) {
			case 0:
				from.add(precision, 0, 0);
				return from;
			case 1:
				from.add(-precision, 0, 0);
				return from;
			case 2:
				from.add(0, precision, 0);
				return from;
			case 3:
				from.add(0, -precision, 0);
				return from;
			case 4:
				from.add(0, 0, precision);
				return from;
			case 5:
				from.add(0, 0, -precision);
				return from;
			default:
				return null;
		}
		
	}
	
}
