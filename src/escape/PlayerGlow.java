package escape;

import org.flixel.FlxG;
import org.flixel.FlxSprite;

public class PlayerGlow extends FlxSprite {

	public float alphaRate = 0.75f;
	private String ImgPlayer = "pack:Player";

	public float ttl = 1;

	public PlayerGlow() {
		super();
		loadGraphic(this.ImgPlayer, true, true, 16, 20);
		// blend = "add";
		addAnimation("slide", new int[] { 5 });
		addAnimation("jump", new int[] { 3 });
		addAnimation("fall", new int[] { 4 });
		addAnimation("idle", new int[] { 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 1, 2 },
				4);
		addAnimation("shock", new int[] { 7, 6 }, 15);
		addAnimation("crisp", new int[] { 6 });
	}

	@Override
	public void update() {
		super.update();

		float alpha = getAlpha() - FlxG.elapsed / this.alphaRate;
		setAlpha(alpha);
	}
}
