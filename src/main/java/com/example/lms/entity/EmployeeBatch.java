/*package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_batch")
public class EmployeeBatch {

    @EmbeddedId
    private EmployeeBatchId id;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "batch_name", nullable = false)
    private String batchName;

    // Getters and Setters
    public EmployeeBatchId getId() {
        return id;
    }

    public void setId(EmployeeBatchId id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
}
*/

package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_batch")
public class EmployeeBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "batch_id", nullable = false)
    private Long batchId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "batch_name", nullable = false)
    private String batchName;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
}
