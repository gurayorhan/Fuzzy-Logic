package com.bm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.bm.defuzzification.WAM;
import com.bm.fuzzification.Mamdani;
import com.bm.fuzzification.RuleResult;
import com.bm.rule.Clause;

@SuppressWarnings("serial")
public class ResultFrame extends JFrame{
	
	Border blackline = BorderFactory.createLineBorder(Color.black);
	
	JTextArea kurallarSonuclariTextArea = new JTextArea();
	JScrollPane kurallarSonuclariJScrollPane = new JScrollPane(kurallarSonuclariTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JLabel kuralSonuclariLabel = new JLabel("Kural Sonuçlarý", SwingConstants.CENTER);
	
	JLabel girisDegerleriLabel = new JLabel("Giriþ Deðerleri", SwingConstants.CENTER);
	JLabel cikisDegerleriLabel = new JLabel("Çýkýþ Deðeri", SwingConstants.CENTER);
	
	JLabel iletkenLabel = new JLabel("Ýletkenlik:", SwingConstants.LEFT);
	JLabel iletkenDegerLabel = new JLabel("0", SwingConstants.RIGHT);

	JLabel yagLabel = new JLabel("Yað ve Gres:", SwingConstants.LEFT);
	JLabel yagDegerLabel = new JLabel("0", SwingConstants.RIGHT);
	
	JLabel azotLabel = new JLabel("Toplam Azot:", SwingConstants.LEFT);
	JLabel azotDegerLabel = new JLabel("0", SwingConstants.RIGHT);
	
	JLabel oksijenLabel = new JLabel("Çözünmüþ Oksijen:", SwingConstants.LEFT);
	JLabel oksijenDegerLabel = new JLabel("0", SwingConstants.RIGHT);
	
	JLabel sonucLabel = new JLabel("Sonuç:", SwingConstants.LEFT);
	JLabel sonucDegerLabel = new JLabel("0", SwingConstants.RIGHT);
	
	JButton button = new JButton("Geri");
	

	public ResultFrame(final Application application,double iletkenlikValue,double yagValue,double oksijenValue,double azotValue){
		
		Mamdani mamdani = application.getMamdani(iletkenlikValue, yagValue, oksijenValue, azotValue);
		
		mamdani.run();
		
	    WAM wam = new WAM();
	    double wamResult = wam.start(mamdani,application.getSonuc());
		
		setSize(1280, 720);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		kuralSonuclariLabel.setBounds(10, 202, 900, 20);
		
		girisDegerleriLabel.setBounds(910, 410, 360, 20);
		
		iletkenLabel.setBounds(930, 440, 250, 20);
		iletkenDegerLabel.setBounds(910+200+20, 440, 110, 20);
		iletkenDegerLabel.setText(iletkenlikValue+"");
		
		yagLabel.setBounds(930, 470, 250, 20);
		yagDegerLabel.setBounds(910+200+20, 470, 110, 20);
		yagDegerLabel.setText(yagValue+"");
		
		azotLabel.setBounds(930, 500, 250, 20);
		azotDegerLabel.setBounds(910+200+20, 500, 110, 20);
		azotDegerLabel.setText(azotValue+"");
		
		oksijenLabel.setBounds(930, 530, 250, 20);
		oksijenDegerLabel.setBounds(910+200+20, 530, 110, 20);
		oksijenDegerLabel.setText(oksijenValue+"");
		
		cikisDegerleriLabel.setBounds(910, 560, 360, 20);
		
		sonucLabel.setBounds(930, 590, 250, 20);
		sonucDegerLabel.setBounds(910+200+20, 590, 110, 20);
		sonucDegerLabel.setText(String.format("%.3f", wamResult));
		
		button.setBounds(915, 634, 340, 40);
	
		kurallarSonuclariTextArea.setBounds(10, 30+200, 900, 445);
		kurallarSonuclariJScrollPane.setBounds(10, 30+200, 900, 445);
		kurallarSonuclariJScrollPane.getViewport().add(kurallarSonuclariTextArea);
		
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainFrame frame = new MainFrame(application);
			}
		});
		
		kurallarTextAreaAddRules(mamdani);
		
		add(cikisDegerleriLabel);
		add(sonucDegerLabel);
		add(sonucLabel);
		
		add(iletkenLabel);
		add(iletkenDegerLabel);
		
		add(yagLabel);
		add(yagDegerLabel);
		
		add(azotLabel);
		add(azotDegerLabel);
		
		add(oksijenLabel);
		add(oksijenDegerLabel);
		
		add(button);
		add(kurallarSonuclariJScrollPane);
		add(kuralSonuclariLabel);
		add(girisDegerleriLabel);

		memberShipFunctionResult(wamResult);
		memberShipFunctionIletken(iletkenlikValue);
		memberShipFunctionYag(yagValue);
		memberShipFunctionOksijen(oksijenValue);
		memberShipFunctionAzot(azotValue);
		
		setVisible(true);
	}
	
	public void kurallarTextAreaAddRules(Mamdani mamdani) {
		for (RuleResult ruleResult : mamdani.getResults()) {
			String string =" "+ruleResult.getRule().getName()+" -  Eðer ";
			int i = 0;
			for(Clause c: ruleResult.getRule().getClauses()) {
				i++;
				if(i == ruleResult.getRule().getClauses().size()) {
					string+=c.getFuzzySet().getName()+" "+c.getString()+" ("+ String.format("%.3f", c.getFuzzySet().getDegree(c.getString())) +") ise , O halde ";
				}else {
					string+=c.getFuzzySet().getName()+" "+c.getString()+" ("+ String.format("%.3f", c.getFuzzySet().getDegree(c.getString())) +") ve ";
				}
			}
			string += ruleResult.getRule().getClause().getFuzzySet().getName()+" "+ruleResult.getRule().getClause().getString()+"("+ String.format("%.3f", ruleResult.getMin())+")"+"\n";
			kurallarSonuclariTextArea.setText(kurallarSonuclariTextArea.getText()+string);
		}
	}
	
	
	public void memberShipFunctionResult(double wamResult) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(915, 200, 340, 200);
		
		XYSeries zayif = new XYSeries("zayýf");
		zayif.add(0, 1);
		zayif.add(20, 1);
		zayif.add(40, 0);

		XYSeries orta = new XYSeries("orta");
		orta.add(20, 0);
		orta.add(40, 1);
		orta.add(60, 1);
		orta.add(80,0);

		
		XYSeries iyi = new XYSeries("iyi");
		iyi.add(60, 0);
		iyi.add(80, 1);
		iyi.add(100, 1);
		
		XYSeries sonuc = new XYSeries("sonuç");
		sonuc.add(wamResult, 1);
		sonuc.add(wamResult, 0);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(sonuc);
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);

		JFreeChart chart = ChartFactory.createXYLineChart("Sonuç",null,null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(340, 195));
		jPanel.add(cp);
		add(jPanel);
	}
	
	public void memberShipFunctionIletken(double value) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(5, 5, 310, 190);
		
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
		
		XYSeries sonuc = new XYSeries("deðer");
		sonuc.add(value, 1);
		sonuc.add(value, 0);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);
		dataset.addSeries(sonuc);

		JFreeChart chart = ChartFactory.createXYLineChart("Ýletkenlik",null,null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(310, 185));
		jPanel.add(cp);
		add(jPanel);
	}

	public void memberShipFunctionYag(double value) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(5+315, 5, 310, 190);
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
		
		XYSeries sonuc = new XYSeries("deðer");
		sonuc.add(value, 1);
		sonuc.add(value, 0);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);
		dataset.addSeries(sonuc);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Yað ve Gres",null,null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(310, 185));
		jPanel.add(cp);
		add(jPanel);
	}

	public void memberShipFunctionOksijen(double value) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(5+315+315, 5, 310, 190);
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
		
		XYSeries sonuc = new XYSeries("deðer");
		sonuc.add(value, 1);
		sonuc.add(value, 0);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);
		dataset.addSeries(sonuc);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Çözünmüþ Oksijen",null,null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(310, 185));
		jPanel.add(cp);
		add(jPanel);
	}

	public void memberShipFunctionAzot(double value) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(5+315+315+315, 5, 310, 190);
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
		
		XYSeries sonuc = new XYSeries("deðer");
		sonuc.add(value, 1);
		sonuc.add(value, 0);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(zayif);
		dataset.addSeries(orta);
		dataset.addSeries(iyi);
		dataset.addSeries(sonuc);
		
		

		JFreeChart chart = ChartFactory.createXYLineChart("Toplam Azot",null,null, dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel cp = new ChartPanel(chart);
		cp.setBorder(blackline);
		
		cp.setPreferredSize(new java.awt.Dimension(310, 185));
		jPanel.add(cp);
		add(jPanel);
	}

	
}
