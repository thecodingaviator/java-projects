import java.awt.Color;
import java.awt.Graphics;

public class Hunter {
  private Vertex current;

  public Hunter(Vertex current) {
    this.current = current;
  }

  public void draw(Graphics g, int scale) {
    this.current.setVisible(true);
    this.current.draw(g, scale);
    int x = this.current.getX() * scale;
    int y = this.current.getY() * scale;

    g.setColor(Color.black);
    g.fillOval(x + scale / 2 - scale / 8, y + scale / 2 - scale / 8, scale / 4, scale / 4);
  }

  public void move(Vertex v) {
    this.current = v;
  }

  public Vertex getCurrent() {
    return this.current;
  }
}
