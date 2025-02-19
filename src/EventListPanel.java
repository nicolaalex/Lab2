// event list panel shows the panel to sort, filter, and create new events

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class EventListPanel extends JPanel {

    // create objects for GUI
    ArrayList<Event> events;
    //panels
    JPanel controlPanel;
    JPanel displayPanel;
    //drop down box
    JComboBox<String> sortDropDown;
    final String[] SORT_OPTIONS = {"Name", "ReverseOrder", "DateTime"};
    //filters
    Map<String, Predicate<Event>> filters;
    ArrayList<JCheckBox> displayFilters;
    EventFilters eventFilter = new EventFilters();
    //button
    JButton addEventButton;

    //constants
    private static final int CONTROL_PANEL_WIDTH = 320;
    private static final int CONTROL_PANEL_HEIGHT = 320;
    private static final int DISPLAY_PANEL_WIDTH = 900;
    private static final int DISPLAY_PANEL_HEIGHT = 250;
    public static final int FONT_SIZE = 20;

    // creating event list panel
    public EventListPanel() {

        setBackground(Color.WHITE);
        events = new ArrayList<>();

        //control panel
        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));

        //display panel
        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(DISPLAY_PANEL_WIDTH, DISPLAY_PANEL_WIDTH));


        // add Event Button
        addEventButton = new JButton("Add New Event");
        addEventButton.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));
        addEventButton.addActionListener(e -> new AddEventModal(this));
        controlPanel.add(addEventButton);

        // add drop down box
        sortDropDown = new JComboBox<>(SORT_OPTIONS);
        sortDropDown.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
        sortDropDown.addActionListener(e -> {
            //Name
            if (sortDropDown.getSelectedItem().equals(SORT_OPTIONS[0]))
                events.sort((e1, e2) -> e2.getName().compareTo(e1.getName()) * -1);
            //Reverse Name
            if (sortDropDown.getSelectedItem().equals(SORT_OPTIONS[1]))
                events.sort((e1, e2) -> e1.getName().compareTo(e2.getName()) * -1);
            //Dates
            if (sortDropDown.getSelectedItem().equals(SORT_OPTIONS[2]))
                events.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()) * -1);

            updateDisplay();

        });
        // Add dropdown to panel
        controlPanel.add(sortDropDown);

        // Add Filters
        filters = new HashMap<>();
        filters.putAll(eventFilter.getEventFilters());
        displayFilters = new ArrayList<>();

        //Display filter checkboxes
        for (String filter: filters.keySet()) {
            JCheckBox checkBox = new JCheckBox(filter);
            checkBox.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
            checkBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    updateDisplay();
                }
            });
            displayFilters.add(checkBox);
        }

        //add filters to panel
        for (JCheckBox filter: displayFilters) {
            controlPanel.add(filter);
        }
        //add panels to frame
        add(controlPanel);
        add(displayPanel);

        //add default events
        EventPlanner.addDefaultEvents(this);

    }

    //add new event to display
    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }

    //Remove events when box is checked
    public boolean isFiltered(Event event) {
        boolean result = false;
        for (JCheckBox filter: displayFilters) {
            if (filter.isSelected()) {
                Predicate<Event> filterPredicate = filters.getOrDefault(filter.getText(), null);
                if (filterPredicate.test(event)) {
                    result = true;
                }
            }
        }
        return result;
    }

    //add events to display
    public void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            if(!isFiltered(event)) {
                displayPanel.add(new EventPanel(event));
            }
        }

        revalidate();
        repaint();
    }

}