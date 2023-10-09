package chatbot.project;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class ChatbotUsingJswing extends JFrame implements ActionListener  {
	private JTextArea textArea=new JTextArea();
	private JTextField textField = new JTextField();
	private JButton button=new JButton("Go");
	private ImageIcon image=new ImageIcon("logo.png");
	
	 ChatbotUsingJswing(){
			JFrame frame=new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setSize(600,600);
			frame.getContentPane().setBackground(new Color(216,75,32));
			frame.setTitle("ChatBot using java");
			frame.add(textArea);
			textArea.setSize(400,400);
			textArea.setLocation(1,1);
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			
			frame.add(button);
			button.setSize(400,40);
			button.setLocation(300,420);
			
			frame.add(textField);
			textField.setSize(300,40);
			textField.setLocation(1,420);
			
			frame.setIconImage(image.getImage());
			
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(e.getSource()==button) {
						String text=textField.getText();
						textArea.append("You: " + text + "\n");
						textField.setText("");
						
						String answer = null;
						try {
							answer = getAnsFromDb(text);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						replyMethod(answer);
						
					}
				}
				
			});
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
	 
	 public static String getAnsFromDb(String query) throws SQLException{
		 String answer=null;
		 
		 try(Connection con=establishConnection()){
			 String queryForDb="SELECT Result FROM QnA WHERE Query = ? ";
			 try(PreparedStatement preparedStatement=con.prepareStatement(queryForDb)){
				 preparedStatement.setString(1, query);
				 try (ResultSet rs=preparedStatement.executeQuery()){
					 if(rs.next()) {
						 answer=rs.getString("Result");
					 }
				 }
			 }
		 }
		 
		 return answer;
	 }
	
	public static void main(String[] args) {
		
		ChatbotUsingJswing chatbot= new ChatbotUsingJswing();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==button) {
			
		}
		
	}
	

}
