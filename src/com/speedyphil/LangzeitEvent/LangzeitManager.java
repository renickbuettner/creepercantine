package com.speedyphil.LangzeitEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.speedyphil.Core.CreeperCantineShared;

public class LangzeitManager {

	private FileConfiguration config;
	private List<String> blacklist = new ArrayList<String>();
	private List<String> consumeBlacklist = new ArrayList<String>();
	private List<String> commandWhitelist = new ArrayList<String>();
	private boolean enabled = false;
	private String worldName = "";
	private Location spawn = null;
	
	public LangzeitManager() {
		config = CreeperCantineShared.getPlugin().getConfig();
		if(config.contains("langzeitevent.enable"))
			enabled = config.getBoolean("langzeitevent.enable");
		if(config.contains("langzeitevent.worldname"))
			worldName = config.getString("langzeitevent.worldname");
		if(worldName.equals(""))
			enabled = false;
		if(enabled) {
			if(Bukkit.getWorld(worldName) == null) {
				enabled = false;
				CreeperCantineShared.writeToLog("Das LangzeitEvent wurde deaktiviert! Welt "+worldName+" nicht gefunden!");
			}
		}
		
		if(config.contains("langzeitevent.spawn"))
		{
			spawn = new Location(Bukkit.getWorld(config.getString("langzeitevent.spawn.world")),
								 config.getInt("langzeitevent.spawn.x"),
								 config.getInt("langzeitevent.spawn.y"),
								 config.getInt("langzeitevent.spawn.z"),
								 (float)config.getDouble("langzeitevent.spawn.yaw"),
								 (float)config.getDouble("langzeitevent.spawn.pitch"));
		}
		if(config.contains("langzeitevent.consume_blacklist"))
		{
			consumeBlacklist = config.getStringList("langzeitevent.consume_blacklist");
		}
		if(config.contains("langzeitevent.command_whitelist"))
		{
			commandWhitelist = config.getStringList("langzeitevent.command_whitelist");
		}
		
		if(enabled)
		{
			CreeperCantineShared.writeToLog("Das LangzeitEvent ist auf der Welt "+worldName+" aktiviert!");
		}
		else
		{
			CreeperCantineShared.writeToLog("Das LangzeitEvent ist nicht aktiviert!");
		}
	}

	public void setSpawn(Location location) {
		spawn = location;
		config.set("langzeitevent.spawn.world", location.getWorld().getName());
		config.set("langzeitevent.spawn.x", location.getBlockX());
		config.set("langzeitevent.spawn.y", location.getBlockY());
		config.set("langzeitevent.spawn.z", location.getBlockZ());
		config.set("langzeitevent.spawn.yaw", location.getYaw());
		config.set("langzeitevent.spawn.pitch", location.getPitch());
		CreeperCantineShared.getPlugin().saveConfig();
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public void blacklistPlayer(String UUID) {
		if(!blacklist.contains(UUID)) {
			blacklist.add(UUID);
			this.saveBlacklist();
		}
	}
	
	public boolean isBlacklisted(String UUID) {
		return blacklist.contains(UUID);
	}
	
	public void loadBlacklist() {
		blacklist = CreeperCantineShared.getLangzeitConfiguration().loadNames();
	}
	
	public void saveBlacklist() {
		CreeperCantineShared.getLangzeitConfiguration().saveNames(blacklist);
	}
	
	public boolean isConsumeBlacklisted(String material) {
		return consumeBlacklist.contains(material);
	}
	
	public boolean isCommandWhiteisted(String command) {
		return commandWhitelist.contains(command.toUpperCase());
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public String getWorldName() {
		return worldName;
	}	
	
	public World getWorld() {
		return Bukkit.getWorld(worldName);
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isInEvent(Player player) {
		return player.getWorld() == getWorld();
	}
}
