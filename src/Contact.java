public class Contact {
    private String mobileNumber;
    private String name;
    private String email;
    private String relationShip;
    private String DOB;

    public Contact(String mobileNumber, String name, String email, String relationship, String date) {
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.email = email;
        this.relationShip = relationship;
        this.DOB = date;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
