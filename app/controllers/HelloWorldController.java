package controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class HelloWorldController extends Controller {

    public Result helloWorld() {
        return ok("helloworld");
    }

    public Result hello(String name) {
        return ok("Hello" + name + "!!!");
    }

    public Result hellos(String name, Integer count) {
        final StringBuffer sb = new StringBuffer();

        final String msg = "Hello" + name + "\n";
        for (int i = 0; i < count; i++) {
            sb.append(msg);
        }
        return ok(sb.toString());
    }

    public Result greetings() {
        final JsonNode json = request().body().asJson();

        final String firstName = json.get("first_name").asText();
        final String lastName = json.get("last_name").asText();

        final Person person = Json.fromJson(json, Person.class);


        final String msg = "Hello " + person.getFirstName() + "  " + person.getLastName() + "!!";

        return ok(msg);
    }

    public Result me() {

        final Person me = new Person("prasanna", "reddy");

        final JsonNode json = Json.toJson(me);

        return ok(json);
    }

    static class Person {

        @JsonProperty("first_name")
        String firstName;

        @JsonProperty("last_name")
        String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}















