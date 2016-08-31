package dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */
@Entity
@Table(name = "GRANT_CONDITION")
public class GrantCondition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRANT_ID", nullable = false)
    private int grantId;

    @Column(name = "GRANT_NAME", nullable = false)
    private String grantName;

    @Column(name = "MIN_DURATION", nullable = false)
    private int minDuration;

    @Column(name = "MAX_DURATION", nullable = false)
    private int maxDuration;

    @Column(name = "MIN_AMOUNT", nullable = false)
    private BigDecimal minAmount;

    @Column(name = "MAX_AMOUNT", nullable = false)
    private BigDecimal maxAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "LOAN_TYPE_ID", nullable = false)
    private LoanType loanType;

//-------------setter getter---------------
    public int getGrantId() {
        return grantId;
    }

    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    public String getGrantName() {
        return grantName;
    }

    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public GrantCondition() {
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
