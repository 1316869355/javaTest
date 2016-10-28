package com.up72.imageDeal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
final class cat extends JFrame {
    final static private Dimension SIZE = new Dimension(800, 600);

    public String getTitle() {return "cat";}

    public Dimension getPreferredSize() {return SIZE;}

    public Dimension getMinimumSize() {return SIZE;}

    public Dimension getMaximumSize() {return SIZE;}

    public Dimension getSize() {return SIZE;}

    private Canvas canvas;
    private Brush brush;

    cat() throws HeadlessException {
        init();
        attachListeners();
        doLay();
        setVisible(true);
    }

    private void init() {
        brush = new CatBrush();
        canvas = new Canvas(brush);
    }

    private void attachListeners() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    private void doLay() {
        Container container = getContentPane();
        container.add(canvas, BorderLayout.CENTER);
        pack();
    }

    public static void main(String... args) {
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        SwingUtilities.invokeLater(cat::new);
    }

    private interface Brush extends Observer {
        /**
         * do paint action
         * @param g the graphics context in you application
         */
        void paint(Graphics g);

        /**
         * The component that this brush hold
         * @return component instance
         */
        Component getOwner();

        void setOwner(Component owner);
    }
    private static final class CatBrush implements Brush {
        private Component owner;
        private Image img;
        private AffineTransform affineTransform = AffineTransform.getScaleInstance(.4d,.4d);
        public CatBrush() {
            img = Toolkit.getDefaultToolkit().getImage("G:\\cat.jpg");
        }

        public Component getOwner() {
            return owner;
        }

        public void setOwner(Component owner) {
            this.owner = owner;
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g.create();
            g2.drawImage(img,affineTransform,getOwner());
            g2.dispose();
        }

        public void update(Observable o, Object arg) {
            if (owner != null) owner.repaint();
        }
    }
    /**
     * you canvas
     */
    private class Canvas extends JComponent {
        private Brush brush;

        private Canvas(Brush brush) {
            super();
            this.brush = brush;
            brush.setOwner(this);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (brush == null) return;
            brush.paint(g);
        }
    }
}