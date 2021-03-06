package com.hrzafer.prizma.preprocessing;

import com.google.common.base.CharMatcher;

import java.util.List;
import java.util.regex.Pattern;

/**
 *
 */
public class StandardTokenizer implements ITokenizer {
    @Override
    public List<String> tokenize(String str) {
        Pattern pattern = Pattern.compile("[^\\w']+", Pattern.UNICODE_CHARACTER_CLASS);
        Splitter splitter = new Splitter(pattern, false);
        List<String> tokens = splitter.split(str);
        normalize(tokens);
        return tokens;
    }

    private void normalize(List<String> tokens) {
        for (int i = tokens.size() - 1; i > -1; i--) {
            tokens.set(i, trimAphostropes(tokens.get(i)));
            if (tokens.get(i).isEmpty()) {
                tokens.remove(i);
            }
        }
    }

    public String trimAphostropes(String token) {
        return CharMatcher.is('\'').trimFrom(token);
    }
}
