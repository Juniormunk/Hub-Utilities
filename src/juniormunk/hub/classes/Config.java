package juniormunk.hub.classes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import juniormunk.hub.Main;

public class Config
{
	public static boolean saveLocation(Location loc, String name, ConfigurationSection section, FileConfiguration config)
	{
		if (Main.main.getConfig().isSet("hub"))
		{
			if (Config.readLocation("hub", null, Main.main.getConfig()) != null)
			{
				if (Config.readLocation("hub", null, Main.main.getConfig()).getWorld().equals(loc.getWorld()))
				{
					return false;
				}
			}

		}
		if (section != null)
		{
			ConfigurationSection locsect = section.createSection(name);
			locsect.set("x", loc.getX());
			locsect.set("y", loc.getY());
			locsect.set("z", loc.getZ());
			locsect.set("yaw", loc.getYaw());
			locsect.set("pitch", loc.getPitch());
			locsect.set("world", loc.getWorld().getName());

			return true;

		}
		else if (config != null)
		{

			ConfigurationSection locsect = config.createSection(name);
			locsect.set("x", loc.getX());
			locsect.set("y", loc.getY());
			locsect.set("z", loc.getZ());
			locsect.set("yaw", loc.getYaw());
			locsect.set("pitch", loc.getPitch());
			locsect.set("world", loc.getWorld().getName());

			return true;
		}
		else
			throw new NullPointerException("NULL : Both section and config can not be null!");
	}

	public static boolean saveHomeLocation(Location loc, String name, ConfigurationSection section, FileConfiguration config)
	{
		if (section != null)
		{
			ConfigurationSection locsect = section.createSection(name);
			locsect.set("x", loc.getX());
			locsect.set("y", loc.getY());
			locsect.set("z", loc.getZ());
			locsect.set("yaw", loc.getYaw());
			locsect.set("pitch", loc.getPitch());
			locsect.set("world", loc.getWorld().getName());

			return true;

		}
		else if (config != null)
		{

			ConfigurationSection locsect = config.createSection(name);
			locsect.set("x", loc.getX());
			locsect.set("y", loc.getY());
			locsect.set("z", loc.getZ());
			locsect.set("yaw", loc.getYaw());
			locsect.set("pitch", loc.getPitch());
			locsect.set("world", loc.getWorld().getName());

			return true;
		}
		else
			throw new NullPointerException("NULL : Both section and config can not be null!");
	}

	public static boolean saveHubLocation(Location loc, String name, ConfigurationSection section, FileConfiguration config)
	{

		if (section != null)
		{
			ConfigurationSection locsect = section.createSection(name);
			locsect.set("x", loc.getX());
			locsect.set("y", loc.getY());
			locsect.set("z", loc.getZ());
			locsect.set("yaw", loc.getYaw());
			locsect.set("pitch", loc.getPitch());
			locsect.set("world", loc.getWorld().getName());

			return true;

		}
		else if (config != null)
		{

			ConfigurationSection locsect = config.createSection(name);
			locsect.set("x", loc.getX());
			locsect.set("y", loc.getY());
			locsect.set("z", loc.getZ());
			locsect.set("yaw", loc.getYaw());
			locsect.set("pitch", loc.getPitch());
			locsect.set("world", loc.getWorld().getName());

			return true;
		}
		else
			throw new NullPointerException("NULL : Both section and config can not be null!");
	}

	public static Location readLocation(String name, ConfigurationSection section, FileConfiguration config)
	{
		if (section != null)
		{
			if (!section.isSet(name))
			{
				return null;
			}
			ConfigurationSection locsect = section.getConfigurationSection(name);
			if (!locsect.isSet("world"))
			{
				return null;
			}
			double x = locsect.getDouble("x");
			double y = locsect.getDouble("y");
			double z = locsect.getDouble("z");
			float yaw = (float) locsect.getDouble("yaw");
			float pitch = (float) locsect.getDouble("pitch");
			World w = Bukkit.getWorld(locsect.getString("world"));
			if (w == null)
			{
				return null;
			}

			return (new Location(w, x, y, z, yaw, pitch));
		}
		else if (config != null)
		{
			if (!config.isSet(name))
			{
				return null;
			}
			ConfigurationSection locsect = config.getConfigurationSection(name);
			if (!locsect.isSet("world"))
			{
				return null;
			}
			double x = locsect.getDouble("x");
			double y = locsect.getDouble("y");
			double z = locsect.getDouble("z");
			float yaw = (float) locsect.getDouble("yaw");
			float pitch = (float) locsect.getDouble("pitch");
			World w = Bukkit.getWorld(locsect.getString("world"));

			if (w == null)
			{
				return null;
			}

			return (new Location(w, x, y, z, yaw, pitch));

		}
		else
		{
			return null;
		}

	}
}
