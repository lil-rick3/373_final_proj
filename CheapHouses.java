

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * 
 * @author Jaret Rickel
 *
 *PA2 cheaphouses.java
 *This program creates a gui that askss for a file and a price limit
 *it then maps the houses on a panel that are below the price limit
 */

public class CheapHouses
{
	/**
	 * This is the main graphics function, it basically produces all the 
	 * graphics and organizes them. I chose to not make it smaller because it 
	 * needed to make sense
	 */
	public static void createAndShowGUI()
	{
		int mapSize = 600;
		JFrame mainFrame = new JFrame("Cheap house");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(700,700);
		
		
		
		JPanel mainPanel = new JPanel(null);
		JPanel widgetsPanel = new JPanel();
		widgetsPanel.setLocation(0, 600);
		widgetsPanel.setSize(600,200);
		JButton plotButton = new JButton("Plot");
		

		JTextField fileField = new JTextField("houses.csv");
		fileField.setColumns(10);
		
		JLabel fileLabel = new JLabel("File:");		
		JLabel priceLabel = new JLabel("Price:");	
		
		JTextField moneyField = new JTextField("225000");
		moneyField.setColumns(10);
		
		
		
		widgetsPanel.add(fileLabel);
		widgetsPanel.add(fileField);
		widgetsPanel.add(priceLabel);
		widgetsPanel.add(moneyField);		
		widgetsPanel.add(plotButton);
		
		
		JPanel graphicsPanel = new GPanel(fileField, moneyField, mapSize);
		graphicsPanel.setLocation(0, 0);
		graphicsPanel.setSize(mapSize,mapSize);
		graphicsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(graphicsPanel);
		mainPanel.add(widgetsPanel);
		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		
		
		plotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				((GPanel) graphicsPanel).paint_chart();
			}
		});
		
	}
		
		
	
	public static void main(String[] args)
	{
		createAndShowGUI();
		
	}

	
	/**
	 * 
	 * @author Jaret Rickel
	 *GPAnel is just a special JPanel that takes in the text fields and
	 *when the button is pressed, it will paint the coordinates
	 */
	public static class GPanel extends JPanel
	{
		
		private JTextField fileField;
		private JTextField moneyField;
		private int mapSize;
		public GPanel(JTextField fileText, JTextField moneyText, int mSize)
		{
			fileField = fileText;
			moneyField = moneyText;
			mapSize = mSize;
			
			
		}
		public void paintComponent(Graphics g) {
			paint_chart();
		}
		
		public void paint_chart() {
			
			int maxMoney = Integer.valueOf(moneyField.getText());
			
			double[] coordinateVals = {0,0,0,0};
			/**
			 * coordinateVals is an array of the max and min longitude and latidue
			 * indexes:
			 * 0->minLat
			 * 1->maxLat
			 * 2->minLong
			 * 3->maxLong
			 * I pass it by reference to functions in order to find the bounds of the
			 * map
			 */
			int[] tempLocation = {0,0};
			//x,y coordinates of the house
			Map <String, House> houseMap = null;
			try {
				houseMap = createMap(fileField.getText(), 
						coordinateVals, maxMoney);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Graphics g = getGraphics();
			g.setColor(Color.WHITE);
			
			g.fillRect(0, 0, mapSize, mapSize);
			for(House tempHouse:houseMap.values()) {
				tempLocation = returnLocationOnMap(coordinateVals, mapSize, tempHouse);
				g.setColor(Color.BLACK);
				g.fillOval(tempLocation[0], tempLocation[1], 5, 5);
				
			}
		}
		/**
		 * this function takes in lat and long coordinates, and returns a location
		 * on the map
		 * 
		 * 
		 * @param maxCoord the coordinates of max and min long and lat
		 * @param dim the size of windoe
		 * @param inpHouse, the input house that the coordinates are to be found
		 * @return a pair of x and y coordinates that correspond to the gui
		 */
		private int[] returnLocationOnMap(double[] maxCoord, int dim, House inpHouse){
			int[] location = {0,0};
			
			
			double longDif = (maxCoord[3] - maxCoord[2])*1.02;
			double latDif = (maxCoord[1] - maxCoord[0])*1.02;
			//the 1.02 gives a little room on the far sides 
			
			location[0] = (int) (dim * (inpHouse.getLongitude() - maxCoord[2])/longDif);
			location[1] = (int) (dim * (inpHouse.getLatitude() - maxCoord[0])/latDif);
			
			return location;
			
			
		}
		/**
		 * takes in a filename, and a max price, and returns a map of those 
		 * houses that fit the price bounds. I chose to remake the map every 
		 * time, because it is easier to do that, and if the filename changes, 
		 * you will still have to do that anyways.
		 */
		private  Map<String, House> createMap(String fileName, double[] coordArr, int maxPrice) throws FileNotFoundException
		{
			String[] lineArr = null;
			
			Scanner fileIn = new Scanner(new File(fileName));
			House tempHouse;
			
			boolean isFirstIteration = true;
			Map <String, House> houseMap = new HashMap<>();
			
			fileIn.useDelimiter("\n");
			fileIn.next();
			
			
			while (fileIn.hasNext()) {
				lineArr = fileIn.next().split(",");			
				
					if(Integer.parseInt(lineArr[9]) < maxPrice){
						//checks if the house is below price
						tempHouse = new House(lineArr);
						if (isFirstIteration) {
							coordArr[0] = tempHouse.getLatitude();
							coordArr[1] = tempHouse.getLatitude();
							coordArr[2] = tempHouse.getLongitude();
							coordArr[3] = tempHouse.getLongitude();
							
							isFirstIteration = false;
							
							
						}
						else {
							
							processHouse(coordArr, tempHouse);	
						}
						houseMap.put(lineArr[0], tempHouse);
						
					}
					
			}
			
			return houseMap;
			
		}
		/**
		 * 
		 * @param coordVals
		 * the max and min valuse of coordinates for mapping everything
		 * @param tempHouse
		 * the house created in createmap() that is then proccessed here
		 */
		private void processHouse(double[] coordVals, House tempHouse) {			
				
				
				if(tempHouse.getLatitude() < coordVals[0]) {
					coordVals[0] = tempHouse.getLatitude();
				}
				if(tempHouse.getLatitude() > coordVals[1]) {
					coordVals[1] = tempHouse.getLatitude();
				}
				if(tempHouse.getLongitude() < coordVals[2]) {
					coordVals[2] = tempHouse.getLongitude();
				}
				if(tempHouse.getLongitude() > coordVals[3]) {
					coordVals[3] = tempHouse.getLongitude();
				}
				
			
			
		}
		
	
	

/**
 * 
 * @author Jaret Rickel
 *House class stores the address and other info about the house 
 *
 */

class House{
	
	
	private double latitude;
	private double longitude;
	private int price;
	private String address;
	
	
	
	House(String[] inpArr){
		address = inpArr[0];
		price = Integer.parseInt(inpArr[9]);
		latitude = Double.parseDouble(inpArr[10]);
		longitude = Double.parseDouble(inpArr[11]);
		
	}



	public double getLatitude() {
		return latitude;
	}


	public double getLongitude() {
		return longitude;
	}



	public int getPrice() {
		return price;
	}


	public String getAddress() {
		return address;
	}
	
	
}
	}

}


