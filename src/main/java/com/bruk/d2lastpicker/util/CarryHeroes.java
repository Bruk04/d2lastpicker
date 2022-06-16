package com.bruk.d2lastpicker.util;

import java.util.ArrayList;
import java.util.List;

public class CarryHeroes {


    private List<Integer> positionOneHeroes = new ArrayList<Integer>();
    private List<Integer> positionTwoHeroes = new ArrayList<Integer>();

    public CarryHeroes() {
    }

    private List<Integer> allPossiblePositionOneHeroes()
    {
        positionOneHeroes.add(1);
        positionOneHeroes.add(2);
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
