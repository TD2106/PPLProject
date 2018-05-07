package model;

public class GeneralSpecialty {
    private int generalSpecialtyID;
    private String generalSpecialty;

    public GeneralSpecialty(int generalSpecialtyID, String generalSpecialty) {
        this.generalSpecialtyID = generalSpecialtyID;
        this.generalSpecialty = generalSpecialty;
    }

    public int getGeneralSpecialtyID() {
        return generalSpecialtyID;
    }

    public void setGeneralSpecialtyID(int generalSpecialtyID) {
        this.generalSpecialtyID = generalSpecialtyID;
    }

    public String getGeneralSpecialty() {
        return generalSpecialty;
    }

    public void setGeneralSpecialty(String generalSpecialty) {
        this.generalSpecialty = generalSpecialty;
    }
}
