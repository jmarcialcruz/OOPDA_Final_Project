public interface DegreeInfo {
    public void displayMajorRequirementsInfo();
    public void displayNonProgramRequirementsInfo();
    public void displayPlanInfo();

    public static void displaySectionHeader(String sectionHeader) {
        ColoredOutput.colorBrightBlue("________________________________" + sectionHeader +"________________________________\n");
        ColoredOutput.colorBrightRed("__COURSE_#__\t __GRADE__\t__CREDITS__\t__COURSE_NAME__\n");
    }

}
