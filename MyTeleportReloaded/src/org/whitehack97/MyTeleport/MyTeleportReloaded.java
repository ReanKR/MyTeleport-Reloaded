package org.whitehack97.MyTeleport;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.whitehack97.MyTeleport.Listener.PlayerListener;
import org.whitehack97.MyTeleport.api.PlayerTeleport;


public class MyTeleportReloaded extends JavaPlugin implements Listener
{
	public static MyTeleportReloaded plugin;
	public static String Prefix = "」a[」1M」eT」fR」a]」f ";
	public static Map<String, PlayerTeleport> PlayerInformation = new HashMap<String, PlayerTeleport>();

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		MyTeleportReloaded.plugin = this;

		if(!Bukkit.getServer().getOnlinePlayers().isEmpty())
		{
			for(Player player : Bukkit.getServer().getOnlinePlayers())
			{
				PlayerListener.LoadPlayerFile(player);
			}
		}
		getCommand("MyTeleport.Main").setExecutor(new MyTeleportCommands());
		if(Bukkit.getPluginManager().isPluginEnabled("MyTeleport")) Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("MyTeleport"));
		Bukkit.getConsoleSender().sendMessage(MyTeleportReloaded.Prefix + ChatColor.YELLOW + "MyTeleport Reloaded v" + this.getDescription().getVersion() + " now Enabled.");
		Bukkit.getConsoleSender().sendMessage(MyTeleportReloaded.Prefix + ChatColor.GREEN + "This plugin recreated by Rean KR, Original Plugin : MyTeleport 1.2.0");
		Bukkit.getConsoleSender().sendMessage(MyTeleportReloaded.Prefix + ChatColor.GREEN + "http://cafe.naver.com/suserver24, Support : whitehack97@gmail.com");
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getConsoleSender().sendMessage(MyTeleportReloaded.Prefix + ChatColor.RED + "MyTeleport Reloaded v" + this.getDescription().getVersion() + " now Disabled.");
		Bukkit.getConsoleSender().sendMessage(MyTeleportReloaded.Prefix + ChatColor.GREEN + "This plugin recreated by Rean KR, Original Plugin : MyTeleport 1.2.0");
		Bukkit.getConsoleSender().sendMessage(MyTeleportReloaded.Prefix + ChatColor.GREEN + "http://cafe.naver.com/suserver24, Support : whitehack97@gmail.com");
	}

}
