package org.example.Exercicios.dominio;

public class Map {
    public static void main(String[] args) {
        java.util.Map<String, String> map = new java.util.HashMap<>();
        map.put("teclado", "keyboard");
        map.put("mouse", "mouse");
        map.put("vc", "you");

        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

        System.out.println("---------");

        for (java.util.Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        map.remove("teclado");
        System.out.println("---------");
        for(java.util.Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());


        }
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

    }

}
