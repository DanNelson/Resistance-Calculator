import java.awt.Color;

public class Resistor {
	public String resistance = "";
	public String totalResistance = "";
	public double multiplier = 0;
	public double band1,band2,band3,band4,band5,band6 = 0;
	Color bandColor[];
	Color multColor[];
	Color tolColor[];
	Color tempColor[];
	public double[] bands3, multiplierBand,toleranceBand,tempBand;

	public Resistor(double band1In, double band2In, double band3In, double band4In, double band5In, double band6In){
		band1 = band1In;
		band2 = band2In;
		band3 = band3In;
		band4 = band4In;
		band5 = band5In;
		band6 = band6In;
		bandColor = new Color[] {new Color(202,162,101),new Color(99,74,54),
				Color.RED,new Color(237,97,2), Color.YELLOW, new Color(21,92,52),Color.BLUE, new Color(126,11,128),
				new Color(128,128,128), Color.WHITE};
		multColor = new Color[]{new Color(202,162,101),new Color(222,230,232), new Color(247,212,119),new Color(0,0,0),new Color(99,74,54),
				Color.RED,new Color(237,97,2), Color.YELLOW, new Color(21,92,52),Color.BLUE};
		tolColor = new Color[]{new Color(202,162,101),new Color(222,230,232), new Color(247,212,119),new Color(99,74,54),Color.RED,
				new Color(21,92,52),Color.BLUE, new Color(126,11,128),new Color(128,128,128)};
		tempColor = new Color[]{new Color(202,162,101),new Color(99,74,54),Color.RED,new Color(237,97,2), Color.YELLOW};
		bands3 = new double[]{0,1,2,3,4,5,6,7,8,9};
		multiplierBand = new double[]{0,.01,.1,1,10,100,1000,10000,100000,1000000,10000000};
		toleranceBand = new double[]{0,10,5,1,2,.5,.25,.1,.05};
		tempBand = new double[]{0,100,50,15,25};
	}

	public String calculateResistance(){
		String str = "";
		int band1Calc, band2Calc, band3Calc;
		double total;
		band1Calc =(int) bands3[(int)band1];
		band2Calc = (int)bands3[(int)band2];
		band3Calc = (int)bands3[(int)band3];
		str = "" + band1Calc + band2Calc + band3Calc;
		total = Double.parseDouble(str);
		total = total * multiplierBand[(int)band4];
		str = Double.toString(total);
		return str;	
	}
	
	public String getTol(){
		String str = "" + toleranceBand[(int)band5];
		return str;
	}
	
	public String getTemp(){
		String str = "" + tempBand[(int)band6];
		return str;
	}
	
	public void change(int i, String j, int k){
		if(i == 1 && j.equalsIgnoreCase("incriment")){
			if(band1 == 9){
				band1 = 0;
			}
			else{
				band1++;
			}
		}
		else if(i == 1 && j.equalsIgnoreCase("update")){
			band1 = k;
		}
		
		else if(i == 2 && j.equalsIgnoreCase("incriment")){
			if(band2 == 9){
				band2 = 0;
			}
			else{
				band2++;
			}
		}
		else if(i == 2 && j.equalsIgnoreCase("update")){
			band2 = k;
		}
		else if(i == 3 && j.equalsIgnoreCase("incriment")){
			if(band3 == 9){
				band3 = 0;
			}
			else{
				band3++;
			}
		}
		else if(i == 3 && j.equalsIgnoreCase("update")){
			band3 = k;
		}
		else if(i == 4 && j.equalsIgnoreCase("incriment")){
			if(band4 == 9){
				band4 = 0;
			}
			else{
					band4++;	
			}
		}
		else if(i == 4 && j.equalsIgnoreCase("update")){
			band4 = k;
		}
		else if(i == 5 && j.equalsIgnoreCase("incriment")){
			if(band5 == 8){
				band5 = 0;
			}
			else{
				band5++;	
			}
		}
		else if(i == 5 && j.equalsIgnoreCase("update")){
			
		}
		else if(i == 6 && j.equalsIgnoreCase("incriment")){
			if(band6 == 4){
				band6 = 0;
			}
			else{
				band6++;	
			}
		}
		else if(i == 6 && j.equalsIgnoreCase("update")){
			
		}
	}
		

	
	public Color getColor3(int i){
		if(i == 1){
			i = (int) band1;
		return bandColor[i];
		}
		if(i == 2){
			i = (int)band2;
		return bandColor[i];
		}
		if(i==3){
			i = (int)band3;
		return bandColor[i];
		}
		if(i==4){
			i = (int)band4;
			return multColor[i];
		}
		if(i==5){
			i = (int)band5;
			return tolColor[i];		
		}
		if(i==6){
			i = (int)band6;
			return tempColor[i];		
		}
		else{
		return bandColor[0];
		}
		
	}
	                                 
	
}
