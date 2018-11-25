import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class CaptchaGenerator {
	public final int WIDTH = 400;
	public final int HEIGHT = 400;
	
	public DrawingComponent dc;
	
	public String captcha;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Captcha Generator");
		frame.setResizable(false);
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
				cg.dc = new DrawingComponent(cg.captcha);
				frame.add(cg.dc);
				frame.setVisible(true);
				input_captcha.setText("");
			}
		});
		
		JButton btn_download = new JButton("Download");
		btn_download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cg.dc.download();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
			}
		});
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		Box horizontal_box = Box.createHorizontalBox();
		horizontal_box.add(input_captcha);
		horizontal_box.add(btn_submit);
		horizontal_box.add(btn_new_captcha);
		horizontal_box.add(btn_download);
		content.add(horizontal_box, BorderLayout.SOUTH);
		
		frame.setContentPane(content);
		frame.setVisible(true);
		
		cg.captcha = generator.generate_word();
		cg.dc = new DrawingComponent(cg.captcha);
		frame.add(cg.dc);
	}
}