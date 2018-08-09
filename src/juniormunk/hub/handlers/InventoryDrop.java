package juniormunk.hub.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import net.md_5.bungee.api.ChatColor;

public class InventoryDrop implements Listener
{
	@EventHandler
	public void InventoryItemDrop(PlayerDropItemEvent event)
	{

		if ((event.getPlayer() instanceof Player))
		{
			Player p = (Player) event.getPlayer();

			if (p.hasPermission("hub.tpbow"))
			{
				if (Main.main.getConfig() != null)
				{
					if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
					{
						if (p.getWorld().equals(Config.readLocation("hub", null, Main.main.getConfig()).getWorld()))
						{

							event.setCancelled(true);

						}
					}
				}
			}
		}

	}
}