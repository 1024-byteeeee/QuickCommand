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

package top.byteeeee.quickcommand.commands.quickcommandcommand;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

import top.byteeeee.quickcommand.helpers.CommandHelper;

public class QuickCommandCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
            ClientCommandManager.literal("quickCommand")
            .executes(context -> CommandHelper.showListWithRun(context.getSource().getPlayer()))

            // add
            .then(ClientCommandManager.literal("add")
            .then(ClientCommandManager.argument("name", StringArgumentType.string())
            .then(ClientCommandManager.argument("command", StringArgumentType.string())
            .executes(context -> CommandHelper.add(
                    context.getSource().getPlayer(),
                StringArgumentType.getString(context, "name"),
                StringArgumentType.getString(context, "command")
            )))))

            // remove
            .then(ClientCommandManager.literal("remove")
            .then(ClientCommandManager.argument("name", StringArgumentType.string())
            .executes(context -> CommandHelper.remove(
                    context.getSource().getPlayer(),
                StringArgumentType.getString(context, "name")
            ))))

            // removeAll
            .then(ClientCommandManager.literal("removeAll")
            .executes(context -> CommandHelper.initiateRemoveAll(context.getSource().getPlayer()))
            .then(ClientCommandManager.literal("confirm")
            .executes(context -> CommandHelper.confirmRemoveAll(context.getSource().getPlayer()))))

            // displayCommandInList
            .then(ClientCommandManager.literal("displayCommandInList")
            .then(ClientCommandManager.argument("value", BoolArgumentType.bool())
            .executes(context -> CommandHelper.setDisplayCommandInList(
                    context.getSource().getPlayer(),
                BoolArgumentType.getBool(context, "value")
            ))))

            // listWithRun
            .then(ClientCommandManager.literal("listWithRun")
            .executes(context -> CommandHelper.showListWithRun(context.getSource().getPlayer())))

            // swap
            .then(ClientCommandManager.literal("swap")
            .then(ClientCommandManager.argument("index1", IntegerArgumentType.integer(1))
            .then(ClientCommandManager.argument("index2", IntegerArgumentType.integer(1))
            .executes(context -> CommandHelper.swap(
                context.getSource().getPlayer(),
                IntegerArgumentType.getInteger(context, "index1"),
                IntegerArgumentType.getInteger(context, "index2")
            )))))

            // help
            .then(ClientCommandManager.literal("help")
            .executes(context -> CommandHelper.help(context.getSource().getPlayer())))
        );
    }
}
