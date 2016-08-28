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


    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }



//----------constructor-----------------
    public Customer() {

    }
//--------------setter getter-----------
    public int getCustomerId(int i) {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}

