package juniormunk.hub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import juniormunk.hub.classes.PlayerConfig;
import juniormunk.hub.classes.Server;

public class server implements CommandExecutor
{
	Main main;

	public server(Main main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "You need to be a in-game player to run this command!");
			return true;
		}

		Player p = (Player) sender;
		if (p.hasPermission("hub.serverother"))
		{
			if (args.length == 2)
			{
				p = Bukkit.getPlayer(args[1]);
				if (p == null)
				{
					sender.sendMessage(ChatColor.RED + "Player not found!");
					return true;
				}
			}
		}
		if (!(args.length > 0 && (args.length <= 2)))
		{
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /server <server> or use /servers to list the servers.");
			return true;
		}

		if (Server.getServerByName(args[0]) == null)
		{
			sender.sendMessage(ChatColor.RED + "That server doesn't exists!");
			return true;
		}
		
		if (Server.getServerByName(args[0]).isHidden()&& !p.hasPermission("hub.hidden"))
		{
			sender.sendMessage(ChatColor.RED + "That server doesn't exists!");
			return true;
		}

		if (p.getWorld().getName().equals(args[0]))
		{
			sender.sendMessage(ChatColor.RED + "Your Already In That Server!");
			return true;
		}

		if (!Server.getServerByName(args[0]).isLocked() && !p.hasPermission("hub.locked"))
		{
			sender.sendMessage(ChatColor.RED + "That server is locked!");
			return true;
		}

		String servername = Server.getServerByName(args[0]).getName();

		if (servername == null)
		{
			servername = p.getWorld().getName();
		}

		Location loc = Config.readLocation(servername + "_Last_Location", null, PlayerConfig.getConfig(p).userconfig);
		if (loc != null)
		{
			p.teleport(loc);
		}
		else
			p.teleport(Server.getServerByName(args[0]).getWorld().getSpawnLocation());

		sender.sendMessage(ChatColor.GOLD + "Moving you to the " + servername + " server.");

		return true;

	}

}
