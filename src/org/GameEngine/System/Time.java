package org.GameEngine.System;

public final class Time {

	//time is in seconds
	private static float maxDeltaTime = 0.1f;
	private static float currentTime = 0;
	private static float deltaTime = 0;
	private static float timeScale = 1;
	private static float unscaledTime = 0;
	private static float unscaledDeltaTime = 0;
	private static float lastTime = 0;
	
	
	public static float CurrentTime(){
		return currentTime;
	}
	public static float DeltaTime(){
		return deltaTime;
	}
	public static float TimeScale(){
		return timeScale;
	}
	public static float UnscaledTime(){
		return unscaledTime;
	}
	public static float UnscaledDeltaTime(){
		return unscaledDeltaTime;
	}
	public static void setTimeScale(float newTimeScale){
		timeScale = newTimeScale;
	}
	
	public static void setCurrentTime(float newTime){
		currentTime = newTime;
	}
	public static void setUnscaledTime(float newTime){
		unscaledTime = newTime;
	}
	
	public static void setMaxDeltaTime(float newMax){
		maxDeltaTime = newMax;
	}
	
	//static void maintainFPS(int fpsmax){
	//	
	//}
	
	public static void ResetTime(){
		currentTime  = 0;
		unscaledTime = 0;
	}
	
	public static void Update(){
		System.nanoTime(); 
		
		
		long timeLong = System.nanoTime();
		float timeFloat = (float)timeLong/1000000000.0f;
	
		unscaledDeltaTime = timeFloat - lastTime;
		if (unscaledDeltaTime > maxDeltaTime)
		{
			unscaledDeltaTime = maxDeltaTime;
		}
		unscaledTime += unscaledDeltaTime;
		
		deltaTime = unscaledDeltaTime*timeScale;
		currentTime += deltaTime;
		unscaledTime += unscaledDeltaTime;

		lastTime = timeFloat;
		
	}
}
