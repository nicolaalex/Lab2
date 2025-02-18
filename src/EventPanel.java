// EventPanel Class
import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {

    private Event event;
    private static final int EVENT_PANEL_WIDTH = 200;
    private static final int EVENT_PANEL_HEIGHT = 200;
    private static final int EVENT_NAME_FONT = 18;

    JButton completeButton = new JButton("Complete");

    public EventPanel(Event event) {
        this.event = event;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(EVENT_PANEL_WIDTH, EVENT_PANEL_HEIGHT));
        setBackground(Color.PINK);

        String eventLabel = event.getName();
        JLabel nameLabel = new JLabel(eventLabel);
        nameLabel.setFont(new Font("Arial", Font.BOLD, EVENT_NAME_FONT));
        add(nameLabel);

        JLabel dueDateLabel = new JLabel("Date: " + event.getDateTime());
        add(dueDateLabel);

        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            JLabel durationLabel = new JLabel("Duration: " + meeting.getDuration());
            JLabel locationLabel = new JLabel("Location: " + meeting.getLocation());
            add(durationLabel);
            add(locationLabel);
        }

        if (event instanceof Completable) {
            JLabel completeLabel = new JLabel(((Completable) event).isComplete() ? "Completed" : "Not Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                completeLabel.setText("Completed");
            });
            add(completeLabel);
        }

        add(completeButton);
    }
}
