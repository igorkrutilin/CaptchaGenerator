import javax.swing.JFrame;

class CaptchaGenerator {
	private int width = 400;
	private int height = 400;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Captcha Generator");
		CaptchaGenerator cg = new CaptchaGenerator();
		frame.setSize(cg.width, cg.height);
		frame.setVisible(true);
		
		DrawingComponent dc = new DrawingComponent();
		frame.add(dc);
	}
	
	public int get_frame_width() {
		CaptchaGenerator cg = new CaptchaGenerator();
		return cg.width;
	}
	
	public int get_frame_height() {
		CaptchaGenerator cg = new CaptchaGenerator();
		return cg.height;
	}
}