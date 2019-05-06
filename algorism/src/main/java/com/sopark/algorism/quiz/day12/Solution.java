package com.sopark.algorism.quiz.day12;

import java.util.*;

public class Solution {

    class Project {
        String name;
        boolean mark;
        List<Project> dependencies = new ArrayList<>();

        public Project(String name) {
            this.name = name;
            this.mark = false;
        }

        public boolean isMark() {
            return mark;
        }

        public void setMark(boolean mark) {
            this.mark = mark;
        }

        public void addDependencies(Project project){
            dependencies.add(project);
        }

        public List<Project> getDependencies() {
            return dependencies;
        }
    }

    private Map<String, Project> projectMap = new HashMap<>();

    public Solution(String[] projects, String[][] dependencies) {
        Arrays.stream(projects).forEach(project -> projectMap.put(project, new Project(project)));
        Arrays.stream(dependencies).forEach(dependency -> {
            projectMap.get(dependency[1]).addDependencies(projectMap.get(dependency[0]));
        });
    }

    public Project[] buildOrder(){
        List<Project> projects = new ArrayList<>();

        this.projectMap.forEach((projectName, project) -> {
            project.getDependencies().stream().forEach(dependency -> {
                if(dependency.isMark()) {
                    return;
                }

                projects.add(dependency);
                dependency.setMark(true);
            });

            if(project.isMark()){
                return;
            }
            projects.add(project);
            project.setMark(true);
        });

        return projects.stream().toArray(size -> new Project[size]);
    }
}
