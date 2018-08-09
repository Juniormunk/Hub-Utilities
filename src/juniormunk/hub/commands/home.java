package juniormunk.hub.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import juniormunk.hub.classes.PlayerConfig;
import juniormunk.hub.classes.Server;

public class home implements CommandExecutor
{
	Main main;

	private ArrayList<String> homes;

	public home(Main main)
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

		String homename = "home";

		if (args.length > 1)
		{
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /home <name> or /home");
			return true;
		}

		if (args.length == 1)
		{
			homename = args[0];
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

		homes = new ArrayList<String>();

		ConfigurationSection sec = null;

		if (PlayerConfig.getConfig(p).userconfig.isSet(name + "_home_list"))
		{
			homes = (ArrayList<String>) PlayerConfig.getConfig(p).userconfig.getStringList(name + "_home_list");
		}

		if (!homes.contains(homename))
		{
			sender.sendMessage(ChatColor.RED + "That home does not exist!");
			return true;
		}

		if (PlayerConfig.getConfig(p).userconfig.isSet(name + "_homes"))
		{
			sec = PlayerConfig.getConfig(p).userconfig.getConfigurationSection(name + "_homes");
		}

		p.teleport(Config.readLocation(name + homename, sec, PlayerConfig.getConfig(p).userconfig));
		sender.sendMessage(ChatColor.GOLD+"Teleporting you to " + homename);

		return true;
	}

}
