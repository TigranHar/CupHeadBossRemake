package me.cuphead.ruseofanooze;

import me.cuphead.ruseofanooze.boss.Yaw;
import me.cuphead.ruseofanooze.character.PlayerShoot;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public final class RuseOfAnOoze extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerShoot(), this);
        getCommand("cuphead").setExecutor(this);

        System.out.print("[Cuphead Boss Remake] Enabled \n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.print("[Cuphead Boss Remake] Disabled \n");
    }

    int SuperJump = 0;
    int hit = 0;
    int won = 0;
    int lost = 0;
    int tick = 0;
    boolean big = false;
    int wait = 0;
    boolean done = false;
    boolean boolHit = false;

    public static BlockFace getYaw(Entity en) {
        BlockFace yaw = en.getFacing();

        return yaw;
    }

    public void setBlocks(Slime slime, Block block2, Block block3, Block block4, Block block5, Block block6, Block block7, Block block8, Block block9) {
        slime.setAI(false);

        if(!done) {
            wait++;
        }

        if(wait == 1) {
            block2.setType(Material.LIGHT_BLUE_CONCRETE);
            block3.setType(Material.RED_CONCRETE);

            Location loc = block3.getLocation();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "summon potion " + (int) loc.getX() + " " + (int)loc.getY()  + " " + (int)loc.getZ() + " {Item:{id:\"minecraft:tnt\",Count:1,tag:{CustomPotionEffects:[{Id:7,Duration:1000,Amplifier:1,ShowParticles:0b}],CustomPotionColor:15879758}}}");
        }

        if(wait == 2) {
            block2.setType(Material.AIR);
            block3.setType(Material.AIR);
            block4.setType(Material.LIGHT_BLUE_CONCRETE);
            block5.setType(Material.RED_CONCRETE);

            Location loc = block5.getLocation();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "summon potion " + (int) loc.getX() + " " + (int)loc.getY()  + " " + (int)loc.getZ() + " {Item:{id:\"minecraft:tnt\",Count:1,tag:{CustomPotionEffects:[{Id:7,Duration:1000,Amplifier:1,ShowParticles:0b}],CustomPotionColor:15879758}}}");
        }

        if(wait == 3) {
            block4.setType(Material.AIR);
            block5.setType(Material.AIR);
            block6.setType(Material.LIGHT_BLUE_CONCRETE);
            block7.setType(Material.RED_CONCRETE);

            Location loc = block7.getLocation();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "summon potion " + (int) loc.getX() + " " + (int)loc.getY()  + " " + (int)loc.getZ() + " {Item:{id:\"minecraft:tnt\",Count:1,tag:{CustomPotionEffects:[{Id:7,Duration:1000,Amplifier:1,ShowParticles:0b}],CustomPotionColor:15879758}}}");

        }

        if(wait == 4) {
            block6.setType(Material.AIR);
            block7.setType(Material.AIR);
            block8.setType(Material.LIGHT_BLUE_CONCRETE);
            block9.setType(Material.RED_CONCRETE);

            Location loc = block9.getLocation();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "summon potion " + (int) loc.getX() + " " + (int)loc.getY()  + " " + (int)loc.getZ() + " {Item:{id:\"minecraft:tnt\",Count:1,tag:{CustomPotionEffects:[{Id:7,Duration:1000,Amplifier:1,ShowParticles:0b}],CustomPotionColor:15879758}}}");

        }

        if(wait == 5) {
            block8.setType(Material.AIR);
            block9.setType(Material.AIR);

            wait = 0;
            done = true;
            boolHit = false;
            slime.setAI(true);
        }
    }

    public void logOutput(boolean log) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule logAdminCommands " + log);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule sendCommandFeedback " + log);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule commandBlockOutput " + log);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            if(cmd.getName().equalsIgnoreCase("cuphead")) {
                if(args.length > 0) {
                    if(Objects.equals(args[0], "glove") && sender instanceof Player) {
                        Player p = (Player) sender;
                        ItemStack glove = new ItemStack(Material.CARROT_ON_A_STICK);
                        ItemMeta meta = (ItemMeta) glove.getItemMeta();
                        meta.setDisplayName("Cuphead Glove");
                        glove.setItemMeta(meta);
                        p.getInventory().addItem(glove);
                    } else if(Objects.equals(args[0], "reload")) {
                        getServer().getPluginManager().disablePlugin(this);
                        getServer().getPluginManager().enablePlugin(this);
                    } else if(Objects.equals(args[0], "boss") && sender instanceof Player) {
                        big = false;
                        SuperJump = 0;
                        hit = 0;
                        won = 0;
                        lost = 0;
                        tick = 0;

                        Player p = (Player) sender;
                        org.bukkit.entity.Slime slime = (org.bukkit.entity.Slime) Bukkit.getServer().getWorld("world").spawn(p.getLocation(), org.bukkit.entity.Slime.class);
                        slime.setCustomName("Ruse of an Ooze");
                        slime.setSize(5);
                        slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
                        slime.setHealth(slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                        slime.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(200);

                        BossBar bossBar = Bukkit.createBossBar(
                                ChatColor.RED + "Ruse of an Ooze",
                                BarColor.RED,
                                BarStyle.SOLID);
                        bossBar.addPlayer(p);
                        p.playSound(p.getLocation(), Sound.MUSIC_DISC_11, 70, 1);

                        logOutput(false);
                        slime.setAI(false);

                        int taskID = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                            @Override
                            public void run() {
                                tick++;

                                if (!slime.isDead()) {
                                    bossBar.setProgress(slime.getHealth() / slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                                    SuperJump++;
                                    hit++;

                                    if(tick == 8) {
                                        slime.setAI(true);
                                        p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 70, 1);
                                    }

                                    if (SuperJump == 20) {
                                        SuperJump = 0;
                                        slime.setVelocity(slime.getLocation().getDirection().multiply(0.8).setY(0.9));
                                    }

                                    if(hit == 32) {
                                        boolHit = true;
                                    }

                                    if (boolHit) {
                                        hit = 0;
                                        BlockFace blockFace = getYaw(slime);

                                        if (blockFace == BlockFace.NORTH) {
                                            done = false;
                                            Location block1 = slime.getLocation().getBlock().getLocation();
                                            Block block2 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() - 4);
                                            Block block3 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() - 5);
                                            Block block4 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() - 6);
                                            Block block5 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() - 7);
                                            Block block6 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() - 8);
                                            Block block7 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() - 9);
                                            Block block8 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() - 10);
                                            Block block9 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() - 11);


                                            setBlocks(slime, block2, block3, block4, block5, block6, block7, block8, block9);
                                        }

                                        if (blockFace == BlockFace.SOUTH) {
                                            done = false;
                                            Location block1 = slime.getLocation().getBlock().getLocation();
                                            Block block2 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() + 4);
                                            Block block3 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() + 5);
                                            Block block4 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() + 6);
                                            Block block5 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() + 7);
                                            Block block6 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() + 8);
                                            Block block7 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() + 9);
                                            Block block8 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() + 10);
                                            Block block9 = slime.getWorld().getBlockAt((int) block1.getX(), (int) block1.getY() + 1, (int) block1.getZ() + 11);

                                            setBlocks(slime, block2, block3, block4, block5, block6, block7, block8, block9);
                                        }

                                        if (blockFace == BlockFace.EAST) {
                                            done = false;
                                            Location block1 = slime.getLocation().getBlock().getLocation();
                                            Block block2 = slime.getWorld().getBlockAt((int) block1.getX() + 4, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block3 = slime.getWorld().getBlockAt((int) block1.getX() + 5, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block4 = slime.getWorld().getBlockAt((int) block1.getX() + 6, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block5 = slime.getWorld().getBlockAt((int) block1.getX() + 7, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block6 = slime.getWorld().getBlockAt((int) block1.getX() + 8, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block7 = slime.getWorld().getBlockAt((int) block1.getX() + 9, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block8 = slime.getWorld().getBlockAt((int) block1.getX() + 10, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block9 = slime.getWorld().getBlockAt((int) block1.getX() + 11, (int) block1.getY() + 1, (int) block1.getZ());

                                            setBlocks(slime, block2, block3, block4, block5, block6, block7, block8, block9);
                                        }

                                        if (blockFace == BlockFace.WEST) {
                                            done = false;
                                            Location block1 = slime.getLocation().getBlock().getLocation();
                                            Block block2 = slime.getWorld().getBlockAt((int) block1.getX() - 4, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block3 = slime.getWorld().getBlockAt((int) block1.getX() - 5, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block4 = slime.getWorld().getBlockAt((int) block1.getX() - 6, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block5 = slime.getWorld().getBlockAt((int) block1.getX() - 7, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block6 = slime.getWorld().getBlockAt((int) block1.getX() - 8, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block7 = slime.getWorld().getBlockAt((int) block1.getX() - 9, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block8 = slime.getWorld().getBlockAt((int) block1.getX() - 10, (int) block1.getY() + 1, (int) block1.getZ());
                                            Block block9 = slime.getWorld().getBlockAt((int) block1.getX() - 11, (int) block1.getY() + 1, (int) block1.getZ());

                                            setBlocks(slime, block2, block3, block4, block5, block6, block7, block8, block9);
                                        }
                                    }

                                    if(p.isDead() && lost == 0) {
                                        lost++;
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cuphead reload");
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stopsound @a");
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title @a title {\"text\":\"YOU LOSE\", \"color\":\"red\"} ");
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:arrow]");
                                        bossBar.removePlayer(p);
                                        bossBar.setVisible(false);

                                        logOutput(true);
                                        p.playSound(p.getLocation(), Sound.MUSIC_DISC_13, 70, 1);
                                    }

                                    if(lost > 4) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=minecraft:slime] ~10000 ~10000 ~100");
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cuphead reload");
                                    }

                                    if((slime.getHealth() / slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) <= 50 && !big && tick > 16) {
                                        Location loc = slime.getLocation();
                                        big = true;
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "particle minecraft:end_rod " + (int) loc.getX() + " " + (int)loc.getY() + 1  + " " + (int)loc.getZ() + " 8 8 8 0 200");
                                        slime.setSize(10);
                                    }
                                } else {
                                    List<Player> players = bossBar.getPlayers();
                                    for (Player player : players) {
                                        bossBar.removePlayer(player);
                                    }
                                    bossBar.setVisible(false);

                                    if(slime.isDead()) {
                                        won++;
                                    }

                                    if(won == 4) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title @a title {\"text\":\"YOU WON!!!\", \"color\":\"green\"} ");
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:arrow]");
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stopsound @a");
                                        p.playSound(p.getLocation(), Sound.MUSIC_DISC_13, 70, 1);
                                    }

                                    if(won > 8) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=minecraft:slime] ~10000 ~10000 ~100");
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cuphead reload");

                                        logOutput(true);
                                    }
                                }
                            }
                        }, 0L, 5L);
                    }
                } else {
                    sender.sendMessage(ChatColor.AQUA + "[Cuphead Boss Remake] To start use " + ChatColor.YELLOW + "/cuphead glove" + ChatColor.AQUA + " or " + ChatColor.YELLOW + "/cuphead boss");
                }
            }
        return true;
    }
}
