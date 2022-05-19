package com.model;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.model.ModelTest;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(144);
		config.setTitle("ModelTest");
		config.setWindowedMode(1920, 1050);
		config.useOpenGL3(true, 3, 2);
		config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 16);
		new Lwjgl3Application(new ModelTest(), config);
	}
}
