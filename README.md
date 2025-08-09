# MCQ Quiz App (QuizApp)

A web-based MCQ (Multiple Choice Questions) Test Application built using the **Java EE stack**.

##  Features

| Feature | Description |
|---------|-------------|
| **User Login & Registration** | Secure entry before taking any test. |
| **Multiple Subjects** | Covers programming languages like Java, Python, DS, Algorithms, etc. |
| **Timed Test** | 30 questions, 30 minutes each, auto-submission after timeout. |
| **Tab Switch Detection** | Auto-submit after 3 tab switches or focus loss. |
| **Scoring & Results** | Final score and percentage displayed post-test. |
| **Full Tech Stack** | JSP, Servlets, Java, MySQL, CSS, JS (front-end interactivity) |

##  Tech Stack

- **Java** (Servlets + JSP)
- **MySQL** (for data persistence of users, subjects, questions, test results)
- **HTML / CSS / JavaScript** (front-end, timer, navigation protection)
- **Eclipse** (with Dynamic Web Project)
- **Jakarta Servlet API**

##  Project Structure

/QuizApp <-- Root directory
|-- src/ <-- Java Servlet & DAO code
|-- WebContent/ <-- JSPs, CSS, JS files
|-- WEB-INF/
| |-- web.xml <-- Web application configuration
|-- sql/ <-- Sample SQL scripts for schema and MCQs
|-- .gitignore
|-- README.md
