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

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;

import top.byteeeee.quickcommand.QuickCommandClient;
import top.byteeeee.quickcommand.config.QuickCommandConfig;
import top.byteeeee.quickcommand.helpers.QuickCommandButton;
import top.byteeeee.quickcommand.translations.TranslationText;
import top.byteeeee.quickcommand.utils.Messenger;

import java.util.*;

public class QuickCommandCommand {
    public static final Map<String, String> QUICK_COMMAND_MAP = new LinkedHashMap<>();
    private static final String MSG_HEAD = "§b<QuickCommand>§r ";
    private static boolean awaitingConfirmation = false;
    public static boolean displayCommandInList = false;

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
            ClientCommandManager.literal("quickCommand")
            .executes(context -> showListWithRun(context.getSource().getPlayer()))

            // add
            .then(ClientCommandManager.literal("add")
            .then(ClientCommandManager.argument("name", StringArgumentType.string())
            .then(ClientCommandManager.argument("command", StringArgumentType.string())
            .executes(context -> add(
                context.getSource().getPlayer(),
                StringArgumentType.getString(context, "name"),
                StringArgumentType.getString(context, "command")
            )))))

            // remove
            .then(ClientCommandManager.literal("remove")
            .then(ClientCommandManager.argument("name", StringArgumentType.string())
            .executes(context -> remove(
                context.getSource().getPlayer(),
                StringArgumentType.getString(context, "name")
            ))))

            // removeAll
            .then(ClientCommandManager.literal("removeAll")
            .executes(context -> initiateRemoveAll(context.getSource().getPlayer()))
            .then(ClientCommandManager.literal("confirm")
            .executes(context -> confirmRemoveAll(context.getSource().getPlayer()))))

            // displayCommandInList
            .then(ClientCommandManager.literal("displayCommandInList")
            .then(ClientCommandManager.argument("value", BoolArgumentType.bool())
            .executes(context -> setDisplayCommandInList(
                context.getSource().getPlayer(),
                BoolArgumentType.getBool(context, "value")
            ))))

            // listWithRun
            .then(ClientCommandManager.literal("listWithRun")
            .executes(context -> showListWithRun(context.getSource().getPlayer())))

            // swap
            .then(ClientCommandManager.literal("swap")
            .then(ClientCommandManager.argument("index1", IntegerArgumentType.integer(1))
            .then(ClientCommandManager.argument("index2", IntegerArgumentType.integer(1))
            .executes(context -> swap(
                context.getSource().getPlayer(),
                IntegerArgumentType.getInteger(context, "index1"),
                IntegerArgumentType.getInteger(context, "index2")
            )))))

