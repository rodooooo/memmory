import java.applet.*;
import java.awt.*;

public class ControlPanel extends Frame {
    private Kernel kernel;
    private static final int PAGE_COUNT = 64;

    Button runButton, stepButton, resetButton, exitButton;
    Button[] pageButtons = new Button[PAGE_COUNT];

    Label statusValueLabel;
    Label timeValueLabel;
    Label instructionValueLabel;
    Label addressValueLabel;
    Label pageFaultValueLabel;
    Label virtualPageValueLabel;
    Label physicalPageValueLabel;
    Label RValueLabel;
    Label MValueLabel;
    Label inMemTimeValueLabel;
    Label lastTouchTimeValueLabel;
    Label lowValueLabel;
    Label highValueLabel;

    Label segmentLabel;
    Label pageInputValueLabel;
    Label pagesLabel; // para setPagesInvolved

    Label[] physicalLabels = new Label[PAGE_COUNT];

    public ControlPanel() {
        this("");
    }

    public ControlPanel(String title) {
        super(title);
        setLayout(null);
        setBackground(Color.white);
        setForeground(Color.black);
        setFont(new Font("Courier", Font.PLAIN, 12));
        setSize(760, 640);
        initUI();
    }

    private void initUI() {
        runButton = makeButton("run", 10, 10, 70, 20, Color.blue, Color.lightGray);
        stepButton = makeButton("step", 90, 10, 70, 20, Color.blue, Color.lightGray);
        resetButton = makeButton("reset", 170, 10, 70, 20, Color.blue, Color.lightGray);
        exitButton = makeButton("exit", 250, 10, 70, 20, Color.blue, Color.lightGray);

        int colX[] = {10, 140};
        for (int i = 0; i < PAGE_COUNT; i++) {
            int col = (i < 32) ? 0 : 1;
            int row = (i % 32);
            Button b = new Button("page " + i);
            b.setBounds(colX[col], (row + 2) * 15 + 25, 70, 15);
            b.setForeground(Color.magenta);
            b.setBackground(Color.lightGray);
            add(b);
            pageButtons[i] = b;
        }

        for (int i = 0; i < PAGE_COUNT; i++) {
            Label l = new Label("", Label.CENTER);
            if (i < 32) {
                l.setBounds(75, (i + 2) * 15 + 25, 60, 15);
            } else {
                l.setBounds(205, ((i - 32) + 2) * 15 + 25, 60, 15);
            }
            l.setForeground(Color.red);
            l.setFont(new Font("Courier", Font.PLAIN, 10));
            add(l);
            physicalLabels[i] = l;
        }

        statusValueLabel = makeLabel("STOP", 400, 10, 140, 15);
        timeValueLabel = makeLabel("0", 400, 30, 140, 15);
        instructionValueLabel = makeLabel("NONE", 400, 60, 230, 15);
        addressValueLabel = makeLabel("NULL", 400, 80, 230, 15);
        pageFaultValueLabel = makeLabel("NO", 400, 110, 140, 15);
        virtualPageValueLabel = makeLabel("x", 400, 130, 230, 15);
        physicalPageValueLabel = makeLabel("0", 400, 150, 230, 15);
        RValueLabel = makeLabel("0", 400, 170, 230, 15);
        MValueLabel = makeLabel("0", 400, 190, 230, 15);
        inMemTimeValueLabel = makeLabel("0", 400, 210, 230, 15);
        lastTouchTimeValueLabel = makeLabel("0", 400, 230, 230, 15);
        lowValueLabel = makeLabel("0", 400, 250, 230, 15);
        highValueLabel = makeLabel("0", 400, 270, 230, 15);

        segmentLabel = makeLabel("", 400, 295, 230, 18);
        segmentLabel.setFont(new Font("Courier", Font.BOLD, 14));
        pageInputValueLabel = makeLabel("", 400, 315, 230, 15);

        pagesLabel = makeLabel("", 400, 335, 230, 15);

        add(makeLabel("status: ", 340, 10, 60, 15));
        add(makeLabel("time: ", 340, 30, 60, 15));
        add(makeLabel("instruction: ", 340, 60, 60, 15));
        add(makeLabel("address: ", 340, 80, 60, 15));
        add(makeLabel("page fault: ", 340, 110, 80, 15));
        add(makeLabel("virtual page: ", 340, 130, 90, 15));
        add(makeLabel("physical page: ", 340, 150, 90, 15));
        add(makeLabel("R: ", 340, 170, 90, 15));
        add(makeLabel("M: ", 340, 190, 90, 15));
        add(makeLabel("inMemTime: ", 340, 210, 90, 15));
        add(makeLabel("lastTouchTime: ", 340, 230, 90, 15));
        add(makeLabel("low: ", 340, 250, 90, 15));
        add(makeLabel("high: ", 340, 270, 90, 15));
        add(makeLabel("Segmento:", 340, 295, 60, 18));
        add(makeLabel("Pagina(s):", 340, 315, 60, 15));

        show();
    }

