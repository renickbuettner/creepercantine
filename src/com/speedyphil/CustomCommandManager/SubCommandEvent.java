package com.speedyphil.CustomCommandManager;

import org.bukkit.command.CommandSender;

public class SubCommandEvent 
{
	private SubCommand subCommand;
	private CommandSender sender;
	private Object[] objects;
	
	//private String command;
	private String[] args;
	
	public SubCommandEvent(SubCommand subCommand, CommandSender sender, Object[] objects) {
		this.subCommand = subCommand;
		this.sender = sender;
		this.objects = objects;
		this.args = subCommand.getArgs();
	}

	public SubCommand getSubCommand() {
		return subCommand;
	}

	public CommandSender getSender() {
		return sender;
	}

	public Object[] getObjects() {
		return objects;
	}

	public String[] getArgs() {
		return args;
	}
}
