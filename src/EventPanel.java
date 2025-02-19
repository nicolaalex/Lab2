// EventPanel that shows the event details

import javax.swing.*;
import java.awt.*;


//EventPanel Class
public class EventPanel extends JPanel {

    //Variables
    private Event event;
    private EventListPanel eventListPanel;
    private static final int EVENT_PANEL_WIDTH = 250;
    private static final int EVENT_PANEL_HEIGHT = 250;
    private static final int EVENT_NAME_FONT = 20;

    //CompleteButton
    JButton completeButton = new JButton("Complete");

    //Event Panel contents
    public EventPanel(Event event) {
        this.event = event;

        //Event panels
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(EVENT_PANEL_WIDTH, EVENT_PANEL_HEIGHT));
        setBackground(Color.BLUE);

        // string that has the event details
        String eventLabel = event.getName();
        JLabel nameLabel = new JLabel(eventLabel);
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, EVENT_NAME_FONT));
        add(nameLabel);

        // due date label
        JLabel dueDateLabel = new JLabel("Date: " + event.getDateTime());
        add(dueDateLabel);

        // verify if it is  a Meeting
        if(event instanceof  Meeting) {
            Meeting meeting = (Meeting) event;
            JLabel durationLabel = new JLabel("Duration: " + meeting.getDuration());
            JLabel locationLabel = new JLabel("Location: " + meeting.getLocation());
            add(durationLabel);
            add(locationLabel);
        }

        // checks if it is Complete
        if (event instanceof Completable){
            JLabel completeLabel = new JLabel(((Completable)event). isComplete() ? "Completed" : "Not Complete");

            // modify text to complete when button is pushed
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                completeLabel.setText("Completed");
            });
            add(completeLabel);

        }
        //add button to panel
        add(completeButton);
    }
}