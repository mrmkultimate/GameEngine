package org.GameEngine.Game;
import java.util.*;

import org.GameEngine.LevelManager.*;
import org.GameEngine.Objects.*;
import org.GameEngine.RenderEngine.*;

public class Main {
	public static void main(String[] args)
	{
		System.out.println("Hello World");
		
		RenderEngine renderEngine = new RenderEngine("This is My Title");
		
		
		renderEngine.start();
		Game game = new Game();
		
		game.play();
		
		Level.testPrint();
		
	}
}
