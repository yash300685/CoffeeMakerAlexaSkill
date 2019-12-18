package com.example;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ciwbm on 5/14/17.
 */

public class CoffeeSpeechHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds.add("amzn1.ask.skill.2b679123-1fd3-4747-8aee-caa069524b7f");
    }
    public CoffeeSpeechHandler() {
        super(new CoffeeSpeechlet(), supportedApplicationIds);
    }
}
