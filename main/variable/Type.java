package oop.ex6.main.variable;

import oop.ex6.main.parser.Regex;

import java.util.HashMap;

/**
 * This enum represents every possible type in s-java.
 */
public enum Type {
    INT(Regex.VALID_INT_VALUE),
    DOUBLE(Regex.VALID_DOUBLE_VALUE),
    STRING(Regex.VALID_STRING_VALUE),
    CHAR(Regex.VALID_CHAR_VALUE),
    BOOLEAN(Regex.VALID_BOOLEAN_VALUE);
    private static final HashMap<String, Type> legalTypes; //hash table that holds the String of each type.
    private final String typeRegex; //the valid value regex of each type.

    static {
        legalTypes = new HashMap<>();
        legalTypes.put("int", INT);
        legalTypes.put("double", DOUBLE);
        legalTypes.put("String", STRING);
        legalTypes.put("char", CHAR);
        legalTypes.put("boolean", BOOLEAN);
    }

    /*
    Private constructor.
     */
    Type(String validRegex) {
        typeRegex = validRegex;
    }

    /**
     * This method returns the Type object according to its key (as a string)
     * @param typeString - Key inside legalTypes' hashmap.
     * @return - Type object if found, null otherwise.
     */
    public static Type getType(String typeString) {
        return legalTypes.get(typeString);
    }

    /**
     * This method gets a String and checks if it fits the current instance's regex rules.
     * @param value - String to be checked.
     * @return true iff value is of (this)'s type.
     */
    public boolean validateValue(String value) {
        return value.matches(typeRegex);
    }

    /**
     * This method gets a Type object, and checks if this type can be treated as current instance's type,
     * according to s-java's type hierarchy.
     * @param type - Type object.
     * @return true if types are matched, false otherwise.
     */
    public boolean validateType(Type type){
        if(this == BOOLEAN){
            return (type == BOOLEAN || type == DOUBLE || type == INT);
        }
        if(this == DOUBLE){
            return (type == DOUBLE || type == INT);
        }
        return this == type;
    }

    /**
     * @return This method returns String in the form of "int|boolean|...". Each of s-java legal types
     * separated by
     * "|".
     */
    public static String getTypeRegexLine(){
        StringBuilder regexLine = new StringBuilder();
        String prefix = "";
        for(String type : legalTypes.keySet()){
            regexLine.append(prefix);
            prefix = Regex.REGEX_SEPARATOR;
            regexLine.append(type);
        }
        return regexLine.toString();
    }
}
