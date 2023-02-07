package com.epam.ofeitus.library.entity.user;

import com.epam.ofeitus.library.entity.user.constituent.UserRole;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * User bean class.
 */
public class User implements Serializable {
    private int userId;
    private Date registrationDate;
    private String name;
    private String surname;
    private String patronymic;
    private Date dateOfBirth;
    private boolean gender;
    private String passportSeries;
    private String passportNumber;
    private String issuedBy;
    private Date dateOfIssuing;
    private String passportId;
    private String placeOfBirth;
    private int cityOfLiving;
    private String address;
    private String phoneHome;
    private String phoneMobile;
    private String placeOfWork;
    private String jobTitle;
    private int cityOfRegistration;
    private String addressOfRegistration;
    private int familyStatus;
    private int disability;
    private boolean pensioner;
    private int salary;
    private boolean conscript;
    private String phoneNumber;
    private String email;
    private String passwordHash;
    private UserRole userRole;
    private boolean deleted;

    public User() {
    }

    public User(int userId, Date registrationDate, String name, String surname, String phoneNumber, String email, String passwordHash, UserRole userRole, boolean deleted) {
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userRole = userRole;
        this.deleted = deleted;
    }

    public User(int userId, Date registrationDate, String name, String surname, String patronymic, Date dateOfBirth, boolean gender, String passportSeries, String passportNumber, String issuedBy, Date dateOfIssuing, String passportId, String placeOfBirth, int cityOfLiving, String address, String phoneHome, String phoneMobile, String placeOfWork, String jobTitle, int cityOfRegistration, String addressOfRegistration, int familyStatus, int disability, boolean pensioner, int salary, boolean conscript, String phoneNumber, String email, String passwordHash, UserRole userRole, boolean deleted) {
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.issuedBy = issuedBy;
        this.dateOfIssuing = dateOfIssuing;
        this.passportId = passportId;
        this.placeOfBirth = placeOfBirth;
        this.cityOfLiving = cityOfLiving;
        this.address = address;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.placeOfWork = placeOfWork;
        this.jobTitle = jobTitle;
        this.cityOfRegistration = cityOfRegistration;
        this.addressOfRegistration = addressOfRegistration;
        this.familyStatus = familyStatus;
        this.disability = disability;
        this.pensioner = pensioner;
        this.salary = salary;
        this.conscript = conscript;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userRole = userRole;
        this.deleted = deleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Date getDateOfIssuing() {
        return dateOfIssuing;
    }

    public void setDateOfIssuing(Date dateOfIssuing) {
        this.dateOfIssuing = dateOfIssuing;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public int getCityOfLiving() {
        return cityOfLiving;
    }

    public void setCityOfLiving(int cityOfLiving) {
        this.cityOfLiving = cityOfLiving;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getCityOfRegistration() {
        return cityOfRegistration;
    }

    public void setCityOfRegistration(int cityOfRegistration) {
        this.cityOfRegistration = cityOfRegistration;
    }

    public String getAddressOfRegistration() {
        return addressOfRegistration;
    }

    public void setAddressOfRegistration(String addressOfRegistration) {
        this.addressOfRegistration = addressOfRegistration;
    }

    public int getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(int familyStatus) {
        this.familyStatus = familyStatus;
    }

    public int getDisability() {
        return disability;
    }

    public void setDisability(int disability) {
        this.disability = disability;
    }

    public boolean isPensioner() {
        return pensioner;
    }

    public void setPensioner(boolean pensioner) {
        this.pensioner = pensioner;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isConscript() {
        return conscript;
    }

    public void setConscript(boolean conscript) {
        this.conscript = conscript;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && gender == user.gender && cityOfLiving == user.cityOfLiving && cityOfRegistration == user.cityOfRegistration && familyStatus == user.familyStatus && disability == user.disability && pensioner == user.pensioner && salary == user.salary && conscript == user.conscript && deleted == user.deleted && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(patronymic, user.patronymic) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(passportSeries, user.passportSeries) && Objects.equals(passportNumber, user.passportNumber) && Objects.equals(issuedBy, user.issuedBy) && Objects.equals(dateOfIssuing, user.dateOfIssuing) && Objects.equals(passportId, user.passportId) && Objects.equals(placeOfBirth, user.placeOfBirth) && Objects.equals(address, user.address) && Objects.equals(phoneHome, user.phoneHome) && Objects.equals(phoneMobile, user.phoneMobile) && Objects.equals(placeOfWork, user.placeOfWork) && Objects.equals(jobTitle, user.jobTitle) && Objects.equals(addressOfRegistration, user.addressOfRegistration) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, registrationDate, name, surname, patronymic, dateOfBirth, gender, passportSeries, passportNumber, issuedBy, dateOfIssuing, passportId, placeOfBirth, cityOfLiving, address, phoneHome, phoneMobile, placeOfWork, jobTitle, cityOfRegistration, addressOfRegistration, familyStatus, disability, pensioner, salary, conscript, phoneNumber, email, passwordHash, userRole, deleted);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", registrationDate=" + registrationDate +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", issuedBy='" + issuedBy + '\'' +
                ", dateOfIssuing=" + dateOfIssuing +
                ", passportId='" + passportId + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", cityOfLiving=" + cityOfLiving +
                ", address='" + address + '\'' +
                ", phoneHome='" + phoneHome + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                ", placeOfWork='" + placeOfWork + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", cityOfRegistration=" + cityOfRegistration +
                ", addressOfRegistration='" + addressOfRegistration + '\'' +
                ", familyStatus=" + familyStatus +
                ", disability=" + disability +
                ", pensioner=" + pensioner +
                ", salary=" + salary +
                ", conscript=" + conscript +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", userRole=" + userRole +
                ", deleted=" + deleted +
                '}';
    }
}
