package LoadingScreen;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoadingModel model = new LoadingModel();
            LoadingView view = new LoadingView();
            LoadingControl controller = new LoadingControl(model, view);
            controller.showView();
        });
    }
}