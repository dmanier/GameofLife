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

		controls = new ControlPanel(this);

		frame = new JFrame();
		frame.setTitle("The Game of Life");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					exit();
				}
		});
		gridPanel = new ColorGrid(rows,cols);

		JPanel main = new JPanel();
		main.setLayout(new FlowLayout());
		main.add(gridPanel);
		main.add(controls.getPanel());

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

    public void setGenTextField() {controls.setGenTextField(this.genCount);}
	public void calculateGrid() {gridPanel.calculateGrid();}
	public void updateGenCount(){this.genCount++;}
	public void clearGenCount(){
		this.genCount = 0;
		controls.setGenTextField(this.genCount);
	}
	public long getGenDelay() { return this.genDelay; }
	public void setGenDelay(long genDelay) {this.genDelay = genDelay; }
	public void randomGrid() {gridPanel.randomGrid();}
	public void clearGrid() {gridPanel.resetGrid();}
	public void setGridChangeable(boolean isChangeable){gridPanel.setChangeable(isChangeable);}
}
