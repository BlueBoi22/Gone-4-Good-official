package org.cistercian.franco.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.cistercian.franco.Gone4good;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Gone4good(), config);
		config.title = "Gone 4 Good";
		config.height = 100;
		config.width = 1840;
		config.fullscreen = true;
	}
}
