import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.System;
import java.util.InputMismatchException;

public class DegreeWorks {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        List<Worksheet> worksheetList = new ArrayList<>();
        int selectedWorksheet;

        // TODO: Prompt user if they want to see entire catalog
        // TODO: Add option to add minor to existing degree plan (maybe CUGs?)
        // TODO: Change worksheetList into a set and reject similar degree plans
        
        System.out.println("=================================");
        System.out.println("==== Welcome to Degree Works ====");
        System.out.println("=================================");

        while (true) {

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

                    if (selection == 1) {
                        if (worksheetList.isEmpty()) {
                            System.out.println("There are no worksheets with degree plans\n");
                            continue MAIN_MENU;
                        }
                        else {
                            int worksheetNumber = 1;
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

                                    if (selection > 0 && selection < worksheetList.size() + 1) {
                                        selectedWorksheet = selection -1;
                                        break MAIN_MENU;
                                    }
                                    else {
                                        System.out.println("\nOut of bounds\n");
                                        continue MAIN_MENU;
                                    }
                                }
                                catch (InputMismatchException e) {
                                    System.out.println("\n#ERROR: Invalid input. Please enter a whole number.\n");
                                    userInput.next();
                                    continue;
                                }
                            }
                        }
                    }
                    else if (selection == 3) {
                        Worksheet worksheet;

                        while(true) {
                            try {
                                System.out.println("---- Select Degree Plan ----");
                                System.out.println("1. Physics");
                                System.out.println("2. Mathematics");
                                System.out.println("3. Computer Science");
                                System.out.println("4. Electical and Computer Engineering");
                                System.out.println("5. To Return to Main Menu");
                                System.out.print("Choose a degree plan: ");
                                selection  = userInput.nextInt();

                                DegreePlan degree;

                                if (selection == 1) {
                                    degree = new PhysicsDegreePlan();
                                }
                                else if (selection == 2) {
                                    degree = new MathDegreePlan();
                                }
                                else if (selection == 3) {
                                    degree = new CompSciDegreePlan();
                                }
                                else if (selection == 4) {
                                    degree = new EceDegreePlan();
                                }
                                else if (selection == 5) {
                                    System.out.println();
                                    continue MAIN_MENU;
                                }
                                else {
                                    System.out.println("\n#ERROR: Incorrect Selection!\n");
                                    continue;
                                }

                                worksheet = new Worksheet(degree);
                                break;
                            }
                            catch (InputMismatchException e) {
                                System.out.println("\n#ERROR: Invalid input. Please enter a whole number.\n");
                                userInput.next();
                                continue;
                            }
                        }

                        worksheetList.add(worksheet);
                        selectedWorksheet = worksheetList.size() -1;
                        break;
                    }
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

COURSE_SEL:
            while(true) {

                worksheetList.get(selectedWorksheet).displayWorksheetInfo();
                System.out.print("Add a course (y) or return to main menu (n): ");
                String selection = userInput.nextLine();

                if (selection.equalsIgnoreCase("n")){
                    System.out.println();
                    break COURSE_SEL;
                }
                else if (!selection.equalsIgnoreCase("y")){
                    System.out.println();
                    continue COURSE_SEL;
                }

                System.out.print("Enter Course #: ");
                String course = userInput.nextLine().toUpperCase();

                // Checks for minimum amount characters for course id i.e. CS 00100 has 8 characters
                if (course.length() < 8) {
                    System.out.println("\nENTER A VALID COURSE #!\n");
                    continue COURSE_SEL;
                }

                System.out.print("Enter letter grade: ");
                String grade = userInput.nextLine().toUpperCase();

                if (!worksheetList.get(selectedWorksheet).isValidNumericGrade(grade)) {
                    System.out.println("\nENTER A VALID GRADE!\n");
                    continue COURSE_SEL;
                }

                if (CompSciCatalog.isCatalogCourse(course)) {
                    worksheetList.get(selectedWorksheet).updateDegreePlanCoursework(CompSciCatalog.getCatalogCourse(course), grade);
                }
                else if (EceCatalog.isCatalogCourse(course)) {
                    worksheetList.get(selectedWorksheet).updateDegreePlanCoursework(EceCatalog.getCatalogCourse(course), grade);
                }
                else if (MathCatalog.isCatalogCourse(course)) {
                    worksheetList.get(selectedWorksheet).updateDegreePlanCoursework(MathCatalog.getCatalogCourse(course), grade);
                }
                else if (PhysicsCatalog.isCatalogCourse(course)) {
                    worksheetList.get(selectedWorksheet).updateDegreePlanCoursework(PhysicsCatalog.getCatalogCourse(course), grade);
                }
                else if (MiscCatalog.isCatalogCourse(course)) {
                    worksheetList.get(selectedWorksheet).updateDegreePlanCoursework(MiscCatalog.getCatalogCourse(course), grade);
                }
                else {
                    System.out.println("\nCOURSE NOT FOUND!\n");
                }

                System.out.println();
            }
        }
    }
}

