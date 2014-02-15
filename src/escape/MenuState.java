package escape;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;

public class MenuState extends FlxState {

	private float _cameraVelocity = 20;
	private BackgroundGroup background;

	private String ImgTitle = "pack:Title";

	private String TitleMusic = "Intro.mp3";

	public MenuState() {
		super();
	}

	@Override
	public void create() {
		FlxG.mouse.show();

		this.background = new BackgroundGroup();
		add(this.background);

		Walls walls = new Walls(7500);
		add(walls);

		FlxG.camera.scroll.y = -8000;

		FlxSprite sprite = new FlxSprite(0, 0);
		sprite.loadGraphic(this.ImgTitle, true, true, 240, 320);
		sprite.scrollFactor.x = sprite.scrollFactor.y = 0;
		add(sprite);

		FlxButton startButton = new StartButton(45, 202);
		add(startButton);
		LeaderboardButton highscoreButton = new LeaderboardButton(80, 270);
		add(highscoreButton);
		FlxText text = new FlxText(0, 270 + 2, FlxG.width, "Leaderboard");
		text.setAlignment("center");
		text.setColor(0x000000);
		text.setSize(24);
		text.scrollFactor.x = text.scrollFactor.y = 0;
		add(text);
		text = new FlxText(-1, 270 + 2 - 1, FlxG.width, "Leaderboard");
		text.setAlignment("center");
		text.setSize(24);
		text.scrollFactor.x = text.scrollFactor.y = 0;
		add(text);

		FlxG.playMusic(this.TitleMusic);
	}

	@Override
	public void update() {
		FlxG.camera.scroll.y = FlxG.camera.scroll.y - FlxG.elapsed
				* this._cameraVelocity;
		super.update();
	}
}
