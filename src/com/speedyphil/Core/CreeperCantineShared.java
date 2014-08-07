package com.speedyphil.Core;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.speedyphil.LangzeitEvent.LangzeitCommandListener;
import com.speedyphil.LangzeitEvent.LangzeitConfiguration;
import com.speedyphil.LangzeitEvent.LangzeitPlayerListener;


public class CreeperCantineShared extends JavaPlugin
{
	private static LangzeitCommandListener lzCL = new LangzeitCommandListener();
	private static LangzeitPlayerListener lzPL = new LangzeitPlayerListener();
	private static LangzeitConfiguration lzCF = new LangzeitConfiguration();
	
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
	
	public static LangzeitConfiguration getLangzeitConfiguration() {
		return lzCF;
	}
}
