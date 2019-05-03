package com.student.admin.easycalls.gettersetter;

public class discode {
    private Response Response;

    private DispoCodeList[] DispoCodeList;

    public Response getResponse ()
    {
        return Response;
    }

    public void setResponse (Response Response)
    {
        this.Response = Response;
    }

    public DispoCodeList[] getDispoCodeList ()
    {
        return DispoCodeList;
    }

    public void setDispoCodeList (DispoCodeList[] DispoCodeList)
    {
        this.DispoCodeList = DispoCodeList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Response = "+Response+", DispoCodeList = "+DispoCodeList+"]";
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


    public class DispoCodeList
    {
        private String dis_code;

        private String dis_desc;

        private String Id;

        public String getDis_code ()
        {
            return dis_code;
        }

        public void setDis_code (String dis_code)
        {
            this.dis_code = dis_code;
        }

        public String getDis_desc ()
        {
            return dis_desc;
        }

        public void setDis_desc (String dis_desc)
        {
            this.dis_desc = dis_desc;
        }

        public String getId ()
        {
            return Id;
        }

        public void setId (String Id)
        {
            this.Id = Id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [dis_code = "+dis_code+", dis_desc = "+dis_desc+", Id = "+Id+"]";
        }
    }


}
