package com.speedyphil.CustomCommandManager;

import javax.swing.event.EventListenerList;

import org.bukkit.command.CommandSender;

public class SubCommand 
{
	protected EventListenerList listenerList = new EventListenerList();
	
	private CommandType type = CommandType.NORMAL;
	
	private String[] args;
	private String argsString;
	private String permission;
	
	public SubCommand(String subCommand, String permission, SubCommandListener listener)
	{
		this.argsString = subCommand;
		this.args = subCommand.split(" ");
		this.permission = permission;
		if(subCommand.equals(""))
			this.args = new String[0];
		this.addSubCommandListener(listener);
	}
	
	public SubCommand(String subCommand, String permission, CommandType type,  SubCommandListener listener)
	{
		this.type = type;
		this.argsString = subCommand;
		this.args = subCommand.split(" ");
		this.permission = permission;
		if(subCommand.equals(""))
			this.args = new String[0];
		this.addSubCommandListener(listener);
	}

	public void addSubCommandListener(SubCommandListener listener) {
		listenerList.add(SubCommandListener.class, listener);
	}
	
	public void removeSubCommandListener(SubCommandListener listener) {
		listenerList.remove(SubCommandListener.class, listener);
	}
	
	public void fireSubCommandEvent(SubCommandEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i+2) {
			if (listeners[i] == SubCommandListener.class) {
				((SubCommandListener) listeners[i+1]).onCommand(evt);
			}
		}
	}

	public EventListenerList getListenerList() {
		return listenerList;
	}

	public String[] getArgs() {
		return args;
	}

	public String getArgsString() {
		return argsString;
	}

	public String getPermission() {
		return permission;
	}	
	
	public CommandType getType() {
		return type;
	}
}
