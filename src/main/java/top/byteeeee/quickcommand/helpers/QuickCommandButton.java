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

package top.byteeeee.quickcommand.helpers;

import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import top.byteeeee.quickcommand.translations.Translator;
import top.byteeeee.quickcommand.utils.MessageTextEventUtils.ClickEventUtil;
import top.byteeeee.quickcommand.utils.MessageTextEventUtils.HoverEventUtil;
import top.byteeeee.quickcommand.utils.Messenger;

public class QuickCommandButton {
    private static  final Translator translator = new Translator("button");

    private static String cmd() {
        return EnvironmentHelper.getCommandPrefix() + " ";
    }

    public static Text runCommandButton(String command) {
        Text hoverText = translator.tr("runButtonHoverText").append(Messenger.s(command)).formatted(Formatting.YELLOW);
        return
            Messenger.s("[▷]").setStyle(
                Style.EMPTY.withColor(Formatting.AQUA).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text removeCommandButton(String name) {
        String command = cmd() + "remove \"" + name + "\"";
        Text hoverText = translator.tr("removeButtonHoverText").append(Messenger.s(name)).formatted(Formatting.YELLOW);
        return
            Messenger.s(" [×]").setStyle(
                Style.EMPTY.withColor(Formatting.RED).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text copyButton(String command) {
        Text hoverText = translator.tr("copyButtonHoverText").append(Messenger.s(command)).formatted(Formatting.YELLOW);
        return
            Messenger.s(" [C] ").setStyle(
                Style.EMPTY.withColor(Formatting.GREEN).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.COPY_TO_CLIPBOARD, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text addCommandButton() {
        String command = cmd() + "add ";
        Text hoverText = translator.tr("addButtonHoverText").formatted(Formatting.YELLOW);
        return
            translator.tr("addButtonText").setStyle(
                Style.EMPTY.withColor(Formatting.GREEN).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.SUGGEST_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text removeAllButton() {
        String command = cmd() + "removeAll";
        Text hoverText = translator.tr("removeAllButtonHoverText").formatted(Formatting.YELLOW);
        return
            translator.tr("removeAllButtonText").setStyle(
                Style.EMPTY.withColor(Formatting.RED).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text helpButton() {
        String command = cmd() + "help";
        Text hoverText = translator.tr("helpButtonHoverText").formatted(Formatting.YELLOW);
        return
            translator.tr("helpButtonText").setStyle(
                Style.EMPTY.withColor(Formatting.AQUA).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text swapButton() {
        String command = cmd() + "swap ";
        Text hoverText = translator.tr("swapButtonHoverText").formatted(Formatting.YELLOW);
        return
            translator.tr("swapButtonText").setStyle(
                Style.EMPTY.withColor(Formatting.YELLOW).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.SUGGEST_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text refreshListButton() {
        String command = cmd() + "listWithRun";
        Text hoverText = translator.tr("refreshListButtonHoverText").formatted(Formatting.YELLOW);
        return
            translator.tr("refreshListButtonText").setStyle(
                Style.EMPTY.withColor(Formatting.GOLD).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text setDisplayCommandInListYesButton() {
        String command = cmd() + "displayCommandInList true";
        Text hoverText = translator.tr("setDisplayCommandInListYesButtonHoverText").formatted(Formatting.YELLOW);
        return
            Messenger.s("[YES]").setStyle(
                Style.EMPTY.withColor(Formatting.GREEN).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text setDisplayCommandInListNoButton() {
        String command = cmd() + "displayCommandInList false";
        Text hoverText = translator.tr("setDisplayCommandInListNoButtonHoverText").formatted(Formatting.YELLOW);
        return
            Messenger.s(" [NO]").setStyle(
                Style.EMPTY.withColor(Formatting.RED).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text setChineseButton() {
        String command = cmd() + "language zh_cn";
        Text hoverText = translator.tr("setChineseHoverText").formatted(Formatting.YELLOW);
        return
            Messenger.s("[中文]").setStyle(
                Style.EMPTY.withColor(Formatting.AQUA).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text setEnglishButton() {
        String command = cmd() + "language en_us";
        Text hoverText = translator.tr("setEnglishHoverText").formatted(Formatting.YELLOW);
        return
            Messenger.s("[English]").setStyle(
                Style.EMPTY.withColor(Formatting.AQUA).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }
}
