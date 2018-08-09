package juniormunk.hub.commands;

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

public class back implements CommandExecutor
{
	Main main;

	public back(Main main)
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

		Server serv = Server.getServerByLinked(p.getWorld().getName());
		String name;
		if (serv != null)
		{
			name = serv.getName();
		}
		else
		{
			name = p.getWorld().getName();
		}

		Location loc = Config.readLocation(name + "_Last_Location", null, PlayerConfig.getConfig(p).userconfig);

		if (loc == null)
		{
			sender.sendMessage(ChatColor.GOLD + "You have no where to be sent to.");
			return true;
		}

		p.teleport(loc);

		sender.sendMessage(ChatColor.GOLD + "You are being sent back to where you were.");
		return true;

	}

}
