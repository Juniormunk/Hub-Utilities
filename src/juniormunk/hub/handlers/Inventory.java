package juniormunk.hub.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import juniormunk.hub.Main;
import juniormunk.hub.classes.Config;
import juniormunk.hub.classes.PlayerConfig;
import juniormunk.hub.classes.Server;
import net.md_5.bungee.api.ChatColor;

public class Inventory implements Listener
{
	@EventHandler
	public void InventoryClick(InventoryClickEvent event)
	{
		if ((event.getWhoClicked() instanceof Player))
		{
			Player p = (Player) event.getWhoClicked();

			if (event.getWhoClicked().hasPermission("hub.tpbow"))
			{
				if (event.getCurrentItem() != null)
				{
					if (event.getCurrentItem().getItemMeta() != null)
					{
						if (event.getCurrentItem().getItemMeta().getLore() != null)
						{
							if (Main.main.getConfig().isSet("hub"))
							{
								if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
								{
									if (Config.readLocation("hub", null, Main.main.getConfig()).getWorld().getName().equals(p.getWorld().getName()))
									{
										if (event.getCurrentItem().equals(ChangeWorld.getSpecialBow()))
										{
											event.setCancelled(true);

											p.getInventory().clear();

											p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 0));
											p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 5));
											p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000000, 1));

											p.getInventory().setItem(0, ChangeWorld.getSpecialBow());
											p.getInventory().setItem(27, ChangeWorld.getSpecialArrow());
											p.getInventory().setItem(4, ChangeWorld.getServer());

										}
										if (event.getCurrentItem().equals(ChangeWorld.getSpecialArrow()))
										{
											event.setCancelled(true);
											p.getInventory().clear();

											p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 0));
											p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 5));
											p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000000, 1));

											p.getInventory().setItem(0, ChangeWorld.getSpecialBow());
											p.getInventory().setItem(27, ChangeWorld.getSpecialArrow());
											p.getInventory().setItem(4, ChangeWorld.getServer());

										}
										if (event.getCurrentItem().equals(ChangeWorld.getServer()))
										{
											p.getInventory().clear();

											p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 0));
											p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 5));
											p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000000, 1));

											p.getInventory().setItem(0, ChangeWorld.getSpecialBow());
											p.getInventory().setItem(27, ChangeWorld.getSpecialArrow());
											p.getInventory().setItem(4, ChangeWorld.getServer());

										}

										if (event.getCurrentItem().getItemMeta().getLore().contains(ChatColor.RESET + "" + ChatColor.GRAY + "Server:"))
										{
											String[] args = new String[1];
											args[0] = event.getCurrentItem().getItemMeta().getLore().get(1);

											if (Server.getServerByName(args[0]) == null)
											{
												p.sendMessage(ChatColor.RED + "That server doesn't exists!");
												return;
											}

											if (Server.getServerByName(args[0]).isHidden() && !p.hasPermission("hub.hidden"))
											{
												p.sendMessage(ChatColor.RED + "That server doesn't exists!");
												return;
											}

											if (p.getWorld().getName().equals(args[0]))
											{
												p.sendMessage(ChatColor.RED + "Your Already In That Server!");
												return;
											}

											if (!Server.getServerByName(args[0]).isLocked() && !p.hasPermission("hub.locked"))
											{
												p.sendMessage(ChatColor.RED + "That server is locked!");
												return;
											}

											String servername = Server.getServerByName(args[0]).getName();

											if (servername == null)
											{
												servername = p.getWorld().getName();
											}

											Location loc = Config.readLocation(servername + "_Last_Location", null, PlayerConfig.getConfig(p).userconfig);
											if (loc != null)
											{
												p.teleport(loc);
											}
											else
												p.teleport(Server.getServerByName(args[0]).getWorld().getSpawnLocation());

											p.sendMessage(ChatColor.GOLD + "Moving you to the " + servername + " server.");
										}
										event.setCancelled(true);
									}
								}

							}
						}

					}

				}
			}
		}

	}
}