    private Button makeButton(String text, int x, int y, int w, int h, Color fg, Color bg) {
        Button b = new Button(text);
        b.setBounds(x, y, w, h);
        b.setForeground(fg);
        b.setBackground(bg);
        add(b);
        return b;
    }

    private Label makeLabel(String text, int x, int y, int w, int h) {
        Label l = new Label(text, Label.LEFT);
        l.setBounds(x, y, w, h);
        add(l);
        return l;
    }

    public void init(Kernel useKernel, String commands, String config) {
        this.kernel = useKernel;
        kernel.setControlPanel(this);
        kernel.init(commands, config);
        show();
    }

    public void paintPage(Page page) {
        if (page == null) return;
        virtualPageValueLabel.setText(Integer.toString(page.id));
        physicalPageValueLabel.setText(Integer.toString(page.physical));
        RValueLabel.setText(Integer.toString(page.R));
        MValueLabel.setText(Integer.toString(page.M));
        inMemTimeValueLabel.setText(Integer.toString(page.inMemTime));
        lastTouchTimeValueLabel.setText(Integer.toString(page.lastTouchTime));
        lowValueLabel.setText(Long.toString(page.low, Kernel.addressradix));
        highValueLabel.setText(Long.toString(page.high, Kernel.addressradix));
    }

    public void addPhysicalPage(int pageNum, int physicalPage) {
        if (physicalPage >= 0 && physicalPage < physicalLabels.length) {
            physicalLabels[physicalPage].setText("page " + pageNum);
        }
    }

    public void removePhysicalPage(int physicalPage) {
        if (physicalPage >= 0 && physicalPage < physicalLabels.length) {
            physicalLabels[physicalPage].setText("");
        }
    }

    public void setPagesInvolved(String s) {
        if (pagesLabel != null) pagesLabel.setText(s);
    }

    public void setStatus(String s) {
        if (statusValueLabel != null) statusValueLabel.setText(s);
    }

    public boolean action(Event e, Object arg) {
        if (e.target == runButton) {
            setStatus("RUN");
            runButton.setEnabled(false);
            stepButton.setEnabled(false);
            resetButton.setEnabled(false);
            if (kernel != null) kernel.run();
            setStatus("STOP");
            resetButton.setEnabled(true);
            return true;
        } else if (e.target == stepButton) {
            setStatus("STEP");
            if (kernel != null) kernel.step();
            setStatus("STOP");
            return true;
        } else if (e.target == resetButton) {
            if (kernel != null) kernel.reset();
            runButton.setEnabled(true);
            stepButton.setEnabled(true);
            return true;
        } else if (e.target == exitButton) {
            System.exit(0);
            return true;
        } else {
            for (int i = 0; i < pageButtons.length; i++) {
                if (e.target == pageButtons[i]) {
                    if (kernel != null) kernel.getPage(i);
                    return true;
                }
            }
        }
        return false;
    }
}