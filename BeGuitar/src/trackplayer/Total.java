package trackplayer;

import chordscale.ChordTab;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Total extends JPanel {

    int WIDTH;
    int HEIGHT;
    public TrackPlayer tp;
    public ImageDrag id;
    DropTarget dt;

    public Total(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        id = new ImageDrag();
        tp = new TrackPlayer(WIDTH - 200, HEIGHT - 50,id);
        dt = new DropTarget(tp, tp);
        this.setFocusable(false);

    }

    public void disableImage(){
        id.but.setEnabled(false);
        id.setEnabled(false);
    }
    public void enableImage(){
        id.but.setEnabled(true);
        id.setEnabled(true);
    }

    public void setChord(String s) {
        //System.out.println("changing chord");
        tp.setChord(s);
    }

    public void setChordTab(ChordTab c){
        tp.setChordTab(c);
    }

    public void displayGUI() {
        setLayout(new BorderLayout());
        this.add(id, BorderLayout.NORTH);
        this.add(new ButtonGroup(tp, this), BorderLayout.WEST);
        this.add(new Right(tp), BorderLayout.EAST);
        this.add(tp, BorderLayout.CENTER);
        this.add(new Bottom(tp), BorderLayout.SOUTH);
        tp.setVisible(true);
        id.setVisible(true);
        this.setVisible(true);
        this.repaint();

    }

    public void save(String filename){ tp.save(filename); }
    public void load(String filename){ tp.load(filename); }
    public void newTrack(){ tp.newTrack(); }


    public void delete() {
        tp.delete();
    }
}


class ButtonGroup extends JPanel {

    ButtonGroup(TrackPlayer tp, Total a) {
        this.setFocusable(false);
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(new Buttons(tp, a));
        //this.add(Box.createRigidArea(new Dimension(10, 0)));

        setVisible(true);
    }
}

class Buttons extends JPanel {
    Buttons(final TrackPlayer tp, Total a) {
        this.setFocusable(false);
        setLayout(new GridLayout(1, 2));
        this.add(new Vol());
        this.add(new B(tp,a));
    }
}
class B extends JPanel {
    boolean isRepeat = false;
    boolean isPlay = false;
    ImageIcon play = new ImageIcon("play.png");
    ImageIcon stop = new ImageIcon("stop.png");
    ImageIcon repeat = new ImageIcon("repeat.png");
    ImageIcon deleter = new ImageIcon("delete.png");
    ImageIcon repeater = new ImageIcon("control_repeat.png");
    ImageIcon player = new ImageIcon("control_play.png");
    JButton p = new JButton(play);
    JButton s = new JButton(stop);
    JButton r = new JButton(repeat);

    B(final TrackPlayer tp, Total a) {
        tp.buttonUpdate = this;
        this.setFocusable(false);

        r.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  if(!isRepeat){ r.setIcon(repeater); isRepeat = true; tp.repeat();}
                  else {r.setIcon(repeat); isRepeat = false; tp.unrepeat();}
            }
        });

        p.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  if(!isPlay){ p.setIcon(player); isPlay = true;}
                  tp.play();
            }
        });
        s.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  if(isPlay){ p.setIcon(play); isPlay = false;}
                  tp.stop();
            }
        });
        setLayout(new GridLayout(0, 1));
        p.setToolTipText("Play");

        this.add(p);
        s.setToolTipText("Stop");
        this.add(s);
        r.setToolTipText("Repeat");
        this.add(r);
    }
}

class Vol extends JPanel {

    Vol() {
        this.setFocusable(false);
        //setLayout(new GridLayout(0,2));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JSlider volume = new JSlider(JSlider.VERTICAL, 0, 100, 50);
        volume.setToolTipText("Adjust Volume");
        this.add(new VolImage());
        this.add(volume);
        //this.add(Box.createRigidArea(new Dimension(50, 0)));
    }
}

class VolImage extends JPanel {

    VolImage() {
        this.setFocusable(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel(new ImageIcon("sound.png")));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(new JLabel(new ImageIcon("sound_none.png")));
    }
}

class Bottom extends JPanel {

    Bottom(final TrackPlayer tp) {
        this.setFocusable(false);
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        final JSlider bpm = new JSlider(JSlider.HORIZONTAL);
        bpm.setSnapToTicks(true);
        bpm.setInverted(true);
        bpm.setMinimum(50);
        bpm.setMaximum(400);
        bpm.setMinorTickSpacing(20);
        bpm.setValue(225);
        bpm.setToolTipText("Adjust Beats Per Minute (BPM)");
        final JLabel currBPM = new JLabel(Integer.toString(bpm.getValue()), JLabel.CENTER);
        bpm.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                tp.updateBPM(bpm.getValue());
                int n = bpm.getValue();
                int diff = 225-n;
                n = 225+diff;
                String num = Integer.toString(n);
                if(num.length()==2) num = "0"+num;
                currBPM.setText(num);
            }
        });
        //this.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel sliderLabel = new JLabel("BPM", JLabel.CENTER);
        this.add(sliderLabel);
        this.add(bpm);
        this.add(currBPM);

    }
}

class Right extends JPanel {

    Right(TrackPlayer tp) {
        this.setFocusable(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.add(Box.createRigidArea(new Dimension(50, 10)));
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(new TotalRight(tp));
        //delete.addActionListener(new ActionListener() {

        //  public void actionPerformed(ActionEvent e) {
        //   tp.delete();
        // }
        //});
        //this.add(delete);
        //BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        //this.setLayout(layout);
        //this.add(new JSlider(JSlider.VERTICAL));
        //this.add(new JLabel("Vol.", JLabel.LEADING));
        //this.add(Box.createRigidArea(new Dimension(50, 50)));
        //this.add(new JSlider(JSlider.VERTICAL));
        //this.add(new Vol());
    }
}

class TotalRight extends JPanel {

    TotalRight(TrackPlayer tp) {
        this.setFocusable(false);
        //setLayout(new GridLayout(2,0));
        //this.add(new BPM());
        this.add(new DeleteButton(tp));
    }
}

class DeleteButton extends JPanel {

    ImageIcon deleter = new ImageIcon("delete.png");

    DeleteButton(final TrackPlayer tp) {
        this.setFocusable(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JButton delete = new JButton(deleter);
        delete.setToolTipText("Delete Selected Note/Chord");
        this.add(delete);
        this.add(Box.createRigidArea(new Dimension(25, 0)));
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(tp.canClick)
                    tp.delete();
            }
        });
    }
}
