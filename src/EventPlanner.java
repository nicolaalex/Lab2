// Event Planner create GUI and adds Event List Panel to it

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;


//EventPlanner class
public class EventPlanner {

    //Constants variables for frame
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 750;


    //Main function to created frame and add panel
    public static void main(String[] args) {

        // Create frame
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creates Event List Panel and adds it to the frame
        EventListPanel eventListPanel = new EventListPanel();
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.getContentPane().add(eventListPanel);
        frame.pack();
        frame.setVisible(true);
    }

    //Creates default events
    public static void addDefaultEvents(EventListPanel events){

        //Default Deadline
        Deadline defaultDeadline = new Deadline("Default Deadline", LocalDateTime.of(2025, 2, 21, 11, 00));

        //Default Meeting
        Meeting defaultMeeting = new Meeting("Default Meeting", LocalDateTime.of(2025, 2, 22, 11, 00),
                LocalDateTime.of(2025, 2, 22, 16, 00), "Office");

        //Add to EventFrame
        events.addEvent(defaultDeadline);
        events.addEvent(defaultMeeting);

    }
}