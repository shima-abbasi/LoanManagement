package dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */

@Entity
@Table(name = "CUSTOMER")
@Inheritance(strategy = InheritanceType.JOINED)

public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "ID", nullable = false, insertable = false, updatable = false, unique = true)
    private int id;

    @Column (name = "CUSTOMER_NUMBER" , nullable = false ,updatable = false , unique = true)
    private String customerNumber ;

    public Customer() {

    }

    //----------setter getter---------
    public String getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getId() {
        return id;
    }
    public void setId(int customerId) {
        this.id = customerId;
    }


}
