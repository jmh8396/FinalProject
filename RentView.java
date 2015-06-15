package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import model.*;

public class RentView extends JFrame implements ActionListener,
		ListSelectionListener, MouseListener {

	String[] booksetstr;
	ArrayList<bookSet> bookset = new ArrayList<bookSet>();
	ArrayList<String> customerList = new ArrayList<String>();

	int selbookset;
	int selbook;

	JPanel frmMain = new JPanel();
	JLabel lblImage = new JLabel(new ImageIcon("src/main.jpg"));
	JPanel frmButton = new JPanel();
	JButton[] button = new JButton[6];

	JPanel frmListBookSet = new JPanel();
	JList list;
	JScrollPane scroll;
	JPanel frmListBook = new JPanel();
	DefaultTableModel dtmBook;
	String[] ciBook = { " 번호", "반납일" };
	JTable tblBook;
	JScrollPane spBook;

	JPanel frmCustomer = new JPanel();
	JLabel lblName = new JLabel("이름");
	JLabel lblPhone = new JLabel("전화번호");;
	JLabel lblRentDate = new JLabel("빌린 날짜(20##-##-##)");;
	JLabel lblReturnDate = new JLabel("반납 날짜(20##-##-##)");;
	JTextField tfName = new JTextField(12);
	JTextField tfPhone = new JTextField(13);
	JTextField tfRentDate = new JTextField(10);
	JTextField tfReturnDate = new JTextField(10);

	public RentView(String title) {
		super(title);

		initContents();

		initListener();

		attachContents();

		tblBook.setRowSelectionAllowed(true);

		this.setResizable(false);
		this.setVisible(true);
		this.setSize(900, 500);
		this.getContentPane().setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initContents() {
		// 초기화

		loadData();

		lblImage.setBounds(0, 0, 855, 432);

		button[0] = new JButton("대여");
		button[1] = new JButton("반납");
		button[2] = new JButton("대여기록");
		button[3] = new JButton("검색");
		button[4] = new JButton("도서추가");
		button[5] = new JButton("종료");

		booksetstr = new String[bookset.size()];
		for (int i = 0; i < bookset.size(); i++) {
			booksetstr[i] = bookset.get(i).type;
		}

		list = new JList(booksetstr);
		scroll = new JScrollPane(list);

		frmMain.setLayout(null);
		frmMain.setBounds(20, 20, 855, 432);
		frmMain.setOpaque(false);
		frmMain.setBackground(new Color(238, 240, 244));
		frmMain.setBorder(BorderFactory.createEtchedBorder());

		frmButton.setLayout(null);
		frmButton.setBounds(20, 20, 140, 392);
		frmButton.setOpaque(false);
		frmButton.setBackground(new Color(238, 240, 244));
		frmButton.setBorder(BorderFactory.createEtchedBorder());

		button[0].setFocusable(false);
		button[1].setFocusable(false);
		button[2].setFocusable(false);
		button[3].setFocusable(false);
		button[4].setFocusable(false);
		button[5].setFocusable(false);
		button[0].setBounds(20, 10, 100, 50);
		button[1].setBounds(20, 70, 100, 50);
		button[2].setBounds(20, 130, 100, 50);
		button[3].setBounds(20, 190, 100, 50);
		button[4].setBounds(20, 250, 100, 50);
		button[5].setBounds(20,310,100,50);
		button[0].setOpaque(false);
		button[1].setOpaque(false);
		button[2].setOpaque(false);
		button[3].setOpaque(false);
		button[4].setOpaque(false);
		button[5].setOpaque(false);
		button[0].setForeground(Color.WHITE);
		button[1].setForeground(Color.WHITE);
		button[2].setForeground(Color.WHITE);
		button[3].setForeground(Color.WHITE);
		button[4].setForeground(Color.WHITE);
		button[5].setForeground(Color.WHITE);
		button[0].setBackground(Color.WHITE);
		button[1].setBackground(Color.WHITE);
		button[2].setBackground(Color.WHITE);
		button[3].setBackground(Color.WHITE);
		button[4].setBackground(Color.WHITE);
		button[5].setBackground(Color.WHITE);

		lblName.setForeground(Color.WHITE);
		lblPhone.setForeground(Color.WHITE);
		lblRentDate.setForeground(Color.WHITE);
		lblReturnDate.setForeground(Color.WHITE);

		frmListBookSet.setLayout(new GridLayout(1, 1));
		frmListBookSet.setBounds(180, 20, 200, 392);
		frmListBookSet.setOpaque(false);
		frmListBookSet.setBackground(new Color(238, 240, 244));
		frmListBookSet.setBorder(BorderFactory.createEtchedBorder());

		dtmBook = new DefaultTableModel();
		dtmBook.setColumnIdentifiers(ciBook);
		tblBook = new JTable(dtmBook);
		spBook = new JScrollPane(tblBook);

		frmListBook.setLayout(new GridLayout(1, 1));
		frmListBook.setBounds(390, 20, 235, 392);
		frmListBook.setOpaque(false);
		frmListBook.setBackground(new Color(238, 240, 244));
		frmListBook.setBorder(BorderFactory.createEtchedBorder());

		frmCustomer.setLayout(null);
		frmCustomer.setBounds(635, 20, 200, 392);
		frmCustomer.setOpaque(false);
		frmCustomer.setBackground(new Color(238, 240, 244));
		frmCustomer.setBorder(BorderFactory.createEtchedBorder());

		lblName.setBounds(10, 10, 100, 30);
		tfName.setBounds(20, 40, 150, 20);
		lblPhone.setBounds(10, 110, 100, 30);
		tfPhone.setBounds(20, 140, 150, 20);
		lblRentDate.setBounds(10, 210, 200, 30);
		tfRentDate.setBounds(20, 240, 150, 20);
		lblReturnDate.setBounds(10, 310, 200, 30);
		tfReturnDate.setBounds(20, 340, 150, 20);

	}

	private void initListener() {
		// 리스너
		for (int i = 0; i < button.length; i++) {
			button[i].addActionListener(this);
		}
		list.addListSelectionListener(this);
		tblBook.addMouseListener(this);
	}

	private void attachContents() {
		// 붙이기
		Container c = this.getContentPane();

		c.setLayout(null);
		c.add(frmMain);

		frmMain.add(frmButton);

		frmButton.add(button[0]);
		frmButton.add(button[1]);
		frmButton.add(button[2]);
		frmButton.add(button[3]);
		frmButton.add(button[4]);
		frmButton.add(button[5]);
		frmMain.add(frmListBookSet);
		frmListBookSet.add(list);

		frmMain.add(frmListBook);
		frmListBook.add(spBook);

		frmMain.add(frmCustomer);
		frmCustomer.add(lblName);
		frmCustomer.add(tfName);
		frmCustomer.add(lblPhone);
		frmCustomer.add(tfPhone);
		frmCustomer.add(lblRentDate);
		frmCustomer.add(tfRentDate);
		frmCustomer.add(lblReturnDate);
		frmCustomer.add(tfReturnDate);
		frmMain.add(lblImage);
	}

	private void loadData() {

		try {

			BufferedReader file = new BufferedReader(new FileReader("book.txt"));

			while (true) {

				String type = file.readLine();
				if (type == null)
					break;
				bookSet newbookset = new bookSet(type);
				bookset.add(newbookset);
				while (true) {
					String data = file.readLine();
					if (data.equals("#"))
						break;
					String[] temp = data.split(",");
					book newbook = new book(type, temp[0]);
					if (temp.length > 1)
						newbook.customer = new Customer(temp[1], temp[2],temp[3], temp[4]);
					newbookset.books.add(newbook);
				}
			}
			file.close();

		} catch (IOException e) {
			return;
		}

	}

	private void saveData() {
		try {

			PrintWriter file = new PrintWriter(new FileWriter("book.txt"));
			customerList.clear();
			for (int i = 0; i < bookset.size(); i++) {
				file.println(bookset.get(i).type);
				for (int j = 0; j < bookset.get(i).books.size(); j++) {
					file.print(bookset.get(i).books.get(j).name + ",");
					if (bookset.get(i).books.get(j).customer != null) {
						file.print(bookset.get(i).books.get(j).customer.name
								+ ",");
						file.print(bookset.get(i).books.get(j).customer.phone
								+ ",");
						file.print(bookset.get(i).books.get(j).customer.rentdate
								+ ",");
						file.print(bookset.get(i).books.get(j).customer.returndate
								+ ",");
						customerList.add(bookset.get(i).books.get(j).name
										+ "-"
										+ bookset.get(i).books.get(j).customer.name
										+ " "
										+ bookset.get(i).books.get(j).customer.phone
										+ " "
										+ bookset.get(i).books.get(j).customer.returndate);
					}
					file.println();
				}
				file.println("#");
			}
			file.close();

		} catch (IOException e) {
			return;
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button[0]) {
			// 렌탈
			System.out.println(selbookset + " " + selbook);
			bookset.get(selbookset).books.get(selbook).customer = new Customer(
					tfName.getText(), tfPhone.getText(), tfRentDate.getText(),
					tfReturnDate.getText());
			tableupdate();
			saveData();
		} else if (e.getSource() == button[1]) {
			// 반납
			bookset.get(selbookset).books.get(selbook).customer = null;
			tableupdate();
			tfName.setText(null);
			tfPhone.setText(null);
			tfRentDate.setText(null);
			tfReturnDate.setText(null);
			saveData();
		} else if (e.getSource() == button[2]) {
			makeListPanel();

		} else if (e.getSource() == button[3]) {
			makeSearchPanel();
		} else if (e.getSource() == button[4]) {
			bookAddPanel();
		} else if (e.getSource() == button[5]){
			System.exit(0);
		}

	}

	private void bookAddPanel() {
		class BAdd extends JFrame {
			BAdd(String BAdd) {
				super(BAdd);
			}
		}
		final TextField bookTitle = new TextField(20);
		final TextField bookType = new TextField(20);

		BAdd BA = new BAdd("도서추가");
		Panel BAPanel = new Panel();
		BA.setSize(500, 300);
		BA.setVisible(true);

		JLabel bookTypeLa = new JLabel("도서타입");
		bookTypeLa.setBounds(50, 100, 100, 50);
		BAPanel.add(bookTypeLa);
		bookType.setBounds(70, 100, 100, 20);
		BAPanel.add(bookType);

		JLabel bookTitleLa = new JLabel("도서제목");
		bookTitleLa.setBounds(50, 150, 100, 50);
		BAPanel.add(bookTitleLa);
		bookTitle.setBounds(70, 150, 100, 20);
		BAPanel.add(bookTitle);

		JButton AddBook = new JButton("추가");
		AddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CbookType = null;
				String CbookTitle = null;
				CbookType = bookType.getText();
				CbookTitle = bookTitle.getText();
				book newBook = new book(CbookType,CbookTitle);
				
				for(int i=0; i<bookset.size(); i++){
					if(bookset.get(i).type.equals(CbookType)){
						bookset.get(i).books.add(newBook);
					}
				}
				try {
					PrintWriter file = new PrintWriter(new FileWriter("book.txt"));
					for (int i = 0; i < bookset.size(); i++) {
						file.println(bookset.get(i).type);
						for (int j = 0; j < bookset.get(i).books.size(); j++) {
							file.print(bookset.get(i).books.get(j).name + ",");
							if (bookset.get(i).books.get(j).customer != null) {
								file.print(bookset.get(i).books.get(j).customer.name
										+ ",");
								file.print(bookset.get(i).books.get(j).customer.phone
										+ ",");
								file.print(bookset.get(i).books.get(j).customer.rentdate
										+ ",");
								file.print(bookset.get(i).books.get(j).customer.returndate
										+ ",");
							}
							file.println();
						}
						file.println("#");
					}
					file.close();
				} catch (IOException e2) {
					return;
				}
				tableupdate();
				BA.setVisible(false);
			}
		});
		BAPanel.add("South", AddBook);
		BA.add(BAPanel);
		BAPanel.layout();
		BAPanel.setSize(300, 300);
		BAPanel.setVisible(true);
	}

	

	private void makeSearchPanel() {
		class search extends JFrame {
			search(String search) {
				super(search);
			}
		}
		search S = new search("검색");
		Panel Search = new Panel();
		S.setSize(500, 300);
		S.setVisible(true);
		S.add(Search);
		Search.layout();
		Search.setSize(300, 300);
		Search.setVisible(true);

		JButton bSearch = new JButton("도서");
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S.setVisible(false);
				final TextField bookInput = new TextField(20);
				final TextArea bookDisplay = new TextArea();
				class bsearch extends JFrame {
					bsearch(String search) {
						super(search);
					}
				}
				bsearch bS = new bsearch("도서검색");
				Panel bSPanel = new Panel();
				bookDisplay.setEditable(false);

				bSPanel.add("North", new Label("도서를 입력하시오."));
				bookInput.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String cheackName = bookInput.getText();
						for (int i = 0; i < bookset.size(); i++) {
							for (int j = 0; j < bookset.get(i).books.size(); j++) {
								if (bookset.get(i).books.get(j).name
										.equals(cheackName)) {
									bookDisplay.append(bookset.get(i).type
											+ " "
											+ bookset.get(i).books.get(j).name);
									if (bookset.get(i).books.get(j).customer != null) {
										bookDisplay.append(" "
												+ bookset.get(i).books.get(j).customer.name
												+ " "
												+ bookset.get(i).books.get(j).customer.phone
												+ "\n");
									} else
										bookDisplay.append(" -대여가능-");
									bookDisplay.append("\n");
								}
							}
						}
					}
				});
				bS.setSize(500, 300);
				bSPanel.add("South", bookInput);
				bSPanel.add("Center", bookDisplay);
				bS.add(bSPanel);
				bSPanel.layout();
				bSPanel.setSize(500, 300);
				bSPanel.setVisible(true);
				bS.setVisible(true);

			}
		});
		bSearch.setBounds(100, 100, 50, 50);
		Search.add(bSearch);
		bSearch.setVisible(true);

		JButton cSearch = new JButton("회원");
		cSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S.setVisible(false);

			}
		});
		cSearch.setBounds(200, 100, 50, 50);
		Search.add(cSearch);
		cSearch.setVisible(true);

	}

	private void makeListPanel() {
		class clist extends JFrame {
			clist(String Customerlist) {
				super(Customerlist);
			}
		}
		clist CL = new clist("대여기록");
		Panel C = new Panel();
		TextArea display = null;
		CL.setSize(500, 300);
		CL.setVisible(true);
		CL.add(C);
		C.layout();
		C.setSize(500, 300);
		C.setVisible(true);
		display = new TextArea();
		for (int i = 0; i < customerList.size(); i++) {
			display.append(customerList.get(i) + "\n");
		}
		C.add(display);
		JButton close = new JButton("확인");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CL.setVisible(false);
			}
		});
		close.setBounds(240, 250, 50, 50);
		C.add(close);
		close.setVisible(true);

	}

	private void tableupdate() {
		String[] temp = { "", "" };

		int rowcount = dtmBook.getRowCount();
		for (int i = 0; i < rowcount; i++)
			dtmBook.removeRow(0);

		for (int i = 0; i < bookset.get(selbookset).books.size(); i++) {
			temp[0] = "" + bookset.get(selbookset).books.get(i).name;
			if (bookset.get(selbookset).books.get(i).customer != null)
				temp[1] = bookset.get(selbookset).books.get(i).customer.returndate;
			else
				temp[1] = "-";
			dtmBook.addRow(temp);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int index = tblBook.getSelectedRow();
		selbook = index;
		if (bookset.get(selbookset).books.get(index).customer != null) {
			tfName.setText(bookset.get(selbookset).books.get(index).customer.name);
			tfPhone.setText(bookset.get(selbookset).books.get(index).customer.phone);
			tfRentDate
					.setText(bookset.get(selbookset).books.get(index).customer.rentdate);
			tfReturnDate
					.setText(bookset.get(selbookset).books.get(index).customer.returndate);
		} else {
			tfName.setText(null);
			tfPhone.setText(null);
			tfRentDate.setText(null);
			tfReturnDate.setText(null);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == list && e.getValueIsAdjusting()) {
			/**
			 * String[] temp = { "", "" }; // 차 번호, 반납일
			 * 
			 * int rowcount = dtmCar.getRowCount(); for(int i = 0;i < rowcount;
			 * i++) dtmCar.removeRow(0);
			 * 
			 * int index = ((JList<String>)e.getSource()).getSelectedIndex();
			 * selcarset = index; for(int i = 0;i <
			 * carset.get(index).cars.size();i++) { temp[0] = "" +
			 * carset.get(index).cars.get(i).number;
			 * if(carset.get(index).cars.get(i).customer != null) temp[1] =
			 * carset.get(index).cars.get(i).customer.returndate; else temp[1] =
			 * "-"; dtmCar.addRow(temp); }
			 */
			selbookset = ((JList) e.getSource()).getSelectedIndex();
			tableupdate();
		}

	}
}
