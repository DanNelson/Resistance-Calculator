import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ResistanceCalc{

	int band1,band2,band3 = 0;
	Color resistorColor = new Color(202,162,101);
	static Resistor resistor;
	Color resistorBands[] = new Color[3];
	public JTextField resistance;
	String res = "";
	int listener = 0;
	boolean listenerTF = true;
	public static int firstBandi=0;
	public static int secondBandi= 0;
	public static int thirdBandi = 0;
	public static int fourthBandi = 0;
	public class graphics extends JComponent {

		graphics() {
			setPreferredSize(new Dimension(300, 250));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			Color wireColor = new Color(192,195,204);
			g.setColor(resistorColor);
			g.fillRoundRect(50, 50, 190, 35,20,20);
			g.setColor(resistor.getColor3(1));
			g.fillRoundRect(60,50,20,35,10,10);
			g.setColor(resistor.getColor3(2));
			g.fillRoundRect(90,50,20,35,10,10);
			g.setColor(resistor.getColor3(3));
			g.fillRoundRect(120,50,20,35,10,10);
			g.setColor(resistor.getColor3(4));
			g.fillRoundRect(150,50,20,35,10,10);
			g.setColor(resistor.getColor3(5));
			g.fillRoundRect(180,50,20,35,10,10);
			g.setColor(resistor.getColor3(6));
			g.fillRoundRect(210,50,20,35,10,10);
			g.setColor(Color.BLACK);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(60,50,20,35,10,10);
			g2.drawRoundRect(90,50,20,35,10,10);
			g2.drawRoundRect(120,50,20,35,10,10);
			g2.drawRoundRect(150,50,20,35,10,10);
			g2.drawRoundRect(180,50,20,35,10,10);
			g2.drawRoundRect(210,50,20,35,10,10);
			g.setColor(wireColor);
			g.fillRect(20, 62, 30, 10);
			g.fillRect(240, 62, 30, 10);


		}
	}

	public void createGUI(){

		final JFrame frame = new JFrame();
		final JPanel panel = new JPanel();
		resistance = new JTextField("",13);
		final JTextField tol = new JTextField("",10);
		final JTextField temp = new JTextField("",10);
		JLabel Resistance = new JLabel("   Total Resistance");
		JLabel Tolerance = new JLabel("                       Tolerance");
		JLabel Temp = new JLabel("Temperature Coefficient");
		frame.setSize(300,250);

		DocumentListener listenerResistance = new DocumentListener() {
			public void changedUpdate(DocumentEvent documentEvent) {

			}
			public void insertUpdate(DocumentEvent documentEvent) {
				if(listenerTF){
					//	System.out.print(resistance.getText());
					textFields(resistance.getText());
					panel.repaint();
				}

			}
			public void removeUpdate(DocumentEvent arg0) {
				if(listenerTF){
					textFields(resistance.getText());
					panel.repaint();
				}
			}

		};
		resistance.getDocument().addDocumentListener(listenerResistance);


		frame.addMouseListener(new MouseAdapter(){

			public void mousePressed(MouseEvent e) {
				
				if((e.getPoint().getX()>=60&&e.getPoint().getX()<=82) && (e.getPoint().getY()>=179 && e.getPoint().getY()<=212)){
					listenerTF = false;
					resistor.change(1,"incriment",0);
				}
				else if((e.getPoint().getX()>=90&&e.getPoint().getX()<=110) && (e.getPoint().getY()>=179 && e.getPoint().getY()<=212)){
					listenerTF = false;
					resistor.change(2,"incriment",0);
				}
				else if((e.getPoint().getX()>=120&&e.getPoint().getX()<=142) && (e.getPoint().getY()>=179 && e.getPoint().getY()<=212)){
					listenerTF = false;
					resistor.change(3,"incriment",0);
				}
				else if((e.getPoint().getX()>=150&&e.getPoint().getX()<=172) && (e.getPoint().getY()>=179 && e.getPoint().getY()<=212)){
					listenerTF = false;
					resistor.change(4,"incriment",0);
				}
				else if((e.getPoint().getX()>=180&e.getPoint().getX()<=202) && (e.getPoint().getY()>=179 && e.getPoint().getY()<=212)){
					listenerTF = false;
					resistor.change(5,"incriment",0);
				}
				else if((e.getPoint().getX()>=210&e.getPoint().getX()<=232) && (e.getPoint().getY()>=179 && e.getPoint().getY()<=212)){
					listenerTF = false;
					resistor.change(6,"incriment",0);
				}
				if(!listenerTF){
				res = resistor.calculateResistance();// + "\u2126";
				String tolerance = "\u00B1" + resistor.getTol() + "%";
				String tempp = resistor.getTemp() + "ppm";
				resistance.setText(res);
				tol.setText(tolerance);
				temp.setText(tempp);
				panel.repaint();
				}
			}

			public void mouseReleased(MouseEvent e) {
				listenerTF = true;
			}

		});


		panel.add(Resistance);
		panel.add(resistance);
		panel.add(Tolerance);
		panel.add(tol);
		panel.add(Temp);
		panel.add(temp);
		panel.add(new graphics());
		frame.add(panel);
		panel.repaint();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);	
	}

	public void textFields(String in){
		long conversion = 0;
		String field = in;
		String conversionS = "";
		String firstBand = ""; 
		String secondBand = "";
		String thirdBand = "";

		//int periodCount;

		try{
			//periodCount = field.replaceAll("[^.]", "").length();
			if(!field.equalsIgnoreCase("0.0")&&!field.equalsIgnoreCase("")){
				String fieldNP = field.replace(".", "");
				//fieldNP = field.replace("\u2126", "");
				conversion = Long.parseLong(fieldNP)*100;
				conversionS = "" + conversion;
				double powConvert = Double.parseDouble(field);
				double powOut = 0;

				for(double i = -2.0; i <= 9.0; i = i + 1.0){
					if((powConvert / (Math.pow(10.0, i)) < 1000.0) && (powConvert / (Math.pow(10.0, i)) >= 100.0) ) {
						powOut = i;
					}

				}

				firstBand = conversionS.substring(0,1);
				secondBand = conversionS.substring(1,2);
				thirdBand = conversionS.substring(2,3);

				firstBandi = Integer.parseInt(firstBand);
				System.out.println("first Band" + firstBandi);
				secondBandi = Integer.parseInt(secondBand);
				System.out.println("second Band" + secondBandi);
				thirdBandi = Integer.parseInt(thirdBand);
				System.out.println("third Band" + thirdBandi);
				fourthBandi = (int)powOut;
				System.out.println("fourth Band" + fourthBandi);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		if(in.equalsIgnoreCase("")){
			firstBandi=0;
			secondBandi= 0;
			thirdBandi = 0;
			fourthBandi = 0;
		}



	System.out.print(fourthBandi);
	switch (firstBandi) {
	case 0: 
		resistor.change(1,"update",0);
		break;
	case 1: 
		resistor.change(1,"update",1);
		break;
	case 2: 
		resistor.change(1,"update",2);
		break;
	case 3: 
		resistor.change(1,"update",3);
		break;
	case 4: 
		resistor.change(1,"update",4);
		break;
	case 5: 
		resistor.change(1,"update",5);
		break;
	case 6: 
		resistor.change(1,"update",6);
		break;
	case 7: 
		resistor.change(1,"update",7);
		break;
	case 8: 
		resistor.change(1,"update",8);
		break;
	case 9: 
		resistor.change(1,"update",9);
		break;
	default: 
		resistor.change(1,"update",0);
	}
	
	switch (secondBandi) {
	case 0: 
		resistor.change(2,"update",0);
		break;
	case 1: 
		resistor.change(2,"update",1);
		break;
	case 2: 
		resistor.change(2,"update",2);
		break;
	case 3: 
		resistor.change(2,"update",3);
		break;
	case 4: 
		resistor.change(2,"update",4);
		break;
	case 5: 
		resistor.change(2,"update",5);
		break;
	case 6: 
		resistor.change(2,"update",6);
		break;
	case 7: 
		resistor.change(2,"update",7);
		break;
	case 8: 
		resistor.change(2,"update",8);
		break;
	case 9: 
		resistor.change(2,"update",9);
		break;
	default: 
		resistor.change(2,"update",0);
	}
	
	
	
	switch (thirdBandi) {
	case 0: 
		resistor.change(3,"update",0);
		break;
	case 1: 
		resistor.change(3,"update",1);
		break;
	case 2: 
		resistor.change(3,"update",2);
		break;
	case 3: 
		resistor.change(3,"update",3);
		break;
	case 4: 
		resistor.change(3,"update",4);
		break;
	case 5: 
		resistor.change(3,"update",5);
		break;
	case 6: 
		resistor.change(3,"update",6);
		break;
	case 7: 
		resistor.change(3,"update",7);
		break;
	case 8: 
		resistor.change(3,"update",8);
		break;
	case 9: 
		resistor.change(3,"update",9);
		break;
	default: 
		resistor.change(3,"update",0);
	}
	
	switch (fourthBandi) {
	case 0: 
		resistor.change(4,"update",0);
		break;
	case 1: 
		resistor.change(4,"update",1);
		break;
	case 2: 
		resistor.change(4,"update",2);
		break;
	case 3: 
		resistor.change(4,"update",3);
		break;
	case 4: 
		resistor.change(4,"update",4);
		break;
	case 5: 
		resistor.change(4,"update",5);
		break;
	case 6: 
		resistor.change(4,"update",6);
		break;
	case 7: 
		resistor.change(4,"update",7);
		break;
	case 8: 
		resistor.change(4,"update",8);
		break;
	case 9: 
		resistor.change(4,"update",9);
		break;
	default: 
		resistor.change(4,"update",0);
	}

}

public static void main(String[] args){
	resistor = new Resistor(0,0,0,0,0,0);
	EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
			ResistanceCalc GUI = new ResistanceCalc();
			GUI.createGUI();
		}
	});

}
}
