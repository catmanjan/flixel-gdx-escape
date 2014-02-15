package escape;

import org.flixel.FlxG;
import org.flixel.FlxObject;
import org.flixel.event.IFlxAnim;

public class LaserGun extends LaserPiece {

	private String BeepSound = "Beep.mp3";
	private String ImgLaserGun = "pack:LaserGun";

	private float twoPi = 6.283185307179586f;

	public LaserGun(float param1, float param2, int param3) {
		super(param1, param2);
		int i = 0;
		int side = param3;
		loadGraphic(this.ImgLaserGun, true, true, 32, 96);

		int[] atRest = new int[] { 0 };
		int[] blinking = new int[] { 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 };
		int[] moving = new int[] { 1, 2, 3, 4 };

		if (side == FlxObject.RIGHT) {
			for (i = 0; i < atRest.length; i++) {
				atRest[i] += 5;
			}
			for (i = 0; i < blinking.length; i++) {
				blinking[i] += 5;
			}
			for (i = 0; i < moving.length; i++) {
				moving[i] += 5;
			}
		}

		addAnimation("atRest", atRest, 0);
		addAnimation("blinking", blinking, 20);
		addAnimation("moving", moving, 30);
		addAnimationCallback(new IFlxAnim() {

			@Override
			public void callback(String CurAnim, int CurFrame, int CurIndex) {
				if (CurAnim.equals("blinking") && CurIndex == 1) {
					FlxG.play(BeepSound, 0.5f);
				}
			}

		});

		play("atRest");
	}

	@Override
	public void update() {
		if (state() == LaserGroup.STATE_BLINKING) {
			play("blinking");
		} else {
			if (state() == LaserGroup.STATE_MOVING) {
				play("moving");
				_curAnim.delay = -(this.twoPi / velocity.y);
			}
		}
	}
}
