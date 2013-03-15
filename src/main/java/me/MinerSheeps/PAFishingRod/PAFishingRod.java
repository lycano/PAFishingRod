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
package me.MinerSheeps.PAFishingRod;

import me.MinerSheeps.PAFishingRod.commands.PAFishingRodCommand;
import me.MinerSheeps.PAFishingRod.listeners.PAFishingRodEntityListener;
import me.MinerSheeps.PAFishingRod.utils.PAFishingRodLogger;
import org.bukkit.plugin.java.JavaPlugin;

public class PAFishingRod extends JavaPlugin {

    public void onLoad() {
        PAFishingRodLogger.initLogger();
    }

    public void onDisable() {
        // disable stuff here if needed
        PAFishingRodLogger.info(String.format("v%s Disabled!", getDescription().getVersion()));
    }

    public void onEnable() {
        PAFishingRodLogger.info(String.format("Enabling plugin v%s ..", this.getVersion()));

        // Initialize listeners
        this.getServer().getPluginManager().registerEvents(new PAFishingRodEntityListener(), this);

        // Register commands
        this.getCommand("pversion").setExecutor(new PAFishingRodCommand(this.getVersion()));

        PAFishingRodLogger.info(String.format("v%s Enabled!", this.getVersion()));
    }

    public String getVersion() {
        return this.getDescription().getVersion();
    }
}