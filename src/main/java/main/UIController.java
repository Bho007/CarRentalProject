package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.VehicleTypeName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;

public class UIController {
    static boolean isReservation;
    static boolean isRental;
    static boolean isReturn;
    
    static String vehicleTypeName;
    static String location;
    
    static String startDate;
    static String startTimeHour;
    static String startTimeMinute;
    static String startTimePeriod;
    static String endDate;
    static String endTimeHour;
    static String endTimeMinute;
    static String endTimePeriod;
    
    static String phone;
    static String name;
    static String address;
    static String driversLicense;
    
    static String confirmationNumber;
    static String estimatedCost;
    
    @FXML
    StackPane globalPane;
    
    @FXML
    AnchorPane menuPane;
    
    @FXML
    AnchorPane splashPane;
    
    @FXML
    AnchorPane viewVehiclesPane;
    @FXML
    TitledPane filterTitledPane;
    @FXML
    AnchorPane filterPane;
    @FXML
    ComboBox<String> filterTypeComboBox;
    @FXML
    TextField filterLocationTextField;
    @FXML
    DatePicker filterFromDatePicker;
    @FXML
    DatePicker filterToDatePicker;
    @FXML
    Button filterSearchButton;
    @FXML
    Label filterSearchErrorLabel;
    
    @FXML
    AnchorPane filterResultsPane;
    
    @FXML
    AnchorPane vehicleTypeSelectionPane;
    @FXML
    Label vehicleTypeSelectionTitleLabel;
    @FXML
    ComboBox<String> vehicleTypeSelectionComboBox;
    @FXML
    TextField vehicleTypeSelectionLocationTextField;
    @FXML
    Button vehicleTypeSelectionNextButton;
    @FXML
    Label vehicleTypeSelectionErrorLabel;
    
    @FXML
    AnchorPane durationPane;
    @FXML
    TitledPane durationTitledPane;
    @FXML
    Label durationTitleLabel;
    @FXML
    DatePicker durationStartDatePicker;
    @FXML
    TextField durationStartHourTextField;
    @FXML
    TextField durationStartMinuteTextField;
    @FXML
    ComboBox<String> durationStartPeriodComboBox;
    @FXML
    DatePicker durationEndDatePicker;
    @FXML
    TextField durationEndHourTextField;
    @FXML
    TextField durationEndMinuteTextField;
    @FXML
    ComboBox<String> durationEndPeriodComboBox;
    @FXML
    Button durationNextButton;
    @FXML
    Label durationErrorLabel;
    
    @FXML
    AnchorPane customerInformationPane;
    @FXML
    TitledPane customerInformationTitledPane;
    @FXML
    Label customerInformationTitleLabel;
    @FXML
    TextField customerInformationPhoneTextField;
    @FXML
    TextField customerInformationNameTextField;
    @FXML
    TextField customerInformationAddressTextField;
    @FXML
    TextField customerInformationDriversLicenseTextField;
    @FXML
    Button customerInformationNextButton;
    @FXML
    Label customerInformationErrorLabel;
    
    @FXML
    AnchorPane confirmationPane;
    @FXML
    Label confirmationPaneTitleLabel;
    @FXML
    TitledPane confirmationTitledPane;
    @FXML
    Label confirmationNumberLabel;
    @FXML
    Label confirmationLocationLabel;
    @FXML
    Label confirmationCustomerNameLabel;
    @FXML
    Label confirmationVehicleTypeLabel;
    @FXML
    Label confirmationEstimatedCostLabel;
    @FXML
    Label confirmationPickupLabel;
    @FXML
    Label confirmationReturnLabel;
    
    @FXML
    AnchorPane rentVehicleInitialPane;
    @FXML
    Group rentVehicleInitialChoiceGroup;
    @FXML
    RadioButton rentVehicleNewCustomerRadioButton;
    @FXML
    RadioButton rentVehicleExistingReservationRadioButton;
    @FXML
    HBox rentVehicleConfirmationNumberBox;
    @FXML
    TextField rentVehicleConfirmationNumberTextField;
    @FXML
    Button rentVehicleInitialNextButton;
    @FXML
    Label rentVehicleInitialErrorLabel;
    
    @FXML
    AnchorPane returnVehicleStatusPane;
    
    @FXML
    AnchorPane paymentInformationPane;
    
    @FXML
    AnchorPane returnVehicleCostBreakdownPane;
    
    @FXML
    AnchorPane generateReportPane;
    
