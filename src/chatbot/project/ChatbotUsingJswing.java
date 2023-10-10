package chatbot.project;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatbotUsingJswing extends JFrame {
    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();
    private JButton button = new JButton("Submit");
    private ImageIcon image = new ImageIcon("logo.png");
    private JScrollPane sp = new JScrollPane(textArea);

    public ChatbotUsingJswing() {
        initializeFrame();
        addComponentsToFrame();
        addListeners();
    }

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setSize(700, 560);
        getContentPane().setBackground(new Color(153, 192, 255));
        setTitle("ChatBot using Java");
        setIconImage(image.getImage());
    }

    private void addComponentsToFrame() {
        textArea.setBounds(5, 5, 675, 450);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 25));
        add(new JScrollPane(textArea));

        button.setSize(100, 40);
        button.setLocation(570, 460);
        button.setFont(new Font("Monospaced", Font.PLAIN, 15));
        add(button);

        textField.setSize(550, 40);
        textField.setLocation(4, 460);
        textField.setFont(new Font("Monospaced", Font.PLAIN, 25));
        add(textField);
        
        setIconImage(image.getImage());
		
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(5, 5, 675, 450);

        add(sp);
    }

    private void addListeners() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button) {
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
                                replyMethod(Double.toString(result));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            String answer = null;
                            try {
                                answer = getAnsFromDb(text);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                            if (answer != null) {
                                replyMethod(answer);
                            } else {
                                replyMethod("Sorry, I don't have an answer for that question. Let me search it for you...");
                                try {
                                    searchInGoogle(text);
                                } catch (IOException e1) {
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
        String[] tokens = text.split("\\s+");

        if (tokens.length < 3 || tokens.length % 2 == 0) {
            replyMethod("Make sure the expression is valid and provide spaces between operands");
        }

        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);
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
                        replyMethod("Divide by zero not allowed");
                        throw new ArithmeticException("Division by zero");
                    }
                    result /= operand;
                    break;
                default:
                    replyMethod("Some error occurred. Here are some Google search results");
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
        ChatbotUsingJswing chatbot = new ChatbotUsingJswing();
    }
}
