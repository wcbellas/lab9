$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("nostalgic/textadv/voxam/parser.feature");
formatter.feature({
  "line": 1,
  "name": "Parsing User Input",
  "description": "",
  "id": "parsing-user-input",
  "keyword": "Ability"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "the game parser",
  "keyword": "Given "
});
formatter.match({
  "location": "ParserSteps.the_game_parser()"
});
formatter.result({
  "duration": 288361311,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Parse a valid single word command",
  "description": "",
  "id": "parsing-user-input;parse-a-valid-single-word-command",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "the command \"inventory\" is parsed",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "the verb is \"inventory\"",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "there is no direct object",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "the parser response is \"Success\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "inventory",
      "offset": 13
    }
  ],
  "location": "ParserSteps.the_command_is_parsed(String)"
});
formatter.result({
  "duration": 1394090,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "inventory",
      "offset": 13
    }
  ],
  "location": "ParserSteps.the_verb_is(String)"
});
formatter.result({
  "duration": 1020414,
  "status": "passed"
});
formatter.match({
  "location": "ParserSteps.there_is_no_direct_object()"
});
formatter.result({
  "duration": 32638,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Success",
      "offset": 24
    }
  ],
  "location": "ParserSteps.the_parser_response_is(String)"
});
formatter.result({
  "duration": 47465,
  "status": "passed"
});
});