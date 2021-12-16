package io.kaoto.backend.model.deployment;

public class Integration {

    private boolean running;
    private String name;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
