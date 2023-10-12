package chatbot.project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatbotUsingJswing extends JFrame {
	private JTextArea textArea = new JTextArea();
	private JTextField textField = new JTextField();
	private JButton button = new JButton("Submit");
	private ImageIcon image = new ImageIcon("logo.png");
	
	ChatbotUsingJswing() {
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(700, 820);
		frame.getContentPane().setBackground(new Color(31, 65, 114));
		frame.setTitle("ChatBot");
		
		JLabel label=new JLabel(image);
		label.setBounds(230, 5, image.getIconWidth(), image.getIconHeight());
		frame.add(label);
		System.out.println( "height " + image.getIconHeight() + " width " + image.getIconWidth());
		
		JLabel welcomeLabel = new JLabel("Welcome to Chatbot, Ask me something.");
        welcomeLabel.setFont(new Font("Rockwell", Font.PLAIN, 25));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setBounds(10, 271, 675, 40); // Adjust the dimensions as needed
        frame.add(welcomeLabel);

		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Rockwell", Font.PLAIN, 25));
		frame.add(textArea);

		
//
		button.setSize(120, 40);
		button.setLocation(560, 730);
		button.setFont(new Font("Rockwell", Font.PLAIN, 15));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setFocusable(false);
		frame.add(button);

		frame.add(textField);
		textField.setSize(550, 40);
		textField.setLocation(5, 730);
		textField.setFont(new Font("Rockwell", Font.PLAIN, 25));
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

		frame.setIconImage(image.getImage());

		JScrollPane sp = new JScrollPane(textArea);
		sp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(5, 266, 675, 450);

		frame.add(sp);

		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				button.setBackground(new Color(241, 180, 187));
				button.setBorder(BorderFactory.createLineBorder(new Color(241, 180, 187), 3, true));
			}

			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(253,240,240));
			}
		});
		
		textField.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // This code will execute when the Enter key is pressed in the JTextField.
		        // It simulates a button click.
		        button.doClick();
		    }
		});

		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {
					welcomeLabel.setVisible(false);
					String text = textField.getText();
					textArea.append("You: " + text + "\n");
					textField.setText("");
					if (text.equals("")) {
						replyMethod("Provide some statement");

					} else {
						if (isMathematicalExpression(text)) {
							double result = 0.0;
							try {
								result = evaluate(text);
							} catch (IOException e1) {

								e1.printStackTrace();
							}
							String s = "The result is: " + Double.toString(result);
							replyMethod(s);
						} else {
							String answer = null;
							try {
								answer = getAnsFromDb(text);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							if (answer != null) {
								replyMethod(answer);
							} else {
								replyMethod(
										"Sorry, I don't have an answer for that question. Let me search it for you...");
								try {
									searchInGoogle(text);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}

				}
			}

		});

	}

	public double evaluate(String text) throws IOException {
		String[] token = text.split("\\s+");

		if (token.length < 3 || token.length % 2 == 0) {
			replyMethod("Make sure the expression is valid and provide spaces between operands");
		}

		double result = Double.parseDouble(token[0]);
		for (int i = 1; i < token.length; i += 2) {
			String operator = token[i];
			double operand = Double.parseDouble(token[i + 1]);
			switch (operator) {
			case "+":
				result += operand;
				break;
			case "-":
				result -= operand;
				break;
			case "*":
				result *= operand;
				break;
			case "/":
				if (operand == 0) {
					replyMethod(
							"Invalid Expression: Divide by zero not allowed,Please Make sure the expression is valid");
					throw new ArithmeticException("Division by zero");
				}
				result /= operand;
				break;
			default:
				replyMethod("Some error Occured,Here are some google search");
				searchInGoogle(text);
				throw new IllegalArgumentException("Invalid operator: " + operator);

			}
		}
		return result;
	}

	public boolean isMathematicalExpression(String text) {
		String pattern = "^[0-9+\\-*/()\\s]*$";
		return text.matches(pattern);
	}

	public void searchInGoogle(String query) throws IOException {
		try {
			String googleSearchUrl = "https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8");
			Desktop.getDesktop().browse(new URI(googleSearchUrl));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			replyMethod("Oops, there was an error while trying to open Google.");
		}
	}

	public void replyMethod(String text) {
		textArea.append("Bot: " + text + "\n");
	}

	public static Connection establishConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/chatbot";
		String username = "Aman";
		String password = "Chatbot@123";

		Connection connection = DriverManager.getConnection(jdbcURL, username, password);
		return connection;
	}

	public static String getAnsFromDb(String query) throws SQLException {
		String answer = null;

		try (Connection con = establishConnection()) {
			String queryForDb = "SELECT Result FROM QnA WHERE Query = ? ";
			try (PreparedStatement preparedStatement = con.prepareStatement(queryForDb)) {
				preparedStatement.setString(1, query);
				try (ResultSet rs = preparedStatement.executeQuery()) {
					if (rs.next()) {
						answer = rs.getString("Result");
					}
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            ChatbotUsingJswing chatbot = new ChatbotUsingJswing();
	        }
	    });

	}

}
