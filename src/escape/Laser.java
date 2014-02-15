package escape;

public class Laser extends LaserPiece {

	private String ImgLaser = "pack:Laser";

	public Laser(float param1, float param2) {
		super(param1, param2);
		loadGraphic(this.ImgLaser, true, true, 240, 24);
		addAnimation("fire", new int[] { 0, 1 }, 30);
		blend = "add";
		visible = false;
		play("fire");
	}
}
