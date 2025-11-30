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

        System.out.println("=================================");
        System.out.println("==== Welcome to Degree Works ====");
        System.out.println("=================================");

        while (true) {

MAIN_MENU:
            while (true) {
                try {
                    System.out.println("---- Main Menu ----");
                    System.out.println("1. See Worksheets");
                    System.out.println("2. Continue with degree plan");
                    System.out.println("3. Create a new worksheet");
                    System.out.println("4. Exit");
                    System.out.print("Enter selection: ");
                    int selection = userInput.nextInt();

                    if (selection == 1) {
                        if (worksheetList.isEmpty()) {
                            System.out.println("\nThere are no worksheets with degree plans\n");
                            continue MAIN_MENU;
                        }
                        else {
                            System.out.println();
                            int worksheetNumber = 1;
                            for (Worksheet worksheet : worksheetList) {
                                System.out.println("____ Worksheet " + worksheetNumber + " ____");
                                worksheet.dispalyWorksheetHeader();
                                worksheetNumber += 1;
                            }
                        }
                    }
                    else if (selection == 2) {
                        System.out.println();

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
                        System.out.println();
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
                                    worksheet = new Worksheet(degree);
                                    break;
                                }
                                else if (selection == 2) {
                                    degree = new MathDegreePlan();
                                    worksheet = new Worksheet(degree);
                                    break;
                                }
                                else if (selection == 3) {
                                    degree = new CompSciDegreePlan();
                                    worksheet = new Worksheet(degree);
                                    break;
                                }
                                else if (selection == 4) {
                                    degree = new EceDegreePlan();
                                    worksheet = new Worksheet(degree);
                                    break;
                                }
                                else if (selection == 5) {
                                    System.out.println();
                                    continue MAIN_MENU;
                                }
                                else {
                                    System.out.println("\n#ERROR: Incorrect Selection!\n");
                                    continue;
                                }
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
                    continue COURSE_SEL;
                }

                System.out.print("Enter Course ID: ");
                String course = userInput.nextLine();

                // Checks for minimum amount characters for course id i.e. CS 00100 has 8 characters
                if (course.length() < 8) {
                    System.out.println("\nENTER A VALID COURSE ID!\n");
                    continue COURSE_SEL;
                }

                System.out.print("Enter letter grade: ");
                String grade = userInput.nextLine();

                if (!worksheetList.get(selectedWorksheet).isValidNumericGrade(grade)) {
                    System.out.println("\nENTER A VALID GRADE!\n");
                    continue COURSE_SEL;
                }

                // Accepts lower and uppercase
                course = course.toUpperCase();
                grade = grade.toUpperCase(); 

                // TODO: Create a new methods to encapsulate all these statements
                if (course.substring(0,3).equals("CS ")) {
                    if (CompSciCatalog.checkCatalogForCourse(course)) {
                        worksheetList.get(selectedWorksheet).getDegreePlan().addToCompletedCoursework(CompSciCatalog.getCatalogCourse(course), grade);
                        worksheetList.get(selectedWorksheet).getDegreePlan().removeFromRequiredCoursework(CompSciCatalog.getCatalogCourse(course));
                    }
                    else {
                        System.out.println("\nCOURSE NOT FOUND!\n");
                    }
                }
                else if (course.substring(0,4).equals("ECE ")) {
                    if (EceCatalog.checkCatalogForCourse(course)) {
                        worksheetList.get(selectedWorksheet).getDegreePlan().addToCompletedCoursework(EceCatalog.getCatalogCourse(course), grade);
                        worksheetList.get(selectedWorksheet).getDegreePlan().removeFromRequiredCoursework(EceCatalog.getCatalogCourse(course));
                    }
                    else {
                        System.out.println("\nCOURSE NOT FOUND!\n");
                    }
                }
                else if (course.substring(0,5).equals("MATH ")) {
                    if (MathCatalog.checkCatalogForCourse(course)) {
                        worksheetList.get(selectedWorksheet).getDegreePlan().addToCompletedCoursework(MathCatalog.getCatalogCourse(course), grade);
                        worksheetList.get(selectedWorksheet).getDegreePlan().removeFromRequiredCoursework(MathCatalog.getCatalogCourse(course));
                    }
                    else {
                        System.out.println("\nCOURSE NOT FOUND!\n");
                    }
                }
                else if (course.substring(0,5).equals("PHYS ")) {
                    if (PhysicsCatalog.checkCatalogForCourse(course)) {
                        worksheetList.get(selectedWorksheet).getDegreePlan().addToCompletedCoursework(PhysicsCatalog.getCatalogCourse(course), grade);
                        worksheetList.get(selectedWorksheet).getDegreePlan().removeFromRequiredCoursework(PhysicsCatalog.getCatalogCourse(course));
                    }
                    else {
                        System.out.println("\nCOURSE NOT FOUND!\n");
                    }
                }
                else {
                    System.out.println("\nENTER A VALID COURSE ID!");
                }

                System.out.println();
            }
        }
    }
}

