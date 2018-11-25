import java.awt.Color;
import java.awt.Font;
import java.util.Random;

class Generator {
	private final String alphabet = "qwertyuiopasdfghjklzxcvbnm";
	private final String symbols = alphabet + "1234567890";
	private final Random random = new Random();
	
	public String generate_word() {	
		String word = "";
		for (int i = 1; i <= 5; i++) {
			char letter = alphabet.charAt(random.nextInt(alphabet.length() - 1));
			word += String.valueOf(letter);
		}
		return word;
	}
	
	public Font generate_font() {
		String[] families = {"serif", "sanserif", "monospaced"};
		String familie = families[random.nextInt(families.length - 1)];
		int[] styles = {Font.PLAIN, Font.ITALIC, Font.BOLD};
		int style = styles[random.nextInt(styles.length - 1)];
		int size = random.nextInt(10) + 20;
		Font font = new Font(familie, style, size);
		return font;
	}
	
	public Color generate_color() {	
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		Color color = new Color(r, g, b);
		return color;
	}
	
	public String generate_filename() {
		String filename = "";
		for (var i = 0; i < 15; i++) {
			char symbol = symbols.charAt(random.nextInt(symbols.length() - 1));
			filename += String.valueOf(symbol);
		}
		return filename + ".png";
	}
}