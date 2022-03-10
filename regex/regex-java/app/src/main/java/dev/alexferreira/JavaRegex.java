package dev.alexferreira;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegex {

    public boolean matches(String text, String regex) {
        Matcher matcher = createBaseMatcher(text, regex);

        return matcher.matches();
    }

    public boolean contains(String text, String regex) {
        Matcher matcher = createBaseMatcher(text, regex);

        return matcher.find();
    }

    public String find(String text, String regex) {
        Matcher matcher = createBaseMatcher(text, regex);

        return matcher.find() ? matcher.group() : null;
    }

    public int totalFound(String text, String regex) {
        Matcher matcher = createBaseMatcher(text, regex);

        return matcher.groupCount();
    }

    public List<String> findAll(String text, String regex) {
        Matcher matcher = createBaseMatcher(text, regex);
        List<String> results = new ArrayList<>();
        if (!matcher.find()){
            return results;
        }

        for (int i = 1; i <= matcher.groupCount(); i++) {
            results.add(matcher.group(i));
        }

        return results;
    }

    private Matcher createBaseMatcher(String text, String regex) {
        Pattern compile = Pattern.compile(regex);
        return compile.matcher(text);
    }
}
