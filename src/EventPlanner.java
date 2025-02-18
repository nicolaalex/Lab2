// EventPlanner Class
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPlanner {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EventListPanel eventListPanel = new EventListPanel();
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.getContentPane().add(eventListPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void addDefaultEvents(EventListPanel events) {
        Deadline defaultDeadline = new Deadline("Default Deadline", LocalDateTime.of(2025, 9, 19, 15, 00));
        Meeting defaultMeeting = new Meeting("Default Meeting", LocalDateTime.of(2025, 2, 20, 15, 00),
                LocalDateTime.of(2025, 2, 19, 16, 00), "Library");

        events.addEvent(defaultDeadline);
        events.addEvent(defaultMeeting);
    }
}
