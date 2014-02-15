package escape;

import org.flixel.FlxParticle;

public class GibParticle extends FlxParticle {

	private String ImgGibs = "pack:Gibs";

	public GibParticle() {
		super();
		loadGraphic(this.ImgGibs, true, true, 8, 8);
		exists = false;
		antialiasing = false;
		setFrame((int) Math.random() * 5);
	}
}