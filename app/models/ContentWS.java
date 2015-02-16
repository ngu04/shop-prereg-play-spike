package models;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Result;
import play.libs.F;
import play.twirl.api.Html;
import scala.concurrent.Await;

import static play.mvc.Results.ok;

/**
 * Created by ngu04 on 11/02/2015.
 */
public class ContentWS {
    
    

    public static Promise<Result> getTermsAndConditon(){

        Promise<JsonNode> jsonPromise = WS.url("http://content-microservice.nimbus-stage.cg.sky.com/rewards/termsandconditions").get().map(
                new Function<WSResponse, JsonNode>() {
                    public JsonNode apply(WSResponse response) {
                        //String json = response.asJson();
                        return response.asJson().get("tnc");
                    }
                }
        );


        Promise<Html> htmlPromise = jsonPromise.map(
                new Function<JsonNode, Html>() {
                    @Override
                    public Html apply(JsonNode jsonNode) throws Throwable {
                        return Html.apply(jsonNode.asText());
                    }
                }

        );

        Promise<play.mvc.Result> promiseofHtmlResult = htmlPromise.map(
                new Function<Html, Result>() {
                    @Override
                    public Result apply(Html html) throws Throwable {
                        return ok(html);
                    }
                }

        );
        

        
        return  promiseofHtmlResult;


    }

}