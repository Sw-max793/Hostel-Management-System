// Main/MainApp.java
package main;

import controller.FirstPageController;
import view.FirstPageView;

public class Main {
    public static void main(String[] args) {
        FirstPageView view = new FirstPageView();
        new FirstPageController(view);
        view.setVisible(true);
    }
}
