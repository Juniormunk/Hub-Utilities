package juniormunk.hub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;

import juniormunk.hub.classes.Server;

public class spawn implements CommandExecutor
{
	Main main;

	public spawn(Main main)
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

		if (Server.getServerByLinked(p.getWorld().getName()) != null)
		{
			p.teleport(Server.getServerByLinked(p.getWorld().getName()).getSpawn());
			sender.sendMessage(ChatColor.GOLD + "You are being teleported to spawn.");
			return true;

		}

		else if (Main.main.getConfig().isSet("Hub_World"))
		{
			if (Main.main.getConfig().getString("Hub_World").equals(p.getWorld().getName()))
			{

				double X = main.getConfig().getDouble("Hub_X");
				double Y = main.getConfig().getDouble("Hub_Y");
				double Z = main.getConfig().getDouble("Hub_Z");
				float DP = (float) main.getConfig().getDouble("Hub_DP");
				float DY = (float) main.getConfig().getDouble("Hub_DY");

				World w = Bukkit.getWorld(main.getConfig().getString("Hub_World"));
				p.teleport(new Location(w, X, Y, Z, DY, DP));

				sender.sendMessage(ChatColor.GOLD + "You are being teleported to spawn.");

				return true;
			}

		}

		sender.sendMessage(ChatColor.RED + "This is not a server. Teleporting you to the world spawn.");

		p.teleport(p.getWorld().getSpawnLocation());

		return true;
	}

}
