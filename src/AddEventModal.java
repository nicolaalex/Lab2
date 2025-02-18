/*EventModal class creates panel to collect information for the new event*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

//AddEventModal Class
public class AddEventModal extends JDialog {


    private EventListPanel eventListPanel;
    AddEventModal modal;

    //Declare record and list for attribute fields
    record Attribute(String name, JTextField value) {
    }
    ArrayList<Attribute> attributes;

    //create panel to enter event
    JPanel infoCollectorPanel;

    //dropdown box to select which event
    JComboBox<String> eventTypeComboBox;
    public static final String[] eventTypes = {"Deadline", "Meeting"};

    //Constants
    public static final int PANEL_WIDTH = 800;
    public static final int INFO_PANEL_HEIGHT = 300;
    public static final int MODAL_PANEL_HEIGHT = 500;
    public static final int TEXT_HEIGHT = 30;
    public static final int FONT_SIZE = 18;
    public static final int NUM_OF_COLUMNS = 10;

    // AddEventModal
    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        modal = this;

        //Setup panel
        this.setTitle("Add Event");
        this.setPreferredSize(new Dimension(PANEL_WIDTH, MODAL_PANEL_HEIGHT));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().add(addEventPanel());
        this.pack();
        this.setVisible(true);
    }


    private JPanel addEventPanel() {
        //init attribute
        attributes = new ArrayList<>();

        //Setup panels
        JPanel panel = new JPanel();
        infoCollectorPanel = new JPanel();
        infoCollectorPanel.setPreferredSize(new Dimension(PANEL_WIDTH, INFO_PANEL_HEIGHT));
        infoCollectorPanel.setBackground(Color.LIGHT_GRAY);

        //Setup dropdown box
        eventTypeComboBox = new JComboBox<>(eventTypes);
        eventTypeComboBox.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        eventTypeComboBox.addActionListener(getEventChooser());

        //add panel and dropdown box
        panel.add(eventTypeComboBox);
        panel.add(infoCollectorPanel);

        //create submit button and connect to actionlistener
        JButton addEventButton = new JButton("Submit");
        addEventButton.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        addEventButton.addActionListener(getEventCreator());
        panel.add(addEventButton);


        return panel;
    }

    // Action listeners for select which event
    private ActionListener getEventChooser() {

        return e -> {

            attributes.clear();
            infoCollectorPanel.removeAll();

            switch (eventTypeComboBox.getSelectedIndex()) {
                //if Event is selected display these contents
                case 0: {
                    infoCollectorPanel.add(new JLabel("Please Enter Name and Date")).setPreferredSize(new Dimension(PANEL_WIDTH, TEXT_HEIGHT));
                    attributes.add(new Attribute("Name", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Year", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Month", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Day", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Hour", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Minute", new JTextField(NUM_OF_COLUMNS)));
                    break;
                }
                //if meeting is selected display these contents
                case 1: {
                    infoCollectorPanel.add(new JLabel(
                            "Please Enter Name, Location, and Dates")).setPreferredSize(new Dimension(PANEL_WIDTH, TEXT_HEIGHT));
                    attributes.add(new Attribute("Name", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Location", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Start Year", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Start Month", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Start Day", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Start Hour", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("Start Minute", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("End Year", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("End Month", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("End Day", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("End Hour", new JTextField(NUM_OF_COLUMNS)));
                    attributes.add(new Attribute("End Minute", new JTextField(NUM_OF_COLUMNS)));
                    break;
                }
            }
            //add attributes to panel
            attributes.forEach(attr -> {
                infoCollectorPanel.add(new JLabel(attr.name));
                infoCollectorPanel.add(attr.value);
            });

            infoCollectorPanel.revalidate();
            infoCollectorPanel.repaint();

        };
    }

    //action listener to create the event
    private ActionListener getEventCreator(){

        return e -> {

            Event newEvent = new Deadline("Error", LocalDateTime.now());

            switch (eventTypeComboBox.getSelectedIndex()) {
                //Deadline
                case 0: {
                    //date
                    LocalDateTime newDate = LocalDateTime.of(Integer.parseInt(attributes.get(1).value.getText()),
                            Integer.parseInt(attributes.get(2).value.getText()),
                            Integer.parseInt(attributes.get(3).value.getText()),
                            Integer.parseInt(attributes.get(4).value.getText()),
                            Integer.parseInt(attributes.get(5).value.getText()));
                    //create the Deadline
                    newEvent = new Deadline(attributes.get(0).value.getText(), newDate);

                    break;
                }
                //Meeting
                case 1: {
                    //start date
                    LocalDateTime newStartDate = LocalDateTime.of(
                            Integer.parseInt(attributes.get(2).value.getText()),
                            Integer.parseInt(attributes.get(3).value.getText()),
                            Integer.parseInt(attributes.get(4).value.getText()),
                            Integer.parseInt(attributes.get(5).value.getText()),
                            Integer.parseInt(attributes.get(6).value.getText()));
                    //end date
                    LocalDateTime newEndDate = LocalDateTime.of(
                            Integer.parseInt(attributes.get(7).value.getText()),
                            Integer.parseInt(attributes.get(8).value.getText()),
                            Integer.parseInt(attributes.get(9).value.getText()),
                            Integer.parseInt(attributes.get(10).value.getText()),
                            Integer.parseInt(attributes.get(11).value.getText()));

                    //create the meeting
                    newEvent = new Meeting(attributes.get(0).value.getText(), newStartDate,
                            newEndDate, attributes.get(1).value.getText());

                    break;

                }
            }
            //add event to the panel
            eventListPanel.addEvent(newEvent);
            modal.dispose();
        };
    }
}