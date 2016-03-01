package org.whitehack97.MyTeleport.Util;

import org.bukkit.configuration.ConfigurationSection;

public class SectionManager
{
	public static ConfigurationSection addSection(ConfigurationSection Section, String Object)
	{
		return Section.getConfigurationSection(Object);
	}
	
	public static boolean hasKey(ConfigurationSection Section, String Object)
	{
		return Section.contains(Object);
	}
}
