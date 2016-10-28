package com.up72.fond;

/**
 * Created by Administrator on 2016/7/19.
 */
public class B extends A {

    private Long studentNo; //学号
    private String grade;//年级

    public B() {
        System.out.println("B default construct ==== sub class === 1");
    }

    public B(Long studentNo, String grade) {
        this.studentNo = studentNo;
        this.grade = grade;
        System.out.println("B two variables construct ==== sub class === 2");
    }

    public B(String name, int age,boolean sex, Long studentNo, String grade) {
        super(name, age, sex);
        this.studentNo = studentNo;
        this.grade = grade;
        System.out.println("B four variables construct ==== sub class === 2");
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof B)) return false;

        B b = (B) o;

        if (!getStudentNo().equals(b.getStudentNo())) return false;
        return getGrade().equals(b.getGrade());

    }

    @Override
    public int hashCode() {
        int result = getStudentNo().hashCode();
        result = 31 * result + getGrade().hashCode();
        return result;
    }
}
