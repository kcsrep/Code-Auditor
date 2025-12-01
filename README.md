
# Code Compliance Auditor 🔍

A JavaFX-based desktop application that analyzes Java source code for security vulnerabilities and code quality issues.

## 🌟 Features

- **Security Analysis**: Detects hardcoded passwords, API keys, and SQL injection vulnerabilities
- **Code Quality Checks**: Identifies magic numbers, unclear variable names, and poor coding practices
- **User-Friendly GUI**: Modern JavaFX interface with file browser and detailed reporting
- **Comprehensive Reports**: Color-coded issues with severity levels and fix recommendations
- **Extensible Architecture**: Easy to add new detection rules

## 🚀 Technologies Used

- **Java 17**
- **JavaFX 21** - Modern GUI framework
- **Maven** - Dependency management and build automation
- **Design Patterns**: MVC architecture for clean separation of concerns

## 📋 Current Detection Rules

### Security Issues (Critical)
- ✅ Hardcoded passwords and credentials
- ✅ SQL injection vulnerabilities
- ✅ Hardcoded API keys and tokens

### Code Quality Issues (Warning)
- ✅ Magic numbers with unclear variable names
- ✅ Poor variable naming conventions

## 🛠️ Installation & Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Clone and Run
```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/code-auditor.git

# Navigate to project directory
cd code-auditor

# Run the application
mvn clean javafx:run
```

## 📸 Screenshots

### Main Interface
*Professional GUI with file selection and analysis capabilities*

### Analysis Results
*Detailed report showing security vulnerabilities and code quality issues*

## 🎯 How to Use

1. **Launch the application** using `mvn javafx:run`
2. **Click "Browse Files"** to select a Java file to analyze
3. **Click "Analyze Code"** to run the security and quality checks
4. **Review the results** with severity levels and fix recommendations

## 🔧 Project Structure

```
code-auditor/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── codeauditor/
│                   ├── CodeAuditorGUI.java      # Main application
│                   ├── ComprehensiveTest.java   # Test file with issues
│                   └── TestCode.java            # Sample test file
├── pom.xml                                      # Maven configuration
└── README.md                                    # Project documentation
```

## 🚀 Future Enhancements

- [ ] Add more detection rules (empty catch blocks, TODO comments, long methods)
- [ ] Implement severity-based color coding
- [ ] Export reports to HTML/PDF
- [ ] Analyze entire directories/projects
- [ ] Add configuration panel for customizable rules
- [ ] Integration with CI/CD pipelines
- [ ] Historical trend analysis

## 👨‍💻 Development

Built as a portfolio project to demonstrate:
- Java development skills
- GUI application design
- Security awareness
- Code quality standards
- Maven build management

## 📝 License

MIT License - Feel free to use this project for learning and portfolio purposes.

## 🤝 Contributing

This is a portfolio project, but suggestions and improvements are welcome!

