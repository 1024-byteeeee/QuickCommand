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

package top.byteeeee.quickcommand.key;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.lwjgl.glfw.GLFW;
import top.byteeeee.quickcommand.translations.Translator;

@Environment(EnvType.CLIENT)
public class RegisterKeyBinding {
    private static final Translator TR = new Translator("key");
    public static KeyBinding openQuickCommandList;

    public static void Register() {
        openQuickCommandList = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                TR.tr("openQuickCommandListKeyBindingName").getString(),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_U,
                TR.tr("category").getString()
            )
        );
    }
}
