package trackplayer;

import chordscale.ChordTab;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Line2D;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextLayout;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrackPlayer extends Canvas implements DropTargetListener,
        MouseListener {

    volatile int X;
    volatile int Y;
    volatile int WIDTH;
    volatile int HEIGHT;
    volatile double TRACKX;
    volatile double TRACKY;
    volatile double TRACKW;
    volatile double TRACKH;
    volatile double ROUND;
    volatile RoundRectangle2D.Double trackShape;
    volatile RoundRectangle2D.Double trackBorder;
    volatile GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    volatile String name = ge.getAllFonts()[0].getName();
    volatile Font font = new Font(name, Font.PLAIN, 12);
    volatile double MINORTICKSPACING;
    volatile Track track = new Track(16);
    volatile boolean initial = true;
    volatile int selectedNote = -1;
    volatile String chord = "C";
    volatile BufferStrategy buff;
    volatile boolean canClick = true;
    volatile B buttonUpdate;
    volatile int BPM = 225;
    ExecutorService backgroundExec = Executors.newCachedThreadPool();
    PlayThread pt;//= new PlayThread(this);
    ChordTab tab;
    ImageDrag id;

    public TrackPlayer(int w, int y, ImageDrag i) {
        id = i;
        pt = new PlayThread(this, id);
        X = 0;
        Y = 0;
        WIDTH = w;
        HEIGHT = y;
        setBounds(X, Y, WIDTH, HEIGHT);
        this.setFocusable(false);
        //TRACKX = WIDTH * .1;
        //TRACKY = HEIGHT * .1;
        TRACKX = 5;
        TRACKY = 5;
        TRACKW = WIDTH - 5;
        TRACKH = HEIGHT - 5;
        //TRACKW = WIDTH - (TRACKX * 2);
        //TRACKH = HEIGHT - (TRACKY * 2);
        ROUND = WIDTH / 50;

        trackShape = new RoundRectangle2D.Double(TRACKX,
                TRACKY,
                TRACKW,
                TRACKH,
                ROUND,
                ROUND);
        trackBorder = new RoundRectangle2D.Double(TRACKX - 1,
                TRACKY - 1,
                TRACKW + 1,
                TRACKH + 1,
                ROUND,
                ROUND);

        MINORTICKSPACING = (TRACKW - (ROUND * 2)) / 16;
        addMouseListener(this);
        setVisible(true);
    }

    public synchronized void save(String filename) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(track);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        repaint();
    }

    public synchronized void load(String filename) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            track = (Track) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        repaint();
    }

    public synchronized void newTrack() {
        track = new Track(16);
        repaint();
    }

    public void setChordTab(ChordTab c) {
        tab = c;
    }

    public synchronized void changeDisplay() {
        if(selectedNote >= 0){
        if (selectedNote < track.length) {
            if (((TrackBlock) track.array.get(selectedNote)) != null) {
                tab.changeDisplay(((TrackBlock) track.array.get(selectedNote)).note.noteName);
            }
        }
        }
    }

    public void setChord(String c) {
        chord = c;
    }

    public void toggleShade() {
        tab.toggleShade();
    }

    public void play() {
        backgroundExec.execute(pt);
    }

    public void stop() {
        pt.isPlaying = false;
        selectedNote = -1;
        repaint();
    }

    public void repeat() {
        pt.repeat = true;
    }

    public void updateBPM(int a) {
        BPM = a;
        pt.BPM = BPM;
    }

    public void unrepeat() {
        pt.repeat = false;
    }

    public void delete() {
        if (selectedNote < track.length && selectedNote >= 0) {
            if (track.array.get(selectedNote) != null) {
                track.array.set(selectedNote, null);
            }
            repaint();
        }
    }

    public void drop(DropTargetDropEvent dte) {
        if (canClick && id.isEnabled()) {
            DataFlavor array[] = dte.getCurrentDataFlavors();
            //check if it is an image
            if (array[0].equals(DataFlavor.imageFlavor)) {
                //get x location
                double x = dte.getLocation().getX();
                //find which part of  track dropped in
                x -= TRACKX + ROUND;
                x /= MINORTICKSPACING;
                int location = (int) x;
                selectedNote = location;
                if (location < 0) {
                    location = 0;
                }
                if (location > track.length - 1) {
                    location = track.length - 1;
                }
                track.add(location, true, chord);
                repaint();
            }
        }
    }
    private volatile Image buffImage;
    private volatile Graphics buffGraphics;

    @Override
    public synchronized void update(Graphics g) {
        if (buffImage == null) {
            buffImage = createImage(this.getSize().width, this.getSize().height);
            buffGraphics = buffImage.getGraphics();
        }
        buffGraphics.setColor(getBackground());
        buffGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);

        buffGraphics.setColor(getForeground());
        paint(buffGraphics);

        g.drawImage(buffImage, 0, 0, this);
    }
    HashMap<String, Color> noteMap = new HashMap<String, Color>();

    @Override
    public synchronized void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color trackBG = Color.WHITE;

        if (initial) {
            g2.setColor(trackBG);
            g2.draw(trackShape);
            g2.fill(trackShape);
            g2.setColor(Color.black);
            g2.draw(trackBorder);
        }
        //Draw the Notes
        for (int i = 0; i < track.length; i++) {
            TrackBlock a = (TrackBlock) track.array.get(i);
            if (a != null) {
                Note note = a.note;
                if (noteMap.containsKey(note.noteName)) {
                    g2.setColor(noteMap.get(note.noteName));
                } else {
                    double random = Math.random();
                    random *= 1000;
                    int intran = (int) random;
                    intran = intran % (215);
                    intran += 40;
                    noteMap.put(note.noteName, new Color(60, intran - 40, intran));
                }
                g2.setColor(noteMap.get(note.noteName));
                if (i == selectedNote) {
                    g2.setColor(Color.RED);
                }
                double start = (note.startBlockNo * MINORTICKSPACING) + ROUND + TRACKX;

                if (note.length == 1 && a.isOccupied) {

                    RoundRectangle2D.Double rec = new RoundRectangle2D.Double(start + 5,
                            TRACKY + (TRACKY * .2),
                            MINORTICKSPACING - 10,
                            TRACKH - (TRACKY * .4),
                            ROUND / MINORTICKSPACING,
                            ROUND / MINORTICKSPACING);
                    g2.draw(rec);
                    g2.fill(rec);
                    g2.setColor(Color.WHITE);
                    TextLayout layout = new TextLayout(note.noteName, font, g2.getFontRenderContext());
                    layout.draw(g2, (float) rec.getMinX() + 5, (float) rec.getMinY() + 10);
                } else if (note.length > 1 && a.isOccupied) {
                    RoundRectangle2D.Double longrec = new RoundRectangle2D.Double(start + 5,
                            TRACKY + (TRACKY * .2),
                            (MINORTICKSPACING * note.length) - 10,
                            TRACKH - (TRACKY * .4),
                            ROUND / MINORTICKSPACING,
                            ROUND / MINORTICKSPACING);
                    g2.draw(longrec);
                    g2.fill(longrec);
                    i += note.length;
                    continue;
                }
            }
        }
        //Redraw the ticks every time repaint() is called
        boolean eighth = false;
        int count = 0;
        g2.setColor(Color.BLACK);
        for (double i = TRACKX + ROUND; i < TRACKW + TRACKX; i += MINORTICKSPACING) {
            count++;
            if (count == 1 | count == 9 | count == 17) {
                g2.draw(new Line2D.Double(i, TRACKY + TRACKH, i, TRACKY + (.25 * TRACKH)));
                eighth = true;
                if (count == 17) {
                    break;
                }
                continue;
            } else if (eighth) {
                g2.draw(new Line2D.Double(i, TRACKY + TRACKH, i, TRACKY + (.75 * TRACKH)));
                eighth = false;
            } else {
                g2.draw(new Line2D.Double(i, TRACKY + TRACKH, i, TRACKY + (.5 * TRACKH)));
                eighth = true;
            }
        }
    }
    /*
     * End Paint
     */
    /*
     * Mouse Listener Methods
     */
    boolean clickDrag = false;
    boolean move = false;
    int selectedBlock = 0;

    public void mousePressed(MouseEvent e) {
        if (canClick) {
            Point location = e.getPoint();
            /*
             * find if user clicked on a note
             */
            // get x
            double x = location.getX();
            //find which part of  track dropped in
            x -= TRACKX + ROUND;
            double trueval = x;
            x /= MINORTICKSPACING;
            int locx = (int) x;
            selectedBlock = locx;
            x = x - locx;
            if (locx > track.length - 1) {
                locx = track.length - 1;
            }
            if (locx < 0) {
                locx = 0;
            }
            if (track.array.get(locx) != null) {
                boolean occupied = ((TrackBlock) track.array.get(locx)).isOccupied;
                int start = ((TrackBlock) track.array.get(locx)).note.startBlockNo;
                int length = ((TrackBlock) track.array.get(locx)).note.length;
                int end = start + length - 1;
                if (occupied) {
                    if (!(locx < 0 || locx > track.length - 1)) {
                        selectedNote = locx;
                        move = true;
                        this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                        /*
                        if (locx == start || locx == end) {
                        if ((x > .8 || x < .2) && x > 0) {
                        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        clickDrag = true;
                        } else {
                        this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                        move = true;
                        clickDrag = false;
                        }
                        } else {
                        this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                        clickDrag = false;
                        move = true;
                        }
                         *
                         */
                        repaint();
                    }
                } else {
                    clickDrag = false;
                    move = false;
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (canClick) {
            this.changeDisplay();
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            Point location = e.getPoint();
            /*
             * find if user clicked on a note
             */
            // get x
            double x = location.getX();
            //find which part of  track dropped in
            x -= TRACKX + ROUND;
            x /= MINORTICKSPACING;
            int locx = (int) x;
            if (selectedNote != locx) {
                if (move) {
                    move = false;
                    if (selectedBlock <= track.length) {
                        if (track.array.get(selectedBlock) != null) {
                            Note old = ((TrackBlock) track.array.get(selectedBlock)).note;
                            int start = old.startBlockNo;
                            int length = old.length;
                            int end = start + length - 1;
                            int difLeft = selectedBlock - start;
                            int difRight = end - selectedBlock;
                            int newSelectedBlock = locx;
                            int newStart = newSelectedBlock - difLeft;
                            int newEnd = newSelectedBlock + difRight;
                            if (newStart < 0) {
                                newStart = 0;
                            }
                            if (newEnd < 0) {
                                newEnd = 0;
                            }
                            if (newStart > track.length) {
                                newStart = track.length - 1;
                            }
                            if (newEnd > track.length) {
                                newEnd = track.length - 1;
                            }

                            boolean isAccepted = true;
                            for (int i = newStart; i <= newEnd; i++) {
                                if (i >= track.length) {
                                    newEnd = track.length - 1;
                                    break;
                                }
                                if (track.array.get(i) == null) {
                                    continue;
                                } else {
                                    if ((((TrackBlock) track.array.get(i)).isOccupied)) {
                                        if (!((TrackBlock) track.array.get(i)).note.equals(((TrackBlock) track.array.get(selectedBlock)).note)) {
                                            isAccepted = false;
                                            break;
                                        }
                                    }
                                }
                            }


                            if (isAccepted) {
                                for (int i = start; i <= end; i++) {
                                    track.array.set(i, null);
                                }
                                Note newNote = new Note(old.noteName, newEnd - newStart + 1, newStart);
                                newNote.color = old.color;
                                //System.out.println(newNote.startBlockNo);
                                //System.out.println(newNote.length + newNote.startBlockNo - 1);
                                selectedNote = newStart;
                                for (int i = newStart; i <= newEnd; i++) {
                                    track.array.set(i, new TrackBlock(newNote, true));
                                }
                                repaint();
                            }
                        }
                    }
                } else if (clickDrag) {
                    clickDrag = false;
                    if (locx > selectedNote) {
                        if (locx > 15) {
                            locx = 15;
                        }
                        //user is attempting to expand note to the right
                        //check to see if all the nodes in between are empty
                        Note newNote = ((TrackBlock) track.array.get(selectedNote)).note;
                        Note refNote = ((TrackBlock) track.array.get(selectedNote)).note;
                        newNote.color = refNote.color;
                        /*
                        if(selectedBlock == refNote.startBlockNo && refNote.length > 1){
                        //user is making note smaller
                        if(locx <= refNote.startBlockNo + refNote.length-1){
                        newNote.startBlockNo = locx;
                        int length = newNote.startBlockNo + refNote.length -1;
                        //for(int i = locx; i < refNote.length; i++){
                        //  length++;
                        //}
                        newNote.length = length;
                        for(int i = refNote.startBlockNo; i < refNote.startBlockNo + refNote.length; i++){
                        track.array[i] = null;
                        }
                        for(int i = newNote.startBlockNo; i < newNote.startBlockNo+ newNote.length; i++){
                        track.array[i] = new TrackBlock(newNote, true);
                        }
                        }
                        }else{
                        for(int i = refNote.startBlockNo + refNote.length; i <= locx; i++){
                        if(track.array[i] == null) newNote.length++;
                        else break;
                        }
                        for(int i = refNote.startBlockNo; i < refNote.startBlockNo + newNote.length; i++){
                        track.array[i] = new TrackBlock(newNote, true);
                        }
                        }
                         */
                        for (int i = refNote.startBlockNo; i <= locx; i++) {
                            if (track.array.get(i) != null) {
                                if (!((TrackBlock) track.array.get(i)).isOccupied) {
                                    if (((TrackBlock) track.array.get(i)).note != refNote) {
                                        newNote.length++;
                                    }
                                } else if ((((TrackBlock) track.array.get(i)).isOccupied) && (((TrackBlock) track.array.get(i)).note == refNote) && (locx < (refNote.startBlockNo + refNote.length))) {
                                    //user is shrinking node to the right
                                    newNote.length--;
                                    newNote.startBlockNo++;
                                    track.array.set(i - 1, null);
                                } else if (((TrackBlock) track.array.get(i)).note == refNote) {
                                    continue;
                                } else {
                                    break;
                                }
                            } else {
                                newNote.length++;
                            }
                        }
                        for (int i = refNote.startBlockNo; i < refNote.length + refNote.startBlockNo; i++) {
                            track.array.set(i, null);
                        }
                        //now set all blocks in to have this new note
                        for (int i = newNote.startBlockNo; i < newNote.startBlockNo + newNote.length; i++) {
                            track.array.set(i, new TrackBlock(newNote, true));
                        }

                        repaint();
                    } else if (locx < selectedNote) {
                        //user is attempting to expand note to the left
                        if (locx < 0) {
                            locx = 0;
                        }
                        //check to see if all the nodes in between are empty
                        Note newNote = ((TrackBlock) track.array.get(selectedNote)).note;
                        Note refNote = ((TrackBlock) track.array.get(selectedNote)).note;
                        for (int i = (refNote.startBlockNo + refNote.length - 1); i >= locx; i--) {
                            if (track.array.get(i) != null) {
                                if (!((TrackBlock) track.array.get(i)).isOccupied) {
                                    if (((TrackBlock) track.array.get(i)).note != refNote) {
                                        newNote.length++;
                                        newNote.startBlockNo--;
                                    }
                                } else if (((TrackBlock) track.array.get(i)).isOccupied && ((TrackBlock) track.array.get(i)).note == refNote && locx > refNote.startBlockNo) {
                                    newNote.length--;
                                    track.array.set(i, null);
                                    if (i == locx + 1) {
                                        break;
                                    }
                                } else if (((TrackBlock) track.array.get(i)).note == refNote) {
                                    continue;
                                } else {
                                    break;
                                }
                            } else {
                                newNote.length++;
                                newNote.startBlockNo--;
                            }
                        }
                        //now set all blocks in to have this new note
                        for (int i = refNote.startBlockNo; i < refNote.length + refNote.startBlockNo; i++) {
                            track.array.set(i, null);
                        }
                        for (int i = newNote.startBlockNo; i < newNote.startBlockNo + newNote.length; i++) {
                            //System.out.println(i);
                            track.array.set(i, new TrackBlock(newNote, true));
                        }
                        repaint();

                    }
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void dragOver(DropTargetDragEvent dte) {
    }

    public void dropActionChanged(DropTargetDragEvent dte) {
    }

    public void dragEnter(DropTargetDragEvent dte) {
    }
}
