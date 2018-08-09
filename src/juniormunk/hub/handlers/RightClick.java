package juniormunk.hub.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import juniormunk.hub.classes.GUI;

public class RightClick implements Listener
{
	@EventHandler
	public void rightClick(PlayerInteractEvent e)
	{

		if (Main.main.getConfig().isSet("hub"))
		{
			if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
			{
				if (Config.readLocation("hub", null, Main.main.getConfig()).getWorld().getName().equals(e.getPlayer().getWorld().getName()))
				{
					if (e.getPlayer().getItemInHand().equals(ChangeWorld.getServer()))
					{
						GUI.showGUI(e.getPlayer());
						e.setCancelled(true);
					}
				}
			}

		}

	}
}
