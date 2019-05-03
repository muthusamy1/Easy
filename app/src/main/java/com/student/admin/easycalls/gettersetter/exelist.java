package com.student.admin.easycalls.gettersetter;

public class exelist {


    private Response Response;

    private ExecutiveLocationList[] ExecutiveLocationList;

    public Response getResponse ()
    {
        return Response;
    }

    public void setResponse (Response Response)
    {
        this.Response = Response;
    }

    public ExecutiveLocationList[] getExecutiveLocationList ()
    {
        return ExecutiveLocationList;
    }

    public void setExecutiveLocationList (ExecutiveLocationList[] ExecutiveLocationList)
    {
        this.ExecutiveLocationList = ExecutiveLocationList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Response = "+Response+", ExecutiveLocationList = "+ExecutiveLocationList+"]";
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

    public class ExecutiveLocationList
    {
        private String customer_address;

        private String employee_id;

        private String customer_phone;

        private String Id;

        private String customer_name;

        private String customer_accno;

        private String status;

        public String getCustomer_address ()
        {
            return customer_address;
        }

        public void setCustomer_address (String customer_address)
        {
            this.customer_address = customer_address;
        }

        public String getEmployee_id ()
        {
            return employee_id;
        }

        public void setEmployee_id (String employee_id)
        {
            this.employee_id = employee_id;
        }

        public String getCustomer_phone ()
        {
            return customer_phone;
        }

        public void setCustomer_phone (String customer_phone)
        {
            this.customer_phone = customer_phone;
        }

        public String getId ()
        {
            return Id;
        }

        public void setId (String Id)
        {
            this.Id = Id;
        }

        public String getCustomer_name ()
        {
            return customer_name;
        }

        public void setCustomer_name (String customer_name)
        {
            this.customer_name = customer_name;
        }

        public String getCustomer_accno ()
        {
            return customer_accno;
        }

        public void setCustomer_accno (String customer_accno)
        {
            this.customer_accno = customer_accno;
        }

        public String getStatus ()
        {
            return status;
        }

        public void setStatus (String status)
        {
            this.status = status;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [customer_address = "+customer_address+", employee_id = "+employee_id+", customer_phone = "+customer_phone+", Id = "+Id+", customer_name = "+customer_name+", customer_accno = "+customer_accno+", status = "+status+"]";
        }
    }

}
