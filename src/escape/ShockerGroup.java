package escape;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxObject;

public class ShockerGroup extends FlxGroup {

	public static final float SHOCKER_WIDTH = 20;

	private float clusterMax = 3;

	private float cluterMin = 2;
	private float lastPlacedAt = 1000;
	private float maxDistance = 320;
	private float minDistance = 128;
	private float prevY = 0;
	private float probability = 0.005f;
	private int side;
	private float topY = 0;

	public ShockerGroup(int param1) {
		super();
		this.side = param1;
		setMaxSize(50);
		this.topY = -320;
	}

	@Override
	public void update() {
		if (GameTracker.score() > 500) {
			this.probability = 0.02f;
			this.minDistance = 48;
		} else {
			if (GameTracker.score() > 400) {
				this.probability = 0.02f;
				this.minDistance = 64;
				this.maxDistance = 128;
			} else {
				if (GameTracker.score() > 300) {
					this.cluterMin = 3;
					this.probability = 0.0175f;
					this.minDistance = 64;
					this.maxDistance = 160;
				} else {
					if (GameTracker.score() > 200) {
						this.probability = 0.015f;
						this.minDistance = 92;
						this.maxDistance = 192;
					} else {
						if (GameTracker.score() > 100) {
							this.clusterMax = 4;
							this.minDistance = 112;
						}
					}
				}
			}
		}

		if (FlxG.camera.scroll.y < this.topY) {
			this.topY = FlxG.camera.scroll.y;
		}

		if (FlxG.camera.scroll.y < this.prevY) {
			this.prevY = FlxG.camera.scroll.y;
			float rng = (float) Math.random();
			if (rng < this.probability
					|| this.topY - this.lastPlacedAt <= -this.maxDistance) {
				float rng2 = (float) Math.random();
				rng2 = rng2 * this.clusterMax;
				if (rng2 < this.cluterMin) {
					rng2 = this.cluterMin;
				}

				int i = 0;
				while (i < (int) rng2) {
					Shocker shocker = (Shocker) this.recycle(Shocker.class);
					shocker.x = this.side == FlxObject.LEFT ? 0 : FlxG.width
							- SHOCKER_WIDTH - 16;
					shocker.y = this.topY - 16;
					if (this.side == FlxObject.LEFT) {
						shocker.y = shocker.y - 32;
					}
					shocker.side = this.side;
					this.lastPlacedAt = shocker.y;
					this.topY = this.topY - 16;
					i++;
				}

				this.topY = this.topY - this.minDistance;
			}
		}
		super.update();
	}
}
