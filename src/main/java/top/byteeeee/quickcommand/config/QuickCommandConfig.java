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

package top.byteeeee.quickcommand.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;

import top.byteeeee.quickcommand.QuickCommand;
import top.byteeeee.quickcommand.QuickCommandClient;
import top.byteeeee.quickcommand.helpers.CommandHelper;
import top.byteeeee.quickcommand.helpers.EnvironmentHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuickCommandConfig {
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("QuickCommand");
    private static final String CONFIG_FILE = EnvironmentHelper.isServer() ? getSavePath(QuickCommand.getInstance().minecraftServer): CONFIG_PATH.resolve("QuickCommandConfig.json").toString();

    public static void loadFromJson() {
        Gson gson = new Gson();
        Path path = Paths.get(CONFIG_FILE);
        CommandHelper.QUICK_COMMAND_MAP.clear();
        if (Files.exists(path)) {
            try {
                String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                ConfigData configData = gson.fromJson(json, ConfigData.class);
                if (configData != null) {
                    CommandHelper.QUICK_COMMAND_MAP.putAll(configData.commandMap);
                    CommandHelper.currentLanguage = configData.language != null ? configData.language : "zh_cn";
                    CommandHelper.QUICK_COMMAND_MAP.putAll(configData.commandMap);
                    CommandHelper.displayCommandInList = configData.displayCommandInList;
                }
            } catch (IOException e) {
                QuickCommandClient.LOGGER.warn("Failed to load config", e);
            }
        }
    }

    public static void saveToJson(Map<String, String> commandMap) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ConfigData configData = new ConfigData();
        configData.commandMap = new LinkedHashMap<>(commandMap);
        configData.displayCommandInList = CommandHelper.displayCommandInList;
        configData.language = getCurrentLanguage();
        String json = gson.toJson(configData);
        try {
            Path path = Paths.get(CONFIG_FILE);
            Files.createDirectories(path.getParent());
            Files.write(path, json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            QuickCommandClient.LOGGER.warn("Failed to save config", e);
        }
    }

    public static String getCurrentLanguage() {
        return CommandHelper.currentLanguage;
    }

    public static String getSavePath(MinecraftServer server) {
        return server.getSavePath(WorldSavePath.ROOT).resolve("quickCommand/quickCommand" + ".json").toString();
    }

    private static class ConfigData {
        Map<String, String> commandMap = new LinkedHashMap<>();
        boolean displayCommandInList;
        String language = "zh_cn";
    }
}
