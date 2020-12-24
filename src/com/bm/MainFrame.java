package com.bm;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.bm.fuzzification.Mamdani;
import com.bm.rule.Clause;
import com.bm.rule.Rule;

import java.awt.Color;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	Border blackline = BorderFactory.createLineBorder(Color.black);

	JTextField iletkenlikTextField = new JTextField();
	JTextField yagTextField = new JTextField();
	JTextField cozunmusOksijenTextField = new JTextField();
	JTextField toplamAzotTextField = new JTextField();

	JTextArea kurallarTextArea = new JTextArea();
	JScrollPane kurallarJScrollPane = new JScrollPane(kurallarTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JLabel iletkenlikLabel = new JLabel("Ýletkenlik", SwingConstants.CENTER);
	JLabel yagLabel = new JLabel("Yað", SwingConstants.CENTER);
	JLabel cozunmusOksijenLabel = new JLabel("Çözünmüþ Oksijen", SwingConstants.CENTER);
	JLabel toplamAzotLabel = new JLabel("Toplam Azot", SwingConstants.CENTER);

	JButton button = new JButton("Çalýþtýr");

	public MainFrame(final Application application) {
		
		Mamdani mamdani = application.getMamdani(0.0, 0.0, 0.0, 0.0);
		
		setSize(1280, 720);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		iletkenlikLabel.setBounds(10, 10, 250, 30);
		yagLabel.setBounds(270, 10, 250, 30);
		cozunmusOksijenLabel.setBounds(530, 10, 250, 30);
		toplamAzotLabel.setBounds(790, 10, 250, 30);

		iletkenlikTextField.setBounds(10, 40, 250, 30);
		yagTextField.setBounds(270, 40, 250, 30);
		cozunmusOksijenTextField.setBounds(530, 40, 250, 30);
		toplamAzotTextField.setBounds(790, 40, 250, 30);
		button.setBounds(1050, 10, 205, 60);

		kurallarTextArea.setBounds(10, 80, 900, 595);
		kurallarJScrollPane.setBounds(10, 80, 900, 595);
		kurallarJScrollPane.getViewport().add(kurallarTextArea);

		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {

				try {
					double i,y,a,o;
					i = Double.parseDouble(iletkenlikTextField.getText());
					y = Double.parseDouble(yagTextField.getText());
					a = Double.parseDouble(toplamAzotTextField.getText());
					o = Double.parseDouble(cozunmusOksijenTextField.getText());
					
					if(i >= 0 && i <= 3500 && y >= 0.15 && y <= 0.55 && a >= 0 && a <= 20 && o >= 0 && o <= 20) {
						setVisible(false);
						ResultFrame frame = new ResultFrame(application,i,y,o,a);
					}else {
						System.out.println();
					}
					
				} catch (Exception e2) {
					System.out.println(e2.toString());
				}

			}
		});
		
		kurallarTextAreaAddRules(mamdani);

		add(iletkenlikLabel);
		add(yagLabel);
		add(cozunmusOksijenLabel);
		add(toplamAzotLabel);
		add(button);
		add(iletkenlikTextField);
		add(yagTextField);
		add(cozunmusOksijenTextField);
		add(toplamAzotTextField);
		add(kurallarJScrollPane);

		memberShipFunctionIletken();
		memberShipFunctionYag();
		memberShipFunctionOksijen();
		memberShipFunctionAzot();

		setVisible(true);
	}
	
	public void kurallarTextAreaAddRules(Mamdani mamdani) {
		for (Rule rule : mamdani.getRules()) {
			String string = ""+rule.getName()+" -  Eðer ";
			int i = 0;
			for(Clause c: rule.getClauses()) {
				i++;
				if(i == rule.getClauses().size()) {
					string+=c.getFuzzySet().getName()+" "+c.getString()+" ise , O halde ";
				}else {
					string+=c.getFuzzySet().getName()+" "+c.getString()+" ve ";
				}
			}
			string += rule.getClause().getFuzzySet().getName()+" "+rule.getClause().getString()+"\n";
			kurallarTextArea.setText(kurallarTextArea.getText()+string);
		}
	}

	public void memberShipFunctionIletken() {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(915, 75, 340, 150);
		
		XYSeries iyi = new XYSeries("iyi");
		iyi.add(1000, 1);
		iyi.add(2000, 0);

		XYSeries orta = new XYSeries("orta");
		orta.add(1000, 0);
		orta.add(2000, 1);
		orta.add(3000, 0);
		
		XYSeries zayif = new XYSeries("zayif");
		zayif.add(2000, 0);
		zayif.add(3000, 1);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);

		JFreeChart chart = ChartFactory.createXYLineChart(null,"Ýletkenlik",null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(340, 145));
		jPanel.add(cp);
		add(jPanel);
	}

	public void memberShipFunctionYag() {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(915, 80 + 145, 340, 145);
		XYSeries iyi = new XYSeries("iyi");
		iyi.add(0.2, 1);
		iyi.add(0.35, 0);

		XYSeries orta = new XYSeries("orta");
		orta.add(0.2, 0);
		orta.add(0.35, 1);
		orta.add(0.5, 0);
		
		XYSeries zayif = new XYSeries("zayýf");
		zayif.add(0.35, 0);
		zayif.add(0.5, 1);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);

		JFreeChart chart = ChartFactory.createXYLineChart(null,"Yað ve Gres",null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(340, 140));
		jPanel.add(cp);
		add(jPanel);
	}

	public void memberShipFunctionOksijen() {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(915, 80 + 145 + 150, 340, 150);
		XYSeries zayif = new XYSeries("zayýf");
		zayif.add(3, 1);
		zayif.add(6, 0);

		XYSeries orta = new XYSeries("orta");
		orta.add(3, 0);
		orta.add(6, 1);
		orta.add(8, 0);

		XYSeries iyi = new XYSeries("iyi");
		iyi.add(6, 0);
		iyi.add(8, 1);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);

		JFreeChart chart = ChartFactory.createXYLineChart(null,"Çözünmüþ Oksijen",null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(340, 145));
		jPanel.add(cp);
		add(jPanel);
	}

	public void memberShipFunctionAzot() {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(915, 80 + 150 + 150 + 145, 340, 150);
		XYSeries iyi = new XYSeries("iyi");
		iyi.add(0, 1);
		iyi.add(12.5, 0);


		XYSeries orta = new XYSeries("orta");
		orta.add(0, 0);
		orta.add(12.5, 1);
		orta.add(25, 0);
		
		XYSeries zayif = new XYSeries("zayýf");
		zayif.add(12.5, 0);
		zayif.add(25, 1);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);

		JFreeChart chart = ChartFactory.createXYLineChart(null,"Toplam Azot",null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(340, 145));
		jPanel.add(cp);
		add(jPanel);
	}

}
