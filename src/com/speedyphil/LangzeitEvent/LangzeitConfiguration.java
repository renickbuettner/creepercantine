package com.speedyphil.LangzeitEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class LangzeitConfiguration {
	private String folder = "plugins/CreeperCantineShared/LangzeitEvent";
	private File configFile = new File(folder + File.separator + "data.yml");
	private YamlConfiguration config;
	
	private YamlConfiguration loadConfig()
	{
		try
		{
			YamlConfiguration config =  new YamlConfiguration();
			config.load(configFile);
			return config;
		}
		catch(Exception e)
		{
			System.out.println("[LangzeitEvent] An error occured! Please delete your LangzeitEvent folder an reload this Plugin!");
			return null;
		}
	}
	
	public void createConfig()
	{
		new File(folder).mkdir();
		if(!configFile.exists())
		{
			try
			{
				System.out.println("[LangzeitEvent] Creating Config...");
				configFile.createNewFile();
				config = loadConfig();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public void saveNames(List<String> names)
	{
		config = loadConfig();
		config.set("Blacklist",names);
		save();
	}
	
	public List<String> loadNames() {
		config = loadConfig();
		List<String> names = new ArrayList<String>();
		if(config.contains("Blacklist"))
			names = config.getStringList("Blacklist");
		return names;
	}
	
	
	private void save()
	{
		try
		{
			config.save(configFile);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
