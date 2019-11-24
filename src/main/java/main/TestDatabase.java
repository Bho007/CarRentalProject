package main;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestDatabase implements Database {
    @Override
    public DatabaseResponse<List<Vehicle>> getVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        vehicles.add(new Vehicle(ThreadLocalRandom.current().nextInt(2, 100000), "L" + ThreadLocalRandom.current().nextInt(2, 100000), "M" + ThreadLocalRandom.current().nextInt(2, 100000), "M_" + ThreadLocalRandom.current().nextInt(2, 20), ThreadLocalRandom.current().nextInt(1990, 2050) + "", "red", ThreadLocalRandom.current().nextInt(2, 100000), VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        return new TestDatabaseResponse<>("get all vehicles", true, "list of vehicles", vehicles);
    }
    
    @Override
    public DatabaseResponse<String> generateDailyRentalReport() {
        return null;
    }
    
    @Override
    public DatabaseResponse<String> generateDailyBranchRentalReport(String branch) {
        return null;
    }
    
    @Override
    public DatabaseResponse<String> generateDailyReturnReport() {
        return null;
    }
    
    @Override
    public DatabaseResponse<String> generateDailyBranchReturnReport(String branch) {
        return null;
    }

    @Override
    public DatabaseResponse<Boolean> locationExists(String location) {
        return null;
    }

    @Override
    public DatabaseResponse<Boolean> customerExists(String driversLicense) {
        return null;
    }

    @Override
    public DatabaseResponse<Customer> getCustomer(String driversLicense) {
        return null;
    }

    @Override
    public DatabaseResponse<?> addCustomer(String phone, String name, String address, String driversLicense) {
        return null;
    }

    @Override
    public DatabaseResponse<String> reserveVehicle(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public DatabaseResponse<Reservation> getReservationByConfirmationNumber(String confirmationNumber) {
        return null;
    }

    @Override
    public DatabaseResponse<Reservation> getReservationByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public DatabaseResponse<Rental> rentVehicle(String driversLicense, String phone, String confirmatioNumber, VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to, String creditCardNumber, String expiryMonth, String expiryYear, String creditCardType) {
        return null;
    }

//    @Override
//    public DatabaseResponse<String> rentVehicle(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
//        return null;
//    }

    @Override
    public DatabaseResponse<Rental> getRental(String id) {
        return null;
    }

    @Override
    public DatabaseResponse<String> returnVehicle(String rentalID, String location, LocalDateTime time, String odometer, boolean gasTankIsFull, int cost) {
        return null;
    }

    @Override
    public DatabaseResponse<Vehicle> getVehicle(String rentalID) {
        return null;
    }

//    @Override
//    public DatabaseResponse<String> returnVehicle(VehicleTypeName type, String location, LocalDateTime time, String odometer, boolean gasTankIsFull, int cost) {
//        return null;
//    }

    @Override
    public DatabaseResponse<Integer> getHourlyRate(VehicleTypeName type) {
        return null;
    }

    @Override
    public DatabaseResponse<Integer> getDailyRate(VehicleTypeName type) {
        return null;
    }

    @Override
    public DatabaseResponse<Integer> getWeeklyRate(VehicleTypeName type) {
        return null;
    }

    @Override
    public DatabaseResponse<Boolean> locationExists(String location) {
        return new TestDatabaseResponse<>("query location", true, "found location", true);
    }
    
    @Override
    public DatabaseResponse<Boolean> customerExists(String driversLicense) {
        return new TestDatabaseResponse<>("check if customer exists", true, "customer exists response", false);
    }
    
    @Override
    public DatabaseResponse<Customer> getCustomer(String driversLicense) {
        return new TestDatabaseResponse<>("get customer", true, "found customer", 
                new Customer(132232, "test customer", "test address", "123"));
    }
    
    @Override
    public DatabaseResponse<?> addCustomer(String phone, String name, String address, String driversLicense) {
        return new TestDatabaseResponse<>("add customer", true, "added customer", "");
    }
    
    @Override
    public DatabaseResponse<Reservation> reserveVehicle(String driversLicense, String phoneNumber, VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        return new TestDatabaseResponse<>("make reservation query", true, "reservation confirmation number", new Reservation(ThreadLocalRandom.current().nextInt(100000, 200000), null, null, null, null, null, null));
    }
    
    @Override
    public DatabaseResponse<Reservation> getReservationByConfirmationNumber(String confirmationNumber) {
        return new TestDatabaseResponse<>("make reservation query", true, "reservation confirmation number", new Reservation(ThreadLocalRandom.current().nextInt(100000, 200000), null, null, null, null, null, null));
    }
    
    @Override
    public DatabaseResponse<Reservation> getReservationByPhoneNumber(String phoneNumber) {
        return new TestDatabaseResponse<>("make reservation query", true, "reservation confirmation number", new Reservation(ThreadLocalRandom.current().nextInt(100000, 200000), null, null, null, null, null, null));
    }
    
    @Override
    public DatabaseResponse<Rental> rentVehicle(String driversLicense, String phone, String confirmationNumber, VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to, String creditCardNumber, String expiryMonth, String expiryYear, String creditCardType) {
        return new TestDatabaseResponse<>("make rental query", true, "reservation rental number", new Rental(ThreadLocalRandom.current().nextInt(100000, 200000), ThreadLocalRandom.current().nextInt(100000, 200000), null, null, 2, "card", "123", null));
    }
    
    @Override
    public DatabaseResponse<Rental> getRental(String id) {
        return new TestDatabaseResponse<>("make rental query", true, "reservation rental number", new Rental(ThreadLocalRandom.current().nextInt(100000, 200000), ThreadLocalRandom.current().nextInt(100000, 200000), null, null, 2, "card", "123", null));
    }
    
    @Override
    public DatabaseResponse<String> returnVehicle(String rentalID, String location, LocalDateTime time, String odometer, boolean gasTankIsFull, int cost) {
        return new TestDatabaseResponse<>("return vehicle query", true, "returned vehicle", String.valueOf(ThreadLocalRandom.current().nextInt(100000, 200000)));
    }
    
    @Override
    public DatabaseResponse<?> sendQuery(String query) {
        return null;
    }
    
    @Override
    public DatabaseResponse<Integer> getHourlyRate(VehicleTypeName type) {
        return new TestDatabaseResponse<>("get hourly rate", true, "hourly rate response", 1);
    }
    
    @Override
    public DatabaseResponse<Integer> getDailyRate(VehicleTypeName type) {
        return new TestDatabaseResponse<>("get daily rate", true, "daily rate response", 10);
    }
    
    @Override
    public DatabaseResponse<Integer> getWeeklyRate(VehicleTypeName type) {
        return new TestDatabaseResponse<>("get weekly rate", true, "weekly rate response", 100);
    }
}
