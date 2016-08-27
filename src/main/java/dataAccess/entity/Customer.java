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
    @GeneratedValue
    @Column(name = "CUSTOMER_ID", nullable = false, insertable = false, updatable = false, unique = true)
    private Integer customerId;

//----------constructor-----------------
    public Customer() {

    }
//--------------setter getter-----------
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}

