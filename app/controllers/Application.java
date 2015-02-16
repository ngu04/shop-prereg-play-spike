package controllers;

import play.api.libs.ws.WS;
import play.libs.F;
import play.mvc.*;
import play.data.*;

import models.Customer;
import models.ContentWS;
import scala.concurrent.Await;


public class Application extends Controller {
    static Form<Customer> customerForm = Form.form(Customer.class);



    public static F.Promise<Result> index() {
       // return redirect(routes.Application.customers());
        F.Promise<Result> termsAndCondition = ContentWS.getTermsAndConditon();
         return termsAndCondition;
    }


    

    public static Result customers() {
        return ok(
                views.html.index.render(Customer.all(), customerForm)
        );
    }

    public static Result newCustomer() {
        Form<Customer> filledForm = customerForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    views.html.index.render(Customer.all(), filledForm)
            );
        } else {
            Customer.create(filledForm.get());
            return redirect(routes.Application.customers());
        }
    }

    public static Result deleteCustomer(String id) {
        Customer.delete(id);
        return redirect(routes.Application.customers());
    }

    






}



  