            // help
            .then(ClientCommandManager.literal("help")
            .executes(context -> help(context.getSource().getPlayer())))
        );
    }

    private static int add(PlayerEntity player, String name, String command) {
        if (!QUICK_COMMAND_MAP.containsKey(name)) {
            QUICK_COMMAND_MAP.put(name, command);
            saveToJson();
            showListWithRun(player);
            player.sendMessage(Messenger.s(MSG_HEAD).append(String.format("%s [ %s ] - %s", TranslationText.add, name, command)).formatted(Formatting.GREEN), false);
        } else {
            player.sendMessage(Messenger.s(MSG_HEAD).append(name + " " + TranslationText.alreadyExist).formatted(Formatting.RED), false);
        }
        return 1;
    }

    private static int remove(PlayerEntity player, String name) {
        QUICK_COMMAND_MAP.remove(name);
        saveToJson();
        showListWithRun(player);
        player.sendMessage(Messenger.s(MSG_HEAD).append(TranslationText.remove + "[ " + name + " ] ").formatted(Formatting.RED), false);
        return 1;
    }

    private static int initiateRemoveAll(PlayerEntity player) {
        if (!awaitingConfirmation) {
            awaitingConfirmation = true;
            player.sendMessage(Messenger.s(MSG_HEAD).append(TranslationText.initiateRemoveAll_first).formatted(Formatting.RED), false);
            return 1;
        } else {
            player.sendMessage(Messenger.s(MSG_HEAD).append(TranslationText.initiateRemoveAll_after).formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static int confirmRemoveAll(PlayerEntity player) {
        if (awaitingConfirmation) {
            awaitingConfirmation = false;
            QUICK_COMMAND_MAP.clear();
            saveToJson();
            player.sendMessage(Messenger.s(MSG_HEAD).append(TranslationText.removeAll).formatted(Formatting.RED), false);
            return 1;
        } else {
            player.sendMessage(Messenger.s(MSG_HEAD).append(TranslationText.confirmRemoveAll_warn).formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static int setDisplayCommandInList(PlayerEntity player, boolean value) {
        displayCommandInList = value;
        saveToJson();
        showListWithRun(player);
        return 1;
    }

    public static int showListWithRun(PlayerEntity player) {
        player.sendMessage(Messenger.endl(), false);
        // ---------- QuickCommand v[modVersion] ----------
        player.sendMessage(
            Messenger.s(
                "---------- " + QuickCommandClient.modName + " v" + QuickCommandClient.modVersion + " ----------"
            ).formatted(Formatting.AQUA, Formatting.BOLD),
            false
        );
        player.sendMessage(Messenger.s(TranslationText.commandListTitle).formatted(Formatting.LIGHT_PURPLE), false);
        if (QUICK_COMMAND_MAP.isEmpty()) {
            player.sendMessage(Messenger.s("··· ··· ···").formatted(Formatting.AQUA), false);
        }
        int counter = 1;
        refreshListMemory();
        for (Map.Entry<String, String> entry : QUICK_COMMAND_MAP.entrySet()) {
            String name = entry.getKey();
            String command = entry.getValue();
            // [1] [RUN] [DEL] [COPY] 名称: 杀了他顺便杀了我 指令: /kill @e
            player.sendMessage(
                Messenger.s("")
                    .append(Messenger.s("[" + counter + "] ").formatted(Formatting.GOLD))
                    .append(QuickCommandButton.runCommandButton(command))
                    .append(QuickCommandButton.removeCommandButton(name))
                    .append(QuickCommandButton.copyButton(command))
                    .append(Messenger.s(TranslationText.commandName).formatted(Formatting.YELLOW))
                    .append(Messenger.s(name + " ").formatted(Formatting.GOLD))
                    .append(displayCommandInList ? Messenger.s(TranslationText.command).formatted(Formatting.YELLOW) : Messenger.s(""))
                    .append(displayCommandInList ? Messenger.s(command).formatted(Formatting.GOLD) : Messenger.s("")),
                false
            );
            counter++;
        }
        final String displayCommandInListMsgLine = "-----------------------------";
        player.sendMessage(
            Messenger.s(displayCommandInListMsgLine).formatted(Formatting.DARK_AQUA)
                .append(Messenger.endl())
                .append(TranslationText.setDisplayCommandInListButtonTitleText)
                .append(QuickCommandButton.setDisplayCommandInListYesButton())
                .append(QuickCommandButton.setDisplayCommandInListNoButton())
                .append(Messenger.endl())
                .append(Messenger.s(displayCommandInListMsgLine)),
            false
        );
        player.sendMessage(
            Messenger.s( TranslationText.easyOperationTitle + "\n").formatted(Formatting.LIGHT_PURPLE)
                .append(QuickCommandButton.addCommandButton()).append(Messenger.endl())
                .append(QuickCommandButton.removeAllButton()).append(Messenger.endl())
                .append(QuickCommandButton.refreshListButton()).append(Messenger.endl())
                .append(QuickCommandButton.swapButton()).append(Messenger.endl())
                .append(QuickCommandButton.helpButton()).append(Messenger.endl()),
            false
        );
        return 1;
    }

    private static int swap(PlayerEntity player, int index1, int index2) {
        if (index1 == index2 || index1 < 1 || index2 < 1 || index1 > QUICK_COMMAND_MAP.size() || index2 > QUICK_COMMAND_MAP.size()) {
            player.sendMessage(Messenger.s(MSG_HEAD).append(TranslationText.swapFail).formatted(Formatting.RED), false);
            return 0;
        }
        List<Map.Entry<String, String>> entryList = new ArrayList<>(QUICK_COMMAND_MAP.entrySet());
        Collections.swap(entryList, index1 - 1, index2 - 1);
        QUICK_COMMAND_MAP.clear();
        for (Map.Entry<String, String> entry : entryList) {
            QUICK_COMMAND_MAP.put(entry.getKey(), entry.getValue());
        }
        saveToJson();
        showListWithRun(player);
        player.sendMessage(Messenger.s(MSG_HEAD).append(Messenger.tr(TranslationText.swapSuccess, index1, index2)).formatted(Formatting.GREEN), false);
        return 1;
    }

    private static int help(PlayerEntity player) {
        player.sendMessage(
            Messenger.s(TranslationText.helpTitleText).append(Messenger.endl())
                .append(Messenger.s(TranslationText.quickCommandHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.addHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.exampleAddHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.removeHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.exampleRemoveHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.removeAllHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.removeAllConfirmHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.listWithRunHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.displayCommandInListHelpText)).append(Messenger.endl())
                .append(Messenger.s(TranslationText.helpHelpText)),
            false
        );
        return 1;
    }

    private static void refreshListMemory() {
        QUICK_COMMAND_MAP.clear();
        QuickCommandConfig.loadFromJson();
    }

    private static void saveToJson() {
        QuickCommandConfig.saveToJson(QUICK_COMMAND_MAP);
    }
}
