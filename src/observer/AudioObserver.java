package observer;

public class AudioObserver implements Observer {

    @Override
    public void update(String eventDescription) {
        //TODO make it audio not msg
        System.out.println("[AudioObserver] " + eventDescription);

    }
}
