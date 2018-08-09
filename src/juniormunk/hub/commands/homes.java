package juniormunk.hub.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;
import juniormunk.hub.classes.PlayerConfig;
import juniormunk.hub.classes.Server;

public class homes implements CommandExecutor
{
	Main main;

	public homes(Main main)
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

		String name;
		Server serv = Server.getServerByLinked(p.getWorld().getName());
		if (serv != null)
		{
			name = serv.getName();
		}
		else
		{
			name = p.getWorld().getName();
		}

		ArrayList<String> homes = new ArrayList<String>();

		if (!PlayerConfig.getConfig(p).userconfig.isSet(name + "_home_list"))
		{
			sender.sendMessage(ChatColor.RED + "You currently do not have any homes!");
			

			return true;
		}
		homes = (ArrayList<String>) PlayerConfig.getConfig(p).userconfig.getStringList(name + "_home_list");

		if (!(homes.size() > 0))
		{
			
			sender.sendMessage(ChatColor.RED + "You currently do not have any homes!");

			return true;
		}

		sender.sendMessage(ChatColor.GREEN + "Homes : " + String.join(", ", homes));

		return true;

	}

}
