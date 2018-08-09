package juniormunk.hub.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;

public class addserver implements CommandExecutor
{
	Main main;

	public addserver(Main main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args.length != 2)
		{
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /addserver <name> <world>");
			return true;
		}

		if (Bukkit.getWorld(args[1]) == null)
		{
			sender.sendMessage(ChatColor.RED + "That World Does Not Exist");
			return true;
		}
		for (Server serv : Main.serverList)
		{
			if (serv.getName().equalsIgnoreCase(args[0]))
			{
				sender.sendMessage(ChatColor.RED + "That server name already exists!");
				return true;
			}
		}

		if (Server.getServerByLinked(Bukkit.getWorld(args[1]).getName()) != null)
		{
			sender.sendMessage(ChatColor.RED + "That world is already taken by another server!");
			return true;
		}

		Server serv = new Server(args[0], Bukkit.getWorld(args[1]), new ArrayList<String>());
		serv.setSpawn(Bukkit.getWorld(args[1]).getSpawnLocation());
		Main.serverList.add(serv);

		sender.sendMessage(ChatColor.AQUA + "You have added " + args[0] + " the server list.");

		
		Main.main.getConfig().options().copyDefaults(true);
		Main.main.getConfig().options().copyHeader(true);
		
		main.saveConfig();
		main.reloadConfig();
		Server.Save();
		return true;
	}

}
