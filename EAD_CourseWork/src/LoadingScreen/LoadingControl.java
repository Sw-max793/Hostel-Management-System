
package LoadingScreen;

import javax.swing.*;
import view.FirstPage;

public class LoadingControl {
    private LoadingModel model;
    private LoadingView view;

    public LoadingControl(LoadingModel model, LoadingView view) {
        this.model = model;
        this.view = view;

        model.setLoadingListener(view::setProgress);
        model.startLoading();

        Timer timer = new Timer(3000, e -> {
            view.dispose(); 
            FirstPage fp = new FirstPage(); 
            fp.setVisible(true);

            ((Timer) e.getSource()).stop();
        });
        timer.setRepeats(false); 
        timer.start();
    }

    public void showView() {
        view.setVisible(true);
    }
}