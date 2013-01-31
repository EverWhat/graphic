import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.applet.*;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingConstants;


public class graphic3window {

	private JFrame frame;
	private final JLabel lblHeader = new JLabel("y = a * fn (b * x) + c");
	private final JLabel lblA = new JLabel("a:");
	private final JLabel lblB = new JLabel("b:");
	private final JLabel lblC = new JLabel("c:");
	private final JFormattedTextField ftxtA = new JFormattedTextField();
	private final JFormattedTextField ftxtB = new JFormattedTextField();
	private final JFormattedTextField ftxtC = new JFormattedTextField();
	private final JButton btnGo = new JButton("Goooo!");
	private final JPanel panel = new JPanel();
	private final JLabel lblCrossPoint = new JLabel("");
	private final JComboBox cmbFunction = new JComboBox();
	private final JLabel lblFn = new JLabel("fn:");
	private final String constSin = "sin";
	private final String constCos = "cos";
	private final String constTg = "tg";
	private final String constCtg = "ctg";
	private final JButton btnClear = new JButton("Clear");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					graphic3window window = new graphic3window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public graphic3window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().addMouseListener(new MouseAdapter() {
		
		});
			
		frame.setBounds(100, 100, 812, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblHeader.setBounds(0, 0, 434, 14);
		
		frame.getContentPane().add(lblHeader);
		lblA.setBounds(0, 24, 10, 19);
		ftxtA.setText("0");
		
		frame.getContentPane().add(lblA);
		lblB.setBounds(0, 55, 10, 14);
		ftxtB.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				ParameterValidation(ftxtB);
			}
		});
		ftxtB.setText("0");
		
		frame.getContentPane().add(lblB);
		lblC.setBounds(0, 83, 10, 14);
		ftxtC.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ParameterValidation(ftxtC);
			}
		});
		ftxtC.setText("0");
		
		frame.getContentPane().add(lblC);
		ftxtA.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				//value validation of A
				ParameterValidation(ftxtA);
				}
				}
			
		);
		ftxtA.setToolTipText("enter parameter a");
		ftxtA.setBounds(20, 25, 86, 20);
		
		
		frame.getContentPane().add(ftxtA);
		ftxtB.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
			}
		});
		ftxtB.setToolTipText("enter parameter b");
		ftxtB.setBounds(20, 53, 86, 20);
		
		frame.getContentPane().add(ftxtB);
		ftxtC.setToolTipText("enter parameter c");
		ftxtC.setBounds(20, 82, 86, 20);
		
		frame.getContentPane().add(ftxtC);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (GoButtonState()){
					//once I\ll draw a graphic...
					
					Graphics g = panel.getGraphics();
					g.setColor(Color.red);
					//draw X line
					g.drawLine(panel.getWidth()/2, 0, panel.getWidth()/2, panel.getHeight());
					//draw Y line
					g.drawLine(0, panel.getHeight()/2, panel.getWidth(), panel.getHeight()/2);
					//
					g.setColor(Color.black);
					double parA = Double.parseDouble(ftxtA.getText());
					double parB = Double.parseDouble(ftxtB.getText());
					double parC = Double.parseDouble(ftxtC.getText());
					double y = 0;
					int prevX = 0;
					int prevY = 0;
					for (int x=-(panel.getWidth()); x<panel.getWidth(); x=x+4){
						y = parA * FunctionCalculate(parB, x) + parC;
						//point according to panel
						y = y + panel.getHeight()/4;
						
						if (x>0) {g.drawLine(prevX, prevY, x, (int)Math.round(y));}
						//remember previous point
						prevY = (int)Math.round(y);
						prevX = x;
						
					}
					g.dispose();
					panel.paintComponents(g);
					
				}				
			}		});
		btnGo.setBounds(20, 150, 86, 23);
		
		frame.getContentPane().add(btnGo);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(116, 25, 670, 308);
		
		frame.getContentPane().add(panel);
		
		panel.add(lblCrossPoint);
		cmbFunction.setBounds(20, 114, 86, 20);
		
		frame.getContentPane().add(cmbFunction);
		
		cmbFunction.addItem(constSin);
		cmbFunction.addItem(constCos);
		cmbFunction.addItem(constTg);
		cmbFunction.addItem(constCtg);
		lblFn.setBounds(0, 116, 26, 14);
		
		frame.getContentPane().add(lblFn);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel.repaint();
			}
		});
		btnClear.setBounds(20, 184, 89, 23);
		
		frame.getContentPane().add(btnClear);
		
	}
	public Boolean GoButtonState(){
		Boolean ready = false;
		Color clrA = ftxtA.getBackground();
		Color clrB = ftxtB.getBackground();
		Color clrC = ftxtC.getBackground();
		int lngA = ftxtA.getText().length();
		int lngB = ftxtB.getText().length();
		int lngC = ftxtC.getText().length();

		if ((((((clrA.equals(new JFormattedTextField().getBackground())) && (clrB.equals(new JFormattedTextField().getBackground()))) && (clrC.equals(new JFormattedTextField().getBackground()))) && (lngA > 0)) && (lngB > 0)) && (lngC > 0)){
			ready = true;
		} else { }
		return ready;
		}
	
	public void ParameterValidation(JFormattedTextField field){
		field.setBackground(new JFormattedTextField().getBackground());
		try
		{Double.parseDouble(field.getText());
		} catch (java.lang.NumberFormatException e){
			field.setBackground(Color.red);
		}
	}
	
	public double FunctionCalculate (double b, double x) {
		double result = b*x;
		switch(cmbFunction.getSelectedIndex()){
		case 0: result = Math.sin(result);
		case 1: result = Math.cos(result);
		case 2: result = Math.tan(result);
		case 3: result = 1 / Math.tan(result);
		}
		return result;
	}
	
	}
	

	

