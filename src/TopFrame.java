/**
 *
 * @author Aaron
 */
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TopFrame {

	private ControlPanel controls;
	private ColorGrid gridPanel;
	private JFrame frame;
	private long genDelay;
	private int genCount;
	private int rows;
	private int cols;

	public TopFrame() {
		createControls();

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

}