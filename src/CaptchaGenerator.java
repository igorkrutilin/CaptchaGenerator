import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class CaptchaGenerator {
	public final int WIDTH = 400;
	public final int HEIGHT = 400;
	
	public String captcha;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Captcha Generator");
		CaptchaGenerator cg = new CaptchaGenerator();
		frame.setSize(cg.WIDTH, cg.HEIGHT);
		
		Generator generator = new Generator();
		
		JTextField input_captcha = new JTextField();
		input_captcha.setMaximumSize(new Dimension(400, 100));
		
		JButton btn_submit = new JButton("Submit");
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (input_captcha.getText().equals(cg.captcha)) {
					JOptionPane.showMessageDialog(null, "Correct.");
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect.");
				}
			}
		});
		
		JButton btn_new_captcha = new JButton("New captcha");
		btn_new_captcha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cg.captcha = generator.generate_word();
				// after each click we create new DrawingComponent; otherwise, we will have the same captcha
				DrawingComponent dc = new DrawingComponent(cg.captcha);
				frame.add(dc);
				frame.setVisible(true);
				input_captcha.setText("");
			}
		});
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		Box horizontal_box = Box.createHorizontalBox();
		horizontal_box.add(input_captcha);
		horizontal_box.add(btn_submit);
		horizontal_box.add(btn_new_captcha);
		content.add(horizontal_box, BorderLayout.SOUTH);
		
		frame.setContentPane(content);
		frame.setVisible(true);
		
		cg.captcha = generator.generate_word();
		DrawingComponent dc = new DrawingComponent(cg.captcha);
		frame.add(dc);
	}
}