package org.whitehack97.MyTeleport.Listener;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.whitehack97.MyTeleport.MyTeleportReloaded;
import org.whitehack97.MyTeleport.Util.FileManager;
import org.whitehack97.MyTeleport.api.PlayerTeleport;

public class PlayerListener implements Listener
{
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		LoadPlayerFile(player);
	}
	
	public static void LoadPlayerFile(Player player)
	{
		YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
		
		/* If player join at first time,
		 *	Creating new file in Players/{PLAYER_NAME}.yml
		 */
		if(! PlayerSection.contains("UUID")) PlayerSection.set("UUID", player.getUniqueId().toString());
		if(! PlayerSection.contains("Block-Always-Teleport")) PlayerSection.set("Block-Always-Teleport", true);
		if(! PlayerSection.contains("Info-World.Block-Enabled")) PlayerSection.set("Info-World.Block-Enabled", false);
		if(! PlayerSection.contains("Info-WhiteList.Enabled")) PlayerSection.set("Info-WhiteList.Enabled", false);
		if(! PlayerSection.contains("Info-WhiteList.Enabled")) PlayerSection.set("Info-WhiteList.Enabled", false);
		if(! PlayerSection.contains("Always-Teleport-Passed")) PlayerSection.set("Always-Teleport-Passed", false);
		FileManager.SaveFile("/Players/" + player.getName(), PlayerSection);

		// Load player information
		PlayerTeleport Teleport = new PlayerTeleport(player.getName());
		Teleport.setBlockAlwaysTeleport(PlayerSection.getBoolean("Block-Always-Teleport"));
		Teleport.setBlockTeleportinWorld(PlayerSection.getBoolean("Info-World.Block-Enabled"));
		Teleport.setEnableBlacklist(PlayerSection.getBoolean("Info-BlackList.Enabled"));
		Teleport.setEnableWhitelist(PlayerSection.getBoolean("Info-WhiteList.Enabled"));
		Teleport.setAlwaysPassed(PlayerSection.getBoolean("Always-Teleport-Passed"));
		if(PlayerSection.contains("Info-World.Worlds")) Teleport.setWorldList(PlayerSection.getStringList("Info-World.Worlds"));
		if(PlayerSection.contains("Info-BlackList.Blacklist")) Teleport.setBlackList(PlayerSection.getStringList("Info-BlackList.Blacklist"));
		if(PlayerSection.contains("Info-WhiteList.Whitelist")) Teleport.setWhiteList(PlayerSection.getStringList("Info-WhiteList.Whitelist"));
		if(PlayerSection.contains("Info-World.Worldlist")) Teleport.setWorldList(PlayerSection.getStringList("Info-World.Worldlist"));
		MyTeleportReloaded.PlayerInformation.put(player.getName(), Teleport);
		return;
	}
}
