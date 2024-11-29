package com.fpoly.sof3012.controller;

import com.fpoly.sof3012.entity.Employee;
import com.fpoly.sof3012.utils.RestIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tạo một đối tượng JSON
        Employee employee = new Employee("TeoNV", "Nguyễn Văn Tèo", true, 950.5);

        // Sử dụng RestIO để gửi dữ liệu về client dưới dạng JSON
        RestIO.writeObject(response, employee);
    }
}