// EventListPanel Class
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class EventListPanel extends JPanel {

    ArrayList<Event> events;
    JPanel controlPanel;
    JPanel displayPanel;
    JComboBox<String> sortDropDown;
    final String[] SORT_OPTIONS = {"Name", "ReverseOrder", "DateTime"};
    Map<String, Predicate<Event>> filters;
    ArrayList<JCheckBox> displayFilters;
    EventFilters eventFilter = new EventFilters();
    JButton addEventButton;

    private static final int CONTROL_PANEL_WIDTH = 300;
    private static final int CONTROL_PANEL_HEIGHT = 300;
    private static final int DISPLAY_PANEL_WIDTH = 900;
    private static final int DISPLAY_PANEL_HEIGHT = 200;
    public static final int FONT_SIZE = 20;

    public EventListPanel() {
        setBackground(Color.LIGHT_GRAY);
        events = new ArrayList<>();

        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));

        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(DISPLAY_PANEL_WIDTH, DISPLAY_PANEL_HEIGHT));

        addEventButton = new JButton("Add New Event");
        addEventButton.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        addEventButton.addActionListener(e -> new AddEventModal(this));
        controlPanel.add(addEventButton);

        sortDropDown = new JComboBox<>(SORT_OPTIONS);
        sortDropDown.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        sortDropDown.addActionListener(e -> {
            if (sortDropDown.getSelectedItem().equals(SORT_OPTIONS[0]))
                events.sort((e1, e2) -> e2.getName().compareTo(e1.getName()) * -1);
            if (sortDropDown.getSelectedItem().equals(SORT_OPTIONS[1]))
                events.sort((e1, e2) -> e1.getName().compareTo(e2.getName()) * -1);
            if (sortDropDown.getSelectedItem().equals(SORT_OPTIONS[2]))
                events.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()) * -1);

            updateDisplay();
        });
        controlPanel.add(sortDropDown);

        filters = new HashMap<>();
        filters.putAll(eventFilter.getEventFilters());
        displayFilters = new ArrayList<>();

        for (String filter: filters.keySet()) {
            JCheckBox checkBox = new JCheckBox(filter);
            checkBox.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
            checkBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    updateDisplay();
                }
            });
            displayFilters.add(checkBox);
        }

        for (JCheckBox filter: displayFilters) {
            controlPanel.add(filter);
        }
        add(controlPanel);
        add(displayPanel);

        EventPlanner.addDefaultEvents(this);
    }

    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }

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
