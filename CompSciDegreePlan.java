import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public final class CompSciDegreePlan extends DegreePlan implements DegreeInfo {
    private static final int freeElectiveCreditReq = 31;

    CompSciDegreePlan() {
        super("Computer Science (BS)");
        setAdvisor("Graves,Jaclyn");
        setAdvisorEmail("gravesj@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
        createDegreeSections(CompSciCatalog.getNumberOfSections());
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(CompSciCatalog.addMajorRequiredCourses());
        addRequiredCoursework(CompSciCatalog.addNonProgramCourses());
        addRequiredCoursework(CompSciCatalog.addRowanExpCourses());
        addRequiredCoursework(CompSciCatalog.addRowanCoreCourses());
    }

    public void getDegreeSectionProgress() {
        List<Set<Course>> catalogSectionList = CompSciCatalog.getCatalogSectionList();
        int numberOfSections = CompSciCatalog.getNumberOfSections();
        int reqCreditsCompleted = 0;
        int freeElectiveCredits = 0;

        for (Course reqCourse : getRequiredCoursework()) {
            for (int i = 0; i < numberOfSections; i++) {
                for (Course catalogCourse : catalogSectionList.get(i)) {
                    if (reqCourse.equals(catalogCourse) && !reqCourse.getGrade().equals("R")) {
                        getDegreeSectionList().get(i).add(catalogCourse);
                    }
                }

                if (getDegreeSectionList().get(i).equals(catalogSectionList.get(i))) {
                    setProgressBit(i + 3);
                }
                else {
                    unsetProgressBit(i + 3);
                }
            }
            if (!reqCourse.getGrade().equals("R")) {
                reqCreditsCompleted += reqCourse.getCredits();
            }
        }

        freeElectiveCredits = reqCreditsCompleted - getCompletedCredits();

        if (freeElectiveCredits == freeElectiveCreditReq){
            setProgressBit(7);
        }
        else {
            unsetProgressBit(7);
        }
    }

    public void displayRowanExpInfo() {
        DegreeInfo.displaySectionHeader("ROWAN EXPERIENCE");
        displayEachCourse(CompSciCatalog.addRowanExpCourses());
    }

    public void displayRowanCoreInfo() {
        DegreeInfo.displaySectionHeader("ROWAN CORE");
        displayEachCourse(CompSciCatalog.addRowanCoreCourses());
    }

    public void displayNonProgramRequirementsInfo() {
        DegreeInfo.displaySectionHeader("NON-PROGRAM REQUIREMENTS");
        displayEachCourse(CompSciCatalog.addNonProgramCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("COMPUTER SCIENCE (0701)");
        displayEachCourse(CompSciCatalog.addMajorRequiredCourses());
    }

    public void displayFreeElectivesInfo(){
        DegreeInfo.displaySectionHeader("FREE ELECTIVE REQUIREMENTS");
        System.out.println();
    }

    // Free Elective could be the required credits -
    public void displayPlanInfo() {
        displayRowanExpInfo();
        displayRowanCoreInfo();
        displayNonProgramRequirementsInfo();
        displayMajorRequirementsInfo();
        displayFreeElectivesInfo();
    }
}

