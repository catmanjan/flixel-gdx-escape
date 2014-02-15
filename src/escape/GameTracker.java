package escape;

import org.flixel.FlxG;
import org.flixel.FlxSave;

public class GameTracker extends Object {

	private static GameTracker instance = null;

	public static boolean escapeHeld() {
		return FlxG.keys.ESCAPE || FlxG.mouse.pressed();
	}

	public static boolean escapePressed() {
		return FlxG.keys.justPressed("ESCAPE") || FlxG.mouse.justPressed();
	}

	public static int highScore() {
		return instance().highScore;
	}

	private static GameTracker instance() {
		if (instance == null) {
			instance = new GameTracker();
			instance.score = 0;
			instance.save = new FlxSave();
			instance.save.bind("escape-game");
			if (instance.save.data.get("highScore", Integer.TYPE) != null) {
				instance.highScore = instance.save.data.get("highScore",
						Integer.TYPE);
			} else {
				instance.highScore = 0;
			}
		}

		return instance;
	}

	public static boolean playedMusic() {
		return instance().playedMusic;
	}

	public static void playedMusic(boolean param1) {
		instance().playedMusic = param1;
	}

	public static int score() {
		return instance().score;
	}

	public static void score(int param1) {
		instance().score = param1;
		if (instance().score > instance().highScore) {
			instance().highScore = instance().score;
			instance().save.data.put("highScore", instance().score);
		}
	}

	public int highScore;

	public boolean playedMusic;

	public FlxSave save;

	public int score;

	public GameTracker() {
		super();
	}
}