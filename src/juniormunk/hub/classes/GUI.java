package juniormunk.hub.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import juniormunk.hub.Main;
import net.md_5.bungee.api.ChatColor;

public class GUI
{

	public static void showGUI(Player p)
	{
		Inventory inv = null;

		FileConfiguration config = Main.main.getConfig();
		if (config.isSet("ServerData"))
		{
			ConfigurationSection data = config.getConfigurationSection("ServerData");
			data.isSet("Servers");
			{

				List<String> list = data.getStringList("Servers");
				inv = Bukkit.createInventory(null, 9, "Servers");
				for (String name : list)
				{
					Server serv = Server.getServerByName(name);
					if (serv != null)
					{
						ConfigurationSection sec = config.getConfigurationSection("ServerData");
						if (sec.isSet(serv.getName()))
						{

							String material = null;
							int slot = -1;
							sec = sec.getConfigurationSection(serv.getName());
							if (sec.isSet("Material"))
							{
								material = sec.getString("Material");
							}
							if (sec.isSet("Slot"))
							{
								if (sec.getInt("Slot") < inv.getSize() && sec.getInt("Slot") > 0)
								{
									slot = sec.getInt("Slot");
								}
							}

							inv.setItem(slot, getServerItem(serv.getName(), material, serv.getPlayers().size()));

						}
					}
				}
				if (inv != null)
				{
					p.openInventory(inv);
				}
			}
		}

	}

	public static ItemStack getServerItem(String servername, String material, int playerCount)
	{
		Material m = Material.getMaterial(material);

		if (m == null)
		{
			m = Material.ANVIL;
		}

		ItemStack item = new ItemStack(m);

		ItemMeta meta = item.getItemMeta();

		List<String> Lore = new ArrayList<String>();

		Lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Server:");

		Lore.add(servername);

		Lore.add("" + ChatColor.RESET + ChatColor.GRAY + "Players: " + playerCount);

		meta.setLore(Lore);

		meta.setDisplayName(servername);

		item.setItemMeta(meta);

		return item;
	}
}
