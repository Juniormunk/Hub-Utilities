package juniormunk.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;

public class sethub implements CommandExecutor
{
	Main main;

	public sethub(Main main)
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

		Config.saveHubLocation(p.getLocation(), "hub", null, main.getConfig());

		sender.sendMessage(ChatColor.AQUA + "You have set the hub location.");
		
		Main.main.getConfig().options().copyDefaults(true);
		Main.main.getConfig().options().copyHeader(true);
		
		main.saveConfig();
		main.reloadConfig();
		return true;

	}

}
