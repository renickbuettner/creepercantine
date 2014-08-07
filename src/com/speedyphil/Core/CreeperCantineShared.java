package com.speedyphil.Core;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class CreeperCantineShared extends JavaPlugin
{
	private static Plugin plugin;
	private PluginManager pm;
	
	public void onEnable()
	{
		this.pm = Bukkit.getPluginManager();
		this.plugin = this;
		
	}
	
	public void onDisable()
	{

	}
}
