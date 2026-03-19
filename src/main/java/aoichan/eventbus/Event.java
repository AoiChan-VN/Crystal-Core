package aoichan.eventbus;

public abstract class Event {
    private boolean cancelled;

    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }
}
