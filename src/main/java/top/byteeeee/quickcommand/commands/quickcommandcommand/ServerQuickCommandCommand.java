/*
 * This file is part of the QuickCommand project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2025 1024_byteeeee and contributors
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

import net.minecraft.server.command.ServerCommandSource;

import top.byteeeee.quickcommand.helpers.QuickCommandCommandHelper;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ServerQuickCommandCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("serverQuickCommand")
            .executes(context -> QuickCommandCommandHelper.showListWithRun(context.getSource().getPlayer()))
            // add
            .then(literal("add")
            .then(argument("name", StringArgumentType.string())
            .then(argument("command", StringArgumentType.string())
            .executes(context -> QuickCommandCommandHelper.add(
                context.getSource().getPlayer(),
                StringArgumentType.getString(context, "name"),
                StringArgumentType.getString(context, "command")
            )))))

            // remove
            .then(literal("remove")
            .then(argument("name", StringArgumentType.string())
            .executes(context -> QuickCommandCommandHelper.remove(
                context.getSource().getPlayer(),
                StringArgumentType.getString(context, "name")
            ))))

            // removeAll
            .then(literal("removeAll")
            .executes(context -> QuickCommandCommandHelper.initiateRemoveAll(context.getSource().getPlayer()))
            .then(literal("confirm")
            .executes(context -> QuickCommandCommandHelper.confirmRemoveAll(context.getSource().getPlayer()))))

            // displayCommandInList
            .then(literal("displayCommandInList")
            .then(argument("value", BoolArgumentType.bool())
            .executes(context -> QuickCommandCommandHelper.setDisplayCommandInList(
                context.getSource().getPlayer(),
                BoolArgumentType.getBool(context, "value")
            ))))

            // listWithRun
            .then(literal("listWithRun")
            .executes(context -> QuickCommandCommandHelper.showListWithRun(context.getSource().getPlayer())))

            // swap
            .then(literal("swap")
            .then(argument("index1", IntegerArgumentType.integer(1))
            .then(argument("index2", IntegerArgumentType.integer(1))
            .executes(context -> QuickCommandCommandHelper.swap(
                context.getSource().getPlayer(),
                IntegerArgumentType.getInteger(context, "index1"),
                IntegerArgumentType.getInteger(context, "index2")
            )))))

            // help
            .then(literal("help")
            .executes(context -> QuickCommandCommandHelper.help(context.getSource().getPlayer())))

            // language
            .then(literal("language")
            .then(argument("language", StringArgumentType.string())
            .suggests(QuickCommandCommandHelper.SERVER_LANGUAGE_SUGGESTION)
            .executes(context -> QuickCommandCommandHelper.setLanguage(
                context.getSource().getPlayer(),
                StringArgumentType.getString(context, "language")
            ))))
        );
    }
}
