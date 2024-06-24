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

package top.byteeeee.quickcommand.utils.compat;

import net.minecraft.text.*;

public class MessengerCompatFactory {
    //#if MC<11900
    public static LiteralText LiteralText(String text) {
        return new LiteralText(text);
    }
    //#else
    //$$ public static MutableText LiteralText(String text) {
    //$$     return Text.literal(text);
    //$$ }
    //#endif

    public static BaseText TranslatableText(String key, Object... args) {
        //#if MC>=11900
        //$$ return Text.translatable(key, args);
        //#else
        return new TranslatableText(key, args);
        //#endif
    }
}
