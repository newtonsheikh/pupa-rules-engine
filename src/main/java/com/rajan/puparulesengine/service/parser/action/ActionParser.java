package com.rajan.puparulesengine.service.parser.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.rajan.puparulesengine.rulesinterpreter.rule.action.Action;
import com.rajan.puparulesengine.rulesinterpreter.rule.action.SimpleAction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActionParser {

    public List<Action> parse(JsonNode actionsNode) throws Exception {
        List<Action> actions = new ArrayList<>();

        if(!actionsNode.isArray()){
            throw new Exception("Actions should be an array");
        }

        for(JsonNode actionNode: actionsNode){
            String actionType = actionNode.get("type").asText();
            Action action = null;

            if("simpleaction".equalsIgnoreCase(actionType)){
                action = new SimpleAction();
                action.setType("simpleaction");
                action.setField(actionNode.get("field").asText());
                action.setValue(actionNode.get("value").asText());
            }
            actions.add(action);
        }
        return actions;
    }
}
