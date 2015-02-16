
package models;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import play.libs.F;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.ObjectId;


import java.util.List;

public class Customer{

    @Id
    public String id;

    public String name;
    
    public String email;
    
    public String sex;


    private static JacksonDBCollection<Customer, String> coll = MongoDB.getCollection("customers", Customer.class, String.class);

    public Customer(){
        
    }
    public Customer(String id , String name,String email,String sex){
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
    }

    public static List<Customer> all() {
        return Customer.coll.find().toArray();
    }

    public static void create(Customer customer) {
        Customer.coll.save(customer);
    }

    public static void create(String id ,String name,String email,String sex){
        create(new Customer(id,name,email,sex));
    }

    public static void delete(String id) {
        Customer customer = Customer.coll.findOneById(id);
        if (customer != null)
            Customer.coll.remove(customer);
    }

    public static void removeAll(){
        Customer.coll.drop();
    }
    
}