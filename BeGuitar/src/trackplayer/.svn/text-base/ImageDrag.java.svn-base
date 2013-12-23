package trackplayer;

import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import javax.activation.DataHandler;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class ImageDrag extends JPanel implements DragSourceListener, DragGestureListener {

    ImageIcon image;
    public JLabel but;
    DragSource ds;
    DragGestureRecognizer dgr;
    ImageIcon disabledIcon = new ImageIcon("dbox.jpg");

    public ImageDrag() {
        image = new ImageIcon("box.jpg");
        but = new JLabel(image);
        but.setDisabledIcon(disabledIcon);
        but.setToolTipText("Drag To Add Current Note/Chord To Track");
        //but.setBounds(15, 15, 20, 20);

        ds = new DragSource();
        dgr = ds.createDefaultDragGestureRecognizer(but, DnDConstants.ACTION_COPY, this);

        add(but);
    }


    public ImageDrag(String imagename){
        image = new ImageIcon(imagename);
        but = new JLabel(image);
        ds = new DragSource();
        dgr = ds.createDefaultDragGestureRecognizer(but, DnDConstants.ACTION_COPY, this);
        add(but);
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        DataHandler dh = new DataHandler(but, "A");
        JLabelTransferable jlt = new JLabelTransferable();
        if (ds.isDragImageSupported()) {
            ds.startDrag(dge,
                    DragSource.DefaultCopyDrop,
                    image.getImage(),
                    new Point(5, 5),
                    jlt.createTransferable(but),
                    this);
        } else {
            ds.startDrag(dge,
                    DragSource.DefaultCopyDrop,
                    jlt.createTransferable(but),
                    this);
        }
    }

    public void dragDropEnd(DragSourceDropEvent dsde) {
    }

    public void dragExit(DragSourceEvent dse) {
    }

    public void dragEnter(DragSourceDragEvent dsde) {
    }

    public void dragOver(DragSourceDragEvent dsde) {
    }

    public void dropActionChanged(DragSourceDragEvent dsde) {
    }
}
/*
 * JLabelTransferable class
 */

class JLabelTransferable extends TransferHandler implements Transferable {

    private static DataFlavor flavors[] = {DataFlavor.imageFlavor};
    Image image = null;

    JLabelTransferable() {
    }

    public int getSourceActions(JComponent c) {
        return TransferHandler.COPY;
    }

    public boolean canImport(JComponent comp, DataFlavor flavor[]) {
        if (!(comp instanceof JLabel) && !(comp instanceof AbstractButton)) {
            return false;
        }
        for (int i = 0, n = flavor.length; i < n; i++) {
            for (int j = 0, m = flavors.length; j < m; j++) {
                if (flavor[i].equals(flavors[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public Transferable createTransferable(JLabel label) {
        ImageIcon i = (ImageIcon) label.getIcon();
        image = i.getImage();
        return this;
    }

    public Object getTransferData(DataFlavor df) {
        if (isDataFlavorSupported(df)) {
            return image;
        }
        return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavors[0].equals(flavor);
    }
}
