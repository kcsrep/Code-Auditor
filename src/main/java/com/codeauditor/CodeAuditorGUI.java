package com.codeauditor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CodeAuditorGUI extends Application {
    
    // GUI components
    private TextArea resultsArea;
    private Label selectedFileLabel;
    private File selectedFile;
    
    @Override
    public void start(Stage primaryStage) {
        // Set up the window
        primaryStage.setTitle("Code Compliance Auditor");
        
        // Create the main layout container
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #f5f5f5;");
        
        // === HEADER ===
        Label titleLabel = new Label("🔍 Code Compliance Auditor");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        Label subtitleLabel = new Label("Analyze Java code for security and style issues");
        subtitleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");
        
        // === FILE SELECTION SECTION ===
        VBox fileSelectionBox = new VBox(10);
        fileSelectionBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10;");
        
        Label selectLabel = new Label("Select File to Analyze:");
        selectLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        
        HBox fileSection = new HBox(10);
        fileSection.setAlignment(Pos.CENTER_LEFT);
        
        selectedFileLabel = new Label("No file selected");
        selectedFileLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #95a5a6;");
        
        Button browseButton = new Button("📁 Browse Files");
        browseButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-cursor: hand; -fx-background-radius: 5;");
        
        fileSection.getChildren().addAll(selectedFileLabel, browseButton);
        fileSelectionBox.getChildren().addAll(selectLabel, fileSection);
        
        // === ANALYZE BUTTON ===
        Button analyzeButton = new Button("▶ Analyze Code");
        analyzeButton.setStyle("-fx-font-size: 16px; -fx-padding: 15px 40px; -fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 5;");
        analyzeButton.setDisable(true);
        
        // === RESULTS SECTION ===
        VBox resultsBox = new VBox(10);
        resultsBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10;");
        
        Label resultsLabel = new Label("📊 Analysis Results:");
        resultsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setPrefHeight(350);
        resultsArea.setWrapText(true);
        resultsArea.setStyle("-fx-font-family: 'Consolas', 'Courier New', monospace; -fx-font-size: 13px; -fx-control-inner-background: #fafafa;");
        resultsArea.setText("💡 Select a Java file and click 'Analyze Code' to begin...\n\nThis tool checks for:\n• Hardcoded passwords\n• Magic numbers\n• Poor variable naming\n• Potential SQL injection risks\n\nHappy coding!");
        
        resultsBox.getChildren().addAll(resultsLabel, resultsArea);
        
        // === BUTTON ACTIONS ===
        
        // Browse button: Open file chooser
        browseButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Java File to Analyze");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Java Files", "*.java")
            );
            
            selectedFile = fileChooser.showOpenDialog(primaryStage);
            
            if (selectedFile != null) {
                selectedFileLabel.setText("✓ " + selectedFile.getName());
                selectedFileLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #27ae60; -fx-font-weight: bold;");
                analyzeButton.setDisable(false);
                resultsArea.setText("📄 Ready to analyze: " + selectedFile.getName() + "\n\nClick 'Analyze Code' to start!");
            }
        });
        
        // Analyze button: Run the analysis
        analyzeButton.setOnAction(e -> {
            if (selectedFile != null) {
                analyzeCode(selectedFile);
            }
        });
        
        // Hover effects for buttons
        browseButton.setOnMouseEntered(e -> browseButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #2980b9; -fx-text-fill: white; -fx-cursor: hand; -fx-background-radius: 5;"));
        browseButton.setOnMouseExited(e -> browseButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-cursor: hand; -fx-background-radius: 5;"));
        
        analyzeButton.setOnMouseEntered(e -> {
            if (!analyzeButton.isDisabled()) {
                analyzeButton.setStyle("-fx-font-size: 16px; -fx-padding: 15px 40px; -fx-background-color: #229954; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 5;");
            }
        });
        analyzeButton.setOnMouseExited(e -> {
            if (!analyzeButton.isDisabled()) {
                analyzeButton.setStyle("-fx-font-size: 16px; -fx-padding: 15px 40px; -fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 5;");
            }
        });
        
        // === ADD EVERYTHING TO MAIN LAYOUT ===
        mainLayout.getChildren().addAll(
            titleLabel,
            subtitleLabel,
            new Separator(),
            fileSelectionBox,
            analyzeButton,
            resultsBox
        );
        
        // === CREATE AND SHOW THE WINDOW ===
        Scene scene = new Scene(mainLayout, 750, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // The analysis logic
    private void analyzeCode(File file) {
        StringBuilder output = new StringBuilder();
        output.append("═══════════════════════════════════════════\n");
        output.append("        CODE ANALYSIS REPORT\n");
        output.append("═══════════════════════════════════════════\n");
        output.append("File: ").append(file.getName()).append("\n");
        output.append("Path: ").append(file.getAbsolutePath()).append("\n");
        output.append("═══════════════════════════════════════════\n\n");
        
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            int issuesFound = 0;
            
            // Check each line
            for (int i = 0; i < lines.size(); i++) {
                int lineNumber = i + 1;
                String lineContent = lines.get(i);
                
                // Check for hardcoded password
                if (hasHardcodedPassword(lineContent)) {
                    output.append("⚠️  SECURITY ISSUE - LINE ").append(lineNumber).append("\n");
                    output.append("Issue: Hardcoded password detected\n");
                    output.append("Code: ").append(lineContent.trim()).append("\n");
                    output.append("Recommendation: Use environment variables or secure configuration\n\n");
                    issuesFound++;
                }
                
                // Check for magic number
                if (hasMagicNumber(lineContent)) {
                    output.append("⚠️  CODE QUALITY - LINE ").append(lineNumber).append("\n");
                    output.append("Issue: Magic number with unclear variable name\n");
                    output.append("Code: ").append(lineContent.trim()).append("\n");
                    output.append("Recommendation: Use descriptive variable names\n\n");
                    issuesFound++;
                }

                // checks for sql injection
                if (hasSQLInjectionRisk(lineContent)){
                    output.append("⚠️  SECURITY ISSUE - LINE ").append(lineNumber).append("\n");
                    output.append("Issue: Potential SQL Injection risk\n");
                    output.append("Code: ").append(lineContent.trim()).append("\n");
                    output.append("Recommendation: Use prepared statements or parameterized queries\n\n");
                    issuesFound++;
                }
            }
            
            // Summary
            output.append("═══════════════════════════════════════════\n");
            output.append("                SUMMARY\n");
            output.append("═══════════════════════════════════════════\n");
            output.append("Total lines analyzed: ").append(lines.size()).append("\n");
            output.append("Issues found: ").append(issuesFound).append("\n");
            output.append("═══════════════════════════════════════════\n\n");
            
            if (issuesFound == 0) {
                output.append("✅ Excellent! No issues found!\n");
                output.append("Your code follows security and quality best practices.");
            } else if (issuesFound <= 2) {
                output.append("⚠️  Minor issues detected.\n");
                output.append("Review the recommendations above.");
            } else  {
                output.append("❌ Multiple issues detected.\n");
                output.append("Please address the security and quality concerns.");
            }
            
        } catch (IOException e) {
            output.append("❌ ERROR: Could not read file!\n");
            output.append("Reason: ").append(e.getMessage()).append("\n\n");
            output.append("Please ensure:\n");
            output.append("• The file exists\n");
            output.append("• You have read permissions\n");
            output.append("• The file is not corrupted");
        }
        
        // Display results
        resultsArea.setText(output.toString());
    }
    
    // Rule: Check for hardcoded passwords
    private boolean hasHardcodedPassword(String line) {
        String lowerLine = line.toLowerCase();
        
        if (lowerLine.contains("password") && lowerLine.contains("=")) {
            // Exclude comments
            if (!lowerLine.trim().startsWith("//")) {
                return true;
            }
        }
        
        return false;
    }
    
    // Rule: Check for magic numbers
    private boolean hasMagicNumber(String line) {
        String lowerline = line.toLowerCase();

        if (lowerline.contains("int") && lowerline.contains("=")) {
            if (lowerline.contains(" x ") || lowerline.contains(" y ") || lowerline.contains(" temp ")) {
                // Exclude comments
                if (!lowerline.trim().startsWith("//")) {
                    return true;
                }
            }
        }
        
        return false;
    }

    // Rule check for SQL Injection risks
    private boolean hasSQLInjectionRisk(String line){
        String lowerLine = line.toLowerCase();

        if (lowerLine.contains("SELECT * FROM users WHERE id =") && lowerLine.contains("userId")){
            return true;
        }
        return false;

    }
    
    public static void main(String[] args) {
        launch(args);
    }
}