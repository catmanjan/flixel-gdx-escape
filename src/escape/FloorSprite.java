package escape;

import org.flixel.FlxSprite;

public class FloorSprite extends FlxSprite {

	private String ImgFloor = "pack:Floor";

	public FloorSprite(float param1, float param2) {
		super(param1, param2);
		loadGraphic(this.ImgFloor, true, true, 240, 16);
		immovable = true;
	}
}
