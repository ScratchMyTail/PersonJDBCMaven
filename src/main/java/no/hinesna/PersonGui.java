package no.hinesna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by christerhansen on 07.11.14.
 */
public class PersonGui extends JFrame {
    private JPanel rootPanel;
    private JButton leggTilButton;
    private JTextField fornavnTxt;
    private JTextField etternavnTxt;
    private JList list1;

    private DefaultListModel<Person> model;


    public PersonGui(){
        super("Legg til person");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        model = new DefaultListModel<Person>();
        updateModel();
        list1.setModel(model);
        list1.setCellRenderer(new PersonCellRenderer());

        leggTilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = new Person(fornavnTxt.getText(), etternavnTxt.getText());
                PersonDAO personDAO = new PersonDAO();
                personDAO.savePerson(person);
                updateModel();
            }
        });
    }

    public void updateModel(){
        model = new DefaultListModel<Person>();
        list1.setModel(model);
        PersonDAO personDAO = new PersonDAO();
        ArrayList<Person> personer = (ArrayList<Person>) personDAO.getAll();

        for(Person person : personer){
            model.addElement(person);
        }
    }
}

class PersonCellRenderer extends JLabel implements ListCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Person person = (Person) value;
        setText(person.getFornavn()+", "+person.getEtternavn());

        return this;
    }
}

