package juniormunk.hub.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import net.md_5.bungee.api.ChatColor;

public class BowShoot implements Listener
{
	@EventHandler
	public void ShootBowEvent(EntityShootBowEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player) e.getEntity();
			if (p.hasPermission("hub.tpbow"))
			{
				if (Main.main.getConfig().isSet("hub"))
				{
					if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
					{
						if (Config.readLocation("hub", null, Main.main.getConfig()).getWorld().getName().equals(p.getWorld().getName()))
						{
							if (e.getBow().getItemMeta().getLore().contains("" + ChatColor.RESET + ChatColor.GREEN + "TP BOW"))
							{
								e.getProjectile().setMetadata("TP", new FixedMetadataValue(Main.main, "TRUE"));
							}
						}
					}
				}
			}
		}
	}
}
