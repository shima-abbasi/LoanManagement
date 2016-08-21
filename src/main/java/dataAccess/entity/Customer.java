package dataAccess.entity;

import javax.persistence.*;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */

@Entity
@Table(name = "CUSTOMER")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "CUSTOMER_ID", nullable = false, insertable = false, updatable = false, unique = true)
    private int customerId;

    public Customer() {

    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


}
