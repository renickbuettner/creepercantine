package com.speedyphil.LangzeitEvent;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.speedyphil.Core.CreeperCantineShared;
import com.speedyphil.CustomCommandManager.CommandManager;
import com.speedyphil.CustomCommandManager.CommandType;
import com.speedyphil.CustomCommandManager.SubCommand;
import com.speedyphil.CustomCommandManager.SubCommandEvent;
import com.speedyphil.CustomCommandManager.SubCommandListener;


public class LangzeitCommandListener extends CommandManager {

	private static String prefix = ChatColor.GOLD+"[LangzeitEvent] "+ChatColor.GRAY;
	
	public LangzeitCommandListener() {
		super(prefix);
		
		this.addSubCommand(new SubCommand("join", null, CommandType.PLAYER_ONLY, new SubCommandListener()
		{
			@Override
			public void onCommand(SubCommandEvent event) 
			{
				LangzeitManager manager = CreeperCantineShared.getLangzeitManager();
				Player player = (Player)event.getSender();
				
				if(manager.isEnabled()) 
				{
					if(!manager.isBlacklisted(player.getUniqueId().toString()))
					{
						World world = manager.getWorld();
						player.teleport(world.getSpawnLocation());
					}
					else
					{
						player.sendMessage(prefix+ChatColor.RED+"Du bist aus dem Event ausgeschieden!");
					}
				} 
				else 
				{
					player.sendMessage(prefix+ChatColor.RED+"Das Event ist momentan nicht aktiviert!");
				}
			}
			
		} ));
		
		this.addSubCommand(new SubCommand("leave", null, CommandType.PLAYER_ONLY, new SubCommandListener()
		{
			@Override
			public void onCommand(SubCommandEvent event) 
			{
				LangzeitManager manager = CreeperCantineShared.getLangzeitManager();
				Player player = (Player)event.getSender();
				
				if(manager.isEnabled()) 
				{
					if(player.getWorld() == manager.getWorld())
					{
						player.chat("/warp spawn");
					}
					else
					{
						player.sendMessage(prefix+ChatColor.RED+"Du befindest dich nicht im Event!");
					}
				}
				else
				{
					player.sendMessage(prefix+ChatColor.RED+"Das Event ist momentan nicht aktiviert!");
				}
			}
			
		} ));
		
		this.addSubCommand(new SubCommand("setspawn", null, CommandType.OP_ONLY, new SubCommandListener()
		{
			@Override
			public void onCommand(SubCommandEvent event) 
			{
				LangzeitManager manager = CreeperCantineShared.getLangzeitManager();
				Player player = (Player)event.getSender();
				
				
			}
			
		} ));
	}

}
