package dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */

@Entity
@Table(name = "REAL_CUSTOMER")
@PrimaryKeyJoinColumn(name = "CUSTOMER_ID")
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

    @OneToMany(mappedBy = "realCustomer" , cascade = {CascadeType.ALL  })
    private Set<LoanFile> loanFiles = new HashSet<LoanFile>(0);

    public RealCustomer(String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID, Set<LoanFile> loanFiles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.internationalID = internationalID;
        this.loanFiles = loanFiles;
    }
}
