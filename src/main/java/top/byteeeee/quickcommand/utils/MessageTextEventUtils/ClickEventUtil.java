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

package top.byteeeee.quickcommand.utils.MessageTextEventUtils;

import net.minecraft.text.ClickEvent;

@SuppressWarnings("unused")
public class ClickEventUtil {
    public static ClickEvent.Action OPEN_URL = ClickEvent.Action.OPEN_URL;
    public static ClickEvent.Action OPEN_FILE = ClickEvent.Action.OPEN_FILE;
    public static ClickEvent.Action RUN_COMMAND = ClickEvent.Action.RUN_COMMAND;
    public static ClickEvent.Action SUGGEST_COMMAND = ClickEvent.Action.SUGGEST_COMMAND;
    public static ClickEvent.Action CHANGE_PAGE = ClickEvent.Action.CHANGE_PAGE;
    public static ClickEvent.Action COPY_TO_CLIPBOARD = ClickEvent.Action.COPY_TO_CLIPBOARD;

    public static ClickEvent event(ClickEvent.Action action, String value) {
        return new ClickEvent(action, value);
    }
}
