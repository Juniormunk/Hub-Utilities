package juniormunk.hub.classes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class TPARequest
{
	private boolean tpahere;
	private Player sender;
	private Player reciever;

	public TPARequest(boolean tpahere, Player sender, Player reciever)
	{
		this.tpahere = tpahere;
		this.setSender(sender);
		this.setReciever(reciever);
	}

	public boolean accept()
	{
		if(tpahere&&sender.isOnline()&&reciever.isOnline())
		{
			sender.sendMessage(ChatColor.GREEN+"Teleport accepted teleporting them now.");
			reciever.teleport(sender.getLocation());
			return true;
		}
		if(!tpahere&&sender.isOnline()&&reciever.isOnline())
		{
			reciever.sendMessage(ChatColor.GREEN+"Teleport accepted teleporting them now.");

			sender.teleport(reciever.getLocation());
			return true;
		}
		return false;
	}

	public boolean isTpahere()
	{
		return tpahere;
	}

	public void setTpahere(boolean tpahere)
	{
		this.tpahere = tpahere;
	}

	public Player getSender()
	{
		return sender;
	}

	public void setSender(Player sender)
	{
		this.sender = sender;
	}

	public Player getReciever()
	{
		return reciever;
	}

	public void setReciever(Player reciever)
	{
		this.reciever = reciever;
	}
}
