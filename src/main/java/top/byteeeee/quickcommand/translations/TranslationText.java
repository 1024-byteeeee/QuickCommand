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

package top.byteeeee.quickcommand.translations;

import top.byteeeee.quickcommand.utils.Messenger;

public class TranslationText {
    // 指令消息翻译
    public static String commandName;
    public static String command;
    public static String add;
    public static String alreadyExist;
    public static String remove;
    public static String removeAll;
    public static String initiateRemoveAll_first;
    public static String initiateRemoveAll_after;
    public static String confirmRemoveAll_warn;
    public static String commandListTitle;
    public static String easyOperationTitle;
    public static String swapFail;
    public static String swapSuccess;
    public static String helpTitleText;
    public static String quickCommandHelpText;
    public static String addHelpText;
    public static String exampleAddHelpText;
    public static String removeHelpText;
    public static String exampleRemoveHelpText;
    public static String removeAllHelpText;
    public static String removeAllConfirmHelpText;
    public static String listWithRunHelpText;
    public static String swapHelpText;
    public static String displayCommandInListHelpText;
    public static String helpHelpText;

    // 按钮翻译
    public static String runButtonHoverText;
    public static String removeButtonHoverText;
    public static String copyButtonHoverText;
    public static String addButtonHoverText;
    public static String addButtonText;
    public static String removeAllButtonHoverText;
    public static String removeAllButtonText;
    public static String helpButtonHoverText;
    public static String helpButtonText;
    public static String swapButtonHoverText;
    public static String swapButtonText;
    public static String refreshListButtonHoverText;
    public static String refreshListButtonText;
    public static String setDisplayCommandInListButtonTitleText;
    public static String setDisplayCommandInListYesButtonHoverText;
    public static String setDisplayCommandInListNoButtonHoverText;

    // 按键绑定翻译
    public static String quickCommandKeyBindingCategory;
    public static String openQuickCommandListKeyBindingName;

    public static void reloadTranslations() {
        commandName = Messenger.tr("QuickCommand.command.commandName").getString();
        command = Messenger.tr("QuickCommand.command.command").getString();
        add = Messenger.tr("QuickCommand.command.add").getString();
        alreadyExist = Messenger.tr("QuickCommand.command.alreadyExist").getString();
        remove = Messenger.tr("QuickCommand.command.remove").getString();
        removeAll = Messenger.tr("QuickCommand.command.removeAll").getString();
        initiateRemoveAll_first = Messenger.tr("QuickCommand.command.initiateRemoveAll_first").getString();
        initiateRemoveAll_after = Messenger.tr("QuickCommand.command.initiateRemoveAll_after").getString();
        confirmRemoveAll_warn = Messenger.tr("QuickCommand.command.confirmRemoveAll_warn").getString();
        commandListTitle = Messenger.tr("QuickCommand.command.commandListTitle").getString();
        easyOperationTitle = Messenger.tr("QuickCommand.command.easyOperation").getString();
        swapSuccess = Messenger.tr("QuickCommand.command.swapSuccess").getString();
        swapFail = Messenger.tr("QuickCommand.command.swapFail").getString();
        helpTitleText = Messenger.tr("QuickCommand.command.helpTitleText").getString();
        quickCommandHelpText = Messenger.tr("QuickCommand.command.quickCommandHelpText").getString();
        addHelpText = Messenger.tr("QuickCommand.command.addHelpText").getString();
        exampleAddHelpText = Messenger.tr("QuickCommand.command.exampleAddHelpText").getString();
        removeHelpText = Messenger.tr("QuickCommand.command.removeHelpText").getString();
        exampleRemoveHelpText = Messenger.tr("QuickCommand.command.exampleRemoveHelpText").getString();
        removeAllHelpText = Messenger.tr("QuickCommand.command.removeAllHelpText").getString();
        removeAllConfirmHelpText = Messenger.tr("QuickCommand.command.removeAllConfirmHelpText").getString();
        listWithRunHelpText = Messenger.tr("QuickCommand.command.listWithRunHelpText").getString();
        swapHelpText = Messenger.tr("QuickCommand.command.swapHelpText").getString();
        displayCommandInListHelpText = Messenger.tr("QuickCommand.command.displayCommandInListHelpText").getString();
        helpHelpText = Messenger.tr("QuickCommand.command.helpHelpText").getString();
        runButtonHoverText = Messenger.tr("QuickCommand.command.runButtonHoverText").getString();
        removeButtonHoverText = Messenger.tr("QuickCommand.command.removeButtonHoverText").getString();
        copyButtonHoverText = Messenger.tr("QuickCommand.command.copyButtonHoverText").getString();
        addButtonHoverText = Messenger.tr("QuickCommand.command.addButtonHoverText").getString();
        addButtonText = Messenger.tr("QuickCommand.command.addButtonText").getString();
        removeAllButtonHoverText = Messenger.tr("QuickCommand.command.removeAllButtonHoverText").getString();
        removeAllButtonText = Messenger.tr("QuickCommand.command.removeAllButtonText").getString();
        helpButtonHoverText = Messenger.tr("QuickCommand.command.helpButtonHoverText").getString();
        helpButtonText = Messenger.tr("QuickCommand.command.helpButtonText").getString();
        swapButtonHoverText = Messenger.tr("QuickCommand.command.swapButtonHoverText").getString();
        swapButtonText = Messenger.tr("QuickCommand.command.swapButtonText").getString();
        refreshListButtonHoverText = Messenger.tr("QuickCommand.command.refreshListButtonHoverText").getString();
        refreshListButtonText = Messenger.tr("QuickCommand.command.refreshListButtonText").getString();
        setDisplayCommandInListButtonTitleText = Messenger.tr("QuickCommand.command.setDisplayCommandInListButtonTitleText").getString();
        setDisplayCommandInListYesButtonHoverText = Messenger.tr("QuickCommand.command.setDisplayCommandInListYesButtonHoverText").getString();
        setDisplayCommandInListNoButtonHoverText = Messenger.tr("QuickCommand.command.setDisplayCommandInListNoButtonHoverText").getString();
        refreshListButtonText = Messenger.tr("QuickCommand.command.refreshListButtonText").getString();
        quickCommandKeyBindingCategory = Messenger.tr("QuickCommand.key.category").getString();
        openQuickCommandListKeyBindingName = Messenger.tr("QuickCommand.key.openQuickCommandListKeyBindingName").getString();
    }

    static {
        reloadTranslations();
    }
}
