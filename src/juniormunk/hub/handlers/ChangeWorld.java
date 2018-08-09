package juniormunk.hub.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import net.md_5.bungee.api.ChatColor;

public class ChangeWorld implements Listener
{
	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event)
	{
		Player p = event.getPlayer();

		World w = p.getWorld();

		if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
		{
			if (w.equals(Config.readLocation("hub", null, Main.main.getConfig()).getWorld()))
			{
				p.getInventory().clear();

				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 5));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000000, 1));

				p.getInventory().setItem(0, getSpecialBow());
				p.getInventory().setItem(27, getSpecialArrow());
				p.getInventory().setItem(4, getServer());

			}
			else
			{
			}
		}

	}

	public static ItemStack getSpecialBow()
	{
		ItemStack item = new ItemStack(Material.BOW);

		ItemMeta meta = item.getItemMeta();

		List<String> Lore = new ArrayList<String>();

		Lore.add("" + ChatColor.RESET + ChatColor.GREEN + "TP BOW");

		meta.setDisplayName(ChatColor.AQUA + "Teleport Bow");

		meta.setLore(Lore);

		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);

		meta.setUnbreakable(true);

		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getSpecialArrow()
	{
		List<String> Lore = new ArrayList<String>();

		Lore.add("" + ChatColor.RESET + ChatColor.GREEN + "TP ARROW");

		ItemStack item = new ItemStack(Material.ARROW);

		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(ChatColor.AQUA + "Teleport Arrow");

		meta.setLore(Lore);

		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getServer()
	{
		if (Main.main.getConfig().isSet("ServerData"))
		{
			List<String> Lore = new ArrayList<String>();

			Lore.add("" + ChatColor.RESET + ChatColor.GREEN + "SERVERS");

			ItemStack item = new ItemStack(Material.GLOWSTONE);

			ItemMeta meta = item.getItemMeta();

			meta.setDisplayName(ChatColor.AQUA + "Servers");

			meta.setLore(Lore);

			item.setItemMeta(meta);

			return item;
		}
		return new ItemStack(Material.AIR);
	}
}