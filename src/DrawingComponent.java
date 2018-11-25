import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class DrawingComponent extends JComponent {
	public String captcha;
	
	private List<String> captchas = new ArrayList<String>();
	
	DrawingComponent(String captcha) {
		this.captcha = captcha;
	}
	
	public void paintComponent(Graphics g) {
		// <initializing>
		Graphics2D g2d = (Graphics2D) g;
		
		Generator generator = new Generator();
		
		CaptchaGenerator captcha_generator = new CaptchaGenerator();		
		int frame_width = captcha_generator.WIDTH;
		int frame_height = captcha_generator.HEIGHT;
		// </initializing>
		
		// without this if statement we will have the same captcha in different fonts and colors all the time
		if (!this.captchas.contains(this.captcha)) {
			this.captchas.add(this.captcha);
			
			/*
			 * we need to know the width of our string to display it in the center of our frame
			 * 
			 * position of new letter = position of previous letter + 10 (position of the first letter is constant)
			 * we have 5 letters so, string width = 40 + width of last char
			 * so we can draw 4 chars with random width and 1 with constant width
			 * for this we need to have the same font for every last letter in every captcha we generate
			 */
			
			// getting string width to calculate captchas position on X axis
			g2d.setFont(new Font("monospaced", Font.ITALIC, 25));
			char last_char = this.captcha.charAt(4);
			int last_char_width = g2d.getFontMetrics().stringWidth(String.valueOf(last_char));
			int string_width = 40 + last_char_width;
			
			/*
			 * when we want to regenerate captcha for x times, there will be x captchas displayed at once
			 * so, before drawing new one, we fill our screen with white color
			 * that will hide previous captchas
			 */
			
			// filling screen
			g2d.setColor(new Color(255, 255, 255));
			g2d.fillRect(0, 0, frame_width, frame_height);
			
			// displaying string
			int pos_y = frame_height / 2 - 100;
			for (int i = 0; i < this.captcha.length(); i++) {
				g2d.setColor(generator.generate_color());
				if (i != 4) {
					g2d.setFont(generator.generate_font());
				} else {
					g2d.setFont(new Font("monospaced", Font.ITALIC, 25));
				}
				int pos_x = frame_width / 2 - string_width / 2 + i * 10;
				g2d.drawString(String.valueOf(this.captcha.charAt(i)), pos_x, pos_y);
			}
		}
	}
}
