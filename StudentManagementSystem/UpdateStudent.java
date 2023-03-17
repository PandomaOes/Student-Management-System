package StudentManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class UpdateStudent extends JFrame {
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs;

	private JPanel contentPane;
	private JTextField updateEntry;
	private JTextField nameU;
	private JTextField entryU;
	private JTextField emailU;
	private JTextField contactU;
	private JTextField homeU;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UpdateStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 656);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GRAY);
		
		nameU = new JTextField();
		nameU.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNewLabel_1_1 = new JLabel("Entry Number");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNewLabel_1_2 = new JLabel("Email Address");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNewLabel_1_3 = new JLabel("Contact Number");
		lblNewLabel_1_3.setForeground(Color.BLACK);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNewLabel_1_4 = new JLabel("Home City");
		lblNewLabel_1_4.setForeground(Color.BLACK);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		entryU = new JTextField();
		entryU.setColumns(10);
		
		emailU = new JTextField();
		emailU.setColumns(10);
		
		contactU = new JTextField();
		contactU.setColumns(10);
		
		homeU = new JTextField();
		homeU.setColumns(10);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {




				try {
					String csvFile = "student.csv";
					String tempFile = "temp_student.csv";
					String line;
					String csvSplitBy = ",";

					String pid = updateEntry.getText();
					boolean found = false;

					List<String[]> studentData = new ArrayList<>();

					try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
						while ((line = br.readLine()) != null) {
							String[] student = line.split(csvSplitBy);

							if (student[1].equalsIgnoreCase(pid)) {
								found = true;
								if (!nameU.getText().equals("")) {
									student[0] = nameU.getText();
								}
								if (!entryU.getText().equals("")) {
									student[1] = entryU.getText();
								}
								if (!emailU.getText().equals("")) {
									student[2] = emailU.getText();
								}
								if (!contactU.getText().equals("")) {
									student[3] = contactU.getText();
								}
								if (!homeU.getText().equals("")) {
									student[4] = homeU.getText();
								}
							}
							studentData.add(student);
						}
					} catch (IOException ee) {
						JOptionPane.showMessageDialog(null, ee);
					}

					if (found) {
						try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
							for (String[] student : studentData) {
								bw.write(String.join(",", student));
								bw.newLine();
							}
						} catch (IOException ee) {
							JOptionPane.showMessageDialog(null, ee);
						}

						File oldFile = new File(csvFile);
						File newFile = new File(tempFile);

						oldFile.delete();
						newFile.renameTo(oldFile);

						JOptionPane.showMessageDialog(null, "Updated Successfully :)");
						dispose();
						Menu menu = new Menu();
						menu.show();
					} else {
						JOptionPane.showMessageDialog(null, "Record not found");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
				
				
				
				
				
				
			}
		});
		updateBtn.setForeground(Color.BLACK);
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 647, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(127)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)))
							.addGap(22))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(entryU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addComponent(contactU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addComponent(homeU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
					.addGap(114))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(261)
					.addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(261, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(nameU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(entryU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(contactU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(homeU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		updateEntry = new JTextField();
		updateEntry.setBounds(190, 100, 237, 33);
		desktopPane.add(updateEntry);
		updateEntry.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String str = updateEntry.getText();
				boolean recordFound = false;

				try {
					String csvFile = "student.csv";
					String line;
					String csvSplitBy = ",";

					try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
						while ((line = br.readLine()) != null) {
							String[] student = line.split(csvSplitBy);

							if (student[1].equalsIgnoreCase(str)) {
								recordFound = true;
								nameU.setText(student[0]);
								entryU.setText(student[1]);
								emailU.setText(student[2]);
								contactU.setText(student[3]);
								homeU.setText(student[4]);
								break;
							}
						}
					} catch (IOException ee) {
						JOptionPane.showMessageDialog(null, ee);
					}

					if (!recordFound) {
						JOptionPane.showMessageDialog(null, "Record not found");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(334, 164, 149, 33);
		desktopPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLACK);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.show();
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(143, 164, 149, 33);
		desktopPane.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Search the \"Entry Number\"");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(180, 56, 283, 33);
		desktopPane.add(lblNewLabel);
		contentPane.setLayout(gl_contentPane);
	}
}
