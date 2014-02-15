package escape;

import org.flixel.FlxSprite;

public class LaserPiece extends FlxSprite {

	public int state;

	public LaserPiece(float param1, float param2) {
		super(param1, param2);
	}

	public int state() {
		return LaserGroup.state;
	}
}
