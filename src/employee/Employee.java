/*
 * In this program we store two category of employee information i.e - Premanent and Temporary
 * display various details like salary , leaves taken etc.
 */
package employee;

public abstract class Employee {                // main class employee

    final static int CASUAL_LEAVE = 18;                       //no. of  casual leaves
    final static int PAID_LEAVE = 12;                         // no. of paid leaves
    final static int SICK_LEAVE = 12;                         // no. of sick leaves

    int empId;                                       // employee id
    String empName;                                  // employee name
    int total_leaves;                                //total leaves
    double total_salary;                             // total salary
    double pf;
    double hra;

    abstract void calculate_balance_leaves();                               //abstract functional declaration for calculating balance leaves in subclasses

    abstract boolean avail_leave(int no_of_leaves, char type_of_leave);     //abstract functional declaration for availing leaves in subclasses

    abstract void calculate_salary();                                       // abstract function for calculating salary in sub classes

    abstract void setEmpInfo(int empId, String empName);                    // abstract function to take employee details

    abstract void getEmpInfo();                                             // abstract function to display employee details

    public Employee() {                                                                 //default constructor
        this.total_leaves = this.CASUAL_LEAVE + this.PAID_LEAVE + this.SICK_LEAVE;      // setting count for total leaves
    }

    public static void main(String[] args) {                                //main section
        PermanentEmp objPE = new PermanentEmp();                            // object declare & init. for PermanentEmp
        objPE.setEmpInfo(101, "Akash Dubey");                               // setting employee details
        objPE.getEmpInfo();                                                 // getting employee details
        objPE.print_leave_details();                                        // output default leaves
        objPE.avail_leave(1, 'P');                                          // here we take 1 Paid Leaves for objPE
        objPE.avail_leave(6, 'C');                                          // here we take 6 Casual leaves for objPE
        objPE.avail_leave(9, 'S');                                          // here we take 9 Sick leaves for objPE
        objPE.calculate_balance_leaves();                                   // output balance leave method
        objPE.calculate_salary();                                           // output salary details 

        TemporaryEmp objTE = new TemporaryEmp();                            // object declare & init. for TemporaryEmp
        objTE.setEmpInfo(101, "Akash Dubey");                               // setting employee details
        objTE.getEmpInfo();                                                 // getting employee details
        objTE.print_leave_details();                                        // output default leaves
        objTE.avail_leave(1, 'P');                                          // here we take 1 Paid Leaves for objTE
        objTE.avail_leave(6, 'C');                                          // here we take 6 Casual leaves for objTE
        objTE.avail_leave(9, 'S');                                          // here we take 9 Sick leaves for objTE
        objTE.calculate_balance_leaves();                                   // output balance leave method
        objTE.calculate_salary();                                           // output salary details 

    }

}

class PermanentEmp extends Employee {                                       //PermanentEmp inherits Employee class
                                                                            // these variables are used to store 
                                                                            // leaves taken, later we deduct them
                                                                            // from main leave balance
    int casual_leave;                                                       
    int paid_leave;
    int sick_leave;
    
    final static int BASIC = 10000;                                         // basic salary of employee 

    public PermanentEmp() {                                                 // Default Constructor for PermanentEmp 
        this.casual_leave = 0;
        this.paid_leave = 0;
        this.sick_leave = 0;
        this.total_leaves = super.CASUAL_LEAVE + super.PAID_LEAVE + super.SICK_LEAVE;   // initializing total_leaves from values set in it's parent class
    }

    void print_leave_details() {                                             // method used to print leave details 
        System.out.println("----------------------");
        System.out.println("Leave Quota");
        System.out.println("Paid Leaves     : " + this.PAID_LEAVE);
        System.out.println("Casual Leaves   : " + this.CASUAL_LEAVE);
        System.out.println("Sick Leaves     : " + this.SICK_LEAVE);
        System.out.println("Total           : " + this.total_leaves);
    }

    @Override
    void calculate_balance_leaves() {                                           // method ro calculate balance leaves 
        System.out.println("-------------------");
        System.out.println("Available Leaves");
        System.out.println("Paid Leaves     : " + this.paid_leave);
        System.out.println("Casual Leaves   : " + this.casual_leave);
        System.out.println("Sick Leaves     : " + this.sick_leave);
        System.out.println("Total           : " + this.total_leaves);
    }

