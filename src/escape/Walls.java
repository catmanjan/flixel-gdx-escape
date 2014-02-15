package escape;

import java.util.Comparator;

import org.flixel.FlxBasic;
import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxSprite;

public class Walls extends FlxGroup {

	public static final int BLOCKS = 3;
	public static final int WALL_HEIGHT = 352;

	private float offset = 240;

	public FlxGroup shockers;

	private float topY;
	private String WallMap = "pack:WallMap";

	public Walls() {
		this(240);
	}

	public Walls(float param1) {
		super();
		FlxSprite sprite = null;
		this.shockers = new FlxGroup();
		this.offset = param1;
		int i = 1;
		while (i <= BLOCKS) {
			sprite = new FlxSprite(0, FlxG.height - WALL_HEIGHT * i
					- this.offset);
			sprite.loadGraphic(this.WallMap, true, true, 240, WALL_HEIGHT);
			add(sprite);
			i++;
		}
		this.topY = -((BLOCKS + 1) * WALL_HEIGHT) + FlxG.height - this.offset;
	}

	@Override
	public void update() {
		members.sort(new Comparator<FlxBasic>() {

			@Override
			public int compare(FlxBasic arg0, FlxBasic arg1) {
				return Float
						.compare(((FlxSprite) arg0).y, ((FlxSprite) arg1).y);
			}

		});

		FlxSprite sprite = null;
		for (FlxBasic member : members) {
			sprite = (FlxSprite) member;
			if (sprite.y > FlxG.camera.scroll.y + FlxG.height) {
				sprite.y = this.topY;
				this.topY = this.topY - WALL_HEIGHT;
			}
		}

		super.update();
	}
}
