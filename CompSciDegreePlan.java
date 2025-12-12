/**
 * Concrete implementation of the Computer Science (B.S.) degree plan.
 *
 * This class defines the specific credit requirements, advisor details,
 * and course lists needed to complete the CS major. It implements the
 * display logic to print the degree audit section by section.
 *
 * @author  Jordi Marcial Cruz
 */

public final class CompSciDegreePlan extends DegreePlan implements DegreeInfo {

    CompSciDegreePlan() {
        super("Computer Science (BS)");
        setAdvisor("Graves,Jaclyn");
        setAdvisorEmail("gravesj@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
        setFreeElectiveCreditsReq(31);
        setRestrictedElectiveCreditsReq(11);
        
        // Initialize tracking for the specific sections defined in the Catalog
        createDegreeSections(CompSciCatalog.getNumberOfSections());
        setDegreeSectionList(CompSciCatalog.getCatalogSectionList());
    }

    public void addAllDegreeRequirements() {
        // Aggregate all course subsets into the main requirement list
        addRequiredCoursework(CompSciCatalog.getMajorRequiredCourses());
        addRequiredCoursework(CompSciCatalog.getNonProgramCourses());
        addRequiredCoursework(CompSciCatalog.getRowanExpCourses());
        addRequiredCoursework(CompSciCatalog.getRowanCoreCourses());
    }

    @Override
    public void getDegreeSectionProgress() {
        // Calculate progress specifically using the CS Elective list
        getDegreeSectionProgress(CompSciCatalog.getNumberOfSections(), CompSciCatalog.getElectiveCourses());
    }

    public void displayRowanExpInfo() {
        DegreeInfo.displaySectionHeader("ROWAN EXPERIENCE");
        // Filter and display only Rowan Experience courses
        displayEachCourse(CompSciCatalog.getRowanExpCourses());
    }

    public void displayRowanCoreInfo() {
        DegreeInfo.displaySectionHeader("ROWAN CORE");
        // Filter and display only Rowan Core courses
        displayEachCourse(CompSciCatalog.getRowanCoreCourses());
    }

    public void displayNonProgramRequirementsInfo() {
        DegreeInfo.displaySectionHeader("NON-PROGRAM REQUIREMENTS");
        // Filter and display non-CS required courses (Math, Physics, etc.)
        displayEachCourse(CompSciCatalog.getNonProgramCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("COMPUTER SCIENCE (0701)");
        // Filter and display CS Core courses
        displayEachCourse(CompSciCatalog.getMajorRequiredCourses());
    }

    public void displayRestrictedElectivesInfo(){
        DegreeInfo.displaySectionHeader("RESTRICTED ELECTIVES (" + getRestrictedElectiveCreditsReq() + " CREDITS)");
        // Calculate and display which completed courses count toward restricted electives
        displayRestrictedElectives(CompSciCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    public void displayFreeElectivesInfo(){
        DegreeInfo.displaySectionHeader("FREE ELECTIVES (" + getFreeElectiveCreditsReq() + " CREDITS)");
        // Calculate and display remaining credits counting toward free electives
        displayFreeElectiveCourses(CompSciCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    @Override
    public void displayPlanInfo() {
        // Execute the full display sequence for the degree audit
        displayRowanExpInfo();
        displayRowanCoreInfo();
        displayNonProgramRequirementsInfo();
        displayMajorRequirementsInfo();
        displayRestrictedElectivesInfo();
        displayFreeElectivesInfo();
    }
}
