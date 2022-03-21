package com.company.test;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean programWork = true;
        int numType = 0;
        int fileVolume = 0;
        String[] input = null;
        Component fileSys = new Composite();
        Component folder = null;


        while (programWork) {

            System.out.println("Выберите тип создаваемого элемента:\n" +
                    "1. Текстовый файл\n" +
                    "2. Файл-изображение\n" +
                    "3. Папка\n" +
                    "4. Конец");

            input = in.nextLine().split(" ");
            numType = Integer.parseInt(input[0]);
            if (input.length > 1) {
                fileVolume = Integer.parseInt(input[1]);
            }


            switch (numType) {
                case 1: {
                    if (folder != null) {
                        TextFile textFile = new TextFile(fileVolume,"txt");
                        folder.add(textFile);
                    } else {
                        System.out.println("Создайте папку");
                    }
                    break;
                }
                case 2: {
                    if (folder != null) {
                        MultimediaFile multimediaFile = new MultimediaFile(fileVolume,"img");
                        folder.add(multimediaFile);
                    } else {
                        System.out.println("Создайте папку");
                    }
                    break;
                }
                case 3: {
                    if (folder != null) {
                        fileSys.add(folder);
                        folder = new Composite();
                        fileSys.getInfo();
                    } else {
                        folder = new Composite();

                    }
                    System.out.println("\nСоздана новая папка\n");

                    break;
                }
                case 4:
                    programWork = false;
                    break;
            }
        }
    }
}

abstract class Component extends ArrayList {
    public Component() {}

    public abstract void getInfo();
    public abstract void add(Component component);
    public abstract float getWeight();
    public abstract String getCount();
}


class TextFile extends Component {
    private float weight;
    private String type;

    public TextFile(float weight,String type) {
        super();
        this.type = type;
        this.weight = weight;
    }

    @Override
    public void getInfo() {
    }

    @Override
    public void add(Component component) {}

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public String getCount() {
        return this.type;
    }

}

class MultimediaFile extends Component {
    private float weight;
    private String type;

    public MultimediaFile(float weight,String type) {
        this.weight = weight;
        this.type = type;
    }

    @Override
    public void getInfo() {
    }

    @Override
    public void add(Component component) {
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public String getCount() {
        return this.type;
    }
}

class Composite extends Component {
    public List<Component> components = new ArrayList<>();

    @Override
    public void getInfo() {
        float currentSysWeight = getWeight();
        int n = 0;
        String s1 = "файл";
        String s2 = "папка";

        for(Component component:components){
          n += Integer.parseInt(component.getCount());
        }

        if(n>1 && n<5){
            s1 = "файла";
        }else if(n!=1){
            s1 = "файлов";
        }
        if(components.size()>1 && components.size()<5){
            s2 = "папки";
        }else if(components.size()!=1){
            s2 = "папок";
        }

        System.out.println("Объём памяти, занимаемый хранилищем составил "+currentSysWeight+" мегабайт.\n" +
                            "Хранится: "+n+" "+s1+" и "+components.size()+" "+s2);

    }


    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public float getWeight() {
        float currentSysWeight = 0;
        for (int i = 0; i < components.size(); i++) {
            currentSysWeight += components.get(i).getWeight();
        }
        return currentSysWeight;
    }

    @Override
    public String getCount() {
        int n =0;
        for (int i = 0; i < components.size(); i++) {
            if(components.get(i).getCount().equals("txt") || components.get(i).getCount().equals("img"))
            n++;
        }
        return Integer.toString(n);
    }
}

