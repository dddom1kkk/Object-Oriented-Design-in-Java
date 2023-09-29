package ca.sfu.cmpt213.as1;

/**
 * The Bike class represents bike instances. All bike instances are used to
 * register in the system in {@link BikeRegistry} class.
 * 
 * @author Damir Zharikessov
 * @version 1.0
 */
public class Bike {

    private String owner;
    private String bikeType;
    private String serNum;
    private String brakeType;
    private int wheelSize;
    private int id;

    public Bike() {
        id = 0;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[Id:" + getID() + ", Owner:" + getOwner() + ", type:" + getBikeType()
                + ", Serial:" + getSerialNumber() + ", Brake:" + getBrakeType() + ", WheelSize:" + getWheelSize() + "]";
    }

    // GETTER FUNCTIONS
    public String getOwner() {
        return this.owner;
    }

    public String getBikeType() {
        return this.bikeType;
    }

    public String getSerialNumber() {
        return this.serNum;
    }

    public String getBrakeType() {
        return this.brakeType;
    }

    public int getWheelSize() {
        return this.wheelSize;
    }

    public int getID() {
        return id;
    }

    // SETTER FUNCTIONS
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public void setSerialNumber(String serialNumber) {
        this.serNum = serialNumber;
    }

    public void setBrakeType(String brakeType) {
        this.brakeType = brakeType;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public void setID(int id) {
        this.id = id;
    }
}