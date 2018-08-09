package juniormunk.hub.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import juniormunk.hub.Main;

public class Server
{
	public ArrayList<String> linked = new ArrayList<String>();
	private String name;
	private Location spawn;
	World world;

	private boolean isLocked = false;

	private boolean isHidden = false;

	public Server(String name, World world, ArrayList<String> linked)
	{
		this.name = name;
		this.linked = linked;
		this.world = world;
	}

	public boolean isLinked(String name)
	{
		for (String link : linked)
		{
			if (name.equals(link))
			{
				return true;
			}
		}
		return false;
	}

	public String getName()
	{
		return name;
	}

	public World getWorld()
	{
		return world;
	}

	public ArrayList<Player> getPlayers()
	{
		ArrayList<Player> players = new ArrayList<Player>();
		
		for (Player p : Bukkit.getOnlinePlayers())
		{
			if (Server.getServerByLinked(p.getWorld().getName()) == this)
			{
				players.add(p);
			}
		}
		return players;
	}

	public static void Save()
	{
		Main.main.getConfig().options().copyDefaults(true);
		Main.main.getConfig().options().copyHeader(true);
		ArrayList<String> servers = new ArrayList<String>();

		for (Server serv : Main.serverList)
		{
			servers.add(serv.getName());
		}

		Main.ConfigConfiguration.set("ServerList", servers);
		for (Server serv : Main.serverList)
		{
			String server = serv.getName();
			Main.ConfigConfiguration.createSection(server);

			Main.ConfigConfiguration.getConfigurationSection(server).set("Linked", serv.linked);
			Main.ConfigConfiguration.getConfigurationSection(server).set("World", serv.getWorld().getName());

			if (serv.getSpawn() != null)
			{
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_X", serv.getSpawn().getX());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_Y", serv.getSpawn().getY());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_Z", serv.getSpawn().getZ());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_Pitch", serv.getSpawn().getPitch());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_Yaw", serv.getSpawn().getYaw());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_World", serv.getSpawn().getWorld().getName());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Hidden", serv.isHidden);
				Main.ConfigConfiguration.getConfigurationSection(server).set("Locked", serv.isLocked);

			}
			else
			{
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_X", serv.getWorld().getSpawnLocation().getX());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_Y", serv.getWorld().getSpawnLocation().getY());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_Z", serv.getWorld().getSpawnLocation().getZ());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_Pitch", serv.getWorld().getSpawnLocation().getPitch());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_Yaw", serv.getWorld().getSpawnLocation().getYaw());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Spawn_World", serv.getWorld().getSpawnLocation().getWorld().getName());
				Main.ConfigConfiguration.getConfigurationSection(server).set("Hidden", serv.isHidden);
				Main.ConfigConfiguration.getConfigurationSection(server).set("Locked", serv.isLocked);

			}
		}
		try
		{
			Main.ConfigConfiguration.save(Main.ConfigFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void Load()
	{
		List<String> list = null;
		{
			list = Main.ConfigConfiguration.getStringList("ServerList");

			Main.serverList = new ArrayList<Server>();
			if (list != null)
			{
				for (String server : list)
				{
					ArrayList<String> linked = (ArrayList<String>) Main.ConfigConfiguration.getConfigurationSection(server).getStringList("Linked");

					String world = Main.ConfigConfiguration.getConfigurationSection(server).getString("World");
					Server serv = new Server(server, Bukkit.getWorld(world), linked);

					double x = Main.ConfigConfiguration.getConfigurationSection(server).getDouble("Spawn_X");
					double y = Main.ConfigConfiguration.getConfigurationSection(server).getDouble("Spawn_Y");
					double z = Main.ConfigConfiguration.getConfigurationSection(server).getDouble("Spawn_Z");
					float pitch = (float) Main.ConfigConfiguration.getConfigurationSection(server).getDouble("Spawn_Pitch");
					float yaw = (float) Main.ConfigConfiguration.getConfigurationSection(server).getDouble("Spawn_Yaw");
					World spawnworld = Bukkit.getWorld(Main.ConfigConfiguration.getConfigurationSection(server).getString("Spawn_World"));

					serv.setSpawn(new Location(spawnworld, x, y, z, yaw, pitch));

					boolean locked = Main.ConfigConfiguration.getConfigurationSection(server).getBoolean("Locked", false);
					boolean hidden = Main.ConfigConfiguration.getConfigurationSection(server).getBoolean("Hidden", false);

					serv.setLocked(locked);
					serv.setHidden(hidden);

					Main.serverList.add(serv);
				}
			}
		}
	}

	public static World getWorldByName(String server)
	{
		for (Server serv : Main.serverList)
		{
			if (serv.getName().equals(server))
			{
				return serv.getWorld();
			}
		}
		return null;

	}

	public static Server getServerByName(String name)
	{
		for (Server serv : Main.serverList)
		{
			if (serv.getName().equalsIgnoreCase(name))
			{
				return serv;
			}
		}
		return null;
	}

	public static Server getServerByLinked(String linked)
	{

		for (Server serv : Main.serverList)
		{
			if (serv.isLinked(linked))
			{
				return serv;
			}
			if (serv.getWorld().getName().equals(linked))
			{
				return serv;
			}
		}

		return null;
	}

	public static boolean sameServer(Player p, Player p1)
	{
		if (Server.getServerByLinked(p.getWorld().getName()) == Server.getServerByLinked(p1.getWorld().getName()))
		{
			return true;
		}
		return false;
	}

	public Location getSpawn()
	{
		return spawn;
	}

	public void setSpawn(Location spawn)
	{
		this.spawn = spawn;
	}

	public boolean isHidden()
	{
		return isHidden;
	}

	public void setHidden(boolean isHidden)
	{
		this.isHidden = isHidden;
	}

	public boolean isLocked()
	{
		return isLocked;
	}

	public void setLocked(boolean isOpen)
	{
		this.isLocked = isOpen;
	}
}
