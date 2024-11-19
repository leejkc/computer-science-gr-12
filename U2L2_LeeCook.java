public class U2L2_LeeCook {
    private String firstName;
    private String lastName;
    private byte age;
    private int studentID;
    private double currentAvg;
    private byte grade;
    private String[] coursesEnrolled;
    private String email;
    private int phoneNumber;
    private String parentName1;
    private String parentName2;
    private String parentContact1;
    private String parentContact2;

    public U2L2_LeeCook(String firstName, String lastName, byte age, int studentID, double currentAvg, byte grade, String[] coursesEnrolled, String email, int phoneNumber, String parentName1, String parentName2, String parentContact1, String parentContact2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studentID = studentID;
        this.currentAvg = currentAvg;
        this.grade = grade;
        this.coursesEnrolled = coursesEnrolled;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.parentName1 = parentName1;
        this.parentName2 = parentName2;
        this.parentContact1 = parentContact1;
        this.parentContact2 = parentContact2;
    }

    public byte getAge() {
        return age;
    }

    public String[] getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public byte getGrade() {
        return grade;
    }

    public double getCurrentAvg() {
        return currentAvg;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getParentContact1() {
        return parentContact1;
    }

    public String getParentContact2() {
        return parentContact2;
    }

    public String getParentName1() {
        return parentName1;
    }

    public String getParentName2() {
        return parentName2;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void setCoursesEnrolled(String[] coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public void setCurrentAvg(double currentAvg) {
        this.currentAvg = currentAvg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGrade(byte grade) {
        this.grade = grade;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setParentContact1(String parentContact1) {
        this.parentContact1 = parentContact1;
    }

    public void setParentContact2(String parentContact2) {
        this.parentContact2 = parentContact2;
    }

    public void setParentName1(String parentName1) {
        this.parentName1 = parentName1;
    }

    public void setParentName2(String parentName2) {
        this.parentName2 = parentName2;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