    @FXML
    AnchorPane viewEditDatabasePane;
    
    
    @FXML
    public void viewVehicles(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        viewVehiclesPane.setVisible(true);
        if (filterTypeComboBox.getItems().isEmpty()) {
            filterTypeComboBox.getItems().add("Any");
            filterTypeComboBox.getSelectionModel().selectFirst();
            Arrays.stream(VehicleTypeName.values())
                    .map(VehicleTypeName::getName)
                    .forEach(e -> filterTypeComboBox.getItems().add(e));
        }
    }
    
    @FXML
    public void filterVehicles() {
        String type = filterTypeComboBox.getValue();
        String location = filterLocationTextField.getText();
        String from = filterFromDatePicker.getValue() != null ? filterFromDatePicker.getValue().toString() : "";
        String to = filterToDatePicker.getValue() != null ? filterToDatePicker.getValue().toString() : "";
        
        if (filterFromDatePicker.getValue() != null && filterFromDatePicker.getValue().isBefore(ChronoLocalDate.from(LocalDateTime.now().minusYears(10)))) {
            filterSearchErrorLabel.setVisible(true);
            filterSearchErrorLabel.setText("Error: \"To\" Date out of range");
        } else if (filterToDatePicker.getValue() != null && filterToDatePicker.getValue().isAfter(ChronoLocalDate.from(LocalDateTime.now().plusYears(10)))) {
            filterSearchErrorLabel.setVisible(true);
            filterSearchErrorLabel.setText("Error: \"From\" Date out of range");
        } else if (filterFromDatePicker.getValue() != null && filterToDatePicker.getValue() != null && filterFromDatePicker.getValue().isAfter(filterToDatePicker.getValue())) {
            filterSearchErrorLabel.setVisible(true);
            filterSearchErrorLabel.setText("Error: \"From\" Date after \"To\" Date");
        } else {
            // TODO run query
        }
        
        // CompletableFuture.supplyAsync(() -> database.getVehicles(type, location, from, to)).whencom;
    }
    
    @FXML
    public void reserveVehicle(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        vehicleTypeSelectionPane.setVisible(true);
        if (vehicleTypeSelectionComboBox.getItems().isEmpty()) {
            vehicleTypeSelectionComboBox.getItems().add("Any");
            vehicleTypeSelectionComboBox.getSelectionModel().selectFirst();
            Arrays.stream(VehicleTypeName.values())
                    .map(VehicleTypeName::getName)
                    .forEach(e -> vehicleTypeSelectionComboBox.getItems().add(e));
        }
        setReservation(true);
    }
    
    @FXML
    public void processVehicleType(ActionEvent actionEvent) {
        if (vehicleTypeSelectionLocationTextField.getText().isBlank()) {
            vehicleTypeSelectionErrorLabel.setVisible(true);
            vehicleTypeSelectionErrorLabel.setText("Invalid Location");
            return;
        }
        
        vehicleTypeName = vehicleTypeSelectionComboBox.getValue();
        location = vehicleTypeSelectionLocationTextField.getText();
        
        vehicleTypeSelectionErrorLabel.setVisible(false);
        makeAllPanesInvisible();
        if (isReservation) {
            durationTitleLabel.setText("Reserve Vehicles");
        } else if (isRental) {
            durationTitleLabel.setText("Rent Vehicles");
        }
        durationStartPeriodComboBox.getItems().add("am");
        durationStartPeriodComboBox.getSelectionModel().selectFirst();
        durationStartPeriodComboBox.getItems().add("pm");
        durationEndPeriodComboBox.getItems().add("am");
        durationEndPeriodComboBox.getSelectionModel().selectFirst();
        durationEndPeriodComboBox.getItems().add("pm");
        durationPane.setVisible(true);
    }
    
