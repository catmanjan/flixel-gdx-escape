package escape;

import org.flixel.FlxGroup;
import org.flixel.FlxSprite;

public class BackgroundGroup extends FlxGroup {

	private String ImgBg1 = "pack:Bg1";
	private String ImgBg2 = "pack:Bg2";
	private String ImgBg3 = "pack:Bg3";
	private String ImgBg4 = "pack:Bg4";
	private String ImgSky = "pack:Sky";
	private String ImgStartZone = "pack:StartZone";

	public FlxSprite troll;

	public BackgroundGroup() {
		FlxSprite sprite = null;
		sprite = new FlxSprite(0, -1320);
		sprite.loadGraphic(this.ImgSky, true, true, 240, 1600);
		sprite.scrollFactor.y = 0.033f;
		add(sprite);
		sprite = new FlxSprite(0, 28);
		sprite.loadGraphic(this.ImgBg4, true, true, 240, 320);
		sprite.scrollFactor.y = 0.055f;
		add(sprite);
		sprite = new FlxSprite(0, -150);
		sprite.loadGraphic(this.ImgBg3, true, true, 240, 320);
		sprite.scrollFactor.y = 0.1f;
		add(sprite);
		sprite = new FlxSprite(0, -360);
		sprite.loadGraphic(this.ImgBg2, true, true, 240, 640);
		sprite.scrollFactor.y = 0.16f;
		add(sprite);
		this.troll = new FlxSprite(0, 80);
		this.troll.loadGraphic(this.ImgStartZone, true, true, 240, 224);
		this.troll.addAnimation("trolololo", new int[] { 1 });
		add(this.troll);
		sprite = new FlxSprite(0, -2320);
		sprite.loadGraphic(this.ImgBg1, true, true, 240, 2400);
		add(sprite);
	}
}
