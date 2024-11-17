package Parking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Car;

public class Parking {

	private ArrayList<Car> carsList; 	 
	private Scanner scanner;
	private File file;

	public Parking() {
		initCarsIfPossible();
	}

	public void showOptions() {
        System.out.println("\n--- Parking ---");
        System.out.println("1. Add a new car");
        System.out.println("2. Delete a car by ID");
        System.out.println("3. Search a car by ID");
        System.out.println("4. List all cars");
        System.out.println("5. Export cars to CSV");
        System.out.println("6. Exit program");
        System.out.print("Choose an option: ");
    }
	
	public void displayMenu() {
		int option;
        scanner = new Scanner(System.in);
		
		do {
			showOptions();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addCar();
                    break;
                case 2:
                    deleteCarById();
                    break;
                case 3:
                    searchCarById();
                    break;
                case 4:
                    listCars();
                    break;
                case 5:
                	exportCarsToCSV();
                	break;
                case 6:
                    System.out.println("Saving data...");
                    saveCarsToFile();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again");
            }
        } while (option != 6);
	}
	
	public void initCarsIfPossible() {	
		file = new File("coches.dat");
			
		if (file.exists()) {
            System.out.println("Loading cars from file...");
            loadCarsFromFile(file);
        } else {
        	carsList = new ArrayList<Car>();
	        System.out.println("File not found. Starting with an empty list");
        }
	}
	
	public void loadCarsFromFile(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            carsList = (ArrayList<Car>) ois.readObject();
            System.out.println("Cars loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading cars from file: " + e.getMessage() + "\n");
        }
    }

    public void saveCarsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("coches.dat"))) {
            oos.writeObject(carsList);
            System.out.println("Cars saved to file successfully\n");
        } catch (IOException e) {
            System.out.println("Error saving cars to file: " + e.getMessage() + "\n");
        }
    }
	
	 public void addCar() {

        int id = getValidId(true);

        String licensePlate = getValidLicensePlate();

        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        carsList.add(new Car(id, licensePlate, brand, model, color));
        System.out.println("Car added successfully");
    }

    public void deleteCarById() {
        int id = getValidId(false);

        boolean carDeleted = carsList.removeIf(car -> car.getId() == id);

        if (carDeleted) {
            System.out.println("Car deleted successfully");
        } else {
            System.out.println("No car found with the specified ID");
        }
    }

    public void searchCarById() {

        int id = getValidId(false);

        Car foundCar = carsList.stream()
                .filter(car -> car.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundCar != null) {
            System.out.println("Car found:");
            System.out.println(foundCar);
        } else {
            System.out.println("No car found with the specified ID");
        }
    }

    public void listCars() {
        if (carsList.isEmpty()) {
            System.out.println("No cars in the parking");
        } else {
            System.out.println("--- List of Cars ---\n");
            for (Car car : carsList) {
                System.out.println(car);
            }
        }
    }
    
    public int getValidId(boolean isAddindCar) {
        while (true) {
            System.out.print("Enter a valid id (number): ");
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if(isAddindCar) {
                    if (isIdExists(value)) {
                        System.out.println("ID already exists. Please enter a different ID\n");
                    } else {
                        return value;
                    }
                } else {
                	return value;
                }  
            } else {
                System.out.println("Invalid input. Please enter a valid number\n");
                scanner.next(); 
            }
        }
    }
    
    public boolean isIdExists(int id) {
        return carsList.stream().anyMatch(car -> car.getId() == id);
    }
    
    public String getValidLicensePlate() {
        String licensePlate;

        while (true) {
            System.out.print("Enter a unique License Plate: ");
            licensePlate = scanner.nextLine().trim();

            if (isLicensePlateExists(licensePlate)) {
                System.out.println("License Plate already exists. Please enter a different one\n");
            } else {
                break;
            }
        }
        return licensePlate;
    }
    
    public boolean isLicensePlateExists(String licensePlate) {
        return carsList.stream().anyMatch(car -> car.getLicensePlate().equalsIgnoreCase(licensePlate));
    }

    public void exportCarsToCSV() {
        String fileName = "coches.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ID;License Plate;Brand;Model;Color\n");

            for (Car car : carsList) {
                writer.write(car.getId() + ";" + 
                             car.getLicensePlate() + ";" + 
                             car.getBrand() + ";" + 
                             car.getModel() + ";" + 
                             car.getColor() + "\n");
            }

            System.out.println("Cars exported to " + fileName + " successfully");
        } catch (IOException e) {
            System.out.println("Error exporting cars to CSV: " + e.getMessage());
        }
    }

}