    @FXML
    public void processDuration(ActionEvent actionEvent) {
        if (durationStartDatePicker.getValue() == null) {
            durationErrorLabel.setText("Invalid Start Date");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (durationEndDatePicker.getValue() == null) {
            durationErrorLabel.setText("Invalid End Date");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (!durationStartHourTextField.getText().matches("[0]?[0-9]|10|11|12")) {
            durationErrorLabel.setText("Invalid Start Hour");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (!durationEndHourTextField.getText().matches("[0]?[0-9]|10|11|12")) {
            durationErrorLabel.setText("Invalid End Hour");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (!durationStartMinuteTextField.getText().matches("[0]?[0-9]|[0-5][0-9]")) {
            durationErrorLabel.setText("Invalid Start Minute");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (!durationEndMinuteTextField.getText().matches("[0]?[0-9]|[0-5][0-9]")) {
            durationErrorLabel.setText("Invalid End Minute");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (durationStartDatePicker.getValue().isBefore(LocalDate.now())) {
            durationErrorLabel.setText("Start Date is Before Current Date");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (durationEndDatePicker.getValue().isBefore(LocalDate.now())) {
            durationErrorLabel.setText("End Date is Before Current Date");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (durationEndDatePicker.getValue().isBefore(durationStartDatePicker.getValue())) {
            durationErrorLabel.setText("End Date is Before Start Date");
            durationErrorLabel.setVisible(true);
            return;
        }
        
        if (durationStartDatePicker.getValue().isEqual(durationEndDatePicker.getValue())) {
            if (durationStartPeriodComboBox.getValue().equals(durationEndPeriodComboBox.getValue())) {
                if (durationStartHourTextField.getText().equals(durationEndHourTextField.getText())) {
                    if (Integer.parseInt(durationStartMinuteTextField.getText()) > Integer.parseInt(durationEndMinuteTextField.getText())) {
                        durationErrorLabel.setText("Start Time is After End Time");
                        durationErrorLabel.setVisible(true);
                        return;
                    }
                } else if (!durationStartHourTextField.getText().equals("12") && durationEndHourTextField.getText().equals("12")) {
                    durationErrorLabel.setText("Start Time is After End Time");
                    durationErrorLabel.setVisible(true);
                    return;
                } else if (Integer.parseInt(durationStartHourTextField.getText()) > Integer.parseInt(durationEndHourTextField.getText()) && !durationStartHourTextField.getText().equals("12")) {
                    durationErrorLabel.setText("Start Time is After End Time");
                    durationErrorLabel.setVisible(true);
                    return;
                }
            } else if (durationStartPeriodComboBox.getValue().equals("pm") && durationEndPeriodComboBox.getValue().equals("am")) {
                durationErrorLabel.setText("Start Time is After End Time");
                durationErrorLabel.setVisible(true);
                return;
            }
        }
        
        startDate = durationStartDatePicker.getValue().toString();
        startTimeHour = durationStartHourTextField.getText();
        startTimeMinute = durationStartMinuteTextField.getText();
        startTimePeriod = durationStartPeriodComboBox.getValue();
        endDate = durationEndDatePicker.getValue().toString();
        endTimeHour = durationEndHourTextField.getText();
        endTimeMinute = durationEndMinuteTextField.getText();
        endTimePeriod = durationEndPeriodComboBox.getValue();
        
        // TODO check if there is a valid vehicle in selected time frame
        
        durationErrorLabel.setVisible(false);
        if (isReservation) {
            customerInformationTitleLabel.setText("Reserve Vehicles");
            makeAllPanesInvisible();
            customerInformationPane.setVisible(true);
        } else if (isRental) {
            customerInformationTitleLabel.setText("Rent Vehicles");
            makeAllPanesInvisible();
            customerInformationPane.setVisible(true);
        } else {
            customerInformationErrorLabel.setVisible(true);
            customerInformationErrorLabel.setText("Something went wrong - please start over");
        }
    }
    
    @FXML
    public void processCustomerInformation(ActionEvent actionEvent) {
        if (customerInformationPhoneTextField.getText().isBlank()) {
            customerInformationErrorLabel.setText("Phone is required");
            customerInformationErrorLabel.setVisible(true);
            return;
        }
        
        if (customerInformationNameTextField.getText().isBlank()) {
            customerInformationErrorLabel.setText("Name is required");
            customerInformationErrorLabel.setVisible(true);
            return;
        }
        
        if (customerInformationAddressTextField.getText().isBlank()) {
            customerInformationErrorLabel.setText("Address is required");
            customerInformationErrorLabel.setVisible(true);
            return;
        }
        
        if (customerInformationDriversLicenseTextField.getText().isBlank()) {
            customerInformationErrorLabel.setText("Drivers License is required");
            customerInformationErrorLabel.setVisible(true);
            return;
        }
        
        if (!customerInformationPhoneTextField.getText().matches("(\\d|[^a-zA-z])+")) {
            customerInformationErrorLabel.setText("Invalid Phone Number");
            customerInformationErrorLabel.setVisible(true);
            return;
        }
        
        phone = customerInformationPhoneTextField.getText();
        name = customerInformationNameTextField.getText();
        address = customerInformationAddressTextField.getText();
        driversLicense = customerInformationDriversLicenseTextField.getText();
        
        if (isReservation) {
            customerInformationErrorLabel.setVisible(false);
            makeAllPanesInvisible();
            
            // TODO generate confirmation number
            confirmationNumberLabel.setText("TODO");
            confirmationCustomerNameLabel.setText(name);
            confirmationVehicleTypeLabel.setText(vehicleTypeName);
            confirmationLocationLabel.setText(location);
            // TODO calculate estimated cost
            confirmationEstimatedCostLabel.setText("TODO");
            // confirmationPickupLabel.setText("");
            // confirmationReturnLabel.setText("");
            
            confirmationPaneTitleLabel.setText("Reserve Vehicles");
            confirmationTitledPane.setText("Reservation Complete");
            confirmationPane.setVisible(true);
        } else if (isRental) {
            customerInformationErrorLabel.setVisible(false);
            makeAllPanesInvisible();
            
            // TODO get payment information
            // TODO generate confirmation number
            confirmationNumberLabel.setText("TODO");
            confirmationCustomerNameLabel.setText(name);
            confirmationVehicleTypeLabel.setText(vehicleTypeName);
            confirmationLocationLabel.setText(location);
            // TODO calculate estimated cost
            confirmationEstimatedCostLabel.setText("TODO");
            // confirmationPickupLabel.setText("");
            // confirmationReturnLabel.setText("");
            
            confirmationPaneTitleLabel.setText("Rent Vehicles");
            confirmationTitledPane.setText("Rental Complete");
            confirmationPane.setVisible(true);
        } else {
            customerInformationErrorLabel.setText("Something went wrong - please start over.");
            customerInformationErrorLabel.setVisible(true);
        }
    }
    
    @FXML
    public void rentVehicle(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        rentVehicleInitialPane.setVisible(true);
        rentVehicleNewCustomerRadioButton.setOnAction(event -> rentVehicleConfirmationNumberBox.setVisible(false));
        rentVehicleExistingReservationRadioButton.setOnAction(event -> rentVehicleConfirmationNumberBox.setVisible(true));
        setRental(true);
    }
    
    @FXML
    public void processRental(ActionEvent actionEvent) {
        if (rentVehicleExistingReservationRadioButton.isSelected()) {
            if (rentVehicleConfirmationNumberTextField.getText().isBlank()) {
                rentVehicleInitialErrorLabel.setVisible(true);
                rentVehicleInitialErrorLabel.setText("Invalid Reservation or Phone Number");
                return;
            }
            
            // TODO check if reservation or phone number is valid
        }
        
        rentVehicleInitialErrorLabel.setVisible(false);
        makeAllPanesInvisible();
        if (rentVehicleNewCustomerRadioButton.isSelected()) {
            vehicleTypeSelectionTitleLabel.setText("Rent Vehicles");
            if (vehicleTypeSelectionComboBox.getItems().isEmpty()) {
                vehicleTypeSelectionComboBox.getItems().add("Any");
                vehicleTypeSelectionComboBox.getSelectionModel().selectFirst();
                Arrays.stream(VehicleTypeName.values())
                        .map(VehicleTypeName::getName)
                        .forEach(e -> vehicleTypeSelectionComboBox.getItems().add(e));
            }
            vehicleTypeSelectionPane.setVisible(true);
        } else {
            // TODO generate rental confirmation number and add to confirmation screen
            paymentInformationPane.setVisible(true);
        }
    }
    
    @FXML
    public void returnVehicle(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        returnVehicleStatusPane.setVisible(true);
    }
    
    @FXML
    public void generateReport(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        generateReportPane.setVisible(true);
    }
    
    @FXML
    public void viewEditDatabase(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        viewEditDatabasePane.setVisible(true);
    }
    
    @FXML
    private void makeAllPanesInvisible() {
        splashPane.setVisible(false);
        viewVehiclesPane.setVisible(false);
        filterResultsPane.setVisible(false);
        vehicleTypeSelectionPane.setVisible(false);
        confirmationPane.setVisible(false);
        durationPane.setVisible(false);
        customerInformationPane.setVisible(false);
        rentVehicleInitialPane.setVisible(false);
        returnVehicleStatusPane.setVisible(false);
        paymentInformationPane.setVisible(false);
        returnVehicleCostBreakdownPane.setVisible(false);
        generateReportPane.setVisible(false);
        viewEditDatabasePane.setVisible(false);
    }
    
    private void setReservation(boolean value) {
        isReservation = value;
        isRental = false;
        isReturn = false;
    }
    
    private void setRental(boolean value) {
        isReservation = false;
        isRental = value;
        isReturn = false;
    }
    
    private void setReturn(boolean value) {
        isReservation = false;
        isRental = false;
        isReturn = value;
    }
}
