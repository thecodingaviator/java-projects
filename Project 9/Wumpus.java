import java.awt.Color;
import java.awt.Graphics;

public class Wumpus {
  private Vertex current;
  private boolean visible;

  public Wumpus(Vertex current) {
    this.current = current;
    this.visible = false;
  }

  public void draw(Graphics g, int scale) throws InterruptedException {
    if(this.visible) {
      int x = this.current.getX() * scale;
      int y = this.current.getY() * scale;

      g.setColor(Color.red);
      g.fillOval(x + scale / 2 - scale / 8, y + scale / 2 - scale / 8, scale / 4, scale / 4);
    }
  }

  public void setVisible() {
    this.visible = true;
  }

  public boolean isVisible() {
    return this.visible;
  }

  public Vertex getCurrent() {
    return this.current;
  }
}
