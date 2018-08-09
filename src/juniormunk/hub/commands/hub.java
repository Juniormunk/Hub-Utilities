package juniormunk.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;

public class hub implements CommandExecutor
{
	Main main;

	public hub(Main main)
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

		Location hub = Config.readLocation("hub", null, main.getConfig());

		if (hub == null)
		{
			sender.sendMessage(ChatColor.RED + "The hub is currently not set!");

			return true;
		}

		Player p = (Player) sender;

		p.teleport(hub);

		sender.sendMessage(ChatColor.GOLD + "You are being teleported to the hub.");
		return true;

	}

}
