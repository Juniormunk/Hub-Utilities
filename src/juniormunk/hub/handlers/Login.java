package juniormunk.hub.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import juniormunk.hub.classes.PlayerConfig;

public class Login implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();

		@SuppressWarnings("unused")
		PlayerConfig playerConfig = new PlayerConfig(p);

		Main.tpas.remove(p.getUniqueId());

		if (Main.main.getConfig().isSet("hub"))
		{
			if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
			{
				p.teleport(Config.readLocation("hub", null, Main.main.getConfig()));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 5));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000000, 1));

				p.getInventory().setItem(0, ChangeWorld.getSpecialBow());
				p.getInventory().setItem(27, ChangeWorld.getSpecialArrow());
				p.getInventory().setItem(4, ChangeWorld.getServer());
			}
		}

	}
}