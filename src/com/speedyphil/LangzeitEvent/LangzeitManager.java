package com.speedyphil.LangzeitEvent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.speedyphil.Core.CreeperCantineShared;

public class LangzeitManager {

	private List<String> blacklist = new ArrayList<String>();
	private boolean enabled = false;
	private String worldName = "";
	
	public LangzeitManager() {
		FileConfiguration config = CreeperCantineShared.getPlugin().getConfig();
		if(config.contains("enable-langzeitevent"))
			enabled = config.getBoolean("enable-langzeitevent");
		if(config.contains("worldname"))
			worldName = config.getString("worldname");
		if(worldName.equals(""))
			enabled = false;
		
		if(enabled)
		{
			CreeperCantineShared.writeToLog("Das LangzeitEvent ist auf der Welt "+worldName+" aktiviert!");
		}
		else
		{
			CreeperCantineShared.writeToLog("Das LangzeitEvent ist nicht aktiviert!");
		}
	}

	public void blacklistPlayer(String UUID) {
		if(!blacklist.contains(UUID))
			blacklist.add(UUID);
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
	
	public boolean isEnabled() {
		return enabled;
	}

	public String getWorldName() {
		return worldName;
	}	
	
}
