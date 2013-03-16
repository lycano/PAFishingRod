/*
 * PAFishingRod for Bukkit
 * Copyright (C) 2013 Lycano <https://github.com/lycano/PAFishingRod/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.MinerSheeps.PAFishingRod.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.List;

public class PAFishingRodEntityListener implements Listener {

    public PAFishingRodEntityListener() {
    }

    @EventHandler( priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerFishEvent(PlayerFishEvent event) {
        PlayerFishEvent.State hookState = event.getState();

        switch (hookState) {
            case CAUGHT_ENTITY:
            case CAUGHT_FISH:
            case IN_GROUND:
                Player player = event.getPlayer();
                List<Entity> nearbyEntities = player.getNearbyEntities(50, 50, 50); // change this if you want to limit the teleport range (1.4.7-R1.0, a rod will fly 16 blocks)

                for (Entity entity: nearbyEntities) {
                    // get only FISHING_HOOK entities
                    if (entity.getType().equals(EntityType.FISHING_HOOK)) {
                        // get the shooter and cast to player
                        Player shooter = (Player) ((Projectile) entity).getShooter();

                        // was it launched by the shooter?
                        if (shooter.getName().equals(player.getName())) {
                            // create location object as teleport to entity will use the entity yaw and pitch
                            Location locPlayer = player.getLocation();
                            Location targetLoc = entity.getLocation();
                            targetLoc.setYaw(locPlayer.getYaw());
                            targetLoc.setPitch(locPlayer.getPitch());

                            // teleport to location with correct yaw and pitch, if this fails send message to player
                            if (!player.teleport(targetLoc)) {
                                player.sendMessage("Could not teleport to target location");
                            }

                            return;
                        }
                    }
                }
                break;
        }
    }
}