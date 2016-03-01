package org.whitehack97.MyTeleport.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.whitehack97.MyTeleport.MyTeleportReloaded;

public class FileManager
{
	public static File getFile(String Name)
	{
		if (!Name.endsWith(".yml"))
	    {
			Name = Name + ".yml";
	    }
		
		File file = new File("plugins/MyTeleportReloaded/" + Name);
		return file;
	}
	
	public static boolean SaveFile(String Name, YamlConfiguration Select)
	{
		if (!Name.endsWith(".yml"))
	    {
			Name = Name + ".yml";
	    }
		File file = new File("plugins/MyTeleportReloaded/" + Name);
		try
		{
			Select.save(file);
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static YamlConfiguration LoadSourceFile(String Name)
	{
		if (!Name.endsWith(".yml"))
	    {
			Name = Name + ".yml";
	    }

	    File file = getFile(Name);
	    if(!file.exists())
	    {
		      try
		      {
		    	  MyTeleportReloaded.plugin.saveResource(Name, true);
		          Bukkit.getConsoleSender().sendMessage("Create New File " + file.getAbsolutePath());
		      }
		      catch (IllegalArgumentException e)
		      {
		    	  try
		    	  {
					file.createNewFile();
		    	  }
		    	  catch (IOException e1)
		    	  {
					e1.printStackTrace();
		    	  }
		    	  
		    	  Bukkit.getConsoleSender().sendMessage("Create New File " + file.getAbsolutePath());
		      }
	    }
	    YamlConfiguration Section = YamlConfiguration.loadConfiguration(file);
	    return Section;
	}
}
