package escape;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.event.IFlxButton;
import org.flixel.event.IFlxCamera;

public class RetryButton extends FlxButton {

	private String ImgDefaultButton = "pack:DefaultButton";

	public RetryButton(float param1, float param2) {
		super(param1, param2);
		scrollFactor.x = scrollFactor.y = 0;
		loadGraphic(this.ImgDefaultButton, true, true, 80, 20);
		width = 80;
		height = 20;
		addAnimation("out", new int[] { 0 });
		addAnimation("over", new int[] { 1 });
		addAnimation("down", new int[] { 2 });

		onUp = new IFlxButton() {

			@Override
			public void callback() {
				FlxG.fade(0x000000, 0.5f, new IFlxCamera() {

					@Override
					public void callback() {
						FlxG.switchState(new PlayState());
						GameTracker.score(0);
					}

				});
			}

		};
		onOver = new IFlxButton() {

			@Override
			public void callback() {
				play("over");
			}

		};
		onOut = new IFlxButton() {

			@Override
			public void callback() {
				play("out");
			}

		};
		onDown = new IFlxButton() {

			@Override
			public void callback() {
				play("down");
			}

		};
	}
}
