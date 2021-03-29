import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class MaFenetre extends JFrame {

    Button gen = new Button("Generer");
    Button quit = new Button("Quitter");
    Button save = new Button("sauvegarder");
    JScrollBar scrollr = new JScrollBar(Adjustable.HORIZONTAL);
    JScrollBar scrollg = new JScrollBar(Adjustable.HORIZONTAL);
    JScrollBar scrollb = new JScrollBar(Adjustable.HORIZONTAL);
    Panel p1 = new Panel();
    Panel p2 = new Panel();
    JLabel label = new JLabel ("Complexite :");
    int scrollrValue, scrollgValue, scrollbValue;

    ImageGeneree image = new ImageGeneree();

    MaFenetre() {
        super("TP1_poo");
        setSize(650, 600);
        setVisible(true);
        scrollr.setPreferredSize(new Dimension(150, 15));
        scrollg.setPreferredSize(new Dimension(150, 15));
        scrollb.setPreferredSize(new Dimension(150, 15));
        scrollr.setBackground(new Color(255,0,0));
        scrollg.setBackground(new Color(0,255,0));
        scrollb.setBackground(new Color(0,0,255));
        scrollr.setValue(27);
        scrollg.setValue(27);
        scrollb.setValue(27);
        save.setEnabled(false);
        p1.add(label);
        p1.add(scrollr);
        p1.add(scrollb);
        p1.add(scrollg);
        p2.add(save);
        p2.add(gen);
        p2.add(quit);
        Container c = this.getContentPane();
        c.add(p1, BorderLayout.NORTH);
        c.add(image);
        c.add(p2, BorderLayout.SOUTH);

        scrollr.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollr.getValueIsAdjusting())
                    return;
                scrollrValue = (int)(ae.getValue()/9);
            }
        });
        scrollg.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollg.getValueIsAdjusting())
                    return;
                scrollgValue = (int)(ae.getValue()/9);
            }
        });
        scrollb.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollb.getValueIsAdjusting())
                    return;
                scrollbValue = (int)(ae.getValue()/9);
                
            }
        });

        gen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image.ranGenImage(650, 550, scrollrValue, scrollgValue, scrollbValue);
                save.setEnabled(true);
                image.repaint();
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image.saveImage();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    } 



}
