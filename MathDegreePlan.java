public final class MathDegreePlan extends DegreePlan implements DegreeInfo {
    MathDegreePlan() {
        super("Math (BS)");
        setAdvisor("Mason,Cristine");
        setAdvisorEmail("masonc@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
        setFreeElectiveCreditsReq(30);
        setRestrictedElectiveCreditsReq(24);
        createDegreeSections(MathCatalog.getNumberOfSections());
        setDegreeSectionList(MathCatalog.getCatalogSectionList());
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(MathCatalog.getMajorRequiredCourses());
        addRequiredCoursework(MathCatalog.getNonProgramCourses());
        addRequiredCoursework(MathCatalog.getRowanExpCourses());
        addRequiredCoursework(MathCatalog.getRowanCoreCourses());
    }

    public void getDegreeSectionProgress() {
        getDegreeSectionProgress(MathCatalog.getNumberOfSections(), MathCatalog.getElectiveCourses());
    }

    public void displayRowanExpInfo() {
        DegreeInfo.displaySectionHeader("ROWAN EXPERIENCE");
        displayEachCourse(MathCatalog.getRowanExpCourses());
    }

    public void displayRowanCoreInfo() {
        DegreeInfo.displaySectionHeader("ROWAN CORE");
        displayEachCourse(MathCatalog.getRowanCoreCourses());
    }

    public void displayNonProgramRequirementsInfo() {
        DegreeInfo.displaySectionHeader("NON-PROGRAM REQUIREMENTS");
        displayEachCourse(MathCatalog.getNonProgramCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("MATHEMATICS (1702)");
        displayEachCourse(MathCatalog.getMajorRequiredCourses());
    }

    public void displayRestrictedElectivesInfo(){
        DegreeInfo.displaySectionHeader("RESTRICTED ELECTIVES (" + getRestrictedElectiveCreditsReq() + " CREDITS)");
        displayRestrictedElectives(MathCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    public void displayFreeElectivesInfo(){
        DegreeInfo.displaySectionHeader("FREE ELECTIVES (" + getFreeElectiveCreditsReq() + " CREDITS)");
        displayFreeElectiveCourses(MathCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    public void displayPlanInfo() {
        displayRowanExpInfo();
        displayRowanCoreInfo();
        displayNonProgramRequirementsInfo();
        displayMajorRequirementsInfo();
        displayRestrictedElectivesInfo();
        displayFreeElectivesInfo();
    }
}

