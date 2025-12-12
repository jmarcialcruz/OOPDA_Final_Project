import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.System;
import java.util.InputMismatchException;

/**
 * The main driver class for the Degree Tracker application.
 *
 * This class provides the command line interface for users to interact
 * with the system. It handles the main application loop, menu navigation,
 * input validation, and the coordination between Worksheets, DegreePlans,
 * and the Course Catalogs.
 *
 * @author  Jordi Marcial Cruz
 */

public class DegreeTracker {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // List to store multiple student sessions/worksheets in memory
        List<Worksheet> worksheetList = new ArrayList<>();
        int selectedWorksheet;

        System.out.println("=================================");
        System.out.println("==== Welcome to Degree Tracker ====");
        System.out.println("=================================");

        // Main application loop
        while (true) {

// Label for breaking back to the main menu from nested loops
MAIN_MENU:
            while (true) {
                try {
                    System.out.print(ColoredOutput.RESET);
                    System.out.println("---- Main Menu ----");
                    System.out.println("1. See Worksheets");
                    System.out.println("2. Continue with degree plan");
                    System.out.println("3. Create a new worksheet");
                    System.out.println("4. Exit");
                    System.out.print("Enter selection: ");
                    int selection = userInput.nextInt();
                    System.out.println();

                    // --- Option 1: View Summary of All Worksheets ---
                    if (selection == 1) {
                        if (worksheetList.isEmpty()) {
                            System.out.println("There are no worksheets with degree plans\n");
                            continue MAIN_MENU;
                        }
                        else {
                            int worksheetNumber = 1;
                            // Iterate through all active worksheets and display their headers/progress
                            for (Worksheet worksheet : worksheetList) {
                                System.out.print(ColoredOutput.BRIGHT_BLUE);
                                System.out.println("____ Worksheet " + worksheetNumber + " ____");
                                System.out.print(ColoredOutput.BRIGHT_CYAN);
                                worksheet.displayWorksheetHeader();
                                worksheet.displayDegreeProgressBar();
                                worksheetNumber += 1;
                            }
                        }
                    }
                    // --- Option 2: Select an Existing Worksheet to Edit ---
                    else if (selection == 2) {
                        if (worksheetList.isEmpty()) {
                            System.out.println("There are no worksheets to choose from, please create a new worksheet\n");
                            continue MAIN_MENU;
                        }
                        else {
                            while (true) {
                                try {
                                    System.out.println("---- Select Worksheet ----");

                                    int worksheetNumber = 1;
                                    for (Worksheet worksheet : worksheetList) {
                                        System.out.println(worksheetNumber + ". " + worksheet.getDegreePlan().getFieldOfStudy());
                                        worksheetNumber += 1;
                                    }

                                    System.out.print("Enter selection: ");
                                    selection = userInput.nextInt();

                                    // Validate selection range
                                    if (selection > 0 && selection < worksheetList.size() + 1) {
                                        selectedWorksheet = selection -1;
                                        break MAIN_MENU; // Exit to the course selection phase
                                    }
                                    else {
                                        System.out.println("\nOut of bounds\n");
                                        continue MAIN_MENU;
                                    }
                                }
                                catch (InputMismatchException e) {
                                    System.out.println("\n#ERROR: Invalid input. Please enter a whole number.\n");
                                    userInput.next(); // Clear invalid input
                                    continue;
                                }
                            }
                        }
                    }
                    // --- Option 3: Create a New Worksheet ---
                    else if (selection == 3) {
                        Worksheet worksheet;

                        while(true) {
                            try {
                                System.out.println("---- Select Degree Plan ----");
                                System.out.println("1. Physics");
                                System.out.println("2. Mathematics");
                                System.out.println("3. Computer Science");
                                System.out.println("4. To Return to Main Menu");
                                System.out.print("Choose a degree plan: ");
                                selection  = userInput.nextInt();

                                DegreePlan degreePlan;

                                // Instantiate the specific subclass based on user choice
                                if (selection == 1) {
                                    degreePlan = new PhysicsDegreePlan();
                                }
                                else if (selection == 2) {
                                    degreePlan = new MathDegreePlan();
                                }
                                else if (selection == 3) {
                                    degreePlan = new CompSciDegreePlan();
                                }
                                else if (selection == 4) {
                                    System.out.println();
                                    continue MAIN_MENU;
                                }
                                else {
                                    System.out.println("\n#ERROR: Incorrect Selection!\n");
                                    continue;
                                }

                                worksheet = new Worksheet(degreePlan);
                                break;
                            }
                            catch (InputMismatchException e) {
                                System.out.println("\n#ERROR: Invalid input. Please enter a whole number.\n");
                                userInput.next();
                                continue;
                            }
                        }

                        // Add new worksheet and select it immediately
                        worksheetList.add(worksheet);
                        selectedWorksheet = worksheetList.size() -1;
                        break;
                    }
                    // --- Option 4: Exit Application ---
                    else if (selection == 4) {
                        return;
                    }
                    else {
                        System.out.println("\n#ERROR: Incorrect Selection!\n");
                        continue MAIN_MENU;
                    }

                }
                catch (InputMismatchException e) {
                    System.out.println("\n#ERROR: Invalid input. Please enter a whole number.\n");
                    userInput.next();
                    continue;
                }
            }

            System.out.println();
            userInput.nextLine(); 

// Label for the Course Selection / Editing Loop
COURSE_SEL:
            while(true) {
                Worksheet currentWorksheet = worksheetList.get(selectedWorksheet);
                DegreePlan currentDegreePlan = currentWorksheet.getDegreePlan();

                // Show the full report for the current student
                currentWorksheet.displayWorksheetInfo();

                // Check if user wants to edit this worksheet or go back
                while (true) {
                    System.out.print("Continue with course selection (y) or return to main menu (n): ");
                    String selection = userInput.nextLine();

                    if (selection.equalsIgnoreCase("n")){
                        System.out.println();
                        break COURSE_SEL; // Returns to the top of the MAIN_MENU loop
                    }
                    else if (!selection.equalsIgnoreCase("y")){
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.print("Add course (1) or see electives then add course (2) or remove course (3): ");
                    String selection = userInput.nextLine();

                    // --- Add Course Logic (Option 1 or 2) ---
                    if (selection.equals("1") || selection.equals("2")){
                        // If option 2, display available electives first
                        if (selection.equals("2")) {
                            MiscCatalog.displayAllElectiveCourses(currentDegreePlan.getRequiredCoursework(), currentDegreePlan.getCompletedCoursework());
                        }
                        
                        System.out.print("Enter Course #: ");
                        String course = userInput.nextLine().toUpperCase();
                        
                        System.out.print("Enter letter grade: ");
                        String grade = userInput.nextLine().toUpperCase();

                        // Validate grade format and course ID length
                        if (!currentWorksheet.isValidLetterGrade(grade) || course.length() < 8) {
                            System.out.println("\nENTER A VALID COURSE AND GRADE!\n");
                            continue;
                        }

                        // Search catalogs sequentially to find the course object
                        if (CompSciCatalog.isCatalogCourse(course)) {
                            currentWorksheet.updateDegreePlanCoursework(CompSciCatalog.getCatalogCourse(course), grade);
                        }
                        else if (MathCatalog.isCatalogCourse(course)) {
                            currentWorksheet.updateDegreePlanCoursework(MathCatalog.getCatalogCourse(course), grade);
                        }
                        else if (PhysicsCatalog.isCatalogCourse(course)) {
                            currentWorksheet.updateDegreePlanCoursework(PhysicsCatalog.getCatalogCourse(course), grade);
                        }
                        else if (MiscCatalog.isCatalogCourse(course)) {
                            currentWorksheet.updateDegreePlanCoursework(MiscCatalog.getCatalogCourse(course), grade);
                        }
                        else {
                            System.out.println("\nCOURSE NOT FOUND!\n");
                            continue;
                        }
                        break;

                    }

                    // --- Remove Course Logic (Option 3) ---
                    else if (selection.equals("3")){
                        System.out.print("Enter Course #: ");
                        String course = userInput.nextLine().toUpperCase();

                        // Search catalogs to find the course object to remove
                        if (CompSciCatalog.isCatalogCourse(course)) {
                            currentWorksheet.updateDegreePlanCoursework(CompSciCatalog.getCatalogCourse(course));
                        }
                        else if (MathCatalog.isCatalogCourse(course)) {
                            currentWorksheet.updateDegreePlanCoursework(MathCatalog.getCatalogCourse(course));
                        }
                        else if (PhysicsCatalog.isCatalogCourse(course)) {
                            currentWorksheet.updateDegreePlanCoursework(PhysicsCatalog.getCatalogCourse(course));
                        }
                        else if (MiscCatalog.isCatalogCourse(course)) {
                            currentWorksheet.updateDegreePlanCoursework(MiscCatalog.getCatalogCourse(course));
                        }
                        else {
                            System.out.println("\nCOURSE NOT FOUND!\n");
                            continue;
                        }
                        break;
                    }

                    else {
                        continue;
                    }
                }

                System.out.println();
            }
        }
    }
}
