
package com.mycompany.gymorig;

import javax.swing.*;
import java.awt.*;

public class UIHelpers {
    // ---------- Look & Theme Colors ----------
    static final Color OK = new Color(0x127A4B);
    static final Color BAD = new Color(0xC2362B);
    // Core design colors moved out of the main class to clean up space
    public static final Color ACCENT = new Color(0x2A55F5);
    public static final Color BG = new Color(0xE9EDEE);
    public static final Color INK = new Color(0x162023);
    public static final Color MUTE = new Color(0x5B6B70);
    public static final Color LINE = new Color(0xCBD4D6);

    public static JButton button(String text, boolean primary) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        b.setFocusPainted(true);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (primary) {
            b.setBackground(ACCENT);
            b.setForeground(Color.WHITE);
        } else {
            b.setBackground(Color.WHITE);
            b.setForeground(INK);
            b.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(LINE, 2),
                    BorderFactory.createEmptyBorder(6, 14, 6, 14)));
        }
        return b;
    }

    public static JLabel label(String text, int size, int style, Color c) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", style, size));
        l.setForeground(c);
        return l;
    }

    public static <T extends JComponent> T left(T c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        return c;
    }

    public static <T extends JComponent> T wide(T c, int h) {
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, h));
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        return c;
    }

    public static JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE, 2),
                BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        return p;
    }
}
