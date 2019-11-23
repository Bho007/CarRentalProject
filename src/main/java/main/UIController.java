package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Vehicle;
import model.VehicleTypeName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

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
    
    static String creditCardNumber;
    static String expiryMonth;
    static String expiryYear;
    static String creditCardType;
    static boolean paidWithCash;
    
    static String confirmationNumber;
    static String estimatedCost;
    
    static String rentalID;
    static String rentalEndDate;
    static String rentalEndTimeHour;
    static String rentalEndTimeMinute;
    static String rentalEndTimePeriod;
    static String odometer;
    static boolean gasTankIsFull;
    
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
    HBox filterResultBox;
    @FXML
    Label filterResultSuccessLabel;
    @FXML
    Hyperlink filterResultViewAllHyperlink;
    
    @FXML
    AnchorPane filterResultsPane;
    @FXML
    ScrollPane filterVehiclesPane;
    @FXML
    VBox filterVehiclesItemsBox;
    @FXML
    Button sortResultsByVid;
    @FXML
    Button sortResultsByYear;
    @FXML
    Button sortResultsByType;
    
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
    Label confirmationDriversLicenseLabel;
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
    HBox rentVehicleDriversLicenseBox;
    @FXML
    TextField rentVehicleDriversLicenseTextField;
    @FXML
    Button rentVehicleInitialNextButton;
    @FXML
    Label rentVehicleInitialErrorLabel;
    
    @FXML
    AnchorPane paymentInformationPane;
    @FXML
    TextField vehiclePaymentInformationCreditCardNumberTextField;
    @FXML
    ComboBox<String> vehiclePaymentInformationCreditCardExpiryMonthComboBox;
    @FXML
    ComboBox<String> vehiclePaymentInformationCreditCardExpiryYearComboBox;
    @FXML
    ComboBox<String> vehiclePaymentInformationCreditCardTypeComboBox;
    @FXML
    HBox vehiclePaymentInformationCashBox;
    @FXML
    CheckBox vehiclePaymentInformationCashCheckBox;
    @FXML
    Button paymentInformationConfirmButton;
    @FXML
    Label paymentInformationErrorLabel;
    
    @FXML
    AnchorPane returnVehicleStatusPane;
    @FXML
    TextField returnVehicleStatusRentalIDTextField;
    @FXML
    DatePicker returnVehicleRentalEndDatePicker;
    @FXML
    TextField returnVehicleEndHourTextField;
    @FXML
    TextField returnVehicleEndMinuteTextField;
    @FXML
    ComboBox<String> returnVehiclePeriodComboBox;
    @FXML
    TextField returnVehicleOdometerTextField;
    @FXML
    CheckBox returnVehicleFullGasCheckBox;
    @FXML
    Button returnVehicleProcessButton;
    @FXML
    Label returnVehicleStatusErrorLabel;
    
    @FXML
    AnchorPane returnVehicleCostBreakdownPane;
    
    @FXML
    AnchorPane generateReportPane;
    @FXML
    TitledPane generateReportTitledPane;
    @FXML
    ComboBox<String> generateReportTypeComboBox;
    @FXML
    HBox generateReportTypeBranchBox;
    @FXML
    TextField generateReportTypeBranchTextField;
    @FXML
    Button generateReportButton;
    @FXML
    Label generateReportErrorLabel;
    @FXML
    ScrollPane generateReportScrollPane;
    @FXML
    AnchorPane generateReportResultAnchorPane;
    @FXML
    Text generateReportResultText;
    
    @FXML
    AnchorPane viewEditDatabasePane;
    @FXML
    TitledPane viewEditDatabaseTitledPane;
    @FXML
    TextArea viewEditDatabaseQueryTextArea;
    @FXML
    Button viewEditDatabaseQueryButton;
    @FXML
    Label viewEditDatabaseQueryErrorLabel;
    @FXML
    TitledPane viewEditDatabaseResultTitledPane;
    @FXML
    ScrollPane viewEditDatabaseResultScrollPane;
    @FXML
    AnchorPane viewEditDatabaseResultAnchorPane;
    @FXML
    Text viewEditDatabaseResultText;
    
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
        filterSearchErrorLabel.setVisible(false);
        filterResultBox.setVisible(false);
        
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
            DatabaseResponse<List<Vehicle>> response = Main.database.getVehicles(filterTypeComboBox.getValue().equals("Any") ? null : VehicleTypeName.valueOf(filterTypeComboBox.getValue()),
                    filterLocationTextField.getText(),
                    filterFromDatePicker.getValue() == null ? null : filterFromDatePicker.getValue().atStartOfDay(),
                    filterToDatePicker.getValue() == null ? null : filterToDatePicker.getValue().atStartOfDay());
            
            Main.previousResponse = response;
            logResponse(response);
            
            if (response.isSuccess()) {
                if (response.getValue().isEmpty()) {
                    filterResultBox.setVisible(true);
                    filterResultSuccessLabel.setText("No vehicles found.");
                    filterResultViewAllHyperlink.setText("");
                } else {
                    sortResultsByVid.setOnAction(event -> {
                        filterVehiclesItemsBox.getChildren().clear();
                        response.getValue().sort(Comparator.comparing(Vehicle::getVid));
                        response.getValue().forEach(v -> filterVehiclesItemsBox.getChildren()
                                .add(new Label("vid " + v.getVid() + ": " +
                                        v.getYear() + " " + v.getMake() + " " + v.getModel() + ", " +
                                        v.getVehicleTypeName().getName())));
                    });
                    sortResultsByYear.setOnAction(event -> {
                        filterVehiclesItemsBox.getChildren().clear();
                        response.getValue().sort(Comparator.comparing(Vehicle::getYear));
                        response.getValue().forEach(v -> filterVehiclesItemsBox.getChildren()
                                .add(new Label("vid " + v.getVid() + ": " +
                                        v.getYear() + " " + v.getMake() + " " + v.getModel() + ", " +
                                        v.getVehicleTypeName().getName())));
                    });
                    sortResultsByType.setOnAction(event -> {
                        filterVehiclesItemsBox.getChildren().clear();
                        response.getValue().sort(Comparator.comparing(Vehicle::getVehicleTypeName));
                        response.getValue().forEach(v -> filterVehiclesItemsBox.getChildren()
                                .add(new Label("vid " + v.getVid() + ": " +
                                        v.getYear() + " " + v.getMake() + " " + v.getModel() + ", " +
                                        v.getVehicleTypeName().getName())));
                    });
                    
                    filterResultBox.setVisible(true);
                    filterResultSuccessLabel.setText(response.getValue().size() +
                            " matching " +
                            (response.getValue().size() == 1 ? "vehicle" : "vehicles") +
                            " found.");
                    filterResultViewAllHyperlink.setText("View all");
                    filterVehiclesItemsBox.getChildren().clear();
                    filterResultViewAllHyperlink.setOnAction(event -> {
                        response.getValue().forEach(v -> filterVehiclesItemsBox.getChildren()
                                .add(new Label("vid " + v.getVid() + ": " +
                                        v.getYear() + " " + v.getMake() + " " + v.getModel() + ", " +
                                        v.getVehicleTypeName().getName())));
                        makeAllPanesInvisible();
                        filterResultsPane.setVisible(true);
                        filterVehiclesPane.setVisible(true);
                        filterVehiclesItemsBox.setVisible(true);
                    });
                }
            } else {
                filterSearchErrorLabel.setVisible(true);
                filterSearchErrorLabel.setText(response.getResponse());
            }
            // TODO display query
        }
    }
    
    @FXML
    public void reserveVehicle(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        vehicleTypeSelectionTitleLabel.setText("Reserve Vehicles");
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
        
        durationStartPeriodComboBox.getItems().add("am");
        durationStartPeriodComboBox.getSelectionModel().selectFirst();
        durationStartPeriodComboBox.getItems().add("pm");
        durationEndPeriodComboBox.getItems().add("am");
        durationEndPeriodComboBox.getSelectionModel().selectFirst();
        durationEndPeriodComboBox.getItems().add("pm");
        if (isReservation) {
            durationTitleLabel.setText("Reserve Vehicles");
            durationTitledPane.setText("Reserve Vehicles");
        } else if (isRental) {
            durationTitleLabel.setText("Rent Vehicles");
            durationTitledPane.setText("Rent Vehicles");
        }
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
        customerInformationErrorLabel.setVisible(false);
        
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
            confirmationDriversLicenseLabel.setText(driversLicense);
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
            confirmationDriversLicenseLabel.setText(driversLicense);
            confirmationVehicleTypeLabel.setText(vehicleTypeName);
            confirmationLocationLabel.setText(location);
            // TODO calculate estimated cost
            confirmationEstimatedCostLabel.setText("TODO");
            // confirmationPickupLabel.setText("");
            // confirmationReturnLabel.setText("");
            
            confirmationPaneTitleLabel.setText("Rent Vehicles");
            confirmationTitledPane.setText("Rental Complete");
            paymentInformationPane.setVisible(true);
            
            if (vehiclePaymentInformationCreditCardExpiryMonthComboBox.getSelectionModel().isEmpty()) {
                vehiclePaymentInformationCreditCardTypeComboBox.getItems().add("VISA");
                vehiclePaymentInformationCreditCardTypeComboBox.getSelectionModel().selectFirst();
                vehiclePaymentInformationCreditCardTypeComboBox.getItems().add("Mastercard");
                vehiclePaymentInformationCreditCardExpiryMonthComboBox.getItems().add("01");
                vehiclePaymentInformationCreditCardExpiryMonthComboBox.getSelectionModel().selectFirst();
                IntStream.rangeClosed(2, 12).boxed().map(Object::toString).map(e -> {
                    if (e.length() < 2) {
                        return "0" + e;
                    } else {
                        return e;
                    }
                }).forEach(e -> vehiclePaymentInformationCreditCardExpiryMonthComboBox.getItems().add(e));
                IntStream.rangeClosed(LocalDateTime.now().getYear(), LocalDateTime.now().getYear() + 10).boxed()
                        .map(Object::toString).forEach(e -> vehiclePaymentInformationCreditCardExpiryYearComboBox.getItems().add(e));
                vehiclePaymentInformationCreditCardExpiryYearComboBox.getSelectionModel().selectFirst();
                vehiclePaymentInformationCashBox.setVisible(false);
                if (vehiclePaymentInformationCashCheckBox.isSelected()) {
                    vehiclePaymentInformationCashCheckBox.fire();
                }
            }
        } else {
            customerInformationErrorLabel.setText("Something went wrong - please start over.");
            customerInformationErrorLabel.setVisible(true);
        }
    }
    
    @FXML
    public void processPayment(ActionEvent actionEvent) {
        paymentInformationErrorLabel.setVisible(false);
        
        if (vehiclePaymentInformationCashCheckBox.isSelected()) {
            paidWithCash = true;
        } else {
            if (vehiclePaymentInformationCreditCardNumberTextField.getText().isBlank()) {
                paymentInformationErrorLabel.setVisible(true);
                paymentInformationErrorLabel.setText("Missing credit card number");
                return;
            }
            
            if (!vehiclePaymentInformationCreditCardNumberTextField.getText().matches("\\d+")) {
                paymentInformationErrorLabel.setVisible(true);
                paymentInformationErrorLabel.setText("Invalid credit card number");
                return;
            }
            
            creditCardNumber = vehiclePaymentInformationCreditCardNumberTextField.getText();
            expiryMonth = vehiclePaymentInformationCreditCardExpiryMonthComboBox.getValue();
            expiryYear = vehiclePaymentInformationCreditCardExpiryYearComboBox.getValue();
            creditCardType = vehiclePaymentInformationCreditCardTypeComboBox.getValue();
        }
        
        if (isRental) {
            makeAllPanesInvisible();
            confirmationPaneTitleLabel.setText("Rent Vehicles");
            confirmationTitledPane.setText("Rental Complete");
            confirmationPane.setVisible(true);
            
            // TODO
            // query db to set rental
            // get confirmation number, estimated cost
            confirmationCustomerNameLabel.setText(name);
            confirmationDriversLicenseLabel.setText(driversLicense);
            confirmationVehicleTypeLabel.setText(vehicleTypeName);
            confirmationLocationLabel.setText(location);
            // TODO
        } else if (isReturn) {
            makeAllPanesInvisible();
            confirmationPaneTitleLabel.setText("Return Vehicles");
            confirmationTitledPane.setText("Return Complete");
            confirmationPane.setVisible(true);
            
            confirmationNumberLabel.setText(rentalID);
            confirmationCustomerNameLabel.setText(name);
            confirmationDriversLicenseLabel.setText(driversLicense);
            confirmationVehicleTypeLabel.setText(vehicleTypeName);
            confirmationLocationLabel.setText(location);
            // TODO calculate estimated cost + BREAKDOWN
        } else {
            paymentInformationErrorLabel.setVisible(true);
            paymentInformationErrorLabel.setText("Something went wrong - please start over.");
            return;
        }
    }
    
    @FXML
    public void rentVehicle(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        rentVehicleInitialPane.setVisible(true);
        rentVehicleNewCustomerRadioButton.setOnAction(event -> {
            rentVehicleDriversLicenseBox.setVisible(false);
            rentVehicleConfirmationNumberBox.setVisible(false);
        });
        rentVehicleExistingReservationRadioButton.setOnAction(event -> {
            rentVehicleDriversLicenseBox.setVisible(true);
            rentVehicleConfirmationNumberBox.setVisible(true);
        });
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
            
            if (rentVehicleDriversLicenseTextField.getText().isBlank()) {
                rentVehicleInitialErrorLabel.setVisible(true);
                rentVehicleInitialErrorLabel.setText("Invalid Driver's License");
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
            driversLicense = rentVehicleDriversLicenseTextField.getText();
            
            // TODO generate rental confirmation number and add to confirmation screen
            paymentInformationPane.setVisible(true);
            if (vehiclePaymentInformationCreditCardExpiryMonthComboBox.getSelectionModel().isEmpty()) {
                vehiclePaymentInformationCreditCardTypeComboBox.getItems().add("VISA");
                vehiclePaymentInformationCreditCardTypeComboBox.getSelectionModel().selectFirst();
                vehiclePaymentInformationCreditCardTypeComboBox.getItems().add("Mastercard");
                vehiclePaymentInformationCreditCardExpiryMonthComboBox.getItems().add("01");
                vehiclePaymentInformationCreditCardExpiryMonthComboBox.getSelectionModel().selectFirst();
                IntStream.rangeClosed(2, 12).boxed()
                        .map(Object::toString)
                        .map(e -> e.length() < 2 ? "0" + e : e)
                        .forEach(e -> vehiclePaymentInformationCreditCardExpiryMonthComboBox.getItems().add(e));
                IntStream.rangeClosed(LocalDateTime.now().getYear(), LocalDateTime.now().getYear() + 10).boxed()
                        .map(Object::toString).forEach(e -> vehiclePaymentInformationCreditCardExpiryYearComboBox.getItems().add(e));
                vehiclePaymentInformationCreditCardExpiryYearComboBox.getSelectionModel().selectFirst();
            }
        }
    }
    
    @FXML
    public void showReturnVehiclePane(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        returnVehicleStatusPane.setVisible(true);
        setReturn(true);
    }
    
    @FXML
    public void showGenerateReportScreen(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        generateReportPane.setVisible(true);
        
        if (generateReportTypeComboBox.getItems().isEmpty()) {
            generateReportTypeComboBox.getItems().add("Daily Rentals");
            generateReportTypeComboBox.getSelectionModel().selectFirst();
            generateReportTypeComboBox.getItems().add("Daily Rentals for Branch");
            generateReportTypeComboBox.getItems().add("Daily Returns");
            generateReportTypeComboBox.getItems().add("Daily Returns for Branch");
            generateReportTypeComboBox.setOnAction(event -> {
                if (generateReportTypeComboBox.getValue().contains("Branch")) {
                    generateReportTypeBranchBox.setVisible(true);
                } else {
                    generateReportTypeBranchBox.setVisible(false);
                }
            });
        }
    }
    
    @FXML
    public void processReturn(ActionEvent actionEvent) {
        returnVehicleStatusErrorLabel.setVisible(false);
        
        if (returnVehicleStatusRentalIDTextField.getText().isBlank()) {
            returnVehicleStatusErrorLabel.setVisible(true);
            returnVehicleStatusErrorLabel.setText("Missing rental ID");
            return;
        }
        
        if (returnVehicleRentalEndDatePicker.getValue() == null) {
            returnVehicleStatusErrorLabel.setVisible(true);
            returnVehicleStatusErrorLabel.setText("Invalid Rental End Date");
            return;
        }
        
        if (returnVehicleRentalEndDatePicker.getValue().minusYears(10).isAfter(LocalDate.now())) {
            returnVehicleStatusErrorLabel.setVisible(true);
            returnVehicleStatusErrorLabel.setText("Invalid Rental End Date");
            return;
        }
        
        if (!returnVehicleEndHourTextField.getText().matches("[0]?[0-9]|10|11|12")) {
            returnVehicleStatusErrorLabel.setVisible(true);
            returnVehicleStatusErrorLabel.setText("Invalid Rental End Hour");
            return;
        }
        
        if (!returnVehicleEndMinuteTextField.getText().matches("[0]?[0-9]|[0-5][0-9]")) {
            returnVehicleStatusErrorLabel.setVisible(true);
            returnVehicleStatusErrorLabel.setText("Invalid Rental End Minute");
            return;
        }
        
        if (!returnVehicleOdometerTextField.getText().matches("\\d+")) {
            returnVehicleStatusErrorLabel.setVisible(true);
            returnVehicleStatusErrorLabel.setText("Invalid Odometer value");
            return;
        }
        
        
        // TODO
        // get details for rental
        // check if rental exists
        // check that return time is after rental time
        
        makeAllPanesInvisible();
        paymentInformationPane.setVisible(true);
        if (vehiclePaymentInformationCreditCardExpiryMonthComboBox.getSelectionModel().isEmpty()) {
            vehiclePaymentInformationCreditCardTypeComboBox.getItems().add("VISA");
            vehiclePaymentInformationCreditCardTypeComboBox.getSelectionModel().selectFirst();
            vehiclePaymentInformationCreditCardTypeComboBox.getItems().add("Mastercard");
            vehiclePaymentInformationCreditCardExpiryMonthComboBox.getItems().add("01");
            vehiclePaymentInformationCreditCardExpiryMonthComboBox.getSelectionModel().selectFirst();
            IntStream.rangeClosed(2, 12).boxed()
                    .map(Object::toString)
                    .map(e -> e.length() < 2 ? "0" + e : e)
                    .forEach(e -> vehiclePaymentInformationCreditCardExpiryMonthComboBox.getItems().add(e));
            IntStream.rangeClosed(LocalDateTime.now().getYear(), LocalDateTime.now().getYear() + 10).boxed()
                    .map(Object::toString).forEach(e -> vehiclePaymentInformationCreditCardExpiryYearComboBox.getItems().add(e));
            vehiclePaymentInformationCreditCardExpiryYearComboBox.getSelectionModel().selectFirst();
            vehiclePaymentInformationCashBox.setVisible(true);
            vehiclePaymentInformationCashCheckBox.setOnAction(event -> {
                if (vehiclePaymentInformationCashCheckBox.isSelected()) {
                    vehiclePaymentInformationCreditCardNumberTextField.setDisable(true);
                    vehiclePaymentInformationCreditCardExpiryMonthComboBox.setDisable(true);
                    vehiclePaymentInformationCreditCardExpiryYearComboBox.setDisable(true);
                    vehiclePaymentInformationCreditCardTypeComboBox.setDisable(true);
                } else {
                    vehiclePaymentInformationCreditCardNumberTextField.setDisable(false);
                    vehiclePaymentInformationCreditCardExpiryMonthComboBox.setDisable(false);
                    vehiclePaymentInformationCreditCardExpiryYearComboBox.setDisable(false);
                    vehiclePaymentInformationCreditCardTypeComboBox.setDisable(false);
                }
            });
        }
    }
    
    @FXML
    public void generateReport(ActionEvent actionEvent) {
        DatabaseResponse<String> response;
        
        if (generateReportTypeBranchBox.isVisible()) {
            if (generateReportTypeBranchTextField.getText().isBlank()) {
                generateReportErrorLabel.setVisible(true);
                generateReportErrorLabel.setText("Invalid Branch");
                return;
            }
            
            generateReportErrorLabel.setVisible(false);
            if (generateReportTypeComboBox.getValue().contains("Rentals")) {
                response = Main.database.generateDailyBranchRentalReport(generateReportTypeBranchTextField.getText());
            } else {
                response = Main.database.generateDailyBranchReturnReport(generateReportTypeBranchTextField.getText());
            }
        } else {
            generateReportErrorLabel.setVisible(false);
            if (generateReportTypeComboBox.getValue().contains("Rentals")) {
                response = Main.database.generateDailyRentalReport();
            } else {
                response = Main.database.generateDailyReturnReport();
            }
        }
        
        generateReportScrollPane.setVisible(true);
        generateReportResultText.setText(response.getResponse());
    }
    
    @FXML
    public void viewEditDatabase(ActionEvent actionEvent) {
        makeAllPanesInvisible();
        viewEditDatabasePane.setVisible(true);
    }
    
    @FXML
    public void sendQuery(ActionEvent actionEvent) {
        if (viewEditDatabaseQueryTextArea.getText().isBlank()) {
            viewEditDatabaseQueryErrorLabel.setVisible(true);
            viewEditDatabaseQueryErrorLabel.setText("Missing Query");
            return;
        }
        
        DatabaseResponse<?> response = Main.database.sendQuery(viewEditDatabaseQueryTextArea.getText());
        viewEditDatabaseResultTitledPane.setVisible(true);
        viewEditDatabaseResultText.setText(response.getResponse());
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
    
    private void logResponse(DatabaseResponse<?> response) {
        String formatted = "QUERY: " + response.getQuery() + "\n" +
                "SUCCESS: " + response.isSuccess() + "\n" +
                "RESPONSE: " + response.getResponse() + "\n\n";
        
        System.out.println(formatted);
        
        try {
            Files.writeString(Main.logFile, formatted, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Unable to write to log file.");
        }
    }
}
