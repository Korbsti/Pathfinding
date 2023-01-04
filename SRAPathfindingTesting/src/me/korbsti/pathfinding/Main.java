package me.korbsti.pathfinding;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitWorker;

import me.korbsti.pathfinding.api.ShortestPath;
import net.minecraft.world.level.block.Block;

public class Main extends JavaPlugin implements Listener {
	
	public Location from;
	public Location to;
	public ArrayList<Location> lod = new ArrayList<Location>();
	public boolean d = false;
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) throws NoSuchMethodException, SecurityException,
	        IllegalArgumentException, InterruptedException {
		if (event.getMessage().equals("to")) {
			to = event.getPlayer().getLocation();
		}
		if (event.getMessage().equals("from")) {
			from = event.getPlayer().getLocation();
		}
		if (event.getMessage().equals("cancel")) {
			for (BukkitWorker d : Bukkit.getScheduler().getActiveWorkers()) {
				Bukkit.getScheduler().cancelTask(d.getTaskId());
			}
		}
		if (event.getMessage().equals("delete")) {
			d = false;
			lod.clear();
		}
		if (event.getMessage().equals("start")) {
			d = true;
		}
		
		if (event.getMessage().equals("end")) {
			lod.clear();
		}
		if (event.getMessage().equals("calculate")) {
			Pathfinding pathfinding = (Pathfinding) Bukkit.getPluginManager().getPlugin("SRAPathfinding");
			ArrayList<Material> followMats = new ArrayList<Material>();
			followMats.add(Material.AIR);
			followMats.add(Material.CAVE_AIR);
			lod = pathfinding.shortestPathFollow.returnPathFollow(from, to, 0.3, followMats, 200000);
			for (Location l : lod) {
				Bukkit.getScheduler().runTask(this, new Runnable() {
					@Override
					public void run() {
							l.getBlock().setType(Material.SEA_LANTERN);
					}
					
				});
				
			}
			
		}
		
	}
	
}
