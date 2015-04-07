package nostalgic.textadv.voxam;
 
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;
 
import cucumber.api.PendingException;
 
public class ParserSteps {
  private Parser parser;
  private Command command;
  
  @Given("^the game parser$")
  public void the_game_parser() throws Throwable {
    parser = new Parser();
  }
 
  @When("^the command \"(.*?)\" is parsed$")
  public void the_command_is_parsed(String input) throws Throwable {
    command = parser.parse(input);
  }
 
  @Then("^the verb is \"(.*?)\"$")
  public void the_verb_is(String verb) throws Throwable {
    assertEquals(verb, command.getVerb());
  }
 
  @Then("^there is no direct object$")
  public void there_is_no_direct_object() throws Throwable {
    assertNull("Should be no direct object.", command.getDirectObject());
    assertFalse("Should have a direct object.", command.hasDirectObject());
  }
 
  @Then("^the parser response is \"(.*?)\"$")
  public void the_parser_response_is(String response) throws Throwable {
    assertEquals(response, command.getMessage());
  }
}
