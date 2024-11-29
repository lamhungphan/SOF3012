package com.fpoly.sof3012.controller;

import com.fpoly.sof3012.entity.Employee;
import com.fpoly.sof3012.utils.RestIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/employees/*")
public class EmployeeRestServlet extends HttpServlet {
    private Map<String, Employee> map = new HashMap<>(Map.of(
            "NV01", new Employee("NV01", "Nhan vien 01", true, 500),
            "NV02", new Employee("NV02", "Nhan vien 02", false, 1500),
            "NV03", new Employee("NV03", "Nhan vien 03", true, 5000),
            "NV04", new Employee("NV04", "Nhan vien 04", false, 2500),
            "NV05", new Employee("NV05", "Nhan vien 05", true, 3500)
    ));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getPathInfo();
        if (info == null || info.length() == 0) {
            RestIO.writeObject(resp, map.values());
        } else {
            String id = info.substring(1).trim();
            RestIO.writeObject(resp, map.get(id));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = RestIO.readObject(req, Employee.class);
        map.put(employee.getManv(), employee);
        RestIO.writeObject(resp, employee);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1).trim();
        Employee employee = RestIO.readObject(req, Employee.class);
        map.put(id, employee);
        RestIO.writeEmptyObject(resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1).trim();
        map.remove(id);
        RestIO.writeEmptyObject(resp);
    }
}
