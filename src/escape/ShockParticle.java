package escape;

import org.flixel.FlxG;
import org.flixel.FlxParticle;

public class ShockParticle extends FlxParticle {

	public float fadeRate = 1;

	private String ImgSpark = "pack:Spark";

	public ShockParticle() {
		super();
		loadGraphic(this.ImgSpark, true, true, 3, 3);
		exists = false;
		antialiasing = false;
		blend = "add";
		angularVelocity = 0;
	}

	@Override
	public void update() {
		angularVelocity = 0;
		angle = 0;
		setAlpha(getAlpha() - FlxG.elapsed / this.fadeRate);
		super.update();
	}
}
