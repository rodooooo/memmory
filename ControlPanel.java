import java.applet.*;
import java.awt.*;

public class ControlPanel extends Frame 
{
  Kernel kernel ;
  Button runButton = new Button("run");
  Button stepButton = new Button("step");
  Button resetButton = new Button("reset");
  Button exitButton = new Button("exit");
  Button b0 = new Button("page " + (0));
  Button b1 = new Button("page " + (1));
  Button b2 = new Button("page " + (2));
  Button b3 = new Button("page " + (3));
  Button b4 = new Button("page " + (4));
  Button b5 = new Button("page " + (5));
  Button b6 = new Button("page " + (6));
  Button b7 = new Button("page " + (7));
  Button b8 = new Button("page " + (8));
  Button b9 = new Button("page " + (9));
  Button b10 = new Button("page " + (10));
  Button b11 = new Button("page " + (11));
  Button b12 = new Button("page " + (12));
  Button b13 = new Button("page " + (13));
  Button b14 = new Button("page " + (14));
  Button b15 = new Button("page " + (15));
  Button b16 = new Button("page " + (16));
  Button b17 = new Button("page " + (17));
  Button b18 = new Button("page " + (18));
  Button b19 = new Button("page " + (19));
  Button b20 = new Button("page " + (20));
  Button b21 = new Button("page " + (21));
  Button b22 = new Button("page " + (22));
  Button b23 = new Button("page " + (23));
  Button b24 = new Button("page " + (24));
  Button b25 = new Button("page " + (25));
  Button b26 = new Button("page " + (26));
  Button b27 = new Button("page " + (27));
  Button b28 = new Button("page " + (28));
  Button b29 = new Button("page " + (29));
  Button b30 = new Button("page " + (30));
  Button b31 = new Button("page " + (31));
  Button b32 = new Button("page " + (32));
  Button b33 = new Button("page " + (33));
  Button b34 = new Button("page " + (34));
  Button b35 = new Button("page " + (35));
  Button b36 = new Button("page " + (36));
  Button b37 = new Button("page " + (37));
  Button b38 = new Button("page " + (38));
  Button b39 = new Button("page " + (39));
  Button b40 = new Button("page " + (40));
  Button b41 = new Button("page " + (41));
  Button b42 = new Button("page " + (42));
  Button b43 = new Button("page " + (43));
  Button b44 = new Button("page " + (44));
  Button b45 = new Button("page " + (45));
  Button b46 = new Button("page " + (46));
  Button b47 = new Button("page " + (47));
  Button b48 = new Button("page " + (48));
  Button b49 = new Button("page " + (49));
  Button b50 = new Button("page " + (50));
  Button b51 = new Button("page " + (51));
  Button b52 = new Button("page " + (52));
  Button b53 = new Button("page " + (53));
  Button b54 = new Button("page " + (54));
  Button b55 = new Button("page " + (55));
  Button b56 = new Button("page " + (56));
  Button b57 = new Button("page " + (57));
  Button b58 = new Button("page " + (58));
  Button b59 = new Button("page " + (59));
  Button b60 = new Button("page " + (60));
  Button b61 = new Button("page " + (61));
  Button b62 = new Button("page " + (62));
  Button b63 = new Button("page " + (63));
  Label statusValueLabel = new Label("STOP" , Label.LEFT) ;
  Label timeValueLabel = new Label("0" , Label.LEFT) ;
  Label instructionValueLabel = new Label("NONE" , Label.LEFT) ;
  Label addressValueLabel = new Label("NULL" , Label.LEFT) ;
  Label pageFaultValueLabel = new Label("NO" , Label.LEFT) ;
  Label virtualPageValueLabel = new Label("x" , Label.LEFT) ;
  Label physicalPageValueLabel = new Label("0" , Label.LEFT) ;
  Label RValueLabel = new Label("0" , Label.LEFT) ;
  Label MValueLabel = new Label("0" , Label.LEFT) ;
  Label inMemTimeValueLabel = new Label("0" , Label.LEFT) ;
  Label lastTouchTimeValueLabel = new Label("0" , Label.LEFT) ;
  Label lowValueLabel = new Label("0" , Label.LEFT) ;
  Label highValueLabel = new Label("0" , Label.LEFT) ;
  Label l0 = new Label(null, Label.CENTER);
  Label l1 = new Label(null, Label.CENTER);
  Label l2 = new Label(null, Label.CENTER);
  Label l3 = new Label(null, Label.CENTER);
  Label l4 = new Label(null, Label.CENTER);
  Label l5 = new Label(null, Label.CENTER);
  Label l6 = new Label(null, Label.CENTER);
  Label l7 = new Label(null, Label.CENTER);
  Label l8 = new Label(null, Label.CENTER);
  Label l9 = new Label(null, Label.CENTER);
  Label l10 = new Label(null, Label.CENTER);
  Label l11 = new Label(null, Label.CENTER);
  Label l12 = new Label(null, Label.CENTER);
  Label l13 = new Label(null, Label.CENTER);
  Label l14 = new Label(null, Label.CENTER);
  Label l15 = new Label(null, Label.CENTER);
  Label l16 = new Label(null, Label.CENTER);
  Label l17 = new Label(null, Label.CENTER);
  Label l18 = new Label(null, Label.CENTER);
  Label l19 = new Label(null, Label.CENTER);
  Label l20 = new Label(null, Label.CENTER);
  Label l21 = new Label(null, Label.CENTER);
  Label l22 = new Label(null, Label.CENTER);
  Label l23 = new Label(null, Label.CENTER);
  Label l24 = new Label(null, Label.CENTER);
  Label l25 = new Label(null, Label.CENTER);
  Label l26 = new Label(null, Label.CENTER);
  Label l27 = new Label(null, Label.CENTER);
  Label l28 = new Label(null, Label.CENTER);
  Label l29 = new Label(null, Label.CENTER);
  Label l30 = new Label(null, Label.CENTER);
  Label l31 = new Label(null, Label.CENTER);
  Label l32 = new Label(null, Label.CENTER);
  Label l33 = new Label(null, Label.CENTER);
  Label l34 = new Label(null, Label.CENTER);
  Label l35 = new Label(null, Label.CENTER);
  Label l36 = new Label(null, Label.CENTER);
  Label l37 = new Label(null, Label.CENTER);
  Label l38 = new Label(null, Label.CENTER);
  Label l39 = new Label(null, Label.CENTER);
  Label l40 = new Label(null, Label.CENTER);
  Label l41 = new Label(null, Label.CENTER);
  Label l42 = new Label(null, Label.CENTER);
  Label l43 = new Label(null, Label.CENTER);
  Label l44 = new Label(null, Label.CENTER);
  Label l45 = new Label(null, Label.CENTER);
  Label l46 = new Label(null, Label.CENTER);
  Label l47 = new Label(null, Label.CENTER);
  Label l48 = new Label(null, Label.CENTER);
  Label l49 = new Label(null, Label.CENTER);
  Label l50 = new Label(null, Label.CENTER);
  Label l51 = new Label(null, Label.CENTER);
  Label l52 = new Label(null, Label.CENTER);
  Label l53 = new Label(null, Label.CENTER);
  Label l54 = new Label(null, Label.CENTER);
  Label l55 = new Label(null, Label.CENTER);
  Label l56 = new Label(null, Label.CENTER);
  Label l57 = new Label(null, Label.CENTER);
  Label l58 = new Label(null, Label.CENTER);
  Label l59 = new Label(null, Label.CENTER);
  Label l60 = new Label(null, Label.CENTER);
  Label l61 = new Label(null, Label.CENTER);
  Label l62 = new Label(null, Label.CENTER);
  Label l63 = new Label(null, Label.CENTER);

