package juniormunk.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;
import juniormunk.hub.classes.TPARequest;

public class tpaccept implements CommandExecutor
{
	Main main;

	public tpaccept(Main main)
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

		if (!Main.tpas.containsKey(p.getUniqueId()))
		{
			p.sendMessage(ChatColor.RED + "You currently don't have any teleport requests to accept.");
			return true;
		}

		TPARequest tpa = Main.tpas.get(p.getUniqueId());

		if (!Server.sameServer(p, tpa.getSender()))
		{
			sender.sendMessage(ChatColor.RED + "You and " + tpa.getSender().getDisplayName() + ChatColor.RED + " are not in the same world!");
			return true;
		}

		if (!tpa.accept())
		{
			sender.sendMessage(ChatColor.RED + " That player is no longer online");
			Main.tpas.remove(p.getUniqueId());
			return true;
		}

		p.sendMessage(ChatColor.GREEN + "Teleport Accepted");
		Main.tpas.remove(p.getUniqueId());
		return true;
	}

}