    @Override                                                                   // overriding avail leave function
    boolean avail_leave(int no_of_leaves, char type_of_leave) {                 // methose used to log leaves taken 
        if ((type_of_leave == 'P' && no_of_leaves <= super.PAID_LEAVE)
                ||                                                              // leaves are distributed into categories, allow for leave only when 
                (type_of_leave == 'C' && no_of_leaves <= super.CASUAL_LEAVE)
                ||                                                              //requested leave adhere to leaves allotted for the year
                (type_of_leave == 'S' && no_of_leaves <= super.SICK_LEAVE)) {
            switch (type_of_leave) {                                            //determine leave type
                case 'C':
                    this.casual_leave = no_of_leaves;                                     // based on leave type , store no. of leaves in corresponding leave vaieable
                    this.total_leaves = this.total_leaves - this.casual_leave;                //deduct leaves from total leaves
                    break;
                case 'P':
                    this.paid_leave = no_of_leaves;                                       // based on leave type , store no. of leaves in corresponding leave vaieable
                    this.total_leaves = this.total_leaves - this.paid_leave;                // deduct leaves from total leaves
                    break;
                case 'S':
                    this.sick_leave = no_of_leaves;                                       // based on leave type , store no. of leaves in corresponding leave vaieable
                    this.total_leaves = this.total_leaves - this.sick_leave;                //deduct leaves from total leaves
                    break;
                default:

            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    void calculate_salary() {                                                   // override method is used to calulate salary 
        this.pf = this.BASIC * 12 / 100;                                        // getting pf amount 
        this.hra = this.BASIC * 50 / 100;                                       // getting hra amount 
        this.total_salary = this.BASIC + this.hra - this.pf;                    //calculating salary
        System.out.println("-----------------------");
        System.out.println("Salary");
        System.out.println("Basic           : " + this.BASIC);
        System.out.println("PF              : " + this.pf);
        System.out.println("HRA             : " + this.hra);
        System.out.println("Salary          : " + this.total_salary);
    }

    @Override                                                                   //override method is used to set employee 
    void setEmpInfo(int empId, String empName) {                                // personal information
        this.empId = empId;
        this.empName = empName;
    }

    @Override
    void getEmpInfo() {                                                         //override getter to get employee personal
        System.out.println("Permanent Employee Detais");                        // information
        System.out.println("ID              : " + this.empId);
        System.out.println("Name            : " + this.empName);
    }

}

class TemporaryEmp extends Employee {                             //TemporaryEmp inherits Employee class
                                                                   
    int casual_leave;                                               // these variable will store leaves for eact category
    int paid_leave;                                                 // as per leaves taken & later will  get deducted
    int sick_leave;                                                 // from allotted leaves

    final static int BASIC = 5000;                                  // basic salary of temporary employee, i know it 
                                                                    // is just half of permanent ..sad
                                                                    
    void print_leave_details() {                                    // method used to print allotted leave
        System.out.println("----------------------");
        System.out.println("Leave Quota");
        System.out.println("Paid Leaves     : " + this.PAID_LEAVE);
        System.out.println("Casual Leaves   : " + this.CASUAL_LEAVE);
        System.out.println("Sick Leaves     : " + this.SICK_LEAVE);
        System.out.println("Total           : " + this.total_leaves);
    }

    @Override
    boolean avail_leave(int no_of_leaves, char type_of_leave) {         //override method to log leaves taken
        if ((type_of_leave == 'P' && no_of_leaves <= super.PAID_LEAVE)
                ||                                                              //leaves are distributed into categories, allow for leave only when 
                (type_of_leave == 'C' && no_of_leaves <= super.CASUAL_LEAVE)
                ||                                                              //requested leave adhere to leaves allotted for the year
                (type_of_leave == 'S' && no_of_leaves <= super.SICK_LEAVE)) {
            switch (type_of_leave) {                                            //determine leave type
                case 'C':
                    this.casual_leave = no_of_leaves;                           // based on leave type , store no. of leaves in corresponding leave vaieable
                    this.total_leaves = this.total_leaves - this.casual_leave;  //deduct leaves from total leaves
                    break;
                case 'P':
                    this.paid_leave = no_of_leaves;                             // based on leave type , store no. of leaves in corresponding leave vaieable
                    this.total_leaves = this.total_leaves - this.paid_leave;    // deduct leaves from total leaves
                    break;
                case 'S':
                    this.sick_leave = no_of_leaves;                             // based on leave type , store no. of leaves in corresponding leave vaieable
                    this.total_leaves = this.total_leaves - this.sick_leave;    //deduct leaves from total leaves
                    break;
                default:

            }
            return true;
        } else {
            return false;
        }
    }

    @Override                                                       //override method to set employee information
    void setEmpInfo(int empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    @Override                                                      //override method to get employee personal information
    void getEmpInfo() {
        System.out.println("-----------------------------");
        System.out.println("Temporary Employee Detais");
        System.out.println("ID : " + this.empId);
        System.out.println("Name : " + this.empName);
    }

    @Override                                                      //override method to calculate salary 
    void calculate_salary() {
        this.pf = this.BASIC * 5 / 100;                             //calculating pf
        this.hra = this.BASIC * 50 / 100;                           // calculating hra 
        this.total_salary = this.BASIC + this.hra - this.pf;        // calculating total salary
        System.out.println("-----------------------");
        System.out.println("Salary");
        System.out.println("Basic           : " + this.BASIC);
        System.out.println("PF              : " + this.pf);
        System.out.println("HRA             : " + this.hra);
        System.out.println("Salary          : " + this.total_salary);

    }

    @Override                                                       //override method to calculate balance leaves
    void calculate_balance_leaves() {
        System.out.println("-------------------");
        System.out.println("Available Leaves");
        System.out.println("Paid Leaves     : " + this.paid_leave);
        System.out.println("Casual Leaves   : " + this.casual_leave);
        System.out.println("Sick Leaves     : " + this.sick_leave);
        System.out.println("Total           : " + this.total_leaves);
    }

}
