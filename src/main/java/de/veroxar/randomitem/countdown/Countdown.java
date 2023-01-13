package de.veroxar.randomitem.countdown;

import de.veroxar.randomitem.RandomItem;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public abstract class Countdown implements Runnable {

    /*
      Boolean if task run
     */
    private boolean running = false;

    /*
     *remaining Seconds of Task
     */
    private long remainingSeconds;

    /*
      The bukkit task
     */
    private BukkitTask task;
    public void startCountdown(long remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
        this.running = true;
        this.onStart();
        this.task = Bukkit.getScheduler().runTaskTimer(RandomItem.getInstance(), () -> {
            Countdown.this.run();

            if (0 >= this.remainingSeconds) {
                Countdown.this.cancelCountdown();
                this.onEnd();
            }

            Countdown.this.remainingSeconds--;
        }, 0, 20);
    }

    /*
      Will be called on start of task
     */
    public abstract void onStart();

    /*
      Will be called after remaining seconds are 0
     */
    public abstract void onEnd();

    /*
      Cancel the Task
     */
    public void cancelCountdown() {
        this.running = false;
        this.task.cancel();
        this.onEnd();
    }

    public boolean isRunning() {
        return running;
    }

    public int getStartSecond() {
        /*
          started Seconds of Task
         */
        return 0;
    }

    public long getRemainingSeconds() {
        return remainingSeconds;
    }

    public Countdown setRemainingSeconds(Long remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
        return this;
    }

    public BukkitTask getTask() {
        return task;
    }
}
