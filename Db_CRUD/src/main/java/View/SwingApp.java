package View;

import com.example.andres.model.Employee;
import com.example.andres.repository.EmployeeRepository;
import com.example.andres.repository.Repository;
import com.example.andres.util.DataBaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SwingApp extends JFrame {

    private final Repository<Employee> repository;
    private final JTable employeesTable;

    public SwingApp() throws SQLException {
        setTitle("Employees Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,230);

        employeesTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeesTable);

        add(scrollPane, BorderLayout.CENTER);

        JButton addEmployeeBtn = new JButton("Add employee");
        JButton updateEmployeeBtn = new JButton("Update employee");
        JButton deleteEmployeeBtn = new JButton("Delete employee");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addEmployeeBtn);
        buttonPanel.add(updateEmployeeBtn);
        buttonPanel.add(deleteEmployeeBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        addEmployeeBtn.setBackground(new Color(46, 204, 113));
        addEmployeeBtn.setForeground(Color.WHITE);
        addEmployeeBtn.setFocusPainted(false);

        updateEmployeeBtn.setBackground(new Color(52, 152, 219));
        updateEmployeeBtn.setForeground(Color.WHITE);
        updateEmployeeBtn.setFocusPainted(false);

        deleteEmployeeBtn.setBackground(new Color(231, 76, 60));
        deleteEmployeeBtn.setForeground(Color.WHITE);
        deleteEmployeeBtn.setFocusPainted(false);

        repository = new EmployeeRepository();

        refreshTable();

        addEmployeeBtn.addActionListener(e ->{
            try{
                addEmployee();
            }catch (SQLException ex){
                throw  new RuntimeException(ex);
            }
        });

        updateEmployeeBtn.addActionListener(e->updateEmployee());

        deleteEmployeeBtn.addActionListener(e -> deleteEmployee());

    }

    private void refreshTable(){
        try {
            List<Employee> employees = repository.findAll();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Firstname");
            model.addColumn("Surname 1");
            model.addColumn("Surname 2");
            model.addColumn("Email");
            model.addColumn("Salary");

            for(Employee employee : employees){
                Object[] row ={
                        employee.getId(),
                        employee.getFirst_name(),
                        employee.getPa_surname(),
                        employee.getMa_surname(),
                        employee.getEmail(),
                        employee.getSalary()
                };
                model.addRow(row);
            }
             employeesTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error when we tried to obtain the employees: \n"+ e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private  void addEmployee() throws  SQLException{

        JTextField nameField = new JTextField();
        JTextField pSurnameField = new JTextField();
        JTextField mSurnameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField salaryField = new JTextField();

        Object[] fields = {
            "Name: ",nameField,
            "Surname 1: ",pSurnameField,
            "Surname 2: ",mSurnameField,
            "Email: ",emailField,
            "Salary: ",salaryField
        };

        int result = JOptionPane.showConfirmDialog(this,fields,"Add employee",JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION){
            Employee employee = new Employee();

            employee.setFirst_name(nameField.getText());
            employee.setPa_surname(pSurnameField.getText());
            employee.setMa_surname(mSurnameField.getText());
            employee.setEmail(emailField.getText());
            employee.setSalary(Float.parseFloat(salaryField.getText()));

            repository.save(employee);

            refreshTable();

            JOptionPane.showMessageDialog(this,"Employee add successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateEmployee() {
        String idToUpdate = JOptionPane.showInputDialog(this,"Insert id to update","Update employee",JOptionPane.QUESTION_MESSAGE);
        try{
            Employee employee = repository.getById(Integer.parseInt(idToUpdate));

            if (employee != null){
                JTextField nameField = new JTextField(employee.getFirst_name());
                JTextField pSurnameField = new JTextField(employee.getPa_surname());
                JTextField mSurnameField = new JTextField(employee.getMa_surname());
                JTextField emailField = new JTextField(employee.getEmail());
                JTextField salaryField = new JTextField(String.valueOf(employee.getSalary()));

                Object[] fields = {
                        "Name: ",nameField,
                        "Surname 1: ",pSurnameField,
                        "Surname 2: ",mSurnameField,
                        "Email: ",emailField,
                        "Salary: ",salaryField
                };

                int result = JOptionPane.showConfirmDialog(this,fields,"Add employee",JOptionPane.OK_CANCEL_OPTION);

                if(result == JOptionPane.OK_OPTION) {

                    employee.setFirst_name(nameField.getText());
                    employee.setPa_surname(pSurnameField.getText());
                    employee.setMa_surname(mSurnameField.getText());
                    employee.setEmail(emailField.getText());
                    employee.setSalary(Float.parseFloat(salaryField.getText()));

                    repository.save(employee);

                    refreshTable();
                    JOptionPane.showMessageDialog(this,"Employee update successfully","Success",JOptionPane.INFORMATION_MESSAGE);

                }
            }else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los datos del empleado de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void deleteEmployee(){
        String idToDelete = JOptionPane.showInputDialog(this,"Insert id to delete","Delete employee",JOptionPane.QUESTION_MESSAGE);

        try {
            Employee employee = repository.getById(Integer.parseInt(idToDelete));
            if(employee != null){
                int confirmResult = JOptionPane.showConfirmDialog(this, "¿Are you sure you want to delete the employee?", "Confirm delete", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Eliminar el empleado de la base de datos
                    repository.delete(Integer.parseInt(idToDelete));

                    // Actualizar la tabla de empleados en la interfaz
                    refreshTable();
                    JOptionPane.showMessageDialog(this,"Employee delete successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID del empleado", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
