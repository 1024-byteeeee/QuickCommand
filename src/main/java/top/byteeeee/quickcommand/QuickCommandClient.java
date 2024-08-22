/*
 * This file is part of the QuickCommand project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2024 1024_byteeeee and contributors
 *
 * QuickCommand is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QuickCommand is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with QuickCommand. If not, see <https://www.gnu.org/licenses/>.
 */

package top.byteeeee.quickcommand;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import top.byteeeee.quickcommand.commands.RegisterCommands;
import top.byteeeee.quickcommand.event.ClientEvent;
import top.byteeeee.quickcommand.key.RegisterKeyBinding;

@Environment(EnvType.CLIENT)
public class QuickCommandClient implements ClientModInitializer {
    public static final String modName = "QuickCommand";
    public static final String modVersion = "1.0.2";
    public static final Logger LOGGER = LogManager.getLogger(modName);
    public static MinecraftClient minecraftClient;

    @Override
    public void onInitializeClient() {
        LOGGER.info(String.format("%s v%s loaded!", modName, modVersion));
        minecraftClient = MinecraftClient.getInstance();
        RegisterKeyBinding.Register();
        RegisterCommands.register();
        ClientEvent.register();
    }
}
