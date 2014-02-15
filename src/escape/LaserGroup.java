package escape;

import org.flixel.FlxBasic;
import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxObject;
import org.flixel.FlxPoint;

public class LaserGroup extends FlxGroup {

	public static int state;
	public static final int STATE_BLINKING = 1;
	public static final int STATE_MOVING = 2;

	public static final int STATE_REST = 0;

	private FlxPoint acceleration;
	private float dangerZoneY;
	private LaserGun gunLeft;
	private LaserGun gunRight;

	private Laser laser;
	private String LaserSound = "Laser.mp3";
	private PilotLaser pilot;

	public boolean stopped = false;
	private FlxPoint velocity;

	public LaserGroup() {
		super();
		LaserGroup.state = STATE_REST;
		this.laser = new Laser(0, 16);
		add(this.laser);
		this.pilot = new PilotLaser(0, 46);
		add(this.pilot);
		this.gunLeft = new LaserGun(0, -16, FlxObject.LEFT);
		add(this.gunLeft);
		this.gunRight = new LaserGun(FlxG.width - 32, -16, FlxObject.RIGHT);
		add(this.gunRight);

		for (FlxBasic member : members) {
			LaserPiece piece = (LaserPiece) member;
			piece.state = LaserGroup.state;
		}

		this.velocity = new FlxPoint(0, -19);
		this.acceleration = new FlxPoint(0, -4);
		this.dangerZoneY = this.laser.y + 8;
	}

	public FlxPoint acceleration() {
		return this.acceleration;
	}

	public void acceleration(FlxPoint param1) {
		this.acceleration = param1;
	}

	public int stateCallback() {
		return LaserGroup.state;
	}

	public void trigger() {
		LaserGroup.state = STATE_BLINKING;
	}

	@Override
	public void update() {
		if (LaserGroup.state == STATE_MOVING) {
			if (!this.stopped) {
				for (FlxBasic member : members) {
					LaserPiece peiece = (LaserPiece) member;
					peiece.acceleration.y = this.acceleration.y;
					peiece.maxVelocity.y = 150;
					if (peiece.velocity.y == 0) {
						peiece.velocity.y = this.velocity().y;
					}
				}
			} else {
				for (FlxBasic member : members) {
					LaserPiece piece = (LaserPiece) member;
					piece.acceleration.y = 0;
					piece.maxVelocity.y = 0;
					piece.velocity.y = 0;
				}
			}

			this.dangerZoneY = this.laser.y + 8;

			if (this.dangerZoneY > FlxG.camera.scroll.y + FlxG.height + 48) {
				y(FlxG.camera.scroll.y + FlxG.height + 47);
			}

			if (!this.laser.visible) {
				FlxG.shake(0.01f, 0.5f);
				FlxG.play(this.LaserSound);
			}

			this.laser.visible = true;
		}
		if (LaserGroup.state == STATE_BLINKING) {
			this.pilot.play("blinking");
			if (this.pilot.finished) {
				LaserGroup.state = STATE_MOVING;
			}
		}

		super.update();
	}

	public FlxPoint velocity() {
		return this.velocity;
	}

	public void velocity(FlxPoint param1) {
		this.velocity = param1;
	}

	public float y() {
		return this.dangerZoneY;
	}

	public void y(float param1) {
		for (FlxBasic member : members) {
			LaserPiece piece = (LaserPiece) member;
			piece.y = piece.y + (param1 - this.dangerZoneY);
		}

		this.dangerZoneY = param1;
	}
}
