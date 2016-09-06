package dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */
@Entity
@Table(name = "LOAN_TYPE")
public class LoanType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOAN_TYPE_ID")
    private Integer loanTypeId;

    @Column(name = "LOAN_NAME", nullable = false)
    private String loanName;

    @Column(name = "INTEREST_RATE", nullable = false)
    private float interestRate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanType")
    private Set<GrantCondition> grantConditions = new HashSet<GrantCondition>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanType")
    private Set<LoanFile> loanFiles = new HashSet<LoanFile>(0);

    //----------constructors------------------
    public LoanType() {

    }

    public LoanType(String loanName, float interestRate) {
        this.loanName = loanName;
        this.interestRate = interestRate;
    }
    //------------setter getter------------


    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public Set<GrantCondition> getGrantConditions() {
        return grantConditions;
    }

    public void setGrantConditions(Set<GrantCondition> grantConditions) {
        this.grantConditions = grantConditions;
    }

    public Set<LoanFile> getLoanFiles() {
        return loanFiles;
    }

    public void setLoanFiles(Set<LoanFile> loanFiles) {
        this.loanFiles = loanFiles;
    }

    public void setGrantConditions(GrantCondition grantCondition) {
    }
}