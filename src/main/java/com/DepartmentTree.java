package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yinchao
 * @ClassName: Demo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/12/6 18:34
 */
class Department {
    private int id;
    private String path;

    private List<Department> children;

    public Department(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }


    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }
}

public class DepartmentTree {

    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1, "1"));
        departments.add(new Department(2, "1.2"));
        departments.add(new Department(3, "1.2.3"));
        departments.add(new Department(4, "1.2.4"));
        departments.add(new Department(5, "5"));
        departments.add(new Department(6, "5.6"));
        departments.add(new Department(7, "5.6.7"));
        departments.add(new Department(8, "5.6.8"));

        Map<Integer, List<Department>> departmentMap = new HashMap<>();

        for (Department department : departments) {
            String[] pathArray = department.getPath().split("\\.");
            int depth = pathArray.length;

            if (!departmentMap.containsKey(depth)) {
                departmentMap.put(depth, new ArrayList<>());
            }

            departmentMap.get(depth).add(department);
        }

        // Building the tree
        List<Department> topLevelDepartments = departmentMap.get(1);
        for (Department topLevel : topLevelDepartments) {
            buildTree(topLevel, departmentMap, 1);
        }

        // Display the tree
        displayTree(topLevelDepartments, 0);
    }

    private static void buildTree(Department department, Map<Integer, List<Department>> departmentMap, int depth) {
        if (depth >= 3) {
            return;
        }

        int nextDepth = depth + 1;
        List<Department> children = departmentMap.get(nextDepth);

        if (children != null) {
            for (Department child : children) {
                if (child.getPath().startsWith(department.getPath() + ".")) {
                    buildTree(child, departmentMap, nextDepth);
                    department.getChildren().add(child);
                }
            }
        }
    }

    private static void displayTree(List<Department> departments, int depth) {
        for (Department department : departments) {
            System.out.println(indent(depth) + department.getId());
            displayTree(department.getChildren(), depth + 1);
        }
    }

    private static String indent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }
}
