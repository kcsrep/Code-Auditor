package com.codeauditor;

public class TestCode {
    public static void main(String[] args) {
        String password = "admin123";  // BAD: Hardcoded password
        int x = 100;                    // BAD: Unclear variable name
        
        System.out.println("Password is: " + password);
    }
}