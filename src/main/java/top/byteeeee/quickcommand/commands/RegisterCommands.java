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

package top.byteeeee.quickcommand.commands;

//#if MC<11900
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
//#else
//$$ import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
//$$ import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
//#endif

import top.byteeeee.quickcommand.commands.quickcommandcommand.QuickCommandCommand;
import top.byteeeee.quickcommand.commands.quickcommandcommand.ServerQuickCommandCommand;

public class RegisterCommands {
    public static void registerClientCommands() {
        //#if MC<11900
        QuickCommandCommand.register(ClientCommandManager.DISPATCHER);
        //#else
        //$$ ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> QuickCommandCommand.register(dispatcher)));
        //#endif
    }

    public static void registerServerCommands() {
        //#if MC<11900
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> ServerQuickCommandCommand.register(dispatcher));
        //#else
        //$$ CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> ServerQuickCommandCommand.register(dispatcher));
        //#endif
    }
}
