package escape;

public class PilotLaser extends LaserPiece {

	private String ImgPilotLaser = "pack:PilotLaser";

	public PilotLaser(float param1, float param2) {
		super(param1, param2);
		loadGraphic(this.ImgPilotLaser, true, true, 240, 1);

		addAnimation("on", new int[] { 0 });
		addAnimation("off", new int[] { 1 });
		addAnimation("blinking",
				new int[] { 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1 }, 20);

		blend = "add";
		setAlpha(0.3f);
		play("on");
	}

	@Override
	public void update() {
		if (this.state() == LaserGroup.STATE_MOVING) {
			play("off");
		}
	}
}