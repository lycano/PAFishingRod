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
package me.MinerSheeps.PAFishingRod.commands;

import me.MinerSheeps.PAFishingRod.utils.PAFishingRodLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /hookit command
 * @author lycano
 */
public class PAFishingRodCommand implements CommandExecutor {
    String version = "";

    public PAFishingRodCommand(String pluginVersion) {
        this.version = pluginVersion;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        sender.sendMessage(String.format("You are currently using %s v%s", PAFishingRodLogger.getLoggerName(), version));
        return true;
    }
}