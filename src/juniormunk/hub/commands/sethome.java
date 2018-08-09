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

public class sethome implements CommandExecutor
{
	Main main;
	int maxhomes = 1;

	private ArrayList<String> homes;

	public sethome(Main main)
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
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /sethome <name> or /sethome");
			return true;
		}

		if (args.length == 1)
		{
			homename = args[0];
		}

		Player p = (Player) sender;

		if (p.hasPermission("hub.home.unlimited"))
		{
			maxhomes = -1;
		}
		if (p.hasPermission("hub.home.one"))
		{
			maxhomes = 1;
		}
		if (p.hasPermission("hub.home.two"))
		{
			maxhomes = 2;
		}
		if (p.hasPermission("hub.home.three"))
		{
			maxhomes = 3;
		}

		if (!main.getConfig().isSet("homes"))
		{
			main.getConfig().createSection("homes");
		}
		if (!main.getConfig().getConfigurationSection("homes").isSet("normal"))
		{
			main.getConfig().getConfigurationSection("homes").set("normal", 3);
		}
		if (!main.getConfig().getConfigurationSection("homes").isSet("vip"))
		{
			main.getConfig().getConfigurationSection("homes").set("vip", 5);
		}
		if (!main.getConfig().getConfigurationSection("homes").isSet("vip+"))
		{
			main.getConfig().getConfigurationSection("homes").set("vip+", 7);
		}
		if (!main.getConfig().getConfigurationSection("homes").isSet("admin"))
		{
			main.getConfig().getConfigurationSection("homes").set("admin", 10);
		}

		if (p.hasPermission("hub.home.normal"))
		{
			maxhomes = main.getConfig().getConfigurationSection("homes").getInt("normal");
		}
		if (p.hasPermission("hub.home.vip"))
		{
			maxhomes = main.getConfig().getConfigurationSection("homes").getInt("vip");
		}
		if (p.hasPermission("hub.home.vip+"))
		{
			maxhomes = main.getConfig().getConfigurationSection("homes").getInt("vip+");
		}
		if (p.hasPermission("hub.home.admin"))
		{
			maxhomes = main.getConfig().getConfigurationSection("homes").getInt("admin");
		}

		if (p.hasPermission("hub.home.unlimited"))
		{
			maxhomes = -1;
		}

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

		ConfigurationSection sec;

		if (PlayerConfig.getConfig(p).userconfig.isSet(name + "_home_list"))
		{
			homes = (ArrayList<String>) PlayerConfig.getConfig(p).userconfig.getStringList(name + "_home_list");
		}

		if (PlayerConfig.getConfig(p).userconfig.isSet(name + "_homes"))
		{
			sec = PlayerConfig.getConfig(p).userconfig.getConfigurationSection(name + "_homes");
		}
		else
			sec = PlayerConfig.getConfig(p).userconfig.createSection(name + "_homes");

		if (homes.size() == maxhomes)
		{
			sender.sendMessage(ChatColor.RED + "You have reached your maximum number of homes!");
			return true;
		}

		if (homes.contains(homename))
		{
			homes.remove(homename);
		}

		homes.add(homename);

		PlayerConfig.getConfig(p).userconfig.set(name + "_home_list", homes);

		Config.saveHomeLocation(p.getLocation(), name + homename, sec, PlayerConfig.getConfig(p).userconfig);

		sender.sendMessage(ChatColor.GOLD + "You have set a home.");

		PlayerConfig.getConfig(p).save();

		return true;
	}

}
