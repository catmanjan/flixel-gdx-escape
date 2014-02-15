package escape;

import org.flixel.FlxG;
import org.flixel.FlxPoint;
import org.flixel.FlxSprite;

import escape.custom.Function;

public class Player extends FlxSprite {

	public static final float WALL_LEEWAY = 24;

	private boolean alreadyFell = false;
	public String animation = "";
	private boolean escapePressed = false;

	public Function fallCallback = null;
	private float gravity;
	private String ImgPlayer = "pack:Player";
	public Function jumpCallback = null;

	private String JumpSound = "Jump.mp3";

	private float leftWallRange;
	public boolean mobile = true;
	private float rightWallRange;
	public boolean shocked = false;
	private String ShockFallSound = "ShockFall.mp3";

	private float shockThreshold = 0.5f;
	private float shockTimer = 0;
	private FlxPoint speed;

	public boolean standing = true;

	private String SuperJumpSound = "SuperJump.mp3";
	public boolean superMode = false;

	public Player(float param1, float param2) {
		super(param1, param2);
		this.gravity = PlayState.GRAVITY;
		loadGraphic(this.ImgPlayer, true, true, 16, 20);
		width = 16;
		height = 20;
		offset.x = 0;
		offset.y = 0;
		this.speed = new FlxPoint();
		this.speed.y = PlayState.JUMP_HEIGHT;
		this.speed.x = PlayState.JUMP_SPEED_X;
		acceleration.y = this.gravity;
		addAnimation("slide", new int[] { 5 });
		addAnimation("jump", new int[] { 3 });
		addAnimation("fall", new int[] { 4 });
		addAnimation("idle", new int[] { 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 1, 2 },
				4);
		addAnimation("shock", new int[] { 7, 6 }, 15);
		addAnimation("crisp", new int[] { 6 });
		setFacing(RIGHT);
		maxVelocity.y = 600;
	}

	@Override
	public void play(String param1) {
		play(param1, false);
	}

	@Override
	public void play(String param1, boolean param2) {
		super.play(param1, param2);
		this.animation = param1;
	}

	@Override
	public void update() {
		int i = 0;
		this.leftWallRange = PlayState.WALL_WIDTH;
		this.rightWallRange = FlxG.camera.width - PlayState.WALL_WIDTH - width;
		if (this.shocked) {
			this.shockTimer = this.shockTimer + FlxG.elapsed;
			if (this.shockTimer < this.shockThreshold) {
				FlxG.shake(0.005f, 0.05f);
				velocity.y = velocity.x = acceleration.x = acceleration.y = 0;
				this.play("shock");
			} else {
				if (!this.alreadyFell) {
					FlxG.play(this.ShockFallSound, 0.8f);
					FlxG.shake(0.02f, 0.2f);
					FlxG.flash(0xFFFFFF, 0.1f);
					if (this.fallCallback != null) {
						this.fallCallback.callback();
					}
					this.alreadyFell = true;
				}
				angularVelocity = x < FlxG.width / 2 ? 100 : -100;
				this.play("crisp");
				acceleration.y = this.gravity;
				velocity.x = x < FlxG.width / 2 ? 100 : -100;
			}
		} else {
			if ((GameTracker.escapePressed())
					&& (x <= this.leftWallRange + WALL_LEEWAY || x >= this.rightWallRange
							- WALL_LEEWAY)) {
				this.escapePressed = true;
			}
			if ((this.escapePressed) && velocity.x == 0) {
				if (velocity.y < 0) {
					this.superMode = true;
					FlxG.play(this.SuperJumpSound);
					velocity.y = velocity.y - this.speed.y;
				} else {
					velocity.y = -this.speed.y;
					FlxG.play(this.JumpSound);
					this.superMode = false;
				}
				velocity.x = this.speed.x * (getFacing() == LEFT ? -1 : 1);
				this.escapePressed = false;
				if (this.jumpCallback != null) {
					i = 0;
					while (i < 2) {
						this.jumpCallback.callback();
						i++;
					}
				}
			}
			if (x <= this.leftWallRange && getFacing() == LEFT) {
				velocity.x = 0;
				x = PlayState.WALL_WIDTH;
				setFacing(RIGHT);
			} else {
				if (x >= this.rightWallRange && getFacing() == RIGHT) {
					velocity.x = 0;
					x = FlxG.camera.width - PlayState.WALL_WIDTH - width;
					setFacing(LEFT);
				}
			}
			if (velocity.y > this.gravity * 0.75f) {
				velocity.y = this.gravity * 0.75f;
			}
			if (!GameTracker.escapeHeld() && velocity.y < 0) {
				acceleration.y = this.gravity * 3;
			} else {
				acceleration.y = this.gravity;
			}
			if (this.standing) {
				this.play("idle");
			} else {
				if (velocity.x == 0) {
					this.play("slide");
				} else {
					if (velocity.y < 0) {
						this.play("jump");
					} else {
						this.play("fall");
					}
				}
			}
		}
		super.update();
	}
}
