package com.speedyphil.LangzeitEvent;

import org.bukkit.configuration.file.FileConfiguration;

import com.speedyphil.Core.CreeperCantineShared;

public class LangzeitManager {

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
}
