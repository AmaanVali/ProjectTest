package com.gusto.student.obj;

import java.util.ArrayList;

public class StaticStudentList {

    public static ArrayList<StudentObj> getStudentList(){

        ArrayList<StudentObj> studentObjs = new ArrayList<>();

        StudentObj studentObj = new StudentObj();
        studentObj.setId("STU001");
        studentObj.setName("Tun Tun");
        studentObj.setAddress("Latha");
        studentObj.setPhone("09343434");
        studentObj.setPhoto("https://teenlife.s3.amazonaws.com/media/uploads/blogs/7-study-tips-high-school-students/iStock-495718767.jpg");
        studentObj.setActive(1);


        StudentObj studentObj_1 = new StudentObj();
        studentObj_1.setId("STU002");
        studentObj_1.setName("Su Su");
        studentObj_1.setAddress("Sanchung");
        studentObj_1.setPhone("09328432");
        studentObj_1.setPhoto("https://stat.mozo.com.au/redactor/pictures/7353/UniBank_Student_Social_Media_Banking_original.jpg");
        studentObj_1.setActive(1);

        StudentObj studentObj_2 = new StudentObj();
        studentObj_2.setId("STU003");
        studentObj_2.setName("Hla Hla");
        studentObj_2.setAddress("Hlaing");
        studentObj_2.setPhone("797987987");
        studentObj_2.setPhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmucPMD-WkL1FxkeSAk3qKszt4g5nkJP3U1wIUpDlrn28ff4muNg");
        studentObj_2.setActive(1);

        StudentObj studentObj_3 = new StudentObj();
        studentObj_3.setId("STU004");
        studentObj_3.setName("Soe Soe");
        studentObj_3.setAddress("Sanchung");
        studentObj_3.setPhone("09328432");
        studentObj_3.setPhoto("https://carwad.net/sites/default/files/student-images-115653-7895964.jpg");
        studentObj_3.setActive(1);

        StudentObj studentObj_4 = new StudentObj();
        studentObj_4.setId("STU005");
        studentObj_4.setName("Aung Aung");
        studentObj_4.setAddress("Sanchung");
        studentObj_4.setPhone("23423");
        studentObj_4.setPhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq1vtVta_3Z8Z-3SOcoprgUmw3Nk4UTr2TwRtGklFKFp3BMO8G");
        studentObj_4.setActive(1);

        StudentObj studentObj_5 = new StudentObj();
        studentObj_5.setId("STU006");
        studentObj_5.setName("Myo Lay");
        studentObj_5.setAddress("Sanchung");
        studentObj_5.setPhone("324324");
        studentObj_5.setPhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQynWfdaDt0s6Xx7I78DU9bs0D5vBNruyH_vKrp_cLt857AWqUz");
        studentObj_5.setActive(1);

        studentObjs.add(studentObj);
        studentObjs.add(studentObj_1);
        studentObjs.add(studentObj_2);
        studentObjs.add(studentObj_3);
        studentObjs.add(studentObj_4);
        studentObjs.add(studentObj_5);

        return  studentObjs;
    }
}
