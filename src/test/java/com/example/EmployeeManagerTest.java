import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

public class EmployeeManagerTest {

    private EmployeeManager employeeManager;
    private EmployeeRepository employeeRepository;

    @Before
    public void setup() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeManager = new EmployeeManager(employeeRepository);
    }	
    @Test
    public void testPayEmployeesWhenOneEmployeeIsPresent() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", 1000));
        when(employeeRepository.findAll()).thenReturn(employees);
        assertThat(employeeManager.payEmployees()).isEqualTo(1);
    }
    import static org.mockito.ArgumentMatchers.argThat;

    @Test
    public void testArgumentMatcherExample() {
        // equivalent to the previous test, with argument matcher
        Employee notToBePaid = spy(new Employee("1", 1000));
        Employee toBePaid = spy(new Employee("2", 2000));

        when(employeeRepository.findAll())
            .thenReturn(asList(notToBePaid, toBePaid));

        doThrow(new RuntimeException())
            .when(bankService).pay(
                argThat(s -> s.equals("1")),
                anyDouble()
            );

        // number of payments must be 1
        assertThat(employeeManager.payEmployees()).isEqualTo(1);

        // make sure that Employee.paid is updated accordingly
        verify(notToBePaid).setPaid(false);
        verify(toBePaid).setPaid(true);
    }

    
    

}
