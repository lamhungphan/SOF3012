package com.fpoly.sof3012.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String manv;
    private String hoTen;
    private boolean gioiTinh;
    private double luong;
}
