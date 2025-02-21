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

package top.byteeeee.quickcommand.translations;

import net.minecraft.text.BaseText;

import top.byteeeee.quickcommand.helpers.QuickCommandCommandHelper;
import top.byteeeee.quickcommand.helpers.EnvironmentHelper;
import top.byteeeee.quickcommand.utils.Messenger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Translator {
    private final String translationPath;

    public Translator(String translationPath) {
        this.translationPath = translationPath;
    }

    public BaseText tr(String key, Object... args) {
        String fullKey = EnvironmentHelper.isServer() ? "QuickCommand." + translationPath + "." + "server_" + key : "QuickCommand." + translationPath + "." + key;
        String lang = QuickCommandCommandHelper.currentLanguage;
        Map<String, String> translations = TranslationLoader.TRANSLATIONS.getOrDefault(lang, new ConcurrentHashMap<>());
        String template = translations.getOrDefault(fullKey, fullKey);
        return Messenger.s(String.format(template, args));
    }
}
