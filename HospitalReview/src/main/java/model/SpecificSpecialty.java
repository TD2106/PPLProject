package model;

public class SpecificSpecialty {
    private int specificSpecialtyID;
    private int generalSpecialtyID;
    private String specificSpecialty;

    public SpecificSpecialty(int specificSpecialtyID, int generalSpecialtyID, String specificSpecialty) {
        this.specificSpecialtyID = specificSpecialtyID;
        this.generalSpecialtyID = generalSpecialtyID;
        this.specificSpecialty = specificSpecialty;
    }

    public int getSpecificSpecialtyID() {
        return specificSpecialtyID;
    }

    public void setSpecificSpecialtyID(int specificSpecialtyID) {
        this.specificSpecialtyID = specificSpecialtyID;
    }

    public int getGeneralSpecialtyID() {
        return generalSpecialtyID;
    }

    public void setGeneralSpecialtyID(int generalSpecialtyID) {
        this.generalSpecialtyID = generalSpecialtyID;
    }

    public String getSpecificSpecialty() {
        return specificSpecialty;
    }

    public void setSpecificSpecialty(String specificSpecialty) {
        this.specificSpecialty = specificSpecialty;
    }
}
