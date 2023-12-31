package org.Project22;

import org.Project22.CFG_Handler.BobTheBuilder;
import org.Project22.CFG_Handler.CFGtoCNFConverter;
import org.Project22.CFG_Handler.CockeYoungerKasami.Rule;
import org.Project22.Matching.Match1;
import org.Project22.Matching.Match2;
import org.Project22.Matching.Match3;
import org.Project22.Matching.Match4;
import org.Project22.Matching.MatchingInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AnswerGenerator{

    // possible matching algorithms
    public static MatchingInterface matchingAlgorithms[] = {new Match1(),new Match2(),new Match3(),new Match4()};

    // cfg-tree used by the tree traversal algorithm
    public CFGTree cfg;

    // rules used by the cyk algorithm
    public CFGtoCNFConverter converter = new CFGtoCNFConverter();
    public List<Rule> rules = converter.getRulesCNF();

    // client for interacting with the python server
    public HttpClient client = HttpClient.newHttpClient();

    //threshold
    public float ConfidenceCutoff = 0.3f;

    //list of questions
    List<Question> questions;

    /**
     * @param questions list of question objects
     * @param algorithmChoice optional parameter, specify the matching algorithm type if needed
     */
    public AnswerGenerator(List<Question> questions, int... algorithmChoice){
        this.questions = questions;
        this.cfg = new CFGTree();
    }

    /**
     * @param userString question of the user
     * @return the appropriate answer for the user question
     */
    public String getAnswer(String userString){
        Tuple<Integer, Integer> algorithm = Main.ui.getMatchingAlgorithm();

        if (algorithm.x().intValue() == 0) { // template
            Triple<Integer, String, Float> question = matchQuestion(userString,questions, algorithm.y().intValue()).get(0);

            Question question2 = questions.get(question.x());
            List<Tuple<String, String>> variables = question2.getVariable2(userString);
        
            Main.ui.setConfidence(question.z());
            Main.ui.setDebugText(question2.cleanQuestion, variables);

            if (question.z()<ConfidenceCutoff)
                return "too low confidence to make a decision";
            return question2.getAnswer(variables);
        }
        else if (algorithm.x().intValue() == 1) { // cfg
            if (algorithm.y().intValue() == 0) { // tree traversal
                List<Tuple<String,String>> variables = cfg.matchString(userString);
                Main.ui.setDebugText("CFG Path:", variables);
                return cfg.getAnswer(variables, cfg.actions);
            }
            else if (algorithm.y().intValue() == 1) { // cyk
                List<Tuple<String,String>> placeholders = BobTheBuilder.getPlaceholders(rules, userString);
                if (BobTheBuilder.iterateRules(userString.split(" "), rules)) {
                    System.out.println("topic: "+BobTheBuilder.topic);
                    placeholders.add(new Tuple<String,String>(BobTheBuilder.topic.toLowerCase(), "*"));
                }
                Main.ui.setDebugText("Placeholders:", placeholders);
                return cfg.getAnswer(placeholders, converter.actions);
            }
            else if (algorithm.y().intValue() == 2) { // language model
                HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8000/infer"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"inference\":\"" + userString + "\"}"))
                .build();

                HttpResponse<String> response = null;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    e.printStackTrace();
                    return "error connecting to server";
                }

                Main.ui.setDebugText(response.body(), new ArrayList<Tuple<String, String>>());
                String[] strings = response.body().split("\"");
                return strings[3];
            }
        }
        throw new RuntimeException("error selecting matching algorithm.");
    }

    /**
     * Method used for debugging and testing returns the corresponding skill for a certain user question
     * @param userString
     * @param algorithm algorithm choice
     * @return null for invalid algorithm choice and for a valid one the question
     */
    public Tuple<Question,Float> getQuestion(String userString, int algorithm) {
        Triple<Integer, String, Float> question = matchQuestion(userString,questions, algorithm).get(0);
        if (question.equals(null))
            return null;
        if (question.z()<ConfidenceCutoff)
            return null;
        Question question2 = questions.get(question.x());
        return new Tuple<Question,Float>(question2,question.z()-matchQuestion(userString,questions, algorithm).get(1).z());
    }

    /**
     * @param userString question of the user
     * @param Questions list of question aka skills of the chatbot
     * @return an ordered list with (index of question,algorithm used + name of question, matches percentage)
     */
    public static List<Triple<Integer,String,Float>> matchQuestion(String userString, List<Question> Questions, int... algorithm){
        List<Triple<Integer,String,Float>> result = new ArrayList<>();
        int choice = 2;
        if (algorithm.length >= 1)
            choice = algorithm[0];
        if (choice >= matchingAlgorithms.length) {
            System.out.println("Invalid algorithm choice");
            return null;
        }
        for (Question question : Questions) {
            result.add(new Triple<>(Questions.indexOf(question),matchingAlgorithms[choice].getClass().getName()+" ,"+question.name,matchingAlgorithms[choice].Matching(userString,question)));
        }
        Comparator<Triple<Integer, String, Float>> comparator = new Comparator<Triple<Integer, String, Float>>() {
            @Override
            public int compare(Triple<Integer, String, Float> triple1, Triple<Integer, String, Float> triple2) {
                return Float.compare(triple2.z(),triple1.z());
            }
        };
        Collections.sort(result, comparator);
        return result;
    }
}
