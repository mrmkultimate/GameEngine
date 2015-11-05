package org.GameEngine.Main;
import java.util.*;

import org.GameEngine.LevelManager.*;
import org.GameEngine.Objects.*;
import org.GameEngine.RenderEngine.*;

public class Main {
	public static void main(String[] args)
	{
		System.out.println("Hello World");
		
		RenderEngine renderEngine = new RenderEngine();
		renderEngine.start();
		
		Game game = new Game();
		
		game.play();
		
		Level.testPrint();
		
	}
}
