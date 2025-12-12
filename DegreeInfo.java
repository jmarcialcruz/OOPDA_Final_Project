/**
 * Interface defining the standard display behaviors for degree programs.
 *
 * Classes implementing this interface must provide methods to display specific
 * categories of requirements (Major vs. Non-Program) and the overall plan.
 * It also includes a static method for printing standardized headers.
 *
 * @author  Jordi Marcial Cruz
 */

public interface DegreeInfo {
    public void displayMajorRequirementsInfo();
    public void displayNonProgramRequirementsInfo();
    public void displayPlanInfo();

    public static void displaySectionHeader(String sectionHeader) {
        ColoredOutput.colorBrightBlue("________________________________" + sectionHeader +"________________________________\n");
        ColoredOutput.colorBrightRed("__COURSE_#__\t __GRADE__\t__CREDITS__\t__COURSE_NAME__\n");
    }
}
