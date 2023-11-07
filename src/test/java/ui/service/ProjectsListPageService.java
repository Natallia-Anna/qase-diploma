package ui.service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.page.ProjectsListPage;

@Log4j2
public class ProjectsListPageService {
    private ProjectsListPage projectsListPage;

    @Step("Verify if projects ui.page displayed")
    public boolean isProjectsPageDisplayed() {
        projectsListPage = new ProjectsListPage();
        return projectsListPage.isProjectsPageDisplayed();
    }
}
