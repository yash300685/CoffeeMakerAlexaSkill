package com.example;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.ForecastIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoffeeSpeechlet implements Speechlet {
    String tomorrowWeather;
    private static final Logger log = LoggerFactory.getLogger(CoffeeSpeechlet.class);
    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {

        session.getSessionId();

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("makecoffee".equals(intentName)) {
            return makeCoffee();
        }

        else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

//    private String getWeatherTomorrow() {
//
//        RetrofitClient client=new RetrofitClient();
//
//        Call<WeatherInfo> call=client.getApi()
//                .getWeatherForecast();
//        try {
//            WeatherInfo value= call.execute().body();
//            tomorrowWeather= value.getForecast().getTxtForecast().getForecastday().get(2).getFcttextMetric();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return   tomorrowWeather;
////            ForecastIO fio = new ForecastIO("2fc44670a90edf0e300a11293af991cd"); //instantiate the class with the API key.
////            fio.setUnits(ForecastIO.UNITS_SI);             //sets the units as SI - optional
////            //fio.setExcludeURL("hourly,minutely");             //excluded the minutely and hourly reports from the reply
////            fio.getForecast("42.033360","-88.083405");
////
////            FIODaily daily = new FIODaily(fio);
////            //In case there is no daily data available
////            return(""+daily.getDay(1).temperatureMax()+"Centigrade");
//        }


    private SpeechletResponse getWeatherTomorrowResponse() {
        String speechText = null;

            //speechText = getWeatherTomorrow();



        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("WeatherInfo");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }


    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

    }

    /**
     * Creates and returns a {@code SpeechletResponse} with a welcome message.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Hi there, this is BoschCoffeeMachine Bot, you can ask make me to make a coffee , to get Coffee Bean or Water quantity";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Bosch Coffee Machine");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    /**
     * Creates a {@code SpeechletResponse} for the help intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getHelpResponse() {
        String speechText = "You can say hello to me!";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Bosch Coffee Machine");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    /**
     * Creates a {@code SpeechletResponse} for the hello intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse makeCoffee() {
        String speechText = null;
        try {
            speechText = requestMakeCoffee();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Bosch Coffee Machine");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    private String requestMakeCoffee() {
        String coffeeStatus;

        RetrofitClient client = new RetrofitClient();

        Call<String> call = client.getApi().makeCoffee();
        Response<String> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.isSuccessful()) {
            // request successful (status code 200, 201)

            coffeeStatus="Your coffee is ready";
            // publish the post added event

        } else {
            // request not successful (like 400,401,403 etc and 5xx)
            coffeeStatus="There is currently some problem with Coffee Machine ,Please try again later";

        }

return coffeeStatus;
    }



}
