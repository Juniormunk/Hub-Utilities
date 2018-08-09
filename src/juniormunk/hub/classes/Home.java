package juniormunk.hub.classes;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Home
{
	private String name;
	private Location loc;

	public Home(String name, Location loc)
	{
		this.name = name;
		this.loc = loc;
	}

	public Home(String data)
	{
		String[] parts = data.split(",");

		if (parts.length > 0 && 7 > parts.length)
		{
			name = parts[0];
			loc = new Location(Bukkit.getWorld(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Float.parseFloat(parts[5]), Float.parseFloat(parts[6]));
		}
	}

	public String toString()
	{
		return this.name + "," + this.loc.getWorld().getName() + "," + this.loc.getX() + "," + this.loc.getY() + "," + this.loc.getZ() + "," + this.loc.getYaw() + "," + this.loc.getPitch();
	}
}
