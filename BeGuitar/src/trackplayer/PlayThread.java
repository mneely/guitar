package trackplayer;

public class PlayThread implements Runnable {

    volatile boolean isPlaying;
    volatile boolean repeat;
    volatile double velocity = .0005;
    volatile TrackPlayer tp;
    volatile int BPM = 225;
    volatile ImageDrag tot;
    public PlayThread(TrackPlayer t, ImageDrag b) {
        tp = t;
        tot = b;
    }

    @Override
    public void run() {
        tp.toggleShade();
        tp.canClick = false;
        tp.buttonUpdate.p.setEnabled(false);
        tot.but.setEnabled(false);
        isPlaying = true;
        int place = 0;
        double sleep = 0;
        while (isPlaying) {
            tp.selectedNote = place;
            place++;
            if (place == 17) {
                if(repeat){
                    place = 0;
                }else{
                    tp.selectedNote = -1;
                    tp.repaint();
                    break;
                }
            }
            tp.repaint();
            tp.changeDisplay();
            sleep = BPM/.60;
            try {
                Thread.sleep((long) sleep);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        tp.canClick = true;
        tp.buttonUpdate.p.setIcon(tp.buttonUpdate.play);
        tp.buttonUpdate.p.setEnabled(true);
        tot.but.setEnabled(true);
        tp.toggleShade();
        tp.tab.changeToCurrent();
    }
}
