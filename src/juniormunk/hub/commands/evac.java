package juniormunk.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import juniormunk.hub.classes.Server;

public class evac implements CommandExecutor
{
	Main main;

	public evac(Main main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{

		if (args.length != 1)
		{
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /evacserver <server>");
			return true;
		}

		if (Server.getServerByName(args[0]) == null)
		{
			sender.sendMessage(ChatColor.RED + "That server does not exist");
			return true;

		}

		if (Config.readLocation("hub", null, Main.main.getConfig()) == null)
		{
			sender.sendMessage("ERROR: Hub location not set there is no where to send the players.");
			return true;
		}

		for (Player p : Server.getServerByName(args[0]).getPlayers())
		{
			p.teleport(Config.readLocation("hub", null, Main.main.getConfig()));
		}
		
		sender.sendMessage(ChatColor.AQUA + "You have evacuated the " + args[0] + " server.");

		return true;

	}

}