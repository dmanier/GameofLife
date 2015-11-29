/**
 *
 * @author Aaron
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class TopFrame {

	private ControlPanel controls;
	private ColorGrid gridPanel;
	private JFrame frame;
	private long genDelay;
	private int genCount;
	private int rows;
	private int cols;
	private JSONArray patterns;
	private ArrayList<String> defaultOptions = new ArrayList<>();

	public TopFrame() {
		this.patterns = getPatterns();
		createControls();
		definedGrid("Pentadecathlon");

	}


	private void createControls() {
		rows = 20;
		cols = 20;
		genCount = 0;
		genDelay = 2000L;

		//instantiates a control panel
		controls = new ControlPanel(this);

		//instantiates a ColorGrid
		gridPanel = new ColorGrid(rows, cols);

		//sets up top frame
		frame = new JFrame();
		frame.setTitle("The Game of Life");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		//create top level panel
		JPanel main = new JPanel();
		main.setLayout(new FlowLayout());

		//add grid and control panel
		main.add(gridPanel);
		main.add(controls.getPanel());

		//set additional frame settings
		frame.setLayout(new FlowLayout());
		frame.add(main);
		frame.setSize(700, 500);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	public void exit() {
		frame.dispose();
		System.exit(0);
	}

	public JSONArray getPatterns(){
		JSONParser parser = new JSONParser();
		String emptyJson = "{\"Type\":\"Empty\",\"Name\":\"None\",\"Grids\":[]}";
		JSONArray jsonArray = new JSONArray();
		try {
			Object obj = parser.parse(new FileReader(getClass().getResource("patterns.json").getFile()));
			jsonArray = (JSONArray) obj;
			jsonArray.add(0,parser.parse(emptyJson));
		}catch(Exception e1){
			e1.printStackTrace();

		}
		Iterator<JSONObject> iterator = jsonArray.iterator();
		while(iterator.hasNext()){
			JSONObject nextItem = iterator.next();
			System.out.println(nextItem);
			String nextOption = nextItem.get("Name").toString();
			System.out.println(nextOption);
			this.defaultOptions.add(nextOption);
			System.out.println("Added next item.");

		}
		return jsonArray;


	}

	public ArrayList<String> getDefaultOptions(){ return this.defaultOptions;}
	/*
	several wrapper methods exist so that the control panel can pass commands to the grid
	and vice versa without direct connections to eachother that would require dependencies
	in both directions
	 */

	//wrapper over control panel setGenTextField
	public void setGenTextField() {
		controls.setGenTextField(this.genCount);
	}

	//resets genCount to 0
	public void clearGenCount() {
		this.genCount = 0;
		controls.setGenTextField(this.genCount);
	}

	//increments genCount
	public void updateGenCount() {
		this.genCount++;
	}

	//return how long the delay between calculations is
	public long getGenDelay() {
		return this.genDelay;
	}

	//sets how long the delay betweens calculations is
	public void setGenDelay(long genDelay) {
		this.genDelay = genDelay;
	}

	//sets if the grid is in a changeable state
	public void setGridChangeable(boolean isChangeable) {
		gridPanel.setChangeable(isChangeable);
	}

	//wrapper around the ColorGrid calculateGrid method
	public void calculateGrid() {
		gridPanel.calculateGrid();
	}


	//wrapper around the ColorGrid randomGrid method
	public void randomGrid() {
		gridPanel.randomGrid();
	}

	//wrapper around the ColorGrid resetGrid method
	public void clearGrid() {
		gridPanel.resetGrid();
	}
	
	public void definedGrid(String patternName) {
		Iterator<JSONObject> iterator = this.patterns.iterator();
		JSONArray grids = new JSONArray();
		while (iterator.hasNext()) {
			JSONObject nextItem = iterator.next();
			System.out.println(nextItem);
			String nextName = nextItem.get("Name").toString();
			System.out.println(nextName);
			if (nextName.toString().equals(patternName)) {
				grids = (JSONArray) nextItem.get("Grids");
				break;
			}
		}
		gridPanel.definedGrid(grids);
	}

}
