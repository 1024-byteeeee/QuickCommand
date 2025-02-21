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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import top.byteeeee.quickcommand.QuickCommand;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TranslationLoader {
    public static final Map<String, Map<String, String>> TRANSLATIONS = new ConcurrentHashMap<>();

    public static void loadTranslations() {
        final String langPath = "assets/" + QuickCommand.modId + "/lang/";
        final List<String> languages = Arrays.asList("en_us", "zh_cn");
        for (String lang : languages) {
            final String path = langPath + lang + ".json";
            InputStream inputStream = null;
            try {
                inputStream = TranslationLoader.class.getClassLoader().getResourceAsStream(path);
                if (inputStream == null) {
                    continue;
                }
                //#if MC<11800
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).getAsJsonObject();
                //#else
                //$$ JsonObject json = JsonParser.parseReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).getAsJsonObject();
                //#endif
                Map<String, String> langMap = new ConcurrentHashMap<>();
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    langMap.put(entry.getKey(), entry.getValue().getAsString());
                }
                TRANSLATIONS.put(lang, langMap);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        QuickCommand.LOGGER.error("Error closing stream", e);
                    }
                }
            }
        }
    }
}
