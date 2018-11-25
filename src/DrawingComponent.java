import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class DrawingComponent extends JComponent {
	public String captcha;
	public BufferedImage buffered_image;
	
	Generator generator = new Generator();
	
	private List<String> captchas = new ArrayList<String>();
	
	DrawingComponent(String captcha) {
		this.captcha = captcha;
	}
	
	public void paintComponent(Graphics g) {
		// <initializing>
		Graphics2D g2d = (Graphics2D) g;
		
		int image_width = 200;
		int image_height = 50;
		this.buffered_image = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d_bi = this.buffered_image.createGraphics();
		
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
			// doing the same for image
			g2d_bi.setColor(new Color(255, 255, 255));
			g2d_bi.fillRect(0, 0, image_width, image_height);
			
			// displaying string
			int pos_y = frame_height / 2 - 100;
			int pos_y_bi = image_height / 2;
			for (int i = 0; i < this.captcha.length(); i++) {
				Color color = generator.generate_color(); // we will set the same color both for app and for image
				g2d.setColor(color);
				g2d_bi.setColor(color);
				if (i != 4) {
					Font font = generator.generate_font(); // we will set the same font both for app and for image
					g2d.setFont(font);
					g2d_bi.setFont(font);
				} else {
					g2d.setFont(new Font("monospaced", Font.ITALIC, 25));
					g2d_bi.setFont(new Font("monospaced", Font.ITALIC, 25));
				}
				int pos_x = frame_width / 2 - string_width / 2 + i * 10;
				int pos_x_bi = image_width / 2 - string_width / 2 + i * 10;
				g2d.drawString(String.valueOf(this.captcha.charAt(i)), pos_x, pos_y);
				g2d_bi.drawString(String.valueOf(this.captcha.charAt(i)), pos_x_bi, pos_y_bi);
			}
		}
	}
	
	public void download() throws IOException {
		File file = new File(generator.generate_filename());
		ImageIO.write(buffered_image, "png", file);
	}
}
