package com.example.javacore_customers_demo.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class testTeacher {
    static class teacher{
        private String Ma;
        private String Name;
        private int CakeCount;

        @Override
        public String toString() {
            return "teacher{" +
                    "Ma='" + Ma + '\'' +
                    ", Name='" + Name + '\'' +
                    ", CakeCount=" + CakeCount +
                    '}';
        }

        public teacher() {
        }

        public String getMa() {
            return Ma;
        }

        public String getName() {
            return Name;
        }

        public int getCakeCount() {
            return CakeCount;
        }

        public void setMa(String ma) {
            Ma = ma;
        }

        public void setName(String name) {
            Name = name;
        }

        public void setCakeCount(int cakeCount) {
            CakeCount = cakeCount;
        }

        public teacher(String ma, String name, int cakeCount) {
            Ma = ma;
            Name = name;
            CakeCount = cakeCount;
        }
    }

    public static void main(String[] args) throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("Teachers");

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        HashMap<String, teacher> mapTeachers = new HashMap<>();
        for (String line; (line = reader.readLine()) != null;) {
            String[] splitedLine = line.split(" ");
            if (splitedLine.length == 4){
                String teacherID = splitedLine[0];
                String name = splitedLine[1];
                int cakeCount = Integer.parseInt(splitedLine[3]);
                teacher tea = new teacher(teacherID, name, cakeCount);
                if (mapTeachers.containsKey(tea.getMa())){
                    teacher oldTeacher = mapTeachers.get(tea.getMa());
                    oldTeacher.setCakeCount(oldTeacher.getCakeCount() + tea.getCakeCount());
                    mapTeachers.put(oldTeacher.getMa(), oldTeacher);
                }else{
                    mapTeachers.put(tea.getMa(), tea);
                }
            }
        }

        ArrayList<teacher> listTeacher = new ArrayList<>(mapTeachers.values());
        for (teacher t:
             listTeacher) {
            System.out.println(t.toString());
        }


    }
}
