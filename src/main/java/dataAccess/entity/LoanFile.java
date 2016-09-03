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

    public RealCustomer getRealCustomer() {
        return realCustomer;
    }

    public void setRealCustomer(RealCustomer realCustomer) {
        this.realCustomer = realCustomer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOAN_FILE_ID", nullable = false)
    private int loanFileId;

    @Column(name = "DURATION", nullable = false)
    private int duration;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_TYPE_ID", nullable = false)
    private LoanType loanType;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private RealCustomer realCustomer;

    //----constructor---------------------
    public LoanFile() {
    }

    //----------setter getter--------------
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

}

