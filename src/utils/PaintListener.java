package utils;

import java.awt.Graphics;
import java.awt.Graphics2D;

public interface PaintListener {

        public void onRepaint(final Graphics g);

        public void onRepaint(final Graphics2D g);
}