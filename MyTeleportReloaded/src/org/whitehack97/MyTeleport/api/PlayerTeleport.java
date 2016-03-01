package org.whitehack97.MyTeleport.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerTeleport
{
	private Player player;
	private boolean BlockAlwaysTeleport = true;
	private boolean BlockTeleportinWorld = false;
	private boolean AlwaysTeleportPassed = false;
	private boolean EnableWhiteList = false;
	private boolean EnableBlackList = false;
	private List<String> BlackList = new ArrayList<String>();
	private List<String> WhiteList = new ArrayList<String>();
	private List<String> WorldList = new ArrayList<String>();

	public PlayerTeleport(String PlayerName)
	{
		this.player  = Bukkit.getServer().getPlayer(PlayerName);
	}
	public Player getPlayer()
	{
		return this.player;
	}
	
	public List<String> getBlackList()
	{
		return this.BlackList;
	}
	
	public boolean hasBlackList()
	{
		return BlackList.isEmpty() ? false : true;
	}
	
	public void addWhiteList(String playerName)
	{
		this.WhiteList.add(playerName);
	}
	
	public List<String> getWhiteList()
	{
		return this.WhiteList;
	}
	
	public boolean hasWhiteList()
	{
		return WhiteList.isEmpty() ? false : true;
	}
	
	public void setBlockAlwaysTeleport(boolean Blocked)
	{
		this.BlockAlwaysTeleport = Blocked;
	}
	
	public boolean isBlockAlwaysTeleport()
	{
		return this.BlockAlwaysTeleport;
	}
	
	public void setAlwaysPassed(boolean Passed)
	{
		this.AlwaysTeleportPassed = Passed;
	}
	
	public boolean isAlwaysPassed()
	{
		return this.AlwaysTeleportPassed;
	}
	
	public void setBlockTeleportinWorld(boolean Blocked)
	{
		this.BlockTeleportinWorld = Blocked;
	}
	
	public boolean isBlockTeleportinWorld()
	{
		return this.BlockTeleportinWorld;
	}
	
	public void setEnableWhitelist(boolean Enabled)
	{
		this.EnableWhiteList = Enabled;
	}
	
	public void setEnableBlacklist(boolean Enabled)
	{
		this.EnableBlackList = Enabled;
	}

	public void setWorldList(List<String> List)
	{
		this.WorldList = List;
	}
	
	public boolean isEnableWhitelist()
	{
		return this.EnableWhiteList;
	}
	
	public boolean isEnableBlacklist()
	{
		return this.EnableBlackList;
	}

	public List<String> getWorldList()
	{
		return this.WorldList;
	}

	public void setBlackList(List<String> List)
	{
		this.BlackList = List;
	}
	
	public void addWhitelist(String Name)
	{
		this.WhiteList.add(Name.toLowerCase());
	}
	
	public void delWhitelist(String Name)
	{
		this.WhiteList.remove(Name.toLowerCase());
	}
	
	public void addWorldList(String WorldName)
	{
		this.WorldList.add(WorldName.toLowerCase());
	}
	
	public void delWorldList(String WorldName)
	{
		this.WorldList.remove(WorldName.toLowerCase());
	}
	
	public void setWhiteList(List<String> List)
	{
		this.WhiteList = List;
	}
	
	public boolean isWhitelisted(String PlayerName)
	{
		return this.WhiteList.contains(PlayerName.toLowerCase());
	}
	
	public boolean isBlacklisted(String PlayerName)
	{
		return this.BlackList.contains(PlayerName.toLowerCase());
	}
	
	public boolean isWorldAdded(String World)
	{
		return this.WorldList.contains(World.toLowerCase());
	}
	
	public void addWorldlist(String Name)
	{
		this.WorldList.add(Name.toLowerCase());
	}
	
	public void delWorldlist(String Name)
	{
		this.WorldList.remove(Name.toLowerCase());
	}
	
	public void addBlacklist(String Name)
	{
		this.BlackList.add(Name.toLowerCase());
	}
	
	public void delBlacklist(String Name)
	{
		this.BlackList.remove(Name.toLowerCase());
	}
}
