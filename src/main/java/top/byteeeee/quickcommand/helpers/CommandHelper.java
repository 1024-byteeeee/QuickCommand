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

package top.byteeeee.quickcommand.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

import top.byteeeee.quickcommand.QuickCommand;
import top.byteeeee.quickcommand.config.QuickCommandConfig;
import top.byteeeee.quickcommand.translations.Translator;
import top.byteeeee.quickcommand.utils.Messenger;

import java.util.*;

public class CommandHelper {
    private static final Translator translator = new Translator("command");
    public static final Map<String, String> QUICK_COMMAND_MAP = new LinkedHashMap<>();
    private static final String MSG_HEAD = EnvironmentHelper.isServer() ? "§b<ServerQuickCommand>§r " : "§b<QuickCommand>§r ";
    public static boolean awaitingConfirmation = false;
    public static boolean displayCommandInList = false;
    public static String currentLanguage = "zh_cn";

    public static int add(PlayerEntity player, String name, String command) {
        if (!QUICK_COMMAND_MAP.containsKey(name)) {
            QUICK_COMMAND_MAP.put(name, command);
            saveToJson();
            showListWithRun(player);
            player.sendMessage(
                Messenger.s(MSG_HEAD)
                .append(translator.tr("add"))
                .append(Messenger.s(String.format(" [ %s ] - %s ", name, command))).formatted(Formatting.GREEN), false
            );
        } else {
            player.sendMessage(Messenger.s(MSG_HEAD).append(Messenger.s(name + " " + translator.tr("alreadyExist").getString())).formatted(Formatting.RED), false);
        }
        return 1;
    }

    public static int remove(PlayerEntity player, String name) {
        QUICK_COMMAND_MAP.remove(name);
        saveToJson();
        showListWithRun(player);
        player.sendMessage(
            Messenger.s(MSG_HEAD)
            .append(translator.tr("remove"))
            .append(Messenger.s("[ " + name + " ]")).formatted(Formatting.RED), false
        );
        return 1;
    }

    public static int initiateRemoveAll(PlayerEntity player) {
        if (!awaitingConfirmation) {
            awaitingConfirmation = true;
            player.sendMessage(Messenger.s(MSG_HEAD).append(translator.tr("initiateRemoveAll_first").formatted(Formatting.RED)), false);
            return 1;
        } else {
            player.sendMessage(Messenger.s(MSG_HEAD).append(translator.tr("initiateRemoveAll_after").formatted(Formatting.RED)), false);
            return 0;
        }
    }

    public static int confirmRemoveAll(PlayerEntity player) {
        if (awaitingConfirmation) {
            awaitingConfirmation = false;
            QUICK_COMMAND_MAP.clear();
            saveToJson();
            player.sendMessage(Messenger.s(MSG_HEAD).append(translator.tr("removeAll")).formatted(Formatting.RED), false);
            return 1;
        } else {
            player.sendMessage(Messenger.s(MSG_HEAD).append(translator.tr("confirmRemoveAll_warn")).formatted(Formatting.RED), false);
            return 0;
        }
    }

    public static int setDisplayCommandInList(PlayerEntity player, boolean value) {
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
                "---------- " + QuickCommand.modName + " v" + QuickCommand.modVersion + " ----------"
            ).formatted(Formatting.AQUA, Formatting.BOLD),
            false
        );
        player.sendMessage(translator.tr("commandListTitle").formatted(Formatting.LIGHT_PURPLE), false);
        if (QUICK_COMMAND_MAP.isEmpty()) {
            player.sendMessage(Messenger.s("··· ··· ···").formatted(Formatting.AQUA), false);
        }
        int counter = 1;
        refreshListMemory();
        for (Map.Entry<String, String> entry : QUICK_COMMAND_MAP.entrySet()) {
            String name = entry.getKey();
            String command = entry.getValue();
            MutableText message =
                Messenger.s("[" + counter + "] ")
                .formatted(Formatting.GOLD)
                .append(QuickCommandButton.runCommandButton(command))
                .append(QuickCommandButton.removeCommandButton(name))
                .append(QuickCommandButton.copyButton(command))
                .append(translator.tr("commandName").formatted(Formatting.YELLOW))
                .append(Messenger.s(name + " ").formatted(Formatting.GOLD));
            if (displayCommandInList) {
                message.append(translator.tr("command").formatted(Formatting.YELLOW)).append(Messenger.s(command).formatted(Formatting.GOLD));
            }
            player.sendMessage(message, false);
            counter++;
        }
        final String displayCommandInListMsgLine = "-----------------------------";
        player.sendMessage(
            Messenger.s(displayCommandInListMsgLine).formatted(Formatting.DARK_AQUA)
            .append(Messenger.endl())
            .append(translator.tr("setDisplayCommandInListButtonTitleText"))
            .append(QuickCommandButton.setDisplayCommandInListYesButton())
            .append(QuickCommandButton.setDisplayCommandInListNoButton())
            .append(Messenger.endl())
            .append(Messenger.s(displayCommandInListMsgLine)),
            false
        );
        player.sendMessage(
            translator.tr("easyOperation").formatted(Formatting.LIGHT_PURPLE).append(Messenger.endl())
            .append(QuickCommandButton.addCommandButton()).append(Messenger.endl())
            .append(QuickCommandButton.removeAllButton()).append(Messenger.endl())
            .append(QuickCommandButton.refreshListButton()).append(Messenger.endl())
            .append(QuickCommandButton.swapButton()).append(Messenger.endl())
            .append(QuickCommandButton.helpButton()).append(Messenger.endl()),
            false
        );
        return 1;
    }

    public static int swap(PlayerEntity player, int index1, int index2) {
        if (index1 == index2 || index1 < 1 || index2 < 1 || index1 > QUICK_COMMAND_MAP.size() || index2 > QUICK_COMMAND_MAP.size()) {
            player.sendMessage(Messenger.s(MSG_HEAD).append(translator.tr("swapFail")).formatted(Formatting.RED), false);
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
        return 1;
    }

    public static int help(PlayerEntity player) {
        player.sendMessage(
            translator.tr("helpTitleText").append(Messenger.endl())
            .append(translator.tr("quickCommandHelpText")).append(Messenger.endl())
            .append(translator.tr("addHelpText")).append(Messenger.endl())
            .append(translator.tr("exampleAddHelpText")).append(Messenger.endl())
            .append(translator.tr("removeHelpText")).append(Messenger.endl())
            .append(translator.tr("exampleRemoveHelpText")).append(Messenger.endl())
            .append(translator.tr("removeAllHelpText")).append(Messenger.endl())
            .append(translator.tr("removeAllConfirmHelpText")).append(Messenger.endl())
            .append(translator.tr("listWithRunHelpText")).append(Messenger.endl())
            .append(translator.tr("swapHelpText")).append(Messenger.endl())
            .append(translator.tr("displayCommandInListHelpText")).append(Messenger.endl())
            .append(translator.tr("helpHelpText")),
            false
        );
        return 1;
    }

    public static void refreshListMemory() {
        QUICK_COMMAND_MAP.clear();
        QuickCommandConfig.loadFromJson();
    }

    public static void saveToJson() {
        QuickCommandConfig.saveToJson(QUICK_COMMAND_MAP);
    }

    public static int setLanguage(ServerPlayerEntity player, String language) {
        List<String> availableLanguages = Arrays.asList("zh_cn", "en_us");
        if (!availableLanguages.contains(language)) {
            return 0;
        }
        currentLanguage = language;
        QuickCommandConfig.saveToJson(QUICK_COMMAND_MAP);
        refreshListMemory();
        showListWithRun(player);
        return 1;
    }
}
