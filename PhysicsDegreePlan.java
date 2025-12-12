/**
 * Concrete implementation of the Physics (B.S.) degree plan.
 *
 * This class defines the specific credit requirements, advisor details,
 * and course lists needed to complete the Physics major. It implements the
 * display logic to print the degree audit section by section.
 *
 * @author  Jordi Marcial Cruz
 */

public final class PhysicsDegreePlan extends DegreePlan implements DegreeInfo {

    PhysicsDegreePlan() {
        super("Physics (BS)");
        setAdvisor("Mason,Cristine");
        setAdvisorEmail("masonc@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
        setFreeElectiveCreditsReq(31);
        setRestrictedElectiveCreditsReq(11);
        
        // Initialize tracking for the specific sections defined in the Physics Catalog
        createDegreeSections(PhysicsCatalog.getNumberOfSections());
        setDegreeSectionList(PhysicsCatalog.getCatalogSectionList());
    }

    public void addAllDegreeRequirements() {
        // Aggregate all course subsets into the main requirement list
        addRequiredCoursework(PhysicsCatalog.getMajorRequiredCourses());
        addRequiredCoursework(PhysicsCatalog.getNonProgramCourses());
        addRequiredCoursework(PhysicsCatalog.getRowanExpCourses());
        addRequiredCoursework(PhysicsCatalog.getRowanCoreCourses());
    }

    @Override
    public void getDegreeSectionProgress() {
        // Calculate progress specifically using the Physics/Math/CS Elective list
        getDegreeSectionProgress(PhysicsCatalog.getNumberOfSections(), PhysicsCatalog.getElectiveCourses());
    }

    public void displayRowanExpInfo() {
        DegreeInfo.displaySectionHeader("ROWAN EXPERIENCE");
        // Filter and display only Rowan Experience courses
        displayEachCourse(PhysicsCatalog.getRowanExpCourses());
    }

    public void displayRowanCoreInfo() {
        DegreeInfo.displaySectionHeader("ROWAN CORE");
        // Filter and display only Rowan Core courses
        displayEachCourse(PhysicsCatalog.getRowanCoreCourses());
    }

    public void displayNonProgramRequirementsInfo() {
        DegreeInfo.displaySectionHeader("NON-PROGRAM REQUIREMENTS");
        // Filter and display non-Physics required courses (Math, Chemistry, etc.)
        displayEachCourse(PhysicsCatalog.getNonProgramCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("PHYSICS (1902)");
        // Filter and display Physics Core courses
        displayEachCourse(PhysicsCatalog.getMajorRequiredCourses());
    }

    public void displayRestrictedElectivesInfo(){
        DegreeInfo.displaySectionHeader("RESTRICTED ELECTIVES (" + getRestrictedElectiveCreditsReq() + " CREDITS)");
        // Calculate and display which completed courses count toward restricted electives
        displayRestrictedElectives(PhysicsCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    public void displayFreeElectivesInfo(){
        DegreeInfo.displaySectionHeader("FREE ELECTIVES (" + getFreeElectiveCreditsReq() + " CREDITS)");
        // Calculate and display remaining credits counting toward free electives
        displayFreeElectiveCourses(PhysicsCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
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
