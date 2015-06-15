package view;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TitleView extends JFrame implements ActionListener {

	JButton button;
	
	public TitleView(String title) {
		super(title);
		
		ImageIcon icon = new ImageIcon("src/main.jpg");
		JLabel img = new JLabel(icon);
		img.setBackground(Color.black);
		img.setOpaque(true);
		img.setBounds(0, 0, 894, 472);
		button = new JButton("¤ÑStart¤Ñ");
		button.setBounds(390, 400, 120, 40);
		button.setFocusable(false);
		button.setOpaque(false);
		button.setBackground(Color.red);
		button.setMargin(new Insets(0,0,0,0));
		button.setFont(new Font(button.getFont().getName(),Font.PLAIN,25));
		button.setForeground(Color.white);
		
		Container c = this.getContentPane();
		c.setLayout(null);
		c.add(button);
		c.add(img);
		
		
		button.addActionListener(this);
		
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(900, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			new RentView("Rental");
			this.setVisible(false);
		}
	}
	
}
