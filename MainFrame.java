import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private int dirFlag, objx = 0, objy = 0, objw = 205, objh = 222;
    private Container cp;
    private JPanel jpanSouth = new JPanel(new GridLayout(1,1,3,3));
    private JPanel jpanCenter = new JPanel(new GridLayout(1,6,3,3));
    private JLabel lab = new JLabel();
    private ImageIcon icon = new ImageIcon("Yauso.png");
    private Timer t1;
    private Timer t2;
    private JButton jbtn [] = new JButton[6];
    private boolean labflag = false;
    private int tarx,tary,newjlblx,newjlbly;
    private float m ;
    public MainFrame() {
    init();
    }
    private void init(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(120,50,800,800);
        this.cp = this.getContentPane();
        this.jpanCenter.setLayout(null);
        this.cp.setLayout(new BorderLayout(3, 3));
        this.cp.add(this.jpanCenter,"Center");
        this.cp.add(this.jpanSouth,"South");
        jpanCenter.add(lab);
        lab.setIcon(icon);
        lab.setBounds(objx,objy,objw,objh);
        jbtn[0] = new JButton("Run");
        jbtn[1] = new JButton("上");
        jbtn[2] = new JButton("下");
        jbtn[3] = new JButton("左");
        jbtn[4] = new JButton("右");
        jbtn[5] = new JButton("Exit");
        for(int i=0 ; i<6 ; i++){
            this.jpanSouth.add(jbtn[i]);
        }
        lab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               super.mouseClicked(e);
                labflag=true;
                }
        });
        jpanCenter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(labflag){
                    tarx = mouseEvent.getX();
                    tary = mouseEvent.getY();
                    m = (float)(tary-lab.getY())/(float)(tarx-lab.getX());
                    t2.start();
                    labflag = false;
                }
            }
        });
        t2 = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Math.abs(lab.getX()-tarx)<30 && Math.abs(lab.getY()-tary)<30){
                    t2.stop();
                }
                else{
                    if(lab.getX()-tarx<0){
                        newjlblx = lab.getX()+3;
                        }
                        else{
                        newjlblx = lab.getY()-3;
                    }
                    newjlbly = Math.round(m*(float)(newjlblx-tarx)+tary);
                    lab.setLocation(newjlblx,newjlbly);
                    }
            }
        });
        t1 = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (dirFlag){
                    case 1:
                        if(objy-2>0){
                            objy-=2;
                            }
                            break;
                    case 2:
                        if((objy+objh)<740){
                            objy+=2;
                        }
                        break;
                    case 3:
                        if((objx+objw)<800){
                            objx+=2;
                        }
                        break;
                    case 4:
                        if ((objx-2>0)){
                            objx-=2;
                        }
                        break;
                }
                lab.setLocation(objx,objy);
            }
        });
        jbtn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.start();
            }
        });
        jbtn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dirFlag =1;
            }
        });
        jbtn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dirFlag=2;
            }
        });
        jbtn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dirFlag=4;
            }
        });
        jbtn[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dirFlag=3;
            }
        });
        jbtn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
