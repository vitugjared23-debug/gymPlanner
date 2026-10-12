package com.mycompany.gymorig;
import javax.swing.*;
import java.awt.*;
    
    public class BmiBar extends JComponent {
        double bmi = 22;

        BmiBar() { setPreferredSize(new Dimension(300, 30)); }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), y = 10, h = 10;
            double[] cut = {0, 0.18, 0.45, 0.70, 1.0};
            Color[] col = {new Color(0x5AA0D6), new Color(0x4FB286), new Color(0xE0B13C), new Color(0xD4553F)};
            for (int i = 0; i < col.length; i++) {
                g2.setColor(col[i]);
                int x1 = (int) (w * cut[i]), x2 = (int) (w * cut[i + 1]);
                g2.fillRect(x1, y, x2 - x1, h);
            }
            double pos = Math.max(0, Math.min(1, (bmi - 14) / 22.0));
            int mx = (int) (pos * (w - 4));
            g2.setColor(UIHelpers.INK);
            g2.fillRoundRect(mx, 4, 4, 22, 3, 3);
            g2.dispose();
        }
    }
