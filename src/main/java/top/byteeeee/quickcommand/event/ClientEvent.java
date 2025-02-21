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

package top.byteeeee.quickcommand.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.client.gui.screen.ChatScreen;

import top.byteeeee.quickcommand.config.QuickCommandConfig;
import top.byteeeee.quickcommand.helpers.QuickCommandCommandHelper;
import top.byteeeee.quickcommand.key.RegisterKeyBinding;


public class ClientEvent {
    public static void register() {
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> QuickCommandConfig.loadFromJson());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (RegisterKeyBinding.openQuickCommandList.wasPressed()) {
                if (client.player != null) {
                    ChatScreen chatScreen = new ChatScreen("");
                    client.openScreen(chatScreen);
                    QuickCommandCommandHelper.showListWithRun(client.player);
                }
            }
        });
    }
}
