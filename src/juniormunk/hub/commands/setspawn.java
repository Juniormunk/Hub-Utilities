package juniormunk.hub.commands;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;

public class setspawn implements CommandExecutor
{
	Main main;

	public setspawn(Main main)
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
		if (Server.getServerByLinked(p.getWorld().getName()) == null)
		{
			p.getWorld().setSpawnLocation(p.getLocation());
			sender.sendMessage(ChatColor.RED + "This is not a server. Setting world spawn location here.");
			return true;

		}
		Server.getServerByLinked(p.getWorld().getName()).setSpawn(p.getLocation());
		Server.Save();

		sender.sendMessage(ChatColor.AQUA + "You have set the spawn location.");
		return true;

	}

}
