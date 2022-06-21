package com.bruk.d2lastpicker.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarryHeroes {


    private List<Integer> positionOneHeroes = new ArrayList<Integer>();
    private List<Integer> positionTwoHeroes = new ArrayList<Integer>();

    public CarryHeroes() {
    }

    private List<Integer> allPossiblePositionOneHeroes()
    {
        positionOneHeroes.add(1);
        positionOneHeroes.add(6);
        positionOneHeroes.add(8);
        positionOneHeroes.add(10);
        positionOneHeroes.add(12);
        positionOneHeroes.add(15);
        positionOneHeroes.add(18);
        positionOneHeroes.add(19);
        positionOneHeroes.add(32);
        positionOneHeroes.add(41);
        positionOneHeroes.add(42);
        positionOneHeroes.add(44);
        positionOneHeroes.add(46);
        positionOneHeroes.add(48);
        positionOneHeroes.add(53);
        positionOneHeroes.add(54);
        positionOneHeroes.add(56);
        positionOneHeroes.add(63);
        positionOneHeroes.add(67);
        positionOneHeroes.add(70);
        positionOneHeroes.add(72);
        positionOneHeroes.add(73);
        positionOneHeroes.add(80);
        positionOneHeroes.add(81);
        positionOneHeroes.add(89);
        positionOneHeroes.add(93);
        positionOneHeroes.add(94);
        positionOneHeroes.add(95);
        positionOneHeroes.add(109);
        positionOneHeroes.add(114);
        return positionOneHeroes;
    }

    private Map<Integer, String> positionOneNames()
    {
        Map<Integer, String> myMap = new HashMap<>();
        myMap.put(1, "Anti Mage");
        myMap.put(6, "Drow Ranger");
        myMap.put(8, "Juggernaut");
        myMap.put(10, "Morphling");
        myMap.put(12, "Phantom Lancer");
        myMap.put(15, "Razor");
        myMap.put(18, "Sven");
        myMap.put(19, "Tiny");
        myMap.put(32, "Riki");
        myMap.put(41, "Faceless Void");
        myMap.put(42, "Wraith King");
        myMap.put(44, "Phantom Assassin");
        myMap.put(46, "Templar Assassin");
        myMap.put(48, "Luna");
        myMap.put(53, "Natures Prophet");
        myMap.put(54, "Life Stealer");
        myMap.put(56, "Clinkz");
        myMap.put(63, "Weaver");
        myMap.put(67, "Specter");
        myMap.put(70, "Ursa");
        myMap.put(72, "Gyrocopter");
        myMap.put(73, "Alchemist");
        myMap.put(80, "Lone Druid");
        myMap.put(81, "Chaos Knight");
        myMap.put(89, "Naga Siren");
        myMap.put(93, "Slark");
        myMap.put(94, "Medusa");
        myMap.put(95, "Troll Warlord");
        myMap.put(109, "Terrorblade");
        myMap.put(114, "Monkey King");
        return myMap;
    }

    private List<Integer> allPossiblePositionTwoHeroes()
    {
        positionTwoHeroes.add(10);
        positionTwoHeroes.add(11);
        positionTwoHeroes.add(13);
        positionTwoHeroes.add(17);
        positionTwoHeroes.add(19);
        positionTwoHeroes.add(22);
        positionTwoHeroes.add(23);
        positionTwoHeroes.add(25);
        positionTwoHeroes.add(34);
        positionTwoHeroes.add(35);
        positionTwoHeroes.add(36);
        positionTwoHeroes.add(39);
        positionTwoHeroes.add(43);
        positionTwoHeroes.add(45);
        positionTwoHeroes.add(46);
        positionTwoHeroes.add(47);
        positionTwoHeroes.add(49);
        positionTwoHeroes.add(52);
        positionTwoHeroes.add(53);
        positionTwoHeroes.add(59);
        positionTwoHeroes.add(61);
        positionTwoHeroes.add(74);
        positionTwoHeroes.add(75);
        positionTwoHeroes.add(76);
        positionTwoHeroes.add(80);
        positionTwoHeroes.add(82);
        positionTwoHeroes.add(92);
        positionTwoHeroes.add(95);
        positionTwoHeroes.add(97);
        positionTwoHeroes.add(98);
        positionTwoHeroes.add(101);
        positionTwoHeroes.add(106);
        positionTwoHeroes.add(113);
        positionTwoHeroes.add(114);
        positionTwoHeroes.add(120);
        positionTwoHeroes.add(126);
        return positionTwoHeroes;
    }

    private Map<Integer, String> positionTwoNames()
    {
        Map<Integer, String> myMap = new HashMap<>();
        myMap.put(10, "Morphling");
        myMap.put(11, "Shadow Fiend");
        myMap.put(13, "Puck");
        myMap.put(17, "Storm Spirit");
        myMap.put(19, "Tiny");
        myMap.put(22, "Zeus");
        myMap.put(23, "Kunkka");
        myMap.put(25, "Lina");
        myMap.put(34, "Tinker");
        myMap.put(35, "Sniper");
        myMap.put(36, "Necrophos");
        myMap.put(39, "Queen of Pain");
        myMap.put(43, "Death Prophet");
        myMap.put(45, "Pugna");
        myMap.put(46, "Templar Assassin");
        myMap.put(47, "Viper");
        myMap.put(49, "Dragon Knight");
        myMap.put(52, "Leshrac");
        myMap.put(53, "Natures Prophet");
        myMap.put(59, "Huskar");
        myMap.put(61, "Broodmother");
        myMap.put(74, "Invoker");
        myMap.put(75, "Silencer");
        myMap.put(76, "Outworld Destroyer");
        myMap.put(80, "Lone Druid");
        myMap.put(82, "Meepo");
        myMap.put(92, "Visage");
        myMap.put(95, "Troll Warlord");
        myMap.put(97, "Magnus");
        myMap.put(98, "Timbersaw");
        myMap.put(101, "Skywrath Mage");
        myMap.put(113, "Arc Warden");
        myMap.put(114, "Monkey King");
        myMap.put(120, "Pangolier");
        myMap.put(126, "Void Spirit");
        return myMap;
    }

    public Map<Integer, String> getPositionOneHeroNames()
    {
        return positionOneNames();
    }

    public Map<Integer, String> getPositionTwoHeroNames()
    {
        return positionTwoNames();
    }

    public List<Integer> getPositionOneHeroes()
    {
        allPossiblePositionOneHeroes();
        return positionOneHeroes;
    }

    public List<Integer> getPositionTwoHeroesHeroes()
    {
        allPossiblePositionTwoHeroes();
        return positionTwoHeroes;
    }
}
