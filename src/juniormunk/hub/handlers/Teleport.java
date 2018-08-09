package juniormunk.hub.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import juniormunk.hub.classes.PlayerConfig;
import juniormunk.hub.classes.Server;

public class Teleport implements Listener
{
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{

		Player p = event.getPlayer();

		Server serv;
		String tarWorld = "";

		serv = Server.getServerByLinked(p.getWorld().getName());

		Server fromserv = Server.getServerByLinked(event.getFrom().getWorld().getName());

		String name;
		if (serv != null)
		{
			name = fromserv.getName();
		}
		else
		{
			name = event.getFrom().getWorld().getName();
		}

		Server toserv = Server.getServerByLinked(event.getTo().getWorld().getName());

		String name1;
		if (toserv != null)
		{
			name1 = toserv.getName();
		}
		else
		{
			name1 = event.getTo().getWorld().getName();
		}

		if (name.equals(name1))
		{
			Config.saveLocation(p.getLocation(), name + "_Back_Location", null, PlayerConfig.getConfig(p).userconfig);
			PlayerConfig.getConfig(p).save();

		}

		if (serv != null)
		{

			tarWorld = serv.getName();
			boolean dontrun = false;
			if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
			{
				if (Config.readLocation("hub", null, Main.main.getConfig()).getWorld().equals(p.getWorld()))
				{
					dontrun = true;
				}

			}

			if (!dontrun)
			{
				Config.saveLocation(p.getLocation(), tarWorld + "_Last_Location", null, PlayerConfig.getConfig(p).userconfig);

			}
		}
	}
}
