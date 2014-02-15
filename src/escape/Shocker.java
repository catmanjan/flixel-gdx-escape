package escape;

import org.flixel.FlxSprite;

public class Shocker extends FlxSprite {

	private String ImgShocker = "pack:Shocker";

	public int side;

	public Shocker() {
		super(0, 0);
		loadGraphic(this.ImgShocker, true, true, 36, 16);
		addAnimation("left", new int[] { 0 });
		addAnimation("right", new int[] { 1 });
		width = 36;
		height = 16;
	}

	@Override
	public void update() {
		if (this.side == LEFT) {
			play("left");
		} else {
			play("right");
		}
		super.update();
	}
}
