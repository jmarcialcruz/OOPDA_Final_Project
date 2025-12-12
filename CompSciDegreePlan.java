public final class CompSciDegreePlan extends DegreePlan implements DegreeInfo {
    CompSciDegreePlan() {
        super("Computer Science (BS)");
        setAdvisor("Graves,Jaclyn");
        setAdvisorEmail("gravesj@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
        setFreeElectiveCreditsReq(31);
        setRestrictedElectiveCreditsReq(11);
        createDegreeSections(CompSciCatalog.getNumberOfSections());
        setDegreeSectionList(CompSciCatalog.getCatalogSectionList());
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(CompSciCatalog.getMajorRequiredCourses());
        addRequiredCoursework(CompSciCatalog.getNonProgramCourses());
        addRequiredCoursework(CompSciCatalog.getRowanExpCourses());
        addRequiredCoursework(CompSciCatalog.getRowanCoreCourses());
    }

    @Override
    public void getDegreeSectionProgress() {
        getDegreeSectionProgress(CompSciCatalog.getNumberOfSections(), CompSciCatalog.getElectiveCourses());
    }

    public void displayRowanExpInfo() {
        DegreeInfo.displaySectionHeader("ROWAN EXPERIENCE");
        displayEachCourse(CompSciCatalog.getRowanExpCourses());
    }

    public void displayRowanCoreInfo() {
        DegreeInfo.displaySectionHeader("ROWAN CORE");
        displayEachCourse(CompSciCatalog.getRowanCoreCourses());
    }

    public void displayNonProgramRequirementsInfo() {
        DegreeInfo.displaySectionHeader("NON-PROGRAM REQUIREMENTS");
        displayEachCourse(CompSciCatalog.getNonProgramCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("COMPUTER SCIENCE (0701)");
        displayEachCourse(CompSciCatalog.getMajorRequiredCourses());
    }

    public void displayRestrictedElectivesInfo(){
        DegreeInfo.displaySectionHeader("RESTRICTED ELECTIVES (" + getRestrictedElectiveCreditsReq() + " CREDITS)");
        displayRestrictedElectives(CompSciCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    public void displayFreeElectivesInfo(){
        DegreeInfo.displaySectionHeader("FREE ELECTIVES (" + getFreeElectiveCreditsReq() + " CREDITS)");
        displayFreeElectiveCourses(CompSciCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    @Override
    public void displayPlanInfo() {
        displayRowanExpInfo();
        displayRowanCoreInfo();
        displayNonProgramRequirementsInfo();
        displayMajorRequirementsInfo();
        displayRestrictedElectivesInfo();
        displayFreeElectivesInfo();
    }
}

