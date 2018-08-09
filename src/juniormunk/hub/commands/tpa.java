package juniormunk.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;
import juniormunk.hub.classes.TPARequest;

public class tpa implements CommandExecutor
{
	Main main;

	public tpa(Main main)
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
		if (args.length != 1)
		{
			sender.sendMessage(ChatColor.RED + "Incorrect command use! /tpa <player>");
			return true;
		}
		Player p = (Player) sender;
		Player tar = main.getServer().getPlayer(args[0]);

		if (tar == null)
		{
			sender.sendMessage(ChatColor.RED + "Player not found!");
			return true;
		}
		if (!Server.sameServer(p, tar))
		{
			sender.sendMessage(ChatColor.RED + "You and " + tar.getDisplayName() + ChatColor.RED + " are not in the same world!");
			return true;
		}

		Main.tpas.remove(tar.getUniqueId());
		Main.tpas.put(tar.getUniqueId(), new TPARequest(false, p, tar));

		// p.removeMetadata(tar.getUniqueId() + " TPA ", main);
		tar.sendMessage(ChatColor.GOLD + p.getDisplayName() + ChatColor.GOLD + " would like to teleport to you, type /tpaccept to accept and /tpdeny to deny. This request will end in " + main.tplength + " seconds.");
		p.sendMessage(ChatColor.GREEN + "Tpa sent to " + tar.getDisplayName());
		// tar.setMetadata(tar.getUniqueId() + " TPA ", new FixedMetadataValue(main, p.getName() + "---" + "TPA"));

		main.task.put(p.getUniqueId() + " TPA ", new BukkitRunnable()
		{
			int i = main.tplength * 20;

			@Override
			public void run()
			{
				i--;
				if (i == 0)
				{
					Main.tpas.remove(tar.getUniqueId(), new TPARequest(false, p, tar));
					cancel();
				}
			}
		});
		main.task.get(p.getUniqueId() + " TPA ").runTaskTimer(main, 1, 1);

		return true;
	}
}
