package escape;

import org.flixel.FlxBasic;
import org.flixel.FlxEmitter;
import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxObject;
import org.flixel.FlxRect;
import org.flixel.FlxState;
import org.flixel.FlxText;

import escape.custom.Function;

public class PlayState extends FlxState {

	public static final float GRAVITY = 600;
	public static final float JUMP_HEIGHT = 400;
	public static final float JUMP_SPEED_X = 400;
	public static final float SAW_MAX = 75;
	public static final float SAW_SPEED = 3.5f;
	public static final float SUPER_MODE_DISTANCE = 38;
	public static final float WALL_WIDTH = 32;

	private BackgroundGroup backgroundGroup;
	private FlxRect bottomedBounds;
	private FlxRect bottomlessBounds;

	private String DeathSound = "Death.mp3";
	public FlxText debugText;

	private FloorSprite floor;
	private boolean gameOver = false;
	private FlxGroup glowGroup;
	LeaderboardButton highscoreButton;

	FlxText highscoreText1;
	FlxText highscoreText2;
	private LaserGroup laserGroup;
	private ShockerGroup leftShockers;

	private Walls leftWalls;
	private Player player;

	private float playerOffset = 284;

	private String PlayMusic = "Play.mp3";

	RetryButton retryButton;
	FlxText retryText1;

	FlxText retryText2;
	private ShockerGroup rightShockers;
	private Walls rightWalls;

	private FlxText scoreText;
	private String ShockSound = "Shock.mp3";
	private FlxEmitter sparksEmitter;

	public PlayState() {
		super();
	}

	private void checkShocked(ShockerGroup param1) {
		Shocker shocker = null;
		if (!this.player.shocked) {
			for (FlxBasic member : param1.members) {
				shocker = (Shocker) member;
				if (this.player.y > shocker.y - this.player.height + 2
						&& this.player.y < shocker.y + shocker.height - 3) {
					this.player.shocked = true;
					FlxG.play(this.ShockSound);
					break;
				}
			}
		}
	}

	@Override
	public void create() {
		FlxG.mouse.hide();
		FlxG.camera.scroll.y = -16;
		this.backgroundGroup = new BackgroundGroup();
		add(this.backgroundGroup);
		this.leftWalls = new Walls();
		add(this.leftWalls);
		this.rightWalls = new Walls();
		add(this.rightWalls);
		this.leftShockers = new ShockerGroup(FlxObject.LEFT);
		add(this.leftShockers);
		this.rightShockers = new ShockerGroup(FlxObject.RIGHT);
		add(this.rightShockers);
		this.floor = new FloorSprite(0, this.playerOffset + 20);
		add(this.floor);
		this.glowGroup = new FlxGroup();
		this.glowGroup.setMaxSize(30);
		add(this.glowGroup);
		this.player = new Player(WALL_WIDTH, this.playerOffset);
		this.player.jumpCallback = new Function() {

			@Override
			public void callback() {
				createGlow();
			}

		};
		this.player.fallCallback = new Function() {

			@Override
			public void callback() {
				createSparks();
			}

		};
		add(this.player);
		this.laserGroup = new LaserGroup();
		add(this.laserGroup);
		this.scoreText = new FlxText(0, 16, FlxG.width, GameTracker.score()
				+ "m");
		this.scoreText.setAlignment("center");
		this.scoreText.scrollFactor.x = this.scoreText.scrollFactor.y = 0;
		this.scoreText.setShadow(0xFFFFFF);
		add(this.scoreText);
		this.bottomlessBounds = new FlxRect(0, FlxG.height / 2, 240,
				Float.MAX_VALUE);
		this.bottomedBounds = new FlxRect(0, FlxG.height / 2, 240,
				FlxG.height / 2);
		FlxG.camera.bounds = new FlxRect(0, -2000000000, 240,
				2000000000 + FlxG.height + 16);
		FlxG.camera.follow(this.player);
		FlxG.camera.deadzone = this.bottomedBounds;

		highscoreButton = new LeaderboardButton(80, 270);
		add(highscoreButton);
		highscoreText1 = new FlxText(0, 270 + 2, FlxG.width, "Leaderboard");
		highscoreText1.setAlignment("center");
		highscoreText1.setColor(0x000000);
		highscoreText1.setSize(24);
		highscoreText1.scrollFactor.x = highscoreText1.scrollFactor.y = 0;
		add(highscoreText1);
		highscoreText2 = new FlxText(-1, 270 + 2 - 1, FlxG.width, "Leaderboard");
		highscoreText2.setAlignment("center");
		highscoreText2.setSize(24);
		highscoreText2.scrollFactor.x = highscoreText2.scrollFactor.y = 0;
		add(highscoreText2);

		highscoreButton.visible = false;
		highscoreText1.visible = false;
		highscoreText2.visible = false;

		retryButton = new RetryButton(80, 235);
		add(retryButton);
		retryText1 = new FlxText(0, 235 + 2, FlxG.width, "Retry");
		retryText1.setAlignment("center");
		retryText1.setColor(0x000000);
		retryText1.setSize(24);
		retryText1.scrollFactor.x = retryText1.scrollFactor.y = 0;
		add(retryText1);
		retryText2 = new FlxText(-1, 235 + 2 - 1, FlxG.width, "Retry");
		retryText2.setAlignment("center");
		retryText2.setSize(24);
		retryText2.scrollFactor.x = retryText2.scrollFactor.y = 0;
		add(retryText2);

		retryButton.visible = false;
		retryText1.visible = false;
		retryText2.visible = false;
	}

