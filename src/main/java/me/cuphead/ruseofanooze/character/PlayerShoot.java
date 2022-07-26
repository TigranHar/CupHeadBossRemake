package me.cuphead.ruseofanooze.character;

import org.bukkit.*;
import org.bukkit.block.data.type.Snow;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import java.util.Locale;

import static org.bukkit.Bukkit.getServer;

public class PlayerShoot implements Listener {

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {

        Player p = event.getPlayer();
        Action action = event.getAction();

        if((action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Cuphead Glove")) {
            Vector playerDirection = p.getLocation().getDirection();
            Arrow arrow = p.launchProjectile(Arrow.class, playerDirection);
            arrow.setCustomName("bullet");
            arrow.setGravity(false);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT, 100, 1);
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e)
    {
        Projectile p = e.getEntity();

        if(p instanceof Arrow) {
            p.remove();
        } else if(p instanceof Snowball) {
            Location loc = p.getLocation();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "summon potion " + (int) loc.getX() + " " + (int)loc.getY()  + " " + (int)loc.getZ() + " {Item:{id:\"minecraft:tnt\",Count:1,tag:{CustomPotionEffects:[{Id:7,Duration:1000,Amplifier:1,ShowParticles:0b}],CustomPotionColor:15879758}}}");
        }
    }

    @EventHandler
    public void onHitEnt(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Snowball) {
            e.setDamage(4);
            e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.STEP_SOUND, 80, 1);
        }
    }
}
