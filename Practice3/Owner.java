public class Owner {
    private String identityNumber;
    private String fullName;
    private String email;

    public Owner(String identityNumber, String fullName, String email) {
        if (identityNumber == null || !identityNumber.trim().matches("\\d{12}")) {
            throw new IllegalArgumentException("CMND/CCCD phai dung 12 chu so");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ten chu xe khong duoc de trong");
        }
        if (email == null || !email.trim().matches("^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Email khong hop le");
        }
        this.identityNumber = identityNumber.trim();
        this.fullName = fullName.trim();
        this.email = email.trim();
    }

    public String getIdentityNumber() { return identityNumber; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public boolean sameInfo(Owner other) {
        return other != null && fullName.equalsIgnoreCase(other.fullName)
                && email.equalsIgnoreCase(other.email);
    }

    @Override
    public String toString() {
        return fullName + " | " + identityNumber + " | " + email;
    }
}
