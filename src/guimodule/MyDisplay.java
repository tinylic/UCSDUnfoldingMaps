package guimodule;

import processing.core.PApplet;

public class MyDisplay extends PApplet{

		public void setup() {
			size(400, 400);
			background(255, 255, 255);
		}
		
		public void draw() {
			int[] color = ColorSec(second());
			fill(color[0], color[1], color[2]);
			ellipse(200, 200, 390, 390);
			fill(0, 0, 0);
			ellipse(120, 130, 50, 70);
			ellipse(280, 130, 50, 70);
			noFill();
			arc(200, 280, 100, 150, (float)(PI * 0.25), (float)(PI * 0.75));
		}
		public int[] ColorSec(float Seconds) {
			float distance = Math.abs(30 - Seconds);
			float ratio = distance / 30;
			int[] rgb = new int[3];
			rgb[0] = rgb[1] = (int)(255 * ratio);
			rgb[2] = 0;
			return rgb;
			
		}
}
