package dataAccess.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */

@Entity
@DiscriminatorValue("realcustomer")
public class RealCustomer extends Customer {

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "FATHER_NAME", nullable = false)
    private String fatherName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private String dateOfBirth;

    @Column(name = "INTERNATIONAL_ID", nullable = false)
    private String internationalID;

    @OneToMany(mappedBy = "realCustomer", cascade = {CascadeType.ALL})
    private Set<LoanFile> loanFiles = new HashSet<LoanFile>(0);

    public RealCustomer() {
    }

    //------------setter getter------------
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getInternationalID() {
        return internationalID;
    }

    public void setInternationalID(String internationalID) {
        this.internationalID = internationalID;
    }

    public Set<LoanFile> getLoanFiles() {
        return loanFiles;
    }

    public void setLoanFiles(Set<LoanFile> loanFiles) {
        this.loanFiles = loanFiles;
    }
}
