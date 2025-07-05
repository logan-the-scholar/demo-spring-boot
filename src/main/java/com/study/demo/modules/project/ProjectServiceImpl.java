package com.study.demo.modules.project;

import com.study.demo.common.exception.classes.EmptyResourcesException;
import com.study.demo.common.exception.classes.NotFoundException;
import com.study.demo.modules.project.mapper.ProjectResponseMapper;
import com.study.demo.modules.project.model.ProjectCreationDto;
import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.project.repository.ProjectRepository;
import com.study.demo.modules.workspace.service.WorkspaceService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private final ProjectRepository repository;

    private final WorkspaceService workspaceService;

    public ProjectServiceImpl(ProjectRepository repository, WorkspaceService workspaceService) {
        this.repository = repository;
        this.workspaceService = workspaceService;
    }

//    public List<ProjectModel> getAll() {
//        List<ProjectModel> properties;
//
//        try {
//            properties = new ObjectMapper().readValue(
//                    //this.getClass().getClassLoader().getResourceAsStream("/properties.json"),
//                    //new File(String.valueOf(this.getClass().getResource("properties.json"))),
//                    new File("src/main/resources/properties.json"),
//                    new TypeReference<List<ProjectModel>>() {
//                    });
//
//            return properties;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void writeAll(List<ProjectModel> properties) {
//        ObjectMapper om = new ObjectMapper();
//
//        try {
//            om.writerWithDefaultPrettyPrinter()
//                    .writeValue(
//                            new File("src/main/resources/properties.json"),
//                            //new File(String.valueOf(this.getClass().getResource("/properties.json"))),
//                            properties);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public ResponseEntity<?> getByOwner(String owner) {
//        Optional<ProjectModel> optional = this.getAll().stream().filter(p -> p.getOwner().equalsIgnoreCase(owner))
//                .reduce((p, c) -> p).stream().findFirst();
//
//        if (optional.isPresent()) {
//            return ResponseEntity.ok(optional);
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
//    }

//    public ProjectModel getById(String id) {
//        Optional<ProjectModel> optional = this.getAll().stream().filter(p -> p.getID().equalsIgnoreCase(id))
//                .reduce((p, c) -> p).stream().findFirst();
//
//        if (optional.isPresent()) {
//            return optional.get();
//        }
//
//        throw new RuntimeException("Property not found");
//    }

//    public URI createFile(ProjectModel property) {
//        List<ProjectModel> properties;
//        ObjectMapper om = new ObjectMapper();
//
//        properties = this.getAll();
//        properties.add(property);
//        this.writeAll(properties);
//
//        return ServletUriComponentsBuilder.fromCurrentRequest().path("/by-owner/{owner}")
//                .buildAndExpand(property.getOwner())
//                .toUri();
//    }

//    public ResponseEntity<?> codeDemo(DemoSession scriptDemo) {
//        try {
//            String result = CodeRunner.execute(scriptDemo.getCode(), scriptDemo.getLanguage());
//
//            return ResponseEntity.ok(result);
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        List<SessionModel> properties = this.getAll();
//
//        SessionModel modified = properties.stream()
//                .filter(p -> p.getID().equalsIgnoreCase("id"))
//                .findFirst().<SessionModel>map(p -> {
//                    p.setAddress(property.getAddress());
//                    p.setOwner(property.getOwner());
//                    p.setRooms(property.getRooms());
//                    return p;
//                }).orElseThrow(() -> new RuntimeException("Property not found"));
//
//        this.writeAll(properties);

//        return properties.stream()
//                .filter(p -> p.getID().equalsIgnoreCase(id))
//                .findFirst()
//                .<ResponseEntity<?>>map(p -> {
//                    p.setAddress(property.getAddress());
//                    p.setOwner(property.getOwner());
//                    p.setRooms(property.getRooms());
//                    return ResponseEntity.ok(p);
//                }).orElseGet(() -> ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Property not found"));
//    }

//    public ResponseEntity<?> delete(String id) {
//        List<ProjectModel> properties = this.getAll();
//
//        return (new ArrayList<>(properties)).stream()
//                .filter(p -> p.getID().equalsIgnoreCase(id))
//                .findFirst()
//                .<ResponseEntity<?>>map(p -> {
//                    properties.remove(p);
//                    this.writeAll(properties);
//                    return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
//                })
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found"));
//    }

    public List<ProjectResponseMapper> findAllById(UUID workspaceId) {
            List<ProjectModel> projects = repository.findByWorkspace(workspaceService.findById(workspaceId));

            if(projects.isEmpty()) {
                throw new EmptyResourcesException("No projects found in this workspace");
            }

            return projects.stream().map(ProjectResponseMapper::fromEntity).toList();
    }

    public String create(ProjectCreationDto project) {
        try {
            ProjectModel created = new ProjectModel();
            created.setName(project.getName());
            created.setVisibility(project.getVisibility());
            created.setWorkspace(workspaceService.findById(project.getWorkspaceId()));
            repository.save(created);

            return created.getName();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public ProjectModel findById(UUID projectId) throws BadRequestException {
        Optional<ProjectModel> project = repository.findById(projectId);

        if(project.isPresent()) {
            return project.get();
        } else {
            throw new BadRequestException("Project not found");
        }
    }

    public Map<String, String> delete(UUID uuid) throws BadRequestException {
        ProjectModel project = this.findById(uuid);
        repository.delete(project);
        return Map.of("message", "instance deleted successfully");
    }

    public ProjectResponseMapper get(UUID uuid) {
        System.out.println(1);
        Optional<ProjectModel> foundProject = repository.findById(uuid);
        System.out.println(2);
        if(foundProject.isPresent()) {
            System.out.println(foundProject.get().getName());
            return ProjectResponseMapper.fromEntityAndFiles(foundProject.get());
        }

        throw new NotFoundException("Project not found");
    }

//    public ResponseEntity<?> modify(String id, SessionModel property) {
//        List<SessionModel> properties = this.getAll();
//
//        return properties.stream().filter(p -> p.getID().equalsIgnoreCase(id))
//                .findFirst()
//                .<ResponseEntity<?>>map(p -> {
//                    if (property.getOwner() != null) {
//                        p.setOwner(property.getOwner());
//                    }
//                    if (property.getRooms() != null) {
//                        p.setRooms(property.getRooms());
//                    }
//                    if (property.getAddress() != null) {
//                        p.setAddress(property.getAddress());
//                    }
//
//
//                    return ResponseEntity.ok(p);
//                })
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found"));
//    }
}
