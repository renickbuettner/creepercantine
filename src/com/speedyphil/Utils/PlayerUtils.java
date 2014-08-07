package com.speedyphil.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;

public class PlayerUtils 
{
	public String getOfflinePlayerFromShort(String s) {
		for(OfflinePlayer p : Bukkit.getOfflinePlayers())
			if(p.getName().toUpperCase().startsWith(s.toUpperCase()))
				return p.getName();
		return null;
	}
	
	public String getOnlinePlayerFromShort(String s) {
		for(Player p : Bukkit.getOnlinePlayers())
			if(p.getName().toUpperCase().startsWith(s.toUpperCase()))
				return p.getName();
		return null;
	}
	
	public void sendOPMessage(String message) {
		sendOPMessage(message, null);
	}
	
	public void sendOPMessage(String message, String[] exceptions) {
		for(Player player : Bukkit.getOnlinePlayers())
			if(player.isOp())
				if(exceptions != null && !contains(player.getName(), exceptions))
					player.sendMessage(message);
	}
	
	public boolean contains(String s, String[] arr) {
		for(String ss : arr)
			if(ss.equals(s))
				return true;
		return false;
	}
	
	public void sendMessage(String splayer, String message) {
		Player player = Bukkit.getPlayer(splayer);
		if(player != null && player.isOnline())
			player.sendMessage(message);
	}
	
	public boolean hasGroup(String name, PermissionUser user) {
		for(PermissionGroup g : user.getGroups()){
			if(g.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasInventorySpace(Inventory inventory, int amount, Material mat, int data) 
	{
		for(int i = 0; i < inventory.getSize(); i++)
		{
			if(amount > 0)
			{
				ItemStack item = inventory.getItem(i);
				if(item != null)
				{
					if(item.getType() == mat && item.getData().getData() == data)
						amount -= (64 - item.getAmount());
				}
				else
				{
					amount -= 64;
				}
			}
			else
			{
				i = inventory.getSize();
			}
		}
		return amount <= 0;
	}
	
	public void addItemToInventory(Inventory inventory, int amount, Material mat, int data) 
	{
		for(int i = 0; i < inventory.getSize(); i++)
		{
			if(amount > 0)
			{
				ItemStack item = inventory.getItem(i);
				if(item != null)
				{
					if(item.getType() == mat && item.getData().getData() == data)
					{
						int am = 64 - item.getAmount();
						if(amount < am)
							am = amount;
						item.setAmount(item.getAmount()+am);
						amount -= am;
					}	
				}
				else
				{
					int am = 64;
					if(amount < am)
						am = amount;
					ItemStack is = new ItemStack(mat,am, (byte)data);
					inventory.setItem(i, is);
					amount -= am;
				}
			}
			else
			{
				i = inventory.getSize();
			}
		}
	}
	
	public void removeItemFromInventory(Inventory inventory, int amount, Material mat, int data) 
	{
		for(int i = 0; i < inventory.getSize(); i++)
		{
			if(amount > 0)
			{
				ItemStack item = inventory.getItem(i);
				if(item != null)
				{
					if(item.getType() == mat && item.getData().getData() == data)
					{
						if(item.getAmount() > amount) {
							item.setAmount(item.getAmount()-amount);
							amount = 0;
						} else {
							amount -= item.getAmount();
							inventory.remove(item);
						}
					}	
				}
			}
			else
			{
				i = inventory.getSize();
			}
		}
	}
}
