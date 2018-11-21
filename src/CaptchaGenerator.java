import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CaptchaGenerator {
	public final int WIDTH = 400;
	public final int HEIGHT = 400;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Captcha Generator");
		CaptchaGenerator cg = new CaptchaGenerator();
		frame.setSize(cg.WIDTH, cg.HEIGHT);
		
		JButton btn_new_captcha = new JButton("New captcha");
		btn_new_captcha.setPreferredSize(new Dimension(cg.WIDTH, 50));
		btn_new_captcha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// after each click we create new DrawingComponent; otherwise, we will have the same captcha
				DrawingComponent dc = new DrawingComponent();
				frame.add(dc);
				frame.setVisible(true);
			}
		});
		
		JPanel content = new JPanel(new BorderLayout());
		content.add(btn_new_captcha, BorderLayout.PAGE_END);
		
		frame.setContentPane(content);
		frame.setVisible(true);

		DrawingComponent dc = new DrawingComponent();
		frame.add(dc);
	}
}