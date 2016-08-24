package dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */
    @Entity
    @Table(name = "LOAN_FILE")
    public class LoanFile implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "LOAN_FILE_ID", nullable = false)
    private int loanFileId;

    @Column(name = "DURATION", nullable = false)
    private int duration;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "LOAN_ID", nullable = false)
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "ID", nullable = false)

    private GrantCondition grantCondition;


    public LoanFile() {
    }

    public int getLoanFileId() {
        return loanFileId;
    }

    public void setLoanFileId(int loanFileId) {
        this.loanFileId = loanFileId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public GrantCondition getGrantCondition() {
        return grantCondition;
    }

    public void setGrantCondition(GrantCondition grantCondition) {
        this.grantCondition = grantCondition;
    }
}

