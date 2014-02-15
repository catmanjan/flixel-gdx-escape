package escape;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxSprite;
import org.flixel.FlxText;

public class GameOverGroup extends FlxGroup {

	private String ImgGameOver = "pack:GameOver";
	private String ImgNewRecord = "pack:NewRecord";

	public GameOverGroup() {
		super();
		FlxSprite sprite = null;
		FlxText text = null;

		sprite = new FlxSprite(0, 0);
		sprite.scrollFactor.x = sprite.scrollFactor.y = 0;
		sprite.loadGraphic(this.ImgGameOver, true, true, 240, 320);
		add(sprite);
		text = new FlxText(0, 120, FlxG.width, GameTracker.score() + "m");
		text.setAlignment("center");
		text.setColor(0x000000);
		text.setSize(24);
		text.scrollFactor.x = text.scrollFactor.y = 0;
		add(text);
		text = new FlxText(-3, 117, FlxG.width, GameTracker.score() + "m");
		text.setAlignment("center");
		text.setSize(24);
		text.scrollFactor.x = text.scrollFactor.y = 0;
		add(text);
		text = new FlxText(0, 168, FlxG.width, GameTracker.highScore()
				+ "m");
		text.setAlignment("center");
		text.setSize(16);
		text.setColor(0x000000);
		text.scrollFactor.x = text.scrollFactor.y = 0;
		add(text);
		text = new FlxText(-2, 166, FlxG.width, GameTracker.highScore()
				+ "m");
		text.setAlignment("center");
		text.setSize(16);
		text.scrollFactor.x = text.scrollFactor.y = 0;
		add(text);

		if (GameTracker.score() >= GameTracker.highScore()) {
			sprite = new FlxSprite(38, 200);
			sprite.scrollFactor.x = sprite.scrollFactor.y = 0;
			sprite.loadGraphic(this.ImgNewRecord, true, true, 166, 16);
			sprite.addAnimation("flash", new int[] { 0, 1 }, 15);
			sprite.play("flash");
			add(sprite);
		}
	}
}
