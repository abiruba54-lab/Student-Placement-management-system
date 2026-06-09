import java.util.*;
class Student {
    String name;
    int id;
    String email;
    
    public Student(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }
    
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Email: " + email;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> verifiedEmails = new HashSet<>(); 
        List<Student> studentDatabase = new ArrayList<>(); 
        Queue<Student> interviewQueue = new LinkedList<>(); 
        String currentName = "";
        int currentId = 0;
        String currentEmail = "";
        boolean isRegistered = false;
        boolean isEmailVerified = false;

        int n;
        do {
            System.out.println("\n1.Student registration\n2.Email verification\n3.Store student details\n4.Schedule interviews\n5.Show All Data\n6.Exit");
            System.out.print("Enter the choice: ");
            n = sc.nextInt();
            
            switch(n) {
                case 1:
                    System.out.print("Enter student name: ");
                    currentName = sc.next();
                    System.out.print("Enter student ID: ");
                    currentId = sc.nextInt();
                    isRegistered = true;
                    isEmailVerified = false; 
                    System.out.println("Step 1 Complete: Registration data saved temporarily.");
                    break;
                    
                case 2:
                    if (!isRegistered) {
                        System.out.println("Error: Please complete Step 1 (Registration) first!");
                        break;
                    }
                    System.out.print("Enter your Email: ");
                    String emailInput = sc.next();
                    if (verifiedEmails.contains(emailInput)) {
                        System.out.println("Error: This email is already verified for another student!");
                    } else {
                        verifiedEmails.add(emailInput);
                        currentEmail = emailInput;
                        isEmailVerified = true;
                        System.out.println("Step 2 Complete: Email verified successfully using HashSet.");
                    }
                    break;
                    
                case 3:
                    if (!isRegistered || !isEmailVerified) {
                        System.out.println("Error: Complete steps 1 and 2 before storing details!");
                        break;
                    }
                    Student newStudent = new Student(currentName, currentId, currentEmail);
                    studentDatabase.add(newStudent);
                    System.out.println("Step 3 Complete: Student details stored in ArrayList.");
                    isRegistered = false;
                    isEmailVerified = false;
                    break;
                    
                case 4:
                    if (studentDatabase.isEmpty()) {
                        System.out.println("Error: No students found in the database to schedule.");
                        break;
                    }
                    
                    for (Student s : studentDatabase) {
                        if (!interviewQueue.contains(s)) {
                            interviewQueue.add(s);
                        }
                    }
                    System.out.println("Step 4 Complete: Students pushed to Interview Queue (FIFO).");
                    System.out.println("Next up for interview: " + interviewQueue.peek().name);
                    break;
                    
                case 5:
                    System.out.println("Verified Emails (HashSet): " + verifiedEmails);
                    System.out.println("Student Records (ArrayList): " + studentDatabase);
                    System.out.println("Interview Lineup (Queue): " + interviewQueue);
                    break;

                case 6:
                    System.out.println("Exit");
                    break;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        } while (n != 6);
    }
}