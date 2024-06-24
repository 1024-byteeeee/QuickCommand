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

import top.byteeeee.quickcommand.translations.TranslationText;
import top.byteeeee.quickcommand.utils.MessageTextEventUtils.ClickEventUtil;
import top.byteeeee.quickcommand.utils.MessageTextEventUtils.HoverEventUtil;
import top.byteeeee.quickcommand.utils.Messenger;

public class QuickCommandButton {
    public static Text runCommandButton(String command) {
        Text hoverText = Messenger.s(TranslationText.runButtonHoverText + command).formatted(Formatting.YELLOW);
        return
            Messenger.s("[▷]").setStyle(
                Style.EMPTY.withColor(Formatting.AQUA).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text removeCommandButton(String name) {
        Text hoverText = Messenger.s(TranslationText.removeButtonHoverText + name).formatted(Formatting.YELLOW);
        return
            Messenger.s(" [×]").setStyle(
                Style.EMPTY.withColor(Formatting.RED).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, String.format("/quickCommand remove \"%s\"", name))).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text copyButton(String command) {
        Text hoverText = Messenger.s(TranslationText.copyButtonHoverText + command).formatted(Formatting.YELLOW);
        return
            Messenger.s(" [C] ").setStyle(
                Style.EMPTY.withColor(Formatting.GREEN).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.COPY_TO_CLIPBOARD, command)).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text addCommandButton() {
        Text hoverText = Messenger.s(TranslationText.addButtonHoverText).formatted(Formatting.YELLOW);
        return
            Messenger.s(TranslationText.addButtonText).setStyle(
                Style.EMPTY.withColor(Formatting.GREEN).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.SUGGEST_COMMAND, "/quickCommand add ")).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text removeAllButton() {
        Text hoverText = Messenger.s(TranslationText.removeAllButtonHoverText).formatted(Formatting.YELLOW);
        return
            Messenger.s(TranslationText.removeAllButtonText).setStyle(
                Style.EMPTY.withColor(Formatting.RED).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, "/quickCommand removeAll")).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text helpButton() {
        Text hoverText = Messenger.s(TranslationText.helpButtonHoverText).formatted(Formatting.YELLOW);
        return
            Messenger.s(TranslationText.helpButtonText).setStyle(
                Style.EMPTY.withColor(Formatting.AQUA).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, "/quickCommand help")).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text swapButton() {
        Text hoverText = Messenger.s(TranslationText.swapButtonHoverText).formatted(Formatting.YELLOW);
        return
            Messenger.s(TranslationText.swapButtonText).setStyle(
                Style.EMPTY.withColor(Formatting.YELLOW).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.SUGGEST_COMMAND, "/quickCommand swap ")).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text refreshListButton() {
        Text hoverText = Messenger.s(TranslationText.refreshListButtonHoverText).formatted(Formatting.YELLOW);
        return
            Messenger.s(TranslationText.refreshListButtonText).setStyle(
                Style.EMPTY.withColor(Formatting.GOLD).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, "/quickCommand listWithRun")).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text setDisplayCommandInListYesButton() {
        Text hoverText = Messenger.s(TranslationText.setDisplayCommandInListYesButtonHoverText).formatted(Formatting.YELLOW);
        return
            Messenger.s("[YES]").setStyle(
                Style.EMPTY.withColor(Formatting.GREEN).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, "/quickCommand displayCommandInList true")).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }

    public static Text setDisplayCommandInListNoButton() {
        Text hoverText = Messenger.s(TranslationText.setDisplayCommandInListNoButtonHoverText).formatted(Formatting.YELLOW);
        return
            Messenger.s(" [NO]").setStyle(
                Style.EMPTY.withColor(Formatting.RED).
                withClickEvent(ClickEventUtil.event(ClickEventUtil.RUN_COMMAND, "/quickCommand displayCommandInList false")).
                withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, hoverText))
            );
    }
}
