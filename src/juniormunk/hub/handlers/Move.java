package juniormunk.hub.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import juniormunk.hub.classes.PlayerConfig;
import juniormunk.hub.classes.Server;

public class Move implements Listener
{
	@EventHandler
	public void move(PlayerMoveEvent event)
	{

		Player p = event.getPlayer();

		Server serv;
		String tarWorld = "";

		serv = Server.getServerByLinked(p.getWorld().getName());

		if (serv != null)
		{
			tarWorld = serv.getName();
			boolean dontrun = false;
			if (Main.main.getConfig().isSet("hub"))
			{
				if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
				{
					if (Config.readLocation("hub", null, Main.main.getConfig()).getWorld().getName().equals(p.getWorld().getName()))
					{
						dontrun = true;
					}
				}

			}

			if (!dontrun)
			{
				Config.saveLocation(p.getLocation(), tarWorld + "_Last_Location", null, PlayerConfig.getConfig(p).userconfig);
				PlayerConfig.getConfig(p).save();
			}
		}
	}
}
