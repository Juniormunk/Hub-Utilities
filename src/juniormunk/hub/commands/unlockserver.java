
package juniormunk.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;

public class unlockserver implements CommandExecutor
{
	Main main;

	public unlockserver(Main main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{

		if (args.length != 1)
		{
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /unlockserver <server>");
			return true;
		}

		if (Server.getServerByName(args[0]) == null)
		{
			sender.sendMessage(ChatColor.RED + "That server does not exist!");
			return true;

		}
		if (!Server.getServerByName(args[0]).isLocked())
		{
			sender.sendMessage(ChatColor.RED + "That server is not locked!");
			return true;

		}

		Server.getServerByName(args[0]).setLocked(false);
		sender.sendMessage(ChatColor.AQUA + "You have unlocked " + args[0]);
		
		Main.main.getConfig().options().copyDefaults(true);
		Main.main.getConfig().options().copyHeader(true);

		main.saveConfig();
		main.reloadConfig();
		Server.Save();

		return true;

	}

}
