package juniormunk.hub.classes;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;

public class PlayerConfig
{
	public File userfile;
	public YamlConfiguration userconfig;

	public PlayerConfig(Player player)
	{
		File userfolder = new File(Main.main.getDataFolder() + File.separator + "userdata" + File.separator);
		userfolder.mkdir();
		userfile = new File(Main.main.getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");
		if (!userfile.exists())
		{
			try
			{

				userfile.createNewFile();
				userconfig = YamlConfiguration.loadConfiguration(userfile);
				userconfig.set("Username", player.getName());
				userconfig.save(userfile);

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		userconfig = YamlConfiguration.loadConfiguration(userfile);

		Main.playerconfigs.put(player.getUniqueId(), this);

	}

	public void save()
	{
		try
		{
			userconfig.save(userfile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void close()
	{
		try
		{
			userconfig.save(userfile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Main.playerconfigs.values().remove(this);
	}

	public static PlayerConfig getConfig(Player p)
	{
		if (Main.playerconfigs.get(p.getUniqueId()) == null)
		{
			System.out.println("ERROR NULL");
		}
		return Main.playerconfigs.get(p.getUniqueId());
	}
}
