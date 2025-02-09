import Controller.PetController;
import Model.PetDatabase;
import View.MainView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PetDatabase database = new PetDatabase();
            MainView mainView = new MainView();
            new PetController(database, mainView);
        });
    }
}
