package escape;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.event.IFlxButton;
import org.flixel.event.IFlxCamera;

public class StartButton extends FlxButton {

	private String ImgStartGame = "pack:StartGame";

	public StartButton(float param1, float param2) {
		super(param1, param2);
		scrollFactor.x = scrollFactor.y = 0;
		loadGraphic(this.ImgStartGame, true, true, 150, 26);
		width = 150;
		height = 26;
		addAnimation("off", new int[] { 0 });
		addAnimation("on", new int[] { 1 });

		onUp = new IFlxButton() {

			@Override
			public void callback() {
				FlxG.music.fadeOut(1);
				FlxG.fade(0x000000, 1, new IFlxCamera() {

					@Override
					public void callback() {
						FlxG.switchState(new PlayState());

					}
				});
			}

		};
		onOver = new IFlxButton() {

			@Override
			public void callback() {
				play("on");
			}

		};
		onOut = new IFlxButton() {

			@Override
			public void callback() {
				play("off");
			}

		};
	}
}
