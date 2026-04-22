import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HotelManagementSystem extends Application {

    TableView<Rooms> table = new TableView<>();
    ObservableList<Rooms> rooms = FXCollections.observableArrayList();

    TextField roomNo = new TextField();
    ComboBox<String> roomType = new ComboBox<>();
    TextField price = new TextField();

    TextField custName = new TextField();
    TextField contact = new TextField();
    ComboBox<Integer> roomSelect = new ComboBox<>();

    @Override
    public void start(Stage stage) {

        roomType.getItems().addAll("Single", "Double", "Deluxe");

        // Room Form
        GridPane roomPane = new GridPane();
        roomPane.setHgap(10);
        roomPane.setVgap(10);
        roomPane.addRow(0, new Label("Room No"), roomNo);
        roomPane.addRow(1, new Label("Type"), roomType);
        roomPane.addRow(2, new Label("Price"), price);

        Button addRoom = new Button("Add Room");
        Button showAvailable = new Button("Available Rooms");

        HBox roomBtns = new HBox(10, addRoom, showAvailable);

        // Table
        TableColumn<Rooms, Number> c1 = new TableColumn<>("Room No");
        c1.setCellValueFactory(d -> d.getValue().roomNoProperty());

        TableColumn<Rooms, String> c2 = new TableColumn<>("Type");
        c2.setCellValueFactory(d -> d.getValue().typeProperty());

        TableColumn<Rooms, Number> c3 = new TableColumn<>("Price");
        c3.setCellValueFactory(d -> d.getValue().priceProperty());

        TableColumn<Rooms, String> c4 = new TableColumn<>("Status");
        c4.setCellValueFactory(d -> d.getValue().statusProperty());

        table.getColumns().addAll(c1, c2, c3, c4);
        table.setItems(rooms);

        // Customer Form
        GridPane custPane = new GridPane();
        custPane.setHgap(10);
        custPane.setVgap(10);

        custPane.addRow(0, new Label("Customer"), custName);
        custPane.addRow(1, new Label("Contact"), contact);
        custPane.addRow(2, new Label("Room"), roomSelect);

        Button book = new Button("Book Room");
        Button checkout = new Button("Checkout");

        HBox bookBtns = new HBox(10, book, checkout);

        // Add Room
        addRoom.setOnAction(e -> {
            Rooms r = new Rooms(
                    Integer.parseInt(roomNo.getText()),
                    roomType.getValue(),
                    Double.parseDouble(price.getText()),
                    "Available");

            rooms.add(r);
            roomSelect.getItems().add(r.getRoomNo());

            new Alert(Alert.AlertType.INFORMATION, "Room Added").show();

            roomNo.clear();
            roomType.setValue(null);
            price.clear();
        });

        // Show Available Rooms
        showAvailable.setOnAction(e -> {
            ObservableList<Rooms> free = FXCollections.observableArrayList();
            for (Rooms r : rooms)
                if (r.getStatus().equals("Available"))
                    free.add(r);
            table.setItems(free);
        });

        // Book Room
        book.setOnAction(e -> {
            for (Rooms r : rooms) {
                if (r.getRoomNo() == roomSelect.getValue()) {
                    if (r.getStatus().equals("Occupied")) {
                        new Alert(Alert.AlertType.ERROR, "Room Already Occupied").show();
                    } else {
                        r.setStatus("Occupied");
                        table.refresh();
                        new Alert(Alert.AlertType.INFORMATION,
                                "Booked for " + custName.getText()).show();

                        custName.clear();
                        contact.clear();
                        roomSelect.setValue(null);
                    }
                }
            }
        });

        // Checkout
        checkout.setOnAction(e -> {
            Rooms r = table.getSelectionModel().getSelectedItem();
            if (r != null) {
                r.setStatus("Available");
                table.refresh();
                new Alert(Alert.AlertType.INFORMATION, "Checkout Done").show();
            }
        });

        VBox root = new VBox(15,
                new Label("Room Management"), roomPane, roomBtns,
                table,
                new Label("Customer Booking"), custPane, bookBtns);

        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 700, 550));
        stage.setTitle("Hotel Management System");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

class Rooms {
    private IntegerProperty roomNo;
    private StringProperty type;
    private DoubleProperty price;
    private StringProperty status;

    Rooms(int n, String t, double p, String s) {
        roomNo = new SimpleIntegerProperty(n);
        type = new SimpleStringProperty(t);
        price = new SimpleDoubleProperty(p);
        status = new SimpleStringProperty(s);
    }

    public int getRoomNo() { return roomNo.get(); }
    public String getStatus() { return status.get(); }
    public void setStatus(String s) { status.set(s); }

    public IntegerProperty roomNoProperty() { return roomNo; }
    public StringProperty typeProperty() { return type; }
    public DoubleProperty priceProperty() { return price; }
    public StringProperty statusProperty() { return status; }
}
