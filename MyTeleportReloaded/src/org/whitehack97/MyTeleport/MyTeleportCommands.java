package org.whitehack97.MyTeleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.whitehack97.MyTeleport.Util.FileManager;
import org.whitehack97.MyTeleport.api.PlayerTeleport;

public class MyTeleportCommands implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender Sender, Command command, String label, String[] args)
	{
		String cmd = command.getName();
		if(Sender instanceof Player)
		{
			Player player = (Player)Sender;
			if(cmd.equalsIgnoreCase("Myteleport.Main"))
			{
				if(args.length < 1) // not exist argsuments
				{
					Page(player, 0);
					return true;
				}
				else
				{
					if(args[0].equalsIgnoreCase("page"))
					{
						if(args.length < 2)
						{
							Page(player, 0);
							return true;
						}
						else if(args.length < 3)
						{
							Page(player, Integer.parseInt(args[1]));
							return true;
						}
					}

					else if(args[0].equalsIgnoreCase("show"))
					{
							
					}
					else if(args[0].equalsIgnoreCase("allow"))
					{
							if(args.length >= 1)
							{
								if(args.length == 1)
								{
									PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
									teleport.setBlockAlwaysTeleport(!teleport.isBlockAlwaysTeleport());
									YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
									PlayerSection.set("Block-Always-Teleport", teleport.isBlockAlwaysTeleport());
									if(teleport.isBlockAlwaysTeleport() == true)
									{
										if(teleport.isBlockTeleportinWorld())
										{
											teleport.setBlockTeleportinWorld(false);
											PlayerSection.set("Info-World.Block-Enabled", false);
											msg(player, "&e���忡 ���� ���� �ڵ� �źΰ� �ڵ� ��Ȱ��ȭ�Ǿ����ϴ�.");
										}
									}
									PlayerSection.set("Block-Always-Teleport", teleport.isBlockAlwaysTeleport());

									FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
									MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
									String Status = teleport.isBlockAlwaysTeleport() ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
									msg(player, "&a���忡 ������� ���� �ڵ� �źΰ� " + Status + "&b ���·� &6��ȯ�Ǿ����ϴ�.");
									if(! teleport.isBlockAlwaysTeleport() && ! teleport.isBlockTeleportinWorld())
									{
										msg(player, "&f[&e���&f] &4�ź� ����� ��� ��Ȱ��ȭ�� �����Դϴ�!");
									}
									return true;
								}
								else
								{
									if(isBoolean(args[1]))
									{
										PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
										if(Boolean.parseBoolean(args[1]) == teleport.isBlockAlwaysTeleport())
										{
											String Status = Boolean.parseBoolean(args[1]) ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
											msg(player, "&c���忡 ������� ���� �ź� ����� �̹� " + Status + " �����Դϴ�.");
											return false;
										}
										teleport.setBlockAlwaysTeleport(Boolean.parseBoolean(args[1]));
										YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
										PlayerSection.set("Block-Always-Teleport", Boolean.parseBoolean(args[1]));
										if(Boolean.parseBoolean(args[1]) == true)
										{
											if(teleport.isBlockTeleportinWorld())
											{
												teleport.setBlockTeleportinWorld(false);
												PlayerSection.set("Info-World.Block-Enabled", false);
												msg(player, "&e���忡 ���� ���� �ڵ� �źΰ� �ڵ� ��Ȱ��ȭ�Ǿ����ϴ�.");
											}
										}
										FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
										MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
										String Status = Boolean.parseBoolean(args[1]) ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
										msg(player, "&b���忡 ������� ���� �ڵ� �źΰ�  " + Status + "&a�Ǿ����ϴ�.");
										if(! teleport.isBlockAlwaysTeleport() && ! teleport.isBlockTeleportinWorld())
										{
											msg(player, "&f[&e���&f] &4�ź� ����� ��� ��Ȱ��ȭ�� �����Դϴ�!");
										}
										return true;
									}
									else
									{
										msg(player, "&4����:&c [true|false] ���� �߸��� ���Դϴ�.");
										msg(player, "");
										msg(player, "&f����: ");
										msg(player, "&6/mt | mteleport | myteleport allow [true|false]");
										msg(player, "&b�ٸ� �÷��̾ �ڽſ��� �ڷ���Ʈ�� �Ϸ��� �� ��, ���� ���ο� ��� ���� ������ ������ �� ��");
										msg(player, "�ڵ����� �ź����� ���θ� �����մϴ�.");
										msg(player, "&c[ ] ���� �ƹ��͵� �������� �ʴ´ٸ� ���� �ݴ�� ��ȯ�˴ϴ�.");
										msg(player, "&c�̰��� ���� true�� �ϸ�  myteleport worldallow ����� �ڵ����� ��Ȱ��ȭ");
										msg(player, "�Ǵ� �����Ͻʽÿ�!");
										return false;
									}
								}
							}
					}
					else if(args[0].equalsIgnoreCase("worldallow"))
					{
						if(args.length >= 1) //args[0], args[1]
						{
							if(args.length == 1)
							{
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								teleport.setBlockTeleportinWorld(!teleport.isBlockTeleportinWorld());
								YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
								PlayerSection.set("Info-World.Block-Enabled", teleport.isBlockTeleportinWorld());
								if(teleport.isBlockTeleportinWorld() == true)
								{
									if(teleport.isBlockAlwaysTeleport())
									{
										teleport.setBlockAlwaysTeleport(false);
										PlayerSection.set("Block-Always-Teleport", false);
										msg(player, "&e���忡 ��� ���� ���� �ڵ� �źΰ� �ڵ� ��Ȱ��ȭ�Ǿ����ϴ�.");
									}
								}
								PlayerSection.set("Info-World.Block-Enabled", teleport.isBlockTeleportinWorld());

								FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
								MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
								String Status = teleport.isBlockTeleportinWorld() ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
								msg(player, "&a���忡 ���� ���� �ڵ� �źΰ� " + Status + " &b���·� &6��ȯ�Ǿ����ϴ�.");
								if(! teleport.isBlockAlwaysTeleport() && ! teleport.isBlockTeleportinWorld())
								{
									msg(player, "&f[&e���&f] &4�ź� ����� ��� ��Ȱ��ȭ�� �����Դϴ�!");
								}
								return true;
							}
							else
							{
								if(isBoolean(args[1]))
								{
									PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
									if(Boolean.parseBoolean(args[1]) == teleport.isBlockTeleportinWorld())
									{
										String Status = Boolean.parseBoolean(args[1]) ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
										msg(player, "&c���忡 ���� ���� �ź� ����� �̹� " + Status + " �����Դϴ�.");
										return false;
									}
									teleport.setBlockTeleportinWorld(Boolean.parseBoolean(args[1]));
									YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
									PlayerSection.set("Info-World.Block-Enabled", Boolean.parseBoolean(args[1]));
									if((Boolean.parseBoolean(args[1])) == true)
									{
										if(teleport.isBlockAlwaysTeleport())
										{
											teleport.setBlockAlwaysTeleport(false);
											PlayerSection.set("Block-Always-Teleport", false);
											msg(player, "&e���忡 ��� ���� ���� �ڵ� �źΰ� �ڵ� ��Ȱ��ȭ�Ǿ����ϴ�.");
										}
									}
									FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
									MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
									String Status = Boolean.parseBoolean(args[1]) ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
									msg(player, "&b���忡 ���� ���� �ڵ� �źΰ�  " + Status + "&a�Ǿ����ϴ�.");
									if(! teleport.isBlockAlwaysTeleport() && ! teleport.isBlockTeleportinWorld())
									{
										msg(player, "&f[&e���&f] &4�ź� ����� ��� ��Ȱ��ȭ�� �����Դϴ�!");
									}
									return true;
								}
								else
								{
									if(args.length == 2)
									{
										if(args[1].equalsIgnoreCase("add") && args.length < 3)
										{
											if(args.length < 3)
											{
												msg(player, "&4����:&c �����Ͻ� ���尡 �� ���� �����ϴ�.");
												msg(player, "");
												msg(player, "&f����: ");
												msg(player, "&6/mt | mteleport | myteleport worldallow add <world>...");
												msg(player, "&b��Ͽ� �߰��� ���忡���� ������ �ڷ���Ʈ��, ������ �ڵ����� �ź��մϴ�.");
												msg(player, "&b������ ���带 �ڵ� �ź� ��Ͽ� ���� �� �߰��մϴ�.");
												msg(player, "&e���� ��� CA_001, CA_002��� ���带 ��Ͽ� �߰��ϰ��� �Ѵٸ�");
												msg(player, "&6/mt worldallow add CA_001 CA_002");
												return false;
											}
										}
										else if(args[1].equalsIgnoreCase("del") && args.length < 3)
										{
											if(args.length < 3)
											{
												msg(player, "&4����:&c �����Ͻ� ���尡 �� ���� �����ϴ�.");
												msg(player, "");
												msg(player, "&f����: ");
												msg(player, "&6/mt | mteleport | myteleport worldallow del <world>...");
												msg(player, "&b��Ͽ� �߰��� ���忡���� ������ �ڷ���Ʈ��, ������ �ڵ����� �ź��մϴ�.");
												msg(player, "&b������ ���带 �ڵ� �ź� ��Ͽ��� �����մϴ�.");
												msg(player, "&���� ��� CA_001, CA_002��� ���带 ��Ͽ��� �����ϰ��� �Ѵٸ�");
												msg(player, "&6/mt worldallow del CA_001 CA_002");
												return false;
											}
										}
										else
										{
											msg(player, "&4����:&c [true|false] ���� �߸��� ���Դϴ�.");
											msg(player, "");
											msg(player, "&f����: ");
											msg(player, "&6/mt | mteleport | myteleport worldallow [true|false]");
											msg(player, "&aworldallow&f:&b �ڽ��� ���� Ư�� ���忡 �ְ�, �ٸ� ����� �ڷ���Ʈ�Ϸ��� �ϸ�");
											msg(player, "&b�ڵ����� �ź����� ���θ� �����մϴ�.");
											msg(player, "&c[ ] ���� �ƹ��͵� �������� �ʴ´ٸ� ���� �ݴ�� ��ȯ�˴ϴ�.");
											msg(player, "&c�̰��� ���� true�� �ϸ�  myteleport allow ����� �ڵ����� ��Ȱ��ȭ");
											msg(player, "�Ǵ� �����Ͻʽÿ�!");
											return false;
										}
									}
									else if(args[1].equalsIgnoreCase("add") && args.length >= 3)
									{
										msg(player, "&6���带 ��Ͽ� �߰��ϰ� �ֽ��ϴ�...");
										PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
										for(int count = 0; count< args.length; count++)
										{
											if(count == 0 || count == 1) continue;
											else
											{
												if(teleport.isWorldAdded(args[count]))
												{
													msg(player, args[count] + "����� �̹� ��Ͽ� �ֽ��ϴ�!");
												}
												else
												{
													boolean found = false;
													for(World world: Bukkit.getServer().getWorlds())
													{
														if(world.getName().equalsIgnoreCase(args[count]))
														{
															found = true;
														}
													}
													if(found)
													{
														teleport.addWorldlist(args[count]);
													}
													else
													{
														msg(player, "&4����:&f " + args[count] + "��� ����� �� ������ �����ϴ�.");
													}
												}
											}
										}
										YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
										PlayerSection.set("Info-World.Worlds", teleport.getWorldList());
										teleport.setWorldList(teleport.getWorldList());
										FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
										msg(player, "&a�ش� ���带 �߰��Ͽ����ϴ�.");
										if(! teleport.isBlockTeleportinWorld())
										{
											msg(player, "&f[&4����&f] &a���忡 ���� ���� �ź� ����� Ȱ��ȭ���� �ʾҽ��ϴ�.");
											msg(player, "&e���� ����ϰ��� �Ѵٸ� �ش� ����� Ȱ��ȭ�Ͻʽÿ�.");
										}
									}
									else if(args[1].equalsIgnoreCase("del") && args.length >= 3)
									{
										msg(player, "&6���带 ��Ͽ��� �����ϰ� �ֽ��ϴ�...");
										PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
										for(int count = 0; count< args.length; count++)
										{
											if(count == 0 || count == 1) continue;
											else
											{
												if(! teleport.isWorldAdded(args[count]))
												{
													msg(player, args[count] + "����� ��Ͽ� �����ϴ�!");
												}
												else
												{
													teleport.delWorldlist(args[count]);
												}
											}
										}
										YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
										PlayerSection.set("Info-World.Worlds", teleport.getWorldList());
										teleport.setWorldList(teleport.getWorldList());
										FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
										msg(player, "&a�ش� ���带 ��Ͽ��� �����Ͽ����ϴ�.");
									}
									else
									{
										msg(player, "&4����: &c�˼� ���� ��ɾ��̰ų� �����  Ȱ��ȭ �ϴ� ��������  [true|false]���� �߸���.");
										msg(player, "&6/mt | mteleport | myteleport");
										return false;
									}
								}
							}
						}
					}
					else if(args[0].equalsIgnoreCase("whitelist"))
					{
						if(args.length == 1)
						{
							PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
							teleport.setEnableWhitelist(!teleport.isEnableWhitelist());
							YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
							PlayerSection.set("Info-WhiteList.Enabled", teleport.isEnableWhitelist());
							FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
							MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
							String Status = teleport.isEnableWhitelist() ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
							msg(player, "&fȭ��Ʈ����Ʈ ����� " + Status + " &b���·� &6��ȯ�Ǿ����ϴ�.");
							return true;
						}
						else if(args.length == 2)
						{
							if(isBoolean(args[1]))
							{
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								if(Boolean.parseBoolean(args[1]) == teleport.isEnableWhitelist())
								{
									String Status = Boolean.parseBoolean(args[1]) ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
									msg(player, "&c�̹� ȭ��Ʈ����Ʈ ����� " + Status + " �����Դϴ�.");
									return false;
								}
								teleport.setEnableWhitelist(Boolean.parseBoolean(args[1]));
								YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
								PlayerSection.set("Info-WhiteList.Enabled", Boolean.parseBoolean(args[1]));
								FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
								MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
								String Status = Boolean.parseBoolean(args[1]) ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
								msg(player, "&fȭ��Ʈ����Ʈ ����� " + Status + "&e�Ǿ����ϴ�.");
								return true;
							}
							else if(args[1].equalsIgnoreCase("add"))
							{
								if(args.length < 3)
								{
									msg(player, "&4����:&c �����Ͻ� �̸��� �� �� �����ϴ�!");
									msg(player, "");
									msg(player, "&f����: ");
									msg(player, "&6/mt | mteleport | myteleport whltelist add <player>...");
									msg(player, "&awhitelist&f: &b�ڽ��� ���� �ź� ���ο� ������� <playername>�� �ڷ���Ʈ�� ������ �㰡�մϴ�.");
									msg(player, "&b<player>���� 1�� �̻� ������ �����մϴ�. ���� ��� 2���� ������ ȭ��Ʈ����Ʈ�� �߰��ϰ� �ʹٸ�");
									msg(player, "&6/mt whitelist add player1 player2");
									return false;
								}
							}
							else if(args[1].equalsIgnoreCase("del"))
							{
								if(args.length < 3)
								{
									msg(player, "&4����:&c �����Ͻ� �̸��� �� �� �����ϴ�!");
									msg(player, "");
									msg(player, "&f����: ");
									msg(player, "&6/mt | mteleport | myteleport whltelist del <player>...");
									msg(player, "&awhitelist&f: &b�ڽ��� ���� �ź� ���ο� ������� <playername>�� �ڷ���Ʈ�� ������ �㰡�մϴ�.");
									msg(player, "&b<player>���� 1�� �̻� ������ �����մϴ�. ���� ��� 2���� ������ ȭ��Ʈ����Ʈ���� �����ϰ� �ʹٸ�");
									msg(player, "&6/mt whitelist del player1 player2");
									return false;
								}
							}
							else
							{
								msg(player, "&4����: &c�˼� ���� ��ɾ��̰ų� �����  Ȱ��ȭ �ϴ� ��������  [true|false]���� �߸���.");
								msg(player, "&6/mt | mteleport | myteleport");
								return false;
							}
						}
						else if(args.length >= 3 && args[1].equalsIgnoreCase("add") && args[0].equalsIgnoreCase("whitelist"))
						{
							if(args.length >= 3)
							{
								msg(player, "&6ȭ��Ʈ����Ʈ�� �÷��̾ �߰��ϰ� �ֽ��ϴ�...");
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								for(int count = 0; count< args.length; count++)
								{
									if(count == 0 || count == 1) continue;
									else
									{
										if(teleport.isWhitelisted(args[count]))
										{
											msg(player, args[count] + "���� �̹� ȭ��Ʈ����Ʈ�� �ֽ��ϴ�.");
										}
										else
										{
											teleport.addWhitelist(args[count]);
										}
									}
								}
								YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
								PlayerSection.set("Info-WhiteList.Whitelist", teleport.getWhiteList());
								teleport.setWhiteList(teleport.getWhiteList());
								FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
								msg(player, "&aȭ��Ʈ����Ʈ�� ���� �߰��Ͽ����ϴ�.");
								if(! teleport.isEnableWhitelist())
								{
									msg(player, "&f[&4����&f] &aȭ��Ʈ����Ʈ ����� Ȱ��ȭ���� �ʾҽ��ϴ�.");
									msg(player, "&e���� ����ϰ��� �Ѵٸ� �ش� ����� Ȱ��ȭ�Ͻʽÿ�.");
								}
								return true;
							}
						}
						else if(args.length >= 3 && args[1].equalsIgnoreCase("del") && args[0].equalsIgnoreCase("whitelist"))
						{
							if(args.length >= 3)
							{
								msg(player, "&6ȭ��Ʈ����Ʈ���� �ش� �÷��̾ �����ϰ� �ֽ��ϴ�...");
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								for(int count = 0; count< args.length; count++)
								{
									if(count == 0 || count == 1) continue;
									else
									{
										if(! teleport.isWhitelisted(args[count]))
										{
											msg(player, args[count] + "���� �̹� ȭ��Ʈ����Ʈ�� �����ϴ�.");
										}
										else
										{
											teleport.delWhitelist(args[count]);
										}
									}
								}
								YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
								PlayerSection.set("Info-WhiteList.Whitelist", teleport.getWhiteList());
								teleport.setWhiteList(teleport.getWhiteList());
								FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
								msg(player, "&aȭ��Ʈ����Ʈ���� �ش� �÷��̾ ���� �����Ͽ����ϴ�.");
								return true;
							}
						}
						else
						{
							msg(player, "&4����: &c�˼� ���� ��ɾ��Դϴ�.");
							msg(player, "&6/mt | mteleport | myteleport");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("blacklist"))
					{
						if(args.length == 1)
						{
							PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
							teleport.setEnableBlacklist(!teleport.isEnableBlacklist());
							YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
							PlayerSection.set("Info-BlackList.Enabled", teleport.isEnableBlacklist());
							FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
							MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
							String Status = teleport.isEnableBlacklist() ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
							msg(player, "&f������Ʈ ����� " + Status + " &b���·� &6��ȯ�Ǿ����ϴ�.");
							return true;
						}
						else if(args.length == 2)
						{
							if(isBoolean(args[1]))
							{
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								if(Boolean.parseBoolean(args[1]) == teleport.isEnableBlacklist())
								{
									String Status = Boolean.parseBoolean(args[1]) ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
									msg(player, "&c�̹� ������Ʈ ����� " + Status + " �����Դϴ�.");
									return false;
								}
								teleport.setEnableBlacklist(Boolean.parseBoolean(args[1]));
								YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
								PlayerSection.set("Info-BlackList.Enabled", Boolean.parseBoolean(args[1]));
								FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
								MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
								String Status = Boolean.parseBoolean(args[1]) ? "&aȰ��ȭ" : "&c��Ȱ��ȭ";
								msg(player, "&f������Ʈ ����� " + Status + "&e�Ǿ����ϴ�.");
								return true;
							}
							else if(args[1].equalsIgnoreCase("add"))
							{
								if(args.length < 3)
								{
									msg(player, "&4����:&c �����Ͻ� �̸��� �� �� �����ϴ�!");
									msg(player, "");
									msg(player, "&f����: ");
									msg(player, "&6/mt | mteleport | myteleport blacklist add <player>...");
									msg(player, "&4blacklist&f: &b�ڽ��� ���� �ź� ���ο� ������� <playername>�� ���� �ڷ���Ʈ�� ������ �ڵ� �źε˴ϴ�.");
									msg(player, "&b<player>���� 1�� �̻� ������ �����մϴ�. ���� ��� 2���� ������ ������Ʈ�� �߰��ϰ� �ʹٸ�");
									msg(player, "&6/mt blacklist add player1 player2");
									return false;
								}
							}
							else if(args[1].equalsIgnoreCase("del"))
							{
								if(args.length < 3)
								{
									msg(player, "&4����:&c �����Ͻ� �̸��� �� �� �����ϴ�!");
									msg(player, "");
									msg(player, "&f����: ");
									msg(player, "&6/mt | mteleport | myteleport blacklist del <player>...");
									msg(player, "&4blacklist&f: &b�ڽ��� ���� �ź� ���ο� ������� <playername>�� ���� �ڷ���Ʈ�� ������ �ڵ� �źε˴ϴ�.");
									msg(player, "&b<player>���� 1�� �̻� ������ �����մϴ�. ���� ��� 2���� ������ ������Ʈ���� �����ϰ� �ʹٸ�");
									msg(player, "&6/mt blacklist del player1 player2");
									return false;
								}
							}
						}
							else if(args.length >= 3 && args[1].equalsIgnoreCase("add") && args[0].equalsIgnoreCase("blacklist"))
							{
								if(args.length >= 3)
								{
									msg(player, "&c������Ʈ�� �÷��̾ �߰��ϰ� �ֽ��ϴ�...");
									msg(player, "&c������Ʈ�� �߰��� ������ ���� ��ſ��� �ڷ���Ʈ �� �� �����ϴ�.");
									PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
									for(int count = 0; count< args.length; count++)
									{
										if(count == 0 || count == 1) continue;
										else
										{
											if(teleport.isBlacklisted(args[count]))
											{
												msg(player, args[count] + "���� �̹� ������Ʈ�� �ֽ��ϴ�.");
											}
											else
											{
												teleport.addBlacklist(args[count]);
											}
										}
									}
									YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
									PlayerSection.set("Info-BlackList.Blacklist", teleport.getBlackList());
									teleport.setWhiteList(teleport.getBlackList());
									FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
									msg(player, "&a������Ʈ�� ���� �߰��Ͽ����ϴ�.");
									if(! teleport.isEnableBlacklist())
									{
										msg(player, "&f[&4����&f] &a������Ʈ ����� Ȱ��ȭ���� �ʾҽ��ϴ�.");
										msg(player, "&e���� ����ϰ��� �Ѵٸ� �ش� ����� Ȱ��ȭ�Ͻʽÿ�.");
									}
									return true;
								}
							}
							else if(args.length >= 3 && args[1].equalsIgnoreCase("del") && args[0].equalsIgnoreCase("blacklist"))
							{
								if(args.length >= 3)
								{
									msg(player, "&6������Ʈ���� �ش� �÷��̾ �����ϰ� �ֽ��ϴ�...");
									PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
									for(int count = 0; count< args.length; count++)
									{
										if(count == 0 || count == 1) continue;
										else
										{
											if(! teleport.isBlacklisted(args[count]))
											{
												msg(player, args[count] + "���� �̹� ������Ʈ�� �����ϴ�.");
											}
											else
											{
												teleport.delBlacklist(args[count]);
											}
										}
									}
									YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
									PlayerSection.set("Info-BlackList.Blacklist", teleport.getBlackList());
									teleport.setWhiteList(teleport.getBlackList());
									FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
									msg(player, "&c������Ʈ���� �ش� �÷��̾ ���� �����Ͽ����ϴ�.");
									return true;
								}
							}
							else
							{
								msg(player, "&4����: &c�˼� ���� ��ɾ��̰ų� �����  Ȱ��ȭ �ϴ� ��������  [true|false]���� �߸���.");
								msg(player, "&6/mt | mteleport | myteleport");
								return false;
							}
					}
					else
					{
						msg(player, "&4����: &c�˼� ���� ��ɾ��Դϴ�.");
						msg(player, "&6/mt | mteleport | myteleport");
						return false;
					}
				}
			}
			else
			{
			
			}
		}
		return false;
	}
	public void Page(Player player, int Number)
	{
		if(Number == 0 || Number == 1)
		{
			msg(player, "");
			msg(player, "&eMyTeleportReloaded all commands v" + MyTeleportReloaded.plugin.getDescription().getVersion());
			msg(player, "&3MyTeleport all commands 1/2 Page(s)");
			msg(player, "&f< > : �ʼ� ���Զ�, [ ] : ���� ���Զ�");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport");
			msg(player, "&bMyteleport ���� ��ɾ��Դϴ�.");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport page [page]");
			msg(player, "&bMyteleport ���� ��ɾ� <page> �������� ���ϴ�.");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport show <info|worlds|blacklist|whitelist>");
			msg(player, "&bMyteleport�� ����Ǿ� �ִ� �÷��̾� ������ �����ݴϴ�.");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport <allow|worldallow> [true|false]");
			msg(player, "���� ���� / ��ü ���ؿ� ���� ���� ���θ� �����մϴ�.");
			msg(player, "&b������ [ ] ���� �ƹ��͵� �������� �ʴ´ٸ� ���� �ݴ�� ��ȯ�˴ϴ�.");
			msg(player, "");
			msg(player, "&a�� �������� 1 �������Դϴ�.");
			msg(player, "&6���� ������ : /mt page 2");
			return;
		}
		else if(Number == 2)
		{
			msg(player, "");
			msg(player, "&eMyTeleport-Reloaded all commands v" + MyTeleportReloaded.plugin.getDescription().getVersion());
			msg(player, "&3MyTeleport all commands 2/2 Page(s)");
			msg(player, "&f< > : �ʼ� ���Զ�, [ ] : ���� ���Զ�");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport worldallow <add|del> <name>...");
			msg(player, "&b��Ͽ� �߰��� ���忡���� ������ �ڷ���Ʈ��, ������ �ڵ����� �ź��մϴ�.");
			msg(player, "&b<name>���带 �ڵ� �ź� ��Ͽ� ���� �� �߰��ϰų� ������");
			msg(player, "&c&c�̰��� ó�� ����Ѵٸ�  /mt worldallow true�� �̿��� Ȱ��ȭ ���ֽʽÿ�!");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport <whitelist|blacklist> [true|false]");
			msg(player, "&b�ڽ��� ���� �ź� ���ο� ������� ����Ʈ�� �ִ� ������ �ڷ���Ʈ��");
			msg(player, "&b�㰡�ϰų� �ź��� �� �ֽ��ϴ�.");
			msg(player, "&c������ [ ] ���� �ƹ��͵� �������� �ʴ´ٸ� ���� �ݴ�� ��ȯ�˴ϴ�.");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport <whitelist|blacklist> <add|del> <player>...");
			msg(player, "&b<player>��(��) ȭ��Ʈ����Ʈ �Ǵ� ������Ʈ�� ���� ���� �߰��ϰų� ������");
			msg(player, "&có�� ���� /mt <whitelist|blacklist> true�� �̿��� Ȱ��ȭ �Ͻʽÿ�!");
			msg(player, "");
			msg(player, "&a�� �������� 2 �������̸鼭 ������ �������Դϴ�.");
			msg(player, "&6���� ������ : /mt page 1 �Ǵ� /mt page �Ǵ� /mt");
			return;
		}
		else if(Number > 2 || Number < 0)
		{
			msg(player, "&4����: &c��ȿ���� ���� �������Դϴ�. 1~2���������� �ֽ��ϴ�.");
			msg(player, "&c��ɾ�: /mt | mteleport | myteleport page [page]");
			return;
		}
	}
	
	public boolean isBoolean(String Str){ return Str.equalsIgnoreCase("true") || Str.equalsIgnoreCase("false") ? true : false; }
	public void msg(Player p, String str){ p.sendMessage(MyTeleportReloaded.Prefix + ChatColor.translateAlternateColorCodes('&' ,str)); }
	public void cMsg(String Str) {Bukkit.getConsoleSender().sendMessage(MyTeleportReloaded.Prefix + ChatColor.translateAlternateColorCodes('&', Str)); }
}
