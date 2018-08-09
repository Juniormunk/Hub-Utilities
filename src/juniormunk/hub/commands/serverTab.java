package juniormunk.hub.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Server;

public class serverTab implements TabCompleter
{
	Main main;

	public serverTab(Main main)
	{
		this.main = main;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
	{
		ArrayList<String> names = new ArrayList<String>();

		for (Server serv : Main.serverList)
		{
			names.add(serv.getName());
		}
		if (Main.serverList.size() > 0)
		{
			return names;
		}
		return null;
	}

}
