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
											msg(player, "&e월드에 따른 텔포 자동 거부가 자동 비활성화되었습니다.");
										}
									}
									PlayerSection.set("Block-Always-Teleport", teleport.isBlockAlwaysTeleport());

									FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
									MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
									String Status = teleport.isBlockAlwaysTeleport() ? "&a활성화" : "&c비활성화";
									msg(player, "&a월드에 상관없는 텔포 자동 거부가 " + Status + "&b 상태로 &6전환되었습니다.");
									if(! teleport.isBlockAlwaysTeleport() && ! teleport.isBlockTeleportinWorld())
									{
										msg(player, "&f[&e경고&f] &4거부 기능이 모두 비활성화된 상태입니다!");
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
											String Status = Boolean.parseBoolean(args[1]) ? "&a활성화" : "&c비활성화";
											msg(player, "&c월드에 상관없는 텔포 거부 기능이 이미 " + Status + " 상태입니다.");
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
												msg(player, "&e월드에 따른 텔포 자동 거부가 자동 비활성화되었습니다.");
											}
										}
										FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
										MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
										String Status = Boolean.parseBoolean(args[1]) ? "&a활성화" : "&c비활성화";
										msg(player, "&b월드에 상관없는 텔포 자동 거부가  " + Status + "&a되었습니다.");
										if(! teleport.isBlockAlwaysTeleport() && ! teleport.isBlockTeleportinWorld())
										{
											msg(player, "&f[&e경고&f] &4거부 기능이 모두 비활성화된 상태입니다!");
										}
										return true;
									}
									else
									{
										msg(player, "&4오류:&c [true|false] 값이 잘못된 값입니다.");
										msg(player, "");
										msg(player, "&f사용법: ");
										msg(player, "&6/mt | mteleport | myteleport allow [true|false]");
										msg(player, "&b다른 플레이어가 자신에게 텔레포트를 하려고 할 때, 월드 여부에 상관 없이 누군가 텔포를 할 때");
										msg(player, "자동으로 거부할지 여부를 설정합니다.");
										msg(player, "&c[ ] 란에 아무것도 기입하지 않는다면 값이 반대로 전환됩니다.");
										msg(player, "&c이것의 값을 true로 하면  myteleport worldallow 기능이 자동으로 비활성화");
										msg(player, "되니 주의하십시오!");
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
										msg(player, "&e월드에 상관 없는 텔포 자동 거부가 자동 비활성화되었습니다.");
									}
								}
								PlayerSection.set("Info-World.Block-Enabled", teleport.isBlockTeleportinWorld());

								FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
								MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
								String Status = teleport.isBlockTeleportinWorld() ? "&a활성화" : "&c비활성화";
								msg(player, "&a월드에 따른 텔포 자동 거부가 " + Status + " &b상태로 &6전환되었습니다.");
								if(! teleport.isBlockAlwaysTeleport() && ! teleport.isBlockTeleportinWorld())
								{
									msg(player, "&f[&e경고&f] &4거부 기능이 모두 비활성화된 상태입니다!");
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
										String Status = Boolean.parseBoolean(args[1]) ? "&a활성화" : "&c비활성화";
										msg(player, "&c월드에 따른 텔포 거부 기능이 이미 " + Status + " 상태입니다.");
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
											msg(player, "&e월드에 상관 없는 텔포 자동 거부가 자동 비활성화되었습니다.");
										}
									}
									FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
									MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
									String Status = Boolean.parseBoolean(args[1]) ? "&a활성화" : "&c비활성화";
									msg(player, "&b월드에 따른 텔포 자동 거부가  " + Status + "&a되었습니다.");
									if(! teleport.isBlockAlwaysTeleport() && ! teleport.isBlockTeleportinWorld())
									{
										msg(player, "&f[&e경고&f] &4거부 기능이 모두 비활성화된 상태입니다!");
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
												msg(player, "&4오류:&c 기입하신 월드가 한 개도 없습니다.");
												msg(player, "");
												msg(player, "&f사용법: ");
												msg(player, "&6/mt | mteleport | myteleport worldallow add <world>...");
												msg(player, "&b목록에 추가된 월드에서는 누군가 텔레포트시, 텔포를 자동으로 거부합니다.");
												msg(player, "&b기입한 월드를 자동 거부 목록에 여러 개 추가합니다.");
												msg(player, "&e예를 들어 CA_001, CA_002라는 월드를 목록에 추가하고자 한다면");
												msg(player, "&6/mt worldallow add CA_001 CA_002");
												return false;
											}
										}
										else if(args[1].equalsIgnoreCase("del") && args.length < 3)
										{
											if(args.length < 3)
											{
												msg(player, "&4오류:&c 기입하신 월드가 한 개도 없습니다.");
												msg(player, "");
												msg(player, "&f사용법: ");
												msg(player, "&6/mt | mteleport | myteleport worldallow del <world>...");
												msg(player, "&b목록에 추가된 월드에서는 누군가 텔레포트시, 텔포를 자동으로 거부합니다.");
												msg(player, "&b기입한 월드를 자동 거부 목록에서 삭제합니다.");
												msg(player, "&예를 들어 CA_001, CA_002라는 월드를 목록에서 삭제하고자 한다면");
												msg(player, "&6/mt worldallow del CA_001 CA_002");
												return false;
											}
										}
										else
										{
											msg(player, "&4오류:&c [true|false] 값이 잘못된 값입니다.");
											msg(player, "");
											msg(player, "&f사용법: ");
											msg(player, "&6/mt | mteleport | myteleport worldallow [true|false]");
											msg(player, "&aworldallow&f:&b 자신이 정한 특정 월드에 있고, 다른 사람이 텔레포트하려고 하면");
											msg(player, "&b자동으로 거부할지 여부를 설정합니다.");
											msg(player, "&c[ ] 란에 아무것도 기입하지 않는다면 값이 반대로 전환됩니다.");
											msg(player, "&c이것의 값을 true로 하면  myteleport allow 기능이 자동으로 비활성화");
											msg(player, "되니 주의하십시오!");
											return false;
										}
									}
									else if(args[1].equalsIgnoreCase("add") && args.length >= 3)
									{
										msg(player, "&6월드를 목록에 추가하고 있습니다...");
										PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
										for(int count = 0; count< args.length; count++)
										{
											if(count == 0 || count == 1) continue;
											else
											{
												if(teleport.isWorldAdded(args[count]))
												{
													msg(player, args[count] + "월드는 이미 목록에 있습니다!");
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
														msg(player, "&4오류:&f " + args[count] + "라는 월드는 이 서버에 없습니다.");
													}
												}
											}
										}
										YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
										PlayerSection.set("Info-World.Worlds", teleport.getWorldList());
										teleport.setWorldList(teleport.getWorldList());
										FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
										msg(player, "&a해당 월드를 추가하였습니다.");
										if(! teleport.isBlockTeleportinWorld())
										{
											msg(player, "&f[&4유의&f] &a월드에 따른 텔포 거부 기능이 활성화되지 않았습니다.");
											msg(player, "&e정상 사용하고자 한다면 해당 기능을 활성화하십시오.");
										}
									}
									else if(args[1].equalsIgnoreCase("del") && args.length >= 3)
									{
										msg(player, "&6월드를 목록에서 제거하고 있습니다...");
										PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
										for(int count = 0; count< args.length; count++)
										{
											if(count == 0 || count == 1) continue;
											else
											{
												if(! teleport.isWorldAdded(args[count]))
												{
													msg(player, args[count] + "월드는 목록에 없습니다!");
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
										msg(player, "&a해당 월드를 목록에서 삭제하였습니다.");
									}
									else
									{
										msg(player, "&4오류: &c알수 없는 명령어이거나 기능을  활성화 하는 과정에서  [true|false]값이 잘못됨.");
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
							String Status = teleport.isEnableWhitelist() ? "&a활성화" : "&c비활성화";
							msg(player, "&f화이트리스트 기능이 " + Status + " &b상태로 &6전환되었습니다.");
							return true;
						}
						else if(args.length == 2)
						{
							if(isBoolean(args[1]))
							{
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								if(Boolean.parseBoolean(args[1]) == teleport.isEnableWhitelist())
								{
									String Status = Boolean.parseBoolean(args[1]) ? "&a활성화" : "&c비활성화";
									msg(player, "&c이미 화이트리스트 기능이 " + Status + " 상태입니다.");
									return false;
								}
								teleport.setEnableWhitelist(Boolean.parseBoolean(args[1]));
								YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
								PlayerSection.set("Info-WhiteList.Enabled", Boolean.parseBoolean(args[1]));
								FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
								MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
								String Status = Boolean.parseBoolean(args[1]) ? "&a활성화" : "&c비활성화";
								msg(player, "&f화이트리스트 기능이 " + Status + "&e되었습니다.");
								return true;
							}
							else if(args[1].equalsIgnoreCase("add"))
							{
								if(args.length < 3)
								{
									msg(player, "&4오류:&c 기입하신 이름이 한 명도 없습니다!");
									msg(player, "");
									msg(player, "&f사용법: ");
									msg(player, "&6/mt | mteleport | myteleport whltelist add <player>...");
									msg(player, "&awhitelist&f: &b자신의 텔포 거부 여부에 상관없이 <playername>의 텔레포트를 무조건 허가합니다.");
									msg(player, "&b<player>란은 1개 이상 기입이 가능합니다. 예를 들어 2명의 유저를 화이트리스트에 추가하고 싶다면");
									msg(player, "&6/mt whitelist add player1 player2");
									return false;
								}
							}
							else if(args[1].equalsIgnoreCase("del"))
							{
								if(args.length < 3)
								{
									msg(player, "&4오류:&c 기입하신 이름이 한 명도 없습니다!");
									msg(player, "");
									msg(player, "&f사용법: ");
									msg(player, "&6/mt | mteleport | myteleport whltelist del <player>...");
									msg(player, "&awhitelist&f: &b자신의 텔포 거부 여부에 상관없이 <playername>의 텔레포트를 무조건 허가합니다.");
									msg(player, "&b<player>란은 1개 이상 기입이 가능합니다. 예를 들어 2명의 유저를 화이트리스트에서 삭제하고 싶다면");
									msg(player, "&6/mt whitelist del player1 player2");
									return false;
								}
							}
							else
							{
								msg(player, "&4오류: &c알수 없는 명령어이거나 기능을  활성화 하는 과정에서  [true|false]값이 잘못됨.");
								msg(player, "&6/mt | mteleport | myteleport");
								return false;
							}
						}
						else if(args.length >= 3 && args[1].equalsIgnoreCase("add") && args[0].equalsIgnoreCase("whitelist"))
						{
							if(args.length >= 3)
							{
								msg(player, "&6화이트리스트에 플레이어를 추가하고 있습니다...");
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								for(int count = 0; count< args.length; count++)
								{
									if(count == 0 || count == 1) continue;
									else
									{
										if(teleport.isWhitelisted(args[count]))
										{
											msg(player, args[count] + "님은 이미 화이트리스트에 있습니다.");
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
								msg(player, "&a화이트리스트에 정상 추가하였습니다.");
								if(! teleport.isEnableWhitelist())
								{
									msg(player, "&f[&4유의&f] &a화이트리스트 기능이 활성화되지 않았습니다.");
									msg(player, "&e정상 사용하고자 한다면 해당 기능을 활성화하십시오.");
								}
								return true;
							}
						}
						else if(args.length >= 3 && args[1].equalsIgnoreCase("del") && args[0].equalsIgnoreCase("whitelist"))
						{
							if(args.length >= 3)
							{
								msg(player, "&6화이트리스트에서 해당 플레이어를 삭제하고 있습니다...");
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								for(int count = 0; count< args.length; count++)
								{
									if(count == 0 || count == 1) continue;
									else
									{
										if(! teleport.isWhitelisted(args[count]))
										{
											msg(player, args[count] + "님은 이미 화이트리스트에 없습니다.");
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
								msg(player, "&a화이트리스트에서 해당 플레이어를 정상 제거하였습니다.");
								return true;
							}
						}
						else
						{
							msg(player, "&4오류: &c알수 없는 명령어입니다.");
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
							String Status = teleport.isEnableBlacklist() ? "&a활성화" : "&c비활성화";
							msg(player, "&f블랙리스트 기능이 " + Status + " &b상태로 &6전환되었습니다.");
							return true;
						}
						else if(args.length == 2)
						{
							if(isBoolean(args[1]))
							{
								PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
								if(Boolean.parseBoolean(args[1]) == teleport.isEnableBlacklist())
								{
									String Status = Boolean.parseBoolean(args[1]) ? "&a활성화" : "&c비활성화";
									msg(player, "&c이미 블랙리스트 기능이 " + Status + " 상태입니다.");
									return false;
								}
								teleport.setEnableBlacklist(Boolean.parseBoolean(args[1]));
								YamlConfiguration PlayerSection = FileManager.LoadSourceFile("Players/" + player.getName());
								PlayerSection.set("Info-BlackList.Enabled", Boolean.parseBoolean(args[1]));
								FileManager.SaveFile("Players/" + player.getName(), PlayerSection);
								MyTeleportReloaded.PlayerInformation.put(player.getName(), teleport);
								String Status = Boolean.parseBoolean(args[1]) ? "&a활성화" : "&c비활성화";
								msg(player, "&f블랙리스트 기능이 " + Status + "&e되었습니다.");
								return true;
							}
							else if(args[1].equalsIgnoreCase("add"))
							{
								if(args.length < 3)
								{
									msg(player, "&4오류:&c 기입하신 이름이 한 명도 없습니다!");
									msg(player, "");
									msg(player, "&f사용법: ");
									msg(player, "&6/mt | mteleport | myteleport blacklist add <player>...");
									msg(player, "&4blacklist&f: &b자신의 텔포 거부 여부에 상관없이 <playername>에 관한 텔레포트는 무조건 자동 거부됩니다.");
									msg(player, "&b<player>란은 1개 이상 기입이 가능합니다. 예를 들어 2명의 유저를 블랙리스트에 추가하고 싶다면");
									msg(player, "&6/mt blacklist add player1 player2");
									return false;
								}
							}
							else if(args[1].equalsIgnoreCase("del"))
							{
								if(args.length < 3)
								{
									msg(player, "&4오류:&c 기입하신 이름이 한 명도 없습니다!");
									msg(player, "");
									msg(player, "&f사용법: ");
									msg(player, "&6/mt | mteleport | myteleport blacklist del <player>...");
									msg(player, "&4blacklist&f: &b자신의 텔포 거부 여부에 상관없이 <playername>에 관한 텔레포트는 무조건 자동 거부됩니다.");
									msg(player, "&b<player>란은 1개 이상 기입이 가능합니다. 예를 들어 2명의 유저를 블랙리스트에서 삭제하고 싶다면");
									msg(player, "&6/mt blacklist del player1 player2");
									return false;
								}
							}
						}
							else if(args.length >= 3 && args[1].equalsIgnoreCase("add") && args[0].equalsIgnoreCase("blacklist"))
							{
								if(args.length >= 3)
								{
									msg(player, "&c블랙리스트에 플레이어를 추가하고 있습니다...");
									msg(player, "&c블랙리스트에 추가된 유저는 이제 당신에게 텔레포트 할 수 없습니다.");
									PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
									for(int count = 0; count< args.length; count++)
									{
										if(count == 0 || count == 1) continue;
										else
										{
											if(teleport.isBlacklisted(args[count]))
											{
												msg(player, args[count] + "님은 이미 블랙리스트에 있습니다.");
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
									msg(player, "&a블랙리스트에 정상 추가하였습니다.");
									if(! teleport.isEnableBlacklist())
									{
										msg(player, "&f[&4유의&f] &a블랙리스트 기능이 활성화되지 않았습니다.");
										msg(player, "&e정상 사용하고자 한다면 해당 기능을 활성화하십시오.");
									}
									return true;
								}
							}
							else if(args.length >= 3 && args[1].equalsIgnoreCase("del") && args[0].equalsIgnoreCase("blacklist"))
							{
								if(args.length >= 3)
								{
									msg(player, "&6블랙리스트에서 해당 플레이어를 삭제하고 있습니다...");
									PlayerTeleport teleport = MyTeleportReloaded.PlayerInformation.get(player.getName());
									for(int count = 0; count< args.length; count++)
									{
										if(count == 0 || count == 1) continue;
										else
										{
											if(! teleport.isBlacklisted(args[count]))
											{
												msg(player, args[count] + "님은 이미 블랙리스트에 없습니다.");
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
									msg(player, "&c블랙리스트에서 해당 플레이어를 정상 제거하였습니다.");
									return true;
								}
							}
							else
							{
								msg(player, "&4오류: &c알수 없는 명령어이거나 기능을  활성화 하는 과정에서  [true|false]값이 잘못됨.");
								msg(player, "&6/mt | mteleport | myteleport");
								return false;
							}
					}
					else
					{
						msg(player, "&4오류: &c알수 없는 명령어입니다.");
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
			msg(player, "&f< > : 필수 기입란, [ ] : 선택 기입란");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport");
			msg(player, "&bMyteleport 메인 명령어입니다.");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport page [page]");
			msg(player, "&bMyteleport 메인 명령어 <page> 페이지를 봅니다.");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport show <info|worlds|blacklist|whitelist>");
			msg(player, "&bMyteleport에 저장되어 있는 플레이어 정보를 보여줍니다.");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport <allow|worldallow> [true|false]");
			msg(player, "월드 기준 / 전체 기준에 따라 텔포 여부를 결정합니다.");
			msg(player, "&b마지막 [ ] 란에 아무것도 기입하지 않는다면 값이 반대로 전환됩니다.");
			msg(player, "");
			msg(player, "&a이 페이지는 1 페이지입니다.");
			msg(player, "&6다음 페이지 : /mt page 2");
			return;
		}
		else if(Number == 2)
		{
			msg(player, "");
			msg(player, "&eMyTeleport-Reloaded all commands v" + MyTeleportReloaded.plugin.getDescription().getVersion());
			msg(player, "&3MyTeleport all commands 2/2 Page(s)");
			msg(player, "&f< > : 필수 기입란, [ ] : 선택 기입란");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport worldallow <add|del> <name>...");
			msg(player, "&b목록에 추가된 월드에서는 누군가 텔레포트시, 텔포를 자동으로 거부합니다.");
			msg(player, "&b<name>월드를 자동 거부 목록에 여러 개 추가하거나 삭제함");
			msg(player, "&c&c이것을 처음 사용한다면  /mt worldallow true을 이용해 활성화 해주십시오!");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport <whitelist|blacklist> [true|false]");
			msg(player, "&b자신의 텔포 거부 여부에 상관없이 리스트에 있는 유저는 텔레포트를");
			msg(player, "&b허가하거나 거부할 수 있습니다.");
			msg(player, "&c마지막 [ ] 란에 아무것도 기입하지 않는다면 값이 반대로 전환됩니다.");
			msg(player, "");
			msg(player, "&6/mt | mteleport | myteleport <whitelist|blacklist> <add|del> <player>...");
			msg(player, "&b<player>를(을) 화이트리스트 또는 블랙리스트에 여려 명을 추가하거나 삭제함");
			msg(player, "&c처음 사용시 /mt <whitelist|blacklist> true을 이용해 활성화 하십시오!");
			msg(player, "");
			msg(player, "&a이 페이지는 2 페이지이면서 마지막 페이지입니다.");
			msg(player, "&6이전 페이지 : /mt page 1 또는 /mt page 또는 /mt");
			return;
		}
		else if(Number > 2 || Number < 0)
		{
			msg(player, "&4오류: &c유효하지 않은 페이지입니다. 1~2페이지까지 있습니다.");
			msg(player, "&c명령어: /mt | mteleport | myteleport page [page]");
			return;
		}
	}
	
	public boolean isBoolean(String Str){ return Str.equalsIgnoreCase("true") || Str.equalsIgnoreCase("false") ? true : false; }
	public void msg(Player p, String str){ p.sendMessage(MyTeleportReloaded.Prefix + ChatColor.translateAlternateColorCodes('&' ,str)); }
	public void cMsg(String Str) {Bukkit.getConsoleSender().sendMessage(MyTeleportReloaded.Prefix + ChatColor.translateAlternateColorCodes('&', Str)); }
}
