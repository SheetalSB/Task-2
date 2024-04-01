package logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}

class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Your answer: ");
            int userChoice = scanner.nextInt();
            if (userChoice - 1 == question.getCorrectOptionIndex()) {
                score++;
            }
        }
        System.out.println("Your score: " + score + "/" + questions.size());
    }
}

public class QuizPlatform {
    private static Map<String, User> users = new HashMap<>();
    private static List<Quiz> quizzes = new ArrayList<>();

    public static void main(String[] args) {
        // Create users
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");
        users.put("user1", user1);
        users.put("user2", user2);

        // Authenticate user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User loggedInUser = users.get(username);
        if (loggedInUser != null && loggedInUser.authenticate(password)) {
            System.out.println("Login successful.");
            // Sample questions
            List<Question> questions = new ArrayList<>();
            questions.add(new Question("What is 2 + 2?", List.of("4", "5", "6", "7"), 0));
            questions.add(new Question("What is the capital of France?", List.of("London", "Paris", "Berlin", "Rome"), 1));

            // Create a quiz
            Quiz quiz = new Quiz(questions);
            quizzes.add(quiz);

            // Sample user taking the quiz
            quiz.takeQuiz();
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}
