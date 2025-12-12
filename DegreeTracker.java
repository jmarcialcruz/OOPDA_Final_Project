import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.System;
import java.util.InputMismatchException;

public class DegreeTracker {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        List<Worksheet> worksheetList = new ArrayList<>();
        int selectedWorksheet;

        System.out.println("=================================");
        System.out.println("==== Welcome to Degree Tracker ====");
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
                                System.out.println("4. To Return to Main Menu");
                                System.out.print("Choose a degree plan: ");
                                selection  = userInput.nextInt();

                                DegreePlan degreePlan;

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
                Worksheet currentWorksheet = worksheetList.get(selectedWorksheet);
                DegreePlan currentDegreePlan = currentWorksheet.getDegreePlan();

                currentWorksheet.displayWorksheetInfo();

                while (true) {
                    System.out.print("Continue with course selection (y) or return to main menu (n): ");
                    String selection = userInput.nextLine();

                    if (selection.equalsIgnoreCase("n")){
                        System.out.println();
                        break COURSE_SEL;
                    }
                    else if (!selection.equalsIgnoreCase("y")){
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.print("Add course (1) or see electives then add course (2) or remove course (3): ");
                    String selection = userInput.nextLine();

                    if (selection.equals("1") || selection.equals("2")){
                        if (selection.equals("2")) {
                            MiscCatalog.displayAllElectiveCourses(currentDegreePlan.getRequiredCoursework(), currentDegreePlan.getCompletedCoursework());
                        }
                        
                        System.out.print("Enter Course #: ");
                        String course = userInput.nextLine().toUpperCase();
                        
                        System.out.print("Enter letter grade: ");
                        String grade = userInput.nextLine().toUpperCase();

                        if (!currentWorksheet.isValidLetterGrade(grade) || course.length() < 8) {
                            System.out.println("\nENTER A VALID COURSE AND GRADE!\n");
                            continue;
                        }

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

                    else if (selection.equals("3")){
                        System.out.print("Enter Course #: ");
                        String course = userInput.nextLine().toUpperCase();

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

