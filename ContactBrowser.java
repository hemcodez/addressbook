import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.text.html.ListView;
import java.util.List;

public class ContactBrowser extends Application {

    // -- The start method creates the GUI for the application
    // - and populate it with data from the database
    @Override
    public void start(Stage primaryStage){

        // -- Create the DAO
        ContactDAO contact = new ContactDAO();

        // -- Get the list of contacts from the database
        List<ContactPerson> contactPeople = contact.getContacts();

        // -- Add the contacts from the database to the GUI list
        ObservableList<String> observableNames = FXCollections.observableArrayList();
        for (ContactPerson contactPerson : contactPeople) {
            observableNames.add(contactPerson.getName() + " " + contactPerson.getNickName() + " " +
                    contactPerson.getCellphone() + " " + contactPerson.getEmail());
        } //END FOR LOOP

        // -- Puts a wrapper around the list that makes it scrollable
       ListView<String> listView = new ListView<>(observableNames);

        // -- Set the title on the stage
        primaryStage.setTitle("Address Book");

        // -- Sets the application window to max size
        primaryStage.setMaximized(true);

        // -- Creates a new borderpane to hold the GUI components
        BorderPane borderPane = new BorderPane();

        // -- Adds the borderpane to hold the GUI components
        Scene scene = new Scene(borderPane,650,400,true);

        // -- Create a new grid pance layout to organize the GUI components
        GridPane gridPane = new GridPane();

        // -- Sets the padding, and horizontal and vertical gaps in the grind pane layout
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // -- Add the list to the grid pane layout
        gridPane.add(listView, 0, 2);

        // -- Set the list view in the center of the border pane
        borderPane.setCenter(listView);

        // -- Set the scene on the stage
        primaryStage.setScene(scene);

        // -- Show the stage
        primaryStage.show();

    } //END METHOD

    public static void main(String[] args) {
        launch(args);
    } //END MAIN METHOD


} //END CLASS
