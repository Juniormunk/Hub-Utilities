package juniormunk.hub.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;

public class TpArrow implements Listener
{
	@EventHandler
	public void HitEvent​(ProjectileHitEvent pro)
	{

		if (pro.getEntity() instanceof Arrow)
		{
			Arrow a = (Arrow) pro.getEntity();
			if (a.hasMetadata("TP"))
			{

				if (a.getShooter() instanceof Player)
				{

					Player p = (Player) a.getShooter();
					if (p.hasPermission("hub.tpbow"))
					{
						if (Main.main.getConfig().isSet("hub"))
						{
							if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
							{
								if (Config.readLocation("hub", null, Main.main.getConfig()).getWorld().getName().equals(p.getWorld().getName()))
								{
									Location loc = a.getLocation();
									loc.setPitch(p.getLocation().getPitch());
									loc.setYaw(p.getLocation().getYaw());
									p.teleport(loc);
									a.remove();
								}
							}

						}

					}
				}
			}
		}
	}
}