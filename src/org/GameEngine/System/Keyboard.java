package org.GameEngine.System;
import java.util.*;
public final class Keyboard {
	
	//friend class Window;
	static KeyState[] keys = new KeyState[Key.KeyCount.value];
	static KeyState[] previousKeys = new KeyState[Key.KeyCount.value];

	Keyboard(){
		for(int i = 0; i < Key.KeyCount.value; i++)
		{
			keys[i] = KeyState.ksUp;
			previousKeys[i] = keys[i];
			
		}
		
	}
	
	public static void Update(){
		
		
		for(int i = 0; i < Key.KeyCount.value; i++)
		{
			if(keys[i] == KeyState.ksUp){
					previousKeys[i] = KeyState.ksUp;
			}
			else if(keys[i] == KeyState.ksLetGo){
				keys[i] = KeyState.ksUp;
				if(previousKeys[i] == KeyState.ksLetGo){
					previousKeys[i] = KeyState.ksUp;
				}
				else{
					previousKeys[i] = KeyState.ksLetGo;
				}
			}
			else if(keys[i] == KeyState.ksHit){
				keys[i] = KeyState.ksHeld;
				if(previousKeys[i] == KeyState.ksHit){
					previousKeys[i] = KeyState.ksHeld;
				}
				else{
					previousKeys[i] = KeyState.ksHit;
				}
			}else if(keys[i] == KeyState.ksHeld){
				previousKeys[i] = KeyState.ksHeld;
			}
			
		}
		
	}
	public static void onKeyPress(char key){
		
		if (Character.isAlphabetic(key))
		{
			key = Character.toLowerCase(key);
			key = (char) (key - 'a');
			keys[key+Key.A.value] = KeyState.ksHit;
		}
		else if (Character.isDigit(key))
		{

			key = (char) (key - '0');
			keys[key + Key.D0.value] = KeyState.ksHit;
		}
		else
		{
			switch (key)
			{
			case 27:
				keys[Key.Escape.value] = KeyState.ksHit;
				break;
			case ':':
			case ';':
				keys[Key.SemiColon.value] = KeyState.ksHit;
				break;
			case ',':
			case '<':
				keys[Key.Comma.value] = KeyState.ksHit;
				break;
			case '.':
			case '>':
				keys[Key.Period.value] = KeyState.ksHit;
				break;

			case '\'':
			case '\"':
				keys[Key.Quote.value] = KeyState.ksHit;
				break;
			case '/':
			case '?':
				keys[Key.Slash.value] = KeyState.ksHit;
				break;
			case '\\':
			case '|':
				keys[Key.BackSlash.value] = KeyState.ksHit;
				break;

			case '~':
			case '`':
				keys[Key.Tilde.value] = KeyState.ksHit;
				break;
			case '=':
			case '+':
				keys[Key.Equal.value] = KeyState.ksHit;
				break;
			case '-':
			case '_':
				keys[Key.Dash.value] = KeyState.ksHit;
				break;
			case ' ':
				keys[Key.Space.value] = KeyState.ksHit;
				break;
			case 13:
				keys[Key.Escape.value] = KeyState.ksHit;
				break;
			case 8:
				keys[Key.BackSpace.value] = KeyState.ksHit;
				break;
			case 9:
				keys[Key.Tab.value] = KeyState.ksHit;
				break;
			case 127:
				keys[Key.Delete.value] = KeyState.ksHit;
				break;
			}
		}

//		keys[key] = Key.ksHit;
	}
	public static void onKeyRelease(char key){
		if (Character.isAlphabetic(key))
		{
			key = Character.toLowerCase(key);
			key = (char) (key - 'a');
			keys[key + Key.A.value] = KeyState.ksLetGo;
		}
		else if (Character.isDigit(key))
		{
			key = (char) (key - '0');
			keys[key + Key.D0.value] = KeyState.ksLetGo;
		}
		else
		{
			switch (key)
			{
			case 27:
				keys[Key.Escape.value] = KeyState.ksLetGo;
				break;
			case ':':
			case ';':
				keys[Key.SemiColon.value] = KeyState.ksLetGo;
				break;
			case ',':
			case '<':
				keys[Key.Comma.value] = KeyState.ksLetGo;
				break;
			case '.':
			case '>':
				keys[Key.Period.value] = KeyState.ksLetGo;
				break;

			case '\'':
			case '\"':
				keys[Key.Quote.value] = KeyState.ksLetGo;
				break;
			case '/':
			case '?':
				keys[Key.Slash.value] = KeyState.ksLetGo;
				break;
			case '\\':
			case '|':
				keys[Key.BackSlash.value] = KeyState.ksLetGo;
				break;

			case '~':
			case '`':
				keys[Key.Tilde.value] = KeyState.ksLetGo;
				break;
			case '=':
			case '+':
				keys[Key.Equal.value] = KeyState.ksLetGo;
				break;
			case '-':
			case '_':
				keys[Key.Dash.value] = KeyState.ksLetGo;
				break;
			case ' ':
				keys[Key.Space.value] = KeyState.ksLetGo;
				break;
			case 13:
				keys[Key.Escape.value] = KeyState.ksLetGo;
				break;
			case 8:
				keys[Key.BackSpace.value] = KeyState.ksLetGo;
				break;
			case 9:
				keys[Key.Tab.value] = KeyState.ksLetGo;
				break;
			case 127:
				keys[Key.Delete.value] = KeyState.ksLetGo;
				break;
			}
		}
	}

	//returns true if key is was just pressed - edge sensitive
	public static boolean keyHit(Key key){
		
		return previousKeys[key.value] == KeyState.ksHit;
	}
	//returns true if key is was just pressed - edge sensitive
	public static boolean keyHit(int key){
		return previousKeys[key] == KeyState.ksHit;
	}

	//returns true if key is down
	public static boolean keyHeld(Key key){
		return previousKeys[key.value] == KeyState.ksHit || previousKeys[key.value] == KeyState.ksHeld;
	}
	//returns true if key is down
	public static boolean keyHeld(int key){
		return previousKeys[key] == KeyState.ksHit || previousKeys[key] == KeyState.ksHeld;
	}
	
	//returns true if key is was just released - edge sensitive
	public static boolean keyLetGo(Key key){
		return previousKeys[key.value] == KeyState.ksLetGo;
	}
	//returns true ifkey is was just released - edge sensitive
	public static boolean keyLetGo(int key){
		return previousKeys[key] == KeyState.ksLetGo;
	}

	//returns true if key is up
	public static boolean keyUp(Key key){
		return previousKeys[key.value] == KeyState.ksUp || previousKeys[key.value] == KeyState.ksLetGo;
	}
	//returns true if key is up
	public static boolean keyUp(int key){
		return previousKeys[key] == KeyState.ksUp || previousKeys[key] == KeyState.ksLetGo;
	}
	
	//returns true if any key is was just pressed - edge sensitive
	public static boolean anyKeyHit(){
		for(int i = 0; i < Key.KeyCount.value; i++)
		{
			if(keyHeld(i))
				return true;
		}
		return false;
	}
	
	//returns true if any key is down
	public static boolean anyKeyHeld(){
		for(int i = 0; i < Key.KeyCount.value; i++)
		{
			if(keyHit(i))
				return true;
		}
		return false;
	}
	
}
