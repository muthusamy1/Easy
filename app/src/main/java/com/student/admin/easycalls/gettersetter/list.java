package com.student.admin.easycalls.gettersetter;

public class list {
    private Response Response;

    private Employees[] Employees;

    public Response getResponse ()
    {
        return Response;
    }

    public void setResponse (Response Response)
    {
        this.Response = Response;
    }

    public Employees[] getEmployees ()
    {
        return Employees;
    }

    public void setEmployees (Employees[] Employees)
    {
        this.Employees = Employees;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Response = "+Response+", Employees = "+Employees+"]";
    }


    public class Response
    {
        private String response_code;

        private String response_message;

        public String getResponse_code ()
        {
            return response_code;
        }

        public void setResponse_code (String response_code)
        {
            this.response_code = response_code;
        }

        public String getResponse_message ()
        {
            return response_message;
        }

        public void setResponse_message (String response_message)
        {
            this.response_message = response_message;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [response_code = "+response_code+", response_message = "+response_message+"]";
        }
    }


    public class Employees
    {
        private String employee_email;

        private String employee_type;

        private String employee_position;

        private String employee_name;

        private String Id;

        private String employee_pwd;

        private String employee_phone;

        private  boolean isSelected;

        public String getEmployee_email ()
        {
            return employee_email;
        }

        public void setEmployee_email (String employee_email)
        {
            this.employee_email = employee_email;
        }

        public String getEmployee_type ()
        {
            return employee_type;
        }

        public void setEmployee_type (String employee_type)
        {
            this.employee_type = employee_type;
        }

        public String getEmployee_position ()
        {
            return employee_position;
        }

        public void setEmployee_position (String employee_position)
        {
            this.employee_position = employee_position;
        }

        public String getEmployee_name ()
        {
            return employee_name;
        }

        public void setEmployee_name (String employee_name)
        {
            this.employee_name = employee_name;
        }

        public String getId ()
        {
            return Id;
        }

        public void setId (String Id)
        {
            this.Id = Id;
        }

        public String getEmployee_pwd ()
        {
            return employee_pwd;
        }

        public void setEmployee_pwd(String employee_pwd)
        {
            this.employee_pwd = employee_pwd;
        }

        public String getEmployee_phone ()
        {
            return employee_phone;
        }

        public void setEmployee_phone (String employee_phone)
        {
            this.employee_phone = employee_phone;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [employee_email = "+employee_email+", employee_type = "+employee_type+", employee_position = "+employee_position+", employee_name = "+employee_name+", Id = "+Id+", employee_pwd = "+employee_pwd+", employee_phone = "+employee_phone+"]";
        }


        public boolean isSelected() {
            return  isSelected;
        }

        public void setSelected(boolean checked) {

            this.isSelected=checked;

        }


    }


}
