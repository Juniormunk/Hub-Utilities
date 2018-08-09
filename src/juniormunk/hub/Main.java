package juniormunk.hub;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import juniormunk.hub.classes.PlayerConfig;
import juniormunk.hub.classes.Server;
import juniormunk.hub.classes.TPARequest;
import juniormunk.hub.commands.addserver;
import juniormunk.hub.commands.back;
import juniormunk.hub.commands.delserver;
import juniormunk.hub.commands.evac;
import juniormunk.hub.commands.hideserver;
import juniormunk.hub.commands.home;
import juniormunk.hub.commands.homes;
import juniormunk.hub.commands.hub;
import juniormunk.hub.commands.linkworld;
import juniormunk.hub.commands.lockserver;
import juniormunk.hub.commands.serverTab;
import juniormunk.hub.commands.servers;
import juniormunk.hub.commands.sethome;
import juniormunk.hub.commands.sethub;
import juniormunk.hub.commands.setspawn;
import juniormunk.hub.commands.spawn;
import juniormunk.hub.commands.tpa;
import juniormunk.hub.commands.tpaccept;
import juniormunk.hub.commands.tpahere;
import juniormunk.hub.commands.tpdeny;
import juniormunk.hub.commands.unhideserver;
import juniormunk.hub.commands.unlinkworld;
import juniormunk.hub.commands.unlockserver;
import juniormunk.hub.handlers.BowShoot;
import juniormunk.hub.handlers.ChangeWorld;
import juniormunk.hub.handlers.Inventory;
import juniormunk.hub.handlers.InventoryDrop;
import juniormunk.hub.handlers.LogOut;
import juniormunk.hub.handlers.Login;
import juniormunk.hub.handlers.Move;
import juniormunk.hub.handlers.RightClick;
import juniormunk.hub.handlers.Teleport;
import juniormunk.hub.handlers.TpArrow;
import juniormunk.hub.commands.server;

public class Main extends JavaPlugin
{
	public int tplength = 120;
	public static Main main;
	public static ArrayList<Server> serverList = new ArrayList<Server>();

	public HashMap<String, BukkitRunnable> task = new HashMap<String, BukkitRunnable>();

	Player p;

	public static YamlConfiguration ConfigConfiguration;
	public static File ConfigFile;

	public static HashMap<UUID, TPARequest> tpas = new HashMap<UUID, TPARequest>();
	public static HashMap<UUID, PlayerConfig> playerconfigs = new HashMap<UUID, PlayerConfig>();

	@Override
	public void onEnable()
	{

		this.getConfig().options().header("#  *DO NOT EDIT THIS CONFIG WHILE THE SERVER IS RUNNING IT WILL BE DELETED/REPLACED ON SERVER STOP OR /RESET\r\n" + 
				"#\r\n" + 
				"#  *Data saved by the /sethub command to be used by the /hub command\r\n" + 
				"#\r\n" + 
				"#  *Uncomment this to use the server selector \r\n" + 
				"#  ServerData:\r\n" + 
				"#    Servers:\r\n" + 
				"#    - Survival\r\n" + 
				"#    - Creative\r\n" + 
				"#    Survival:\r\n" + 
				"#      Material: REDSTONE\r\n" + 
				"#      Slot: 4\r\n" + 
				"#    Creative:\r\n" + 
				"#      Material: WHITE_WOOL\r\n" + 
				"#      Slot: 4");
		
		main = this;
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().copyHeader(true);
		ConfigFile = new File(getDataFolder(), "Worlds.yml");
		ConfigConfiguration = YamlConfiguration.loadConfiguration(ConfigFile);

		this.saveDefaultConfig();

		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().copyHeader(true);

		// When the player file is created for the first time...
		if (!ConfigFile.exists())
		{
			try
			{
				ConfigConfiguration.set("ServerList", serverList);

				ConfigConfiguration.save(ConfigFile);
			}
			catch (IOException exception)
			{

				exception.printStackTrace();
			}
		}
		else
		{
			this.getConfig().options().copyDefaults(true);
			this.getConfig().options().copyHeader(true);
			Server.Load();

		}

		for (Player p : Bukkit.getOnlinePlayers())
		{
			if (p != null)
			{
				@SuppressWarnings("unused")
				PlayerConfig playerConfig = new PlayerConfig(p);

				Main.tpas.remove(p.getUniqueId());
			}
		}
		getServer().getPluginManager().registerEvents(new TpArrow(), this);
		getServer().getPluginManager().registerEvents(new BowShoot(), this);
		getServer().getPluginManager().registerEvents(new Login(), this);
		getServer().getPluginManager().registerEvents(new Move(), this);
		getServer().getPluginManager().registerEvents(new Teleport(), this);
		getServer().getPluginManager().registerEvents(new LogOut(), this);
		getServer().getPluginManager().registerEvents(new ChangeWorld(), this);
		getServer().getPluginManager().registerEvents(new Inventory(), this);
		getServer().getPluginManager().registerEvents(new InventoryDrop(), this);
		getServer().getPluginManager().registerEvents(new RightClick(), this);

		this.saveDefaultConfig();

		getCommand("hub").setExecutor(new hub(this));

		getCommand("sethub").setExecutor(new sethub(this));

		getCommand("spawn").setExecutor(new spawn(this));

		getCommand("setspawn").setExecutor(new setspawn(this));

		getCommand("tpa").setExecutor(new tpa(this));

		getCommand("tpahere").setExecutor(new tpahere(this));

		getCommand("tpdeny").setExecutor(new tpdeny(this));

		getCommand("tpaccept").setExecutor(new tpaccept(this));

		getCommand("addserver").setExecutor(new addserver(this));

		getCommand("server").setExecutor(new server(this));
		getCommand("server").setTabCompleter(new serverTab(this));

		getCommand("servers").setExecutor(new servers(this));

		getCommand("sethome").setExecutor(new sethome(this));

		getCommand("linkworld").setExecutor(new linkworld(this));

		getCommand("unlinkworld").setExecutor(new unlinkworld(this));

		getCommand("delserver").setExecutor(new delserver(this));

		getCommand("back").setExecutor(new back(this));

		getCommand("homes").setExecutor(new homes(this));

		getCommand("home").setExecutor(new home(this));

		getCommand("lockserver").setExecutor(new lockserver(this));

		getCommand("hideserver").setExecutor(new hideserver(this));

		getCommand("unlockserver").setExecutor(new unlockserver(this));

		getCommand("unhideserver").setExecutor(new unhideserver(this));
		
		getCommand("evacserver").setExecutor(new evac(this));

	}

	@Override
	public void onDisable()
	{
		
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().copyHeader(true);
		this.saveConfig();
		Server.Save();

	}

}