	public void createGlow() {
		createGlow(0.75f);
	}

	public void createGlow(float param1) {
		PlayerGlow _loc2_ = (PlayerGlow) this.glowGroup
				.recycle(PlayerGlow.class);
		_loc2_.x = this.player.x;
		_loc2_.y = this.player.y;
		_loc2_.setAlpha(0.5f);
		_loc2_.setFacing(this.player.getFacing());
		_loc2_.ttl = 1;
		_loc2_.alphaRate = param1;
		_loc2_.play(this.player.animation);
	}

	public void createSparks() {
		ShockParticle _loc2_ = null;
		this.sparksEmitter = new FlxEmitter();
		for (int i = 0; i < 10; i++) {
			_loc2_ = new ShockParticle();
			this.sparksEmitter.add(_loc2_);
		}
		this.sparksEmitter.at(this.player);
		this.sparksEmitter.gravity = GRAVITY * 0.8f;
		if (this.player.x < FlxG.width / 2) {
			this.sparksEmitter.setXSpeed(50, 200);
		} else {
			this.sparksEmitter.setXSpeed(-200, -50);
		}
		this.sparksEmitter.setYSpeed(-200, -80);
		add(this.sparksEmitter);
		this.sparksEmitter.start();
	}

	private void die() {
		FlxG.shake(0.005f, 0.05f);
		GibParticle gib = null;
		this.gameOver = true;
		FlxG.play(this.DeathSound);
		FlxEmitter emitter = new FlxEmitter();
		for (int i = 0; i < 10; i++) {
			gib = new GibParticle();
			emitter.add(gib);
		}
		emitter.bounce = 1;
		emitter.gravity = GRAVITY;
		emitter.at(this.player);
		add(emitter);
		emitter.start();
		emitter.setYSpeed(-400, -200);
		this.player.exists = false;
		GameOverGroup _loc3_ = new GameOverGroup();
		add(_loc3_);
		remove(this.player);
		remove(this.scoreText);
		FlxG.mouse.show();

		highscoreButton.visible = true;
		highscoreText1.visible = true;
		highscoreText2.visible = true;
		retryButton.visible = true;
		retryText1.visible = true;
		retryText2.visible = true;

		if (EscapeGame.leaderboard != null) {
			if (EscapeGame.leaderboard.isConnected()) {
				EscapeGame.leaderboard.highscore(GameTracker.score());
			}
		}
	}

	public Player player() {
		return this.player;
	}

	@Override
	public void update() {
		super.update();

		if (!this.gameOver) {
			if (FlxG.collide(this.player, this.floor)) {
				this.player.standing = true;
			} else {
				this.player.standing = false;
			}
			if (LaserGroup.state == LaserGroup.STATE_REST
					&& this.player.y <= 46) {
				this.laserGroup.trigger();
				this.backgroundGroup.troll.play("trolololo");
			}
			if (this.player.exists) {
				if (this.player.y > this.laserGroup.y()) {
					this.laserGroup.stopped = true;
				} else {
					this.laserGroup.stopped = false;
				}
			}
			if (LaserGroup.state == LaserGroup.STATE_MOVING) {
				if (!GameTracker.playedMusic()) {
					FlxG.playMusic(this.PlayMusic, 1f, true);
					GameTracker.playedMusic(true);
				}
				if (FlxG.camera.deadzone.height < Float.MAX_VALUE
						&& this.player.y < this.laserGroup.y()) {
					FlxG.camera.deadzone = this.bottomlessBounds;
				}
			}
			if (!this.gameOver
					&& this.laserGroup.stateCallback() == LaserGroup.STATE_MOVING
					&& this.laserGroup.y() < this.player.y + this.player.height
					&& this.player.y < this.laserGroup.y() + 8) {
				this.die();
			}
			if (this.player.x <= WALL_WIDTH) {
				this.checkShocked(this.leftShockers);
				this.player.x = WALL_WIDTH;
			} else {
				if (this.player.x >= FlxG.width - WALL_WIDTH
						- this.player.width) {
					this.checkShocked(this.rightShockers);
					this.player.x = FlxG.width - WALL_WIDTH - this.player.width;
				}
			}
			if ((this.player.superMode) && !this.gameOver
					&& !this.player.shocked) {
				this.createGlow();
			}
			if ((FlxG.collide(this.player, this.leftShockers))
					|| (FlxG.overlap(this.player, this.rightShockers))) {
				this.player.shocked = true;
			}
			if (this.player.y - this.playerOffset < -GameTracker.score() * 20) {
				GameTracker
						.score((int) (-(this.player.y - this.playerOffset) / 20));
				this.scoreText.setText(GameTracker.score() + "m");
			}
		}
	}
}
