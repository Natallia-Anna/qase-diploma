package api.adapters;

import api.models.Project;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProjectAdapter extends BaseAdapter {
    private static final String PROJECT_API_ENDPOINT = "/project/";

    public Response getProjectByCode(String projectCode) {
        return get(PROJECT_API_ENDPOINT + projectCode);
    }

    public Response getAllProjects() {
        return get(PROJECT_API_ENDPOINT);
    }

    public Response createNewProject(Project project) {
        return post(PROJECT_API_ENDPOINT, converter.toJson(project));
    }

    public Response deleteProjectByCode(String projectCode) {
        return delete(PROJECT_API_ENDPOINT + projectCode);
    }
}
