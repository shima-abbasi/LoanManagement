package dataAccess.entity;
import javax.persistence.Table;
import javax.persistence.*;

import javax.persistence.Entity;

/**
 * Created by Dotin school 5 on 9/11/2016.
 */
@Entity
@Table(name = "CUSTOMER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private int customerId;

    @Column(name = "CUSTOMER_NUMBER", nullable = false, updatable = false, unique = true)
    private int customerNumber;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
}
