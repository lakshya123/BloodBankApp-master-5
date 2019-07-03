package com.billy.bloodbank;

public class Patient {

    String PatientId;
    String PatientName;
    String FathersName;
    String Gender;
    int Age;
    int Weight;
    String Hospital;
    String DoctorIncharge;
    int RegistrationNo;
    String Ward;
    String BedNo;
    String RequiredBloodGroup;
    String RequiredBloodType;
    int UnitRequired;
    String time;
    long TimeStamp;

    public Patient(){

    }

    public Patient(String patientId, String patientName, String fathersName, String gender, int age, int weight, String hospital, String doctorIncharge, int registrationNo, String ward, String bedNo, String requiredBloodGroup, String requiredBloodType, String timer, int unitRequired, long ts) {
        PatientId = patientId;
        PatientName = patientName;
        FathersName = fathersName;
        Gender = gender;
        Age = age;
        Weight = weight;
        Hospital = hospital;
        DoctorIncharge = doctorIncharge;
        RegistrationNo = registrationNo;
        Ward = ward;
        BedNo = bedNo;
        RequiredBloodGroup = requiredBloodGroup;
        RequiredBloodType = requiredBloodType;
        time = timer;
        UnitRequired = unitRequired;
        TimeStamp = ts;

    }

    public String getPatientId() {
        return PatientId;
    }

    public String getPatientName() {
        return PatientName;
    }

    public String getFathersName() {
        return FathersName;
    }

    public String getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }

    public int getWeight() {
        return Weight;
    }

    public String getHospital() {
        return Hospital;
    }

    public String getDoctorIncharge() {
        return DoctorIncharge;
    }

    public int getRegistrationNo() {
        return RegistrationNo;
    }

    public String getWard() {
        return Ward;
    }

    public String getBedNo() {
        return BedNo;
    }

    public String getRequiredBloodGroup() {
        return RequiredBloodGroup;
    }

    public String getRequiredBloodType() {
        return RequiredBloodType;
    }

    public String getTime(){return time; }

    public int getUnitRequired() {
        return UnitRequired;
    }

    public long getTimeStamp(){ return TimeStamp;}
}
