package juniormunk.hub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;

public class unlinkworld implements CommandExecutor
{
	Main main;

	public unlinkworld(Main main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args.length != 1)
		{
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /unlinkworld <world>");
			return true;
		}

		World world = Bukkit.getWorld(args[0]);
		if (world == null)
		{
			sender.sendMessage(ChatColor.RED + "That world doesn't exists!");
			return true;
		}
		Server serv = Server.getServerByLinked(world.getName());
		if (serv == null)
		{
			sender.sendMessage(ChatColor.RED + "That world is not linked to a server!");
			return true;
		}

		serv.linked.remove(world.getName());
		sender.sendMessage(ChatColor.AQUA + "Unlinked " + world.getName() + " with the " + serv.getName() + " server.");
		Server.Save();

		return true;

	}

}
