package juniormunk.hub.commands;

//import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import juniormunk.hub.Main;

public class tpdeny implements CommandExecutor
{
	Main main;

	public tpdeny(Main main)
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
			p.sendMessage(ChatColor.RED + "You currently don't have any teleport requests to deny.");
			return true;
		}

		p.sendMessage(ChatColor.GOLD + "Teleport Denied");
		Main.tpas.remove(p.getUniqueId());

		return true;

	}
}
