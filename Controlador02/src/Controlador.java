import rmi.RemoteAccess;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Controlador {
    public static void main(String[] args) throws RemoteException {

        try {
            RemoteAccess obj = (RemoteAccess) Naming.lookup("rmi://10.61.5.200/RemoteControler");
            String token = obj.logIn("12345");
            System.out.println("Token: " + token);
            obj.moveMouse(token, 500, 500);

            byte screenshot[] = obj.getScreenshot(token);
            Tela tela = new Tela(screenshot, obj, token);

            double altura = obj.getHeightResolution(token) / (double) (tela.getContentPane().getHeight());
            double largura = obj.getWidthResolution(token) / (double) (tela.getContentPane().getWidth());

            tela.setFatorRedimensionamento(altura, largura);
            tela.setVisible(true);
            for (; ; ) {
                screenshot = obj.getScreenshot(token);
                tela.update(screenshot);
            }

        } catch (Exception e) {
            System.out.println("RemoteControler erro" + e.getMessage());
        }
    }
}
