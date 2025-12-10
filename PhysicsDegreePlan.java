public final class PhysicsDegreePlan extends DegreePlan implements DegreeInfo {
    PhysicsDegreePlan() {
        super("Physics (BS)");
        setAdvisor("Mason,Cristine");
        setAdvisorEmail("masonc@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
        setFreeElectiveCreditsReq(31);
        setRestrictedElectiveCreditsReq(11);
        createDegreeSections(PhysicsCatalog.getNumberOfSections());
        setDegreeSectionList(PhysicsCatalog.getCatalogSectionList());
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(PhysicsCatalog.getMajorRequiredCourses());
        addRequiredCoursework(PhysicsCatalog.getNonProgramCourses());
        addRequiredCoursework(PhysicsCatalog.getRowanExpCourses());
        addRequiredCoursework(PhysicsCatalog.getRowanCoreCourses());
    }

    public void getDegreeSectionProgress() {
        getDegreeSectionProgress(PhysicsCatalog.getNumberOfSections(), PhysicsCatalog.getElectiveCourses());
    }

    public void displayRowanExpInfo() {
        DegreeInfo.displaySectionHeader("ROWAN EXPERIENCE");
        displayEachCourse(PhysicsCatalog.getRowanExpCourses());
    }

    public void displayRowanCoreInfo() {
        DegreeInfo.displaySectionHeader("ROWAN CORE");
        displayEachCourse(PhysicsCatalog.getRowanCoreCourses());
    }

    public void displayNonProgramRequirementsInfo() {
        DegreeInfo.displaySectionHeader("NON-PROGRAM REQUIREMENTS");
        displayEachCourse(PhysicsCatalog.getNonProgramCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("PHYSICS (1902)");
        displayEachCourse(PhysicsCatalog.getMajorRequiredCourses());
    }

    public void displayRestrictedElectivesInfo(){
        DegreeInfo.displaySectionHeader("RESTRICTED ELECTIVES (" + getRestrictedElectiveCreditsReq() + " CREDITS)");
        displayRestrictedElectives(PhysicsCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
    }

    public void displayFreeElectivesInfo(){
        DegreeInfo.displaySectionHeader("FREE ELECTIVES (" + getFreeElectiveCreditsReq() + " CREDITS)");
        displayFreeElectiveCourses(PhysicsCatalog.getElectiveCourses(), getRestrictedElectiveCreditsReq());
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

