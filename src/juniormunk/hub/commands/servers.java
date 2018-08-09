package juniormunk.hub.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;

public class servers implements CommandExecutor
{
	Main main;

	public servers(Main main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{

		ArrayList<String> names = new ArrayList<String>();

		for (Server serv : Main.serverList)
		{
			if (serv.isHidden() && !sender.hasPermission("hub.hidden"))
			{

			}
			else if (serv.isHidden() && !serv.isLocked() && sender.hasPermission("hub.hidden"))
			{
				names.add("" + ChatColor.RESET + ChatColor.GREEN + ChatColor.STRIKETHROUGH + serv.getName());

			}
			else if (serv.isLocked() && !serv.isHidden())
			{
				names.add("" + ChatColor.RESET + ChatColor.RED + serv.getName());
			}
			else if (serv.isLocked() && serv.isHidden() && sender.hasPermission("hub.hidden"))
			{
				names.add("" + ChatColor.RESET + ChatColor.RED + ChatColor.STRIKETHROUGH + serv.getName());
			}
			else
			{
				names.add("" + ChatColor.RESET + ChatColor.GREEN + serv.getName());
			}
		}
		sender.sendMessage(ChatColor.GREEN + "Servers : " + String.join(", ", names));

		return true;

	}

}
