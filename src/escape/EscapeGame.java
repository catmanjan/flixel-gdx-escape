package escape;

import org.flixel.FlxG;
import org.flixel.FlxGame;

import escape.custom.ILeaderboard;

public class EscapeGame extends FlxGame {

	public static ILeaderboard leaderboard;

	public EscapeGame() {
		super(240, 320, MenuState.class, 2);
		FlxG.level = 0;
	}

	public void setLeaderboard(ILeaderboard leaderboard2) {
		EscapeGame.leaderboard = leaderboard2;
	}
}
