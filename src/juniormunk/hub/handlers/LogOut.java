package juniormunk.hub.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import juniormunk.hub.Main;

public class LogOut implements Listener
{
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player p = event.getPlayer();

		Main.playerconfigs.get(p.getUniqueId()).close();

	}
}