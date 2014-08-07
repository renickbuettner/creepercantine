package com.speedyphil.LangzeitEvent;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
				Player player = (Player)event.getSender();
				
				
			}
			
		} ));
	}

}
