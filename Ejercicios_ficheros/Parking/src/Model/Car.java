package Model;

import java.io.Serializable;

public class Car implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    private int id;
    private String licensePlate;
    private String brand;
    private String model;
    private String color;

    public Car(int id, String licensePlate, String brand, String model, String color) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public int getId() {
        return id;
    }
    
	public void setId(int id) {
		this.id = id;
	}

    public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	@Override
    public String toString() {
        return "ID: " + id + ", License Plate: " + licensePlate + ", Brand: " + brand + 
               ", Model: " + model + ", Color: " + color;
    }
}

   
