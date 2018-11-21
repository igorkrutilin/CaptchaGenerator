import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DrawingComponent extends JComponent {
	public void paintComponent(Graphics g) {
		// <initializing>
		Graphics2D g2d = (Graphics2D) g;
		
		Generator generator = new Generator();
		String captcha = generator.generate_word();
		
		CaptchaGenerator captcha_generator = new CaptchaGenerator();
		int frame_width = captcha_generator.get_frame_width();
		int frame_height = captcha_generator.get_frame_height();
		// </inititalizing>
		
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
		char last_char = captcha.charAt(4);
		int last_char_width = g2d.getFontMetrics().stringWidth(String.valueOf(last_char));
		int string_width = 40 + last_char_width;
		
		// displaying string
		int pos_y = frame_height / 2 - 100;
		for (int i = 0; i < captcha.length(); i++) {
			g2d.setColor(generator.generate_color());
			if (i != 4) {
				g2d.setFont(generator.generate_font());
			} else {
				g2d.setFont(new Font("monospaced", Font.ITALIC, 25));
			}
			int pos_x = frame_width / 2 - string_width / 2 + i * 10;
			g2d.drawString(String.valueOf(captcha.charAt(i)), pos_x, pos_y);
		}
	}
}