  Label segmentLabel = new Label("", Label.LEFT);
  // Etiqueta para mostrar la página ingresada
  Label pageInputLabel = new Label("Página/s en comando: ", Label.LEFT);
  Label pageInputValueLabel = new Label("", Label.LEFT);

  public ControlPanel() 
  {
    super();
  }

  public ControlPanel( String title ) 
  {
    super(title);
  }

  public void init( Kernel useKernel , String commands , String config ) 
  {
    kernel = useKernel ;
    kernel.setControlPanel( this );
    setLayout( null );
    setBackground( Color.white );
    setForeground( Color.black );
    resize( 635 , 545 );
    setFont( new Font( "Courier", 0, 12 ) );   

    // ... (resto de tu init igual que antes) ...
    segmentLabel.reshape(395, 265, 150, 15);
    segmentLabel.setFont(new Font("Courier", Font.BOLD, 14));
    add(segmentLabel);

    pageInputLabel.reshape(285, 285, 110, 15);
    pageInputLabel.setFont(new Font("Courier", Font.PLAIN, 12));
    add(pageInputLabel);
    pageInputValueLabel.reshape(395, 285, 150, 15);
    pageInputValueLabel.setFont(new Font("Courier", Font.PLAIN, 12));
    add(pageInputValueLabel);

    // ... (resto de tu init igual que antes) ...
    kernel.init( commands , config );
    show();
  }
  // ... resto de tu clase igual ...
}