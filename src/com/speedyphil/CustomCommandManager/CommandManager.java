package com.speedyphil.CustomCommandManager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.speedyphil.Core.CreeperCantineShared;
import com.speedyphil.Utils.PlayerUtils;


public class CommandManager implements CommandExecutor
{
	private List<SubCommand> commands = new ArrayList<SubCommand>();
	private String prefix;
	
	public CommandManager(String prefix)
	{
		this.prefix = prefix;
	}
	
	public void addSubCommand(SubCommand command) {
		if(!hasSubCommand(command.getArgsString()))
			commands.add(command);
	}
	
	public boolean hasSubCommand(String argsString) {
		for(SubCommand cmd : commands)
			if(cmd.getArgsString().equalsIgnoreCase(argsString))
				return true;
		return false;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) 
	{
		if(args.length == 1)
		{
			if(args[0].equals("?"))
			{
				printHelp(sender, cmdLabel);
				return true;
			}
		}
		
		for(SubCommand subCommand : commands)
		{
			if(subCommand.getArgs().length == args.length)
			{
				
				boolean success = true;
				for(int i = 0; i < args.length; i++) {
					if(!subCommand.getArgs()[i].startsWith("%")) {
						if(!subCommand.getArgs()[i].equalsIgnoreCase(args[i])) {
							success = false;
						}
					}
				}
				if(success)
				{
					if((subCommand.getType() == CommandType.CONSOLE_ONLY) && !sender.getName().equals("CONSOLE")) {
						sender.sendMessage(ChatColor.RED+"Dieser Befehl kann nur von der Console ausgeführt werden!");
						return true;
					}
					if((subCommand.getType() == CommandType.OP_ONLY) && !sender.isOp()) {
						sender.sendMessage(ChatColor.RED+"Dieser Befehl kann nur mit OP-Berechtigung ausgeführt werden!");
						return true;
					}
					if((subCommand.getType() == CommandType.PLAYER_ONLY) && ((sender instanceof Player) == false)) {
						sender.sendMessage(ChatColor.RED+"Dieser Befehl kann nur als Spieler ausgeführt werden!");
						return true;
					}
					
					if(subCommand.getPermission() == null || CreeperCantineShared.hasPermission(sender, subCommand.getPermission()))
					{
						List<Object> objects = new ArrayList<Object>();
						for(int i = 0; i < args.length; i++)
						{
							String arg = subCommand.getArgs()[i];
							if(arg.startsWith("%p"))
							{
								String player = new PlayerUtils().getOfflinePlayerFromShort(args[i]);
								if(player != null)
								{
									objects.add((Player)Bukkit.getOfflinePlayer(player));
								}
								else
								{
									sender.sendMessage(ChatColor.RED+"Der Spieler "+args[i]+" existiert nicht!");
									return true;
								}
							}
							if(arg.startsWith("%op"))
							{
								String player = new PlayerUtils().getOfflinePlayerFromShort(args[i]);
								if(player != null)
								{
									objects.add((OfflinePlayer)Bukkit.getOfflinePlayer(player));
								}
								else
								{
									sender.sendMessage(ChatColor.RED+"Der Spieler "+args[i]+" existiert nicht!");
									return true;
								}
							}
							if(arg.startsWith("%s"))
							{
								objects.add(args[i]);
							}
							if(arg.startsWith("%i"))
							{
								try
								{
									objects.add(Integer.parseInt(args[i]));
								}
								catch(Exception ex)
								{
									sender.sendMessage(ChatColor.RED+"Falsche Eingabe! Eine Zahl wurde erwartet an Stelle von '"+args[i]+"'");
								}
							}
						}
						Object[] obj = new Object[objects.size()];
						for(int i = 0; i < objects.size(); i++)
							obj[i] = objects.get(i);
						
						subCommand.fireSubCommandEvent(new SubCommandEvent(subCommand, sender, obj));
					}
					else
					{
						sender.sendMessage(ChatColor.RED+"Du hast keine Berechtigung für diesen Befehl!");
						return true;
					}
					return true;
				}
			}
		}

		sender.sendMessage(ChatColor.RED+"/"+cmdLabel+" ?");
		return true;
	}

	
	private void printHelp(CommandSender sender, String cmdLabel)
	{
		boolean isOP = sender.isOp();
		
		String helpList = "";
		for(SubCommand subCommand : commands)
		{
			if(subCommand.getPermission() == null || CreeperCantineShared.hasPermission(sender, subCommand.getPermission()))
			{
				if((!isOP && subCommand.getType() == CommandType.CONSOLE_ONLY) || (!isOP && subCommand.getType() == CommandType.OP_ONLY))
				{
					continue;
				}
				helpList += ChatColor.DARK_GRAY+" /"+cmdLabel+" "+subCommand.getArgsString().replace("%op", "<Spieler>")+ChatColor.GRAY;
				if(isOP)
				{
					if(subCommand.getPermission() != null)
					{
						helpList += " - " + subCommand.getPermission();
					}
					if(subCommand.getType() != CommandType.NORMAL)
					{
						helpList += " - " + ChatColor.RED+subCommand.getType().name();
					}
				}
				helpList += "\n";
			}
		}
		
		sender.sendMessage(prefix+"Befehlsliste:");
		sender.sendMessage(helpList);
	}
}
