package juniormunk.hub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;

public class linkworld implements CommandExecutor
{
	Main main;

	public linkworld(Main main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args.length != 2)
		{
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /linkworld <server> <world>");
			return true;
		}

		World world = Bukkit.getWorld(args[1]);
		if (world == null)
		{
			sender.sendMessage(ChatColor.RED + "That world doesn't exists!");
			return true;
		}

		Server serv = Server.getServerByName(args[0]);
		if (serv == null)
		{
			sender.sendMessage(ChatColor.RED + "That server doesn't exists!");

			return true;
		}

		String worldname = world.getName();

		if (Server.getServerByLinked(args[1]) != null)
		{
			sender.sendMessage(ChatColor.RED + "That world is already linked to another server!");
			return true;
		}

		if (serv.linked.contains(worldname))
		{
			sender.sendMessage(ChatColor.RED + "That world is already linked with this server!");
			return true;
		}
		serv.linked.add(worldname);
		sender.sendMessage(ChatColor.AQUA + "Linked " + worldname + " to the " + serv.getName() + " server.");
		Server.Save();

		return true;

	}

}
