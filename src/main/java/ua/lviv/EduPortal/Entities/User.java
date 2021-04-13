package ua.lviv.EduPortal.Entities;

import ua.lviv.EduPortal.Entities.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;
    @Column(unique = true, nullable = false, length = 60)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserRole role;
    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;
    @Column(name = "paid_materials", nullable = false)
    private boolean paidMaterials;
    @Column(name = "non_locked", nullable = false)
    private boolean nonLocked;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, UserRole role,
                byte[] profilePicture, boolean paidMaterials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profilePicture = profilePicture;
        this.paidMaterials = paidMaterials;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isPaidMaterials() {
        return paidMaterials;
    }

    public void setPaidMaterials(boolean paidMaterials) {
        this.paidMaterials = paidMaterials;
    }

    public boolean isNonLocked() {
        return nonLocked;
    }

    public void setNonLocked(boolean nonLocked) {
        this.nonLocked = nonLocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", paidMaterials='" + paidMaterials + '\'' +
                ", role=" + role +
                '}';
    }
}
