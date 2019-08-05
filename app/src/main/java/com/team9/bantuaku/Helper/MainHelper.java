package com.team9.bantuaku.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainHelper {

    public String getFirstName(String name){
        Pattern wordPattern = Pattern.compile("\\w+");
        Matcher wordMatcher = wordPattern.matcher(name);
        String firstName;
        if(!wordMatcher.matches()){
            firstName = name.substring(0, name.indexOf(" "));
            return firstName;
        }else{
            return name;
        }
    }
}
