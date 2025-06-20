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

package top.byteeeee.quickcommand;

import net.fabricmc.api.ModInitializer;

import net.minecraft.server.MinecraftServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import top.byteeeee.quickcommand.commands.RegisterCommands;
import top.byteeeee.quickcommand.helpers.QuickCommandCommandHelper;
import top.byteeeee.quickcommand.helpers.EnvironmentHelper;
import top.byteeeee.quickcommand.translations.TranslationLoader;

public class QuickCommand implements ModInitializer {
	public static final String modName = EnvironmentHelper.isServer() ? "ServerQuickCommand" : "QuickCommand";
	public static final String modId = "quickcommand";
	public static final String modVersion = "1.1.2";
	public MinecraftServer minecraftServer;
	private static final QuickCommand INSTANCE = new QuickCommand();
	public static final Logger LOGGER = LogManager.getLogger(QuickCommand.modName);

	public static QuickCommand getInstance() {
		return INSTANCE;
	}

	@Override
	public void onInitialize() {
		TranslationLoader.loadTranslations();
		RegisterCommands.registerServerCommands();
	}

	public void onServerLoadedWorlds(MinecraftServer server) {
		minecraftServer = server;
		QuickCommandCommandHelper.refreshListMemory();
	}
}
