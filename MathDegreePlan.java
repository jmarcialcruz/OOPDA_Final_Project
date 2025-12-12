/**
 * Concrete implementation of the Mathematics (B.S.) degree plan.
 *
 * This class defines the specific credit requirements, advisor details,
 * and course lists needed to complete the Math major. It implements the
 * display logic to print the degree audit section by section.
 *
 * @author  Jordi Marcial Cruz
 */

public final class MathDegreePlan extends DegreePlan implements DegreeInfo {

    MathDegreePlan() {
        super("Math (BS)");
        setAdvisor("Mason,Cristine");
        setAdvisorEmail("masonc@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
        setFreeElectiveCreditsReq(30);
        setRestrictedElectiveCreditsReq(24);
        
        // Initialize tracking for the specific sections defined in the Math Catalog
        createDegreeSections(MathCatalog.getNumberOfSections());
        setDegreeSectionList(MathCatalog.getCatalogSectionList());
    }

    public void addAllDegreeRequirements() {
        // Aggregate all course subsets into the main requirement list
        addRequiredCoursework(MathCatalog.getMajorRequiredCourses());
        addRequiredCoursework(MathCatalog.getNonProgramCourses());
        addRequiredCoursework(MathCatalog.getRowanExpCourses());
        addRequiredCoursework(MathCatalog.getRowanCoreCourses());
    }

    @Override
    public void getDegreeSectionProgress() {
        // Calculate progress specifically using the Math Elective list
        getDegreeSectionProgress(MathCatalog.getNumberOfSections(), MathCatalog.getElectiveCourses());
    }

    public void displayRowanExpInfo() {
        DegreeInfo.displaySectionHeader("ROWAN EXPERIENCE");
        // Filter and display only Rowan Experience courses
        displayEachCourse(MathCatalog.getRowanExpCourses());
    }

    public void displayRowanCoreInfo() {
        DegreeInfo.displaySectionHeader("ROWAN CORE");
        // Filter and display only Rowan Core courses
        displayEachCourse(MathCatalog.getRowanCoreCourses());
    }

    public void displayNonProgramRequirementsInfo() {
        DegreeInfo.displaySectionHeader("NON-PROGRAM REQUIREMENTS");
        // Filter and display non-Math required courses (CS, Physics, etc.)
        displayEachCourse(MathCatalog.getNonProgramCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("MATHEMATICS (1702)");
        // Filter and display Math Core courses
        displayEachCourse(MathCatalog.getMajorRequiredCourses());
    }

    public void displayRestrictedElectivesInfo(){
        DegreeInfo.displaySectionHeader("RESTRICTED ELECTIVES (" + getRestrictedElectiveCreditsReq() + " CREDITS)");
        // Calculate and display which completed courses count toward restricted electives
        displayRestrictedElectives(MathCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    public void displayFreeElectivesInfo(){
        DegreeInfo.displaySectionHeader("FREE ELECTIVES (" + getFreeElectiveCreditsReq() + " CREDITS)");
        // Calculate and display remaining credits counting toward free electives
        displayFreeElectiveCourses(MathCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
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
