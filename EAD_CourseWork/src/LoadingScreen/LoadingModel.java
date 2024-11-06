package LoadingScreen;


public class LoadingModel {
    private int progress = 0;
    private LoadingListener listener;

    public interface LoadingListener {
        void onProgressUpdate(int progress);
    }

    public void setLoadingListener(LoadingListener listener) {
        this.listener = listener;
    }

    public void startLoading() {
        new Thread(() -> {
            while (progress <= 100) {
                progress++;
                if (listener != null) {
                    listener.onProgressUpdate(progress);
                }
                try {
                    Thread.sleep(30); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public int getProgress() {
        return progress;
}
}