import rmi.RemoteAccess;

import java.awt.event.*;
import java.rmi.RemoteException;

public class Tela extends javax.swing.JFrame {
    private javax.swing.JLabel a;
    private static final long serialVersionUID = -1842261777470977698L;
    double fatAltura, fatLargura;

    public Tela(byte image[], RemoteAccess obj, String token) {
        a = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        a.setIcon(new javax.swing.ImageIcon(image));
        a.requestFocusInWindow();
        add(a);
        pack();
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                try {
                    int x = (int) (e.getX() * fatLargura);
                    int y = (int) (e.getY() * fatAltura - getBounds().y);

                    obj.moveMouse(token, x, y);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    int x = (int) (e.getX() * fatLargura);
                    int y = (int) (e.getY() * fatAltura - getBounds().y);

                    obj.moveMouse(token, x, y);
                } catch (RemoteException ex) {
                    System.out.println("Erro " + ex.getMessage());
                }
            }
        });

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                try {
                    obj.wheelMouse(token, mouseWheelEvent.getScrollAmount());
                } catch (RemoteException e) {
                    System.out.println("Erro whell " + e.getMessage());
                }
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                try {
                    obj.pressKey(token, keyEvent.getKeyCode());
                } catch (RemoteException e) {
                    System.out.println("Erro!!");
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                try {
                    obj.releaseKey(token, keyEvent.getKeyCode());
                } catch (RemoteException e) {
                    System.out.println("Erro " + e.getMessage());
                }
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    obj.releaseMouse(token, InputEvent.getMaskForButton(e.getButton()));
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    obj.pressMouse(token, InputEvent.getMaskForButton(e.getButton()));
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

    }

    public void update(byte image[]) {
        a.setIcon(new javax.swing.ImageIcon(image));
    }

    public void setFatorRedimensionamento(double fatAltura, double fatLargura) {
        this.fatAltura = fatAltura;
        this.fatLargura = fatLargura;
    }
}
