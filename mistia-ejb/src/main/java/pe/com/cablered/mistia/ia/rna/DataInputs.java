/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.ia.rna;

import java.util.HashMap;

/**
 *
 * @author javadeveloper
 */
public class DataInputs {

    public static double inputs[][] = {
        {11.53387671531247,
            0.139443337755679,
            -0.697216688778396,
            -0.697216688778396,
            1.53387671531247,
            -0.418330013267038,
            -0.418330013267038,
            -0.976103364289755,
            0.5,
            -1.5,
            0.5,
            0.5,
            -0.833333333333334,
            0.5,
            1.16666666666667,
            -0.833333333333334},
        {
            1.57329824880063,
            -0.0684042716869841,
            -0.615638445182857,
            -0.615638445182857,
            1.57329824880063,
            -0.889255531930794,
            -0.615638445182857,
            -0.342021358434921,
            -0.740612896651528,
            -0.740612896651528,
            1.37542395092427,
            0.10580184237879,
            -1.28881455019148,
            0.0678323447469187,
            1.15314986069764,
            0.0678323447469187},
        {
            -0.743771569173179,
            -0.430604592679209,
            -0.430604592679209,
            -0.430604592679209,
            -0.743771569173179,
            -0.430604592679209,
            1.6049807545316,
            1.6049807545316,
            -1.21971509707505,
            -0.110883190643186,
            1.21971509707505,
            0.110883190643186,
            -1.14276008904669,
            -0.354649682807595,
            1.22157112967061,
            0.275838642183686

        },
        {
            -1.06066017177982,
            -0.117851130197758,
            0.353553390593274,
            1.29636243217534,
            0.353553390593274,
            1.29636243217534,
            -1.06066017177982,
            -1.06066017177982,
            -0.929669680201368,
            1.09870053114707,
            0.591607978309962,
            -0.760638829255665,
            -1.1427600890467,
            -0.354649682807596,
            0.275838642183685,
            1.2215711296706},
        {
            -0.863781229491797,
            0.0278639106287676,
            -0.863781229491797,
            1.14242033577947,
            1.14242033577947,
            -0.640869944461656,
            1.14242033577947,
            -1.08669251452194,
            -0.750386100411675,
            0.361297011309325,
            1.25064350068612,
            -0.861554411583775,
            -0.764350577895981,
            -0.944197772695035,
            1.03412137009456,
            0.674426980496453,}

    };

    public static HashMap<String, Double> getTrainedData() {

        HashMap<String, Double> weightUpdate = new HashMap<String, Double>();

        weightUpdate.put(weightKey(17, 0), 0.04784151333421412);
        weightUpdate.put(weightKey(17, 1), -0.0068399436358872025);
        weightUpdate.put(weightKey(17, 2), -0.019628937519604962);
        weightUpdate.put(weightKey(17, 3), 0.01108299682193943);
        weightUpdate.put(weightKey(17, 4), 0.031225391081922495);
        weightUpdate.put(weightKey(17, 5), -0.0070262656672540075);
        weightUpdate.put(weightKey(17, 6), 0.008668897783195401);
        weightUpdate.put(weightKey(17, 7), -0.011949378881242798);
        weightUpdate.put(weightKey(17, 8), -0.04024323795284775);
        weightUpdate.put(weightKey(17, 9), -2.430588746249315E-4);
        weightUpdate.put(weightKey(17, 10), 0.05295713691199068);
        weightUpdate.put(weightKey(17, 11), -0.01247084008451802);
        weightUpdate.put(weightKey(17, 12), -0.05533219724763528);
        weightUpdate.put(weightKey(17, 13), -0.014474740749760942);
        weightUpdate.put(weightKey(17, 14), 0.04800449443481843);
        weightUpdate.put(weightKey(17, 15), 0.02180244356257781);
        weightUpdate.put(weightKey(17, 16), 0.0);
        weightUpdate.put(weightKey(18, 17), 0.04784151333421412);
        weightUpdate.put(weightKey(18, 18), -0.0068399436358872025);
        weightUpdate.put(weightKey(18, 19), -0.019628937519604962);
        weightUpdate.put(weightKey(18, 20), 0.01108299682193943);
        weightUpdate.put(weightKey(18, 21), 0.031225391081922495);
        weightUpdate.put(weightKey(18, 22), -0.0070262656672540075);
        weightUpdate.put(weightKey(18, 23), 0.008668897783195401);
        weightUpdate.put(weightKey(18, 24), -0.011949378881242798);
        weightUpdate.put(weightKey(18, 25), -0.04024323795284775);
        weightUpdate.put(weightKey(18, 26), -2.430588746249315E-4);
        weightUpdate.put(weightKey(18, 27), 0.05295713691199068);
        weightUpdate.put(weightKey(18, 28), -0.01247084008451802);
        weightUpdate.put(weightKey(18, 29), -0.05533219724763528);
        weightUpdate.put(weightKey(18, 30), -0.014474740749760942);
        weightUpdate.put(weightKey(18, 31), 0.04800449443481843);
        weightUpdate.put(weightKey(18, 32), 0.02180244356257781);
        weightUpdate.put(weightKey(18, 33), 0.0);
        weightUpdate.put(weightKey(19, 34), 0.04784151333421412);
        weightUpdate.put(weightKey(19, 35), -0.0068399436358872025);
        weightUpdate.put(weightKey(19, 36), -0.019628937519604962);
        weightUpdate.put(weightKey(19, 37), 0.01108299682193943);
        weightUpdate.put(weightKey(19, 38), 0.031225391081922495);
        weightUpdate.put(weightKey(19, 39), -0.0070262656672540075);
        weightUpdate.put(weightKey(19, 40), 0.008668897783195401);
        weightUpdate.put(weightKey(19, 41), -0.011949378881242798);
        weightUpdate.put(weightKey(19, 42), -0.04024323795284775);
        weightUpdate.put(weightKey(19, 43), -2.430588746249315E-4);
        weightUpdate.put(weightKey(19, 44), 0.05295713691199068);
        weightUpdate.put(weightKey(19, 45), -0.01247084008451802);
        weightUpdate.put(weightKey(19, 46), -0.05533219724763528);
        weightUpdate.put(weightKey(19, 47), -0.014474740749760942);
        weightUpdate.put(weightKey(19, 48), 0.04800449443481843);
        weightUpdate.put(weightKey(19, 49), 0.02180244356257781);
        weightUpdate.put(weightKey(19, 50), 0.0);
        weightUpdate.put(weightKey(20, 51), 0.04784151333421412);
        weightUpdate.put(weightKey(20, 52), -0.0068399436358872025);
        weightUpdate.put(weightKey(20, 53), -0.019628937519604962);
        weightUpdate.put(weightKey(20, 54), 0.01108299682193943);
        weightUpdate.put(weightKey(20, 55), 0.031225391081922495);
        weightUpdate.put(weightKey(20, 56), -0.0070262656672540075);
        weightUpdate.put(weightKey(20, 57), 0.008668897783195401);
        weightUpdate.put(weightKey(20, 58), -0.011949378881242798);
        weightUpdate.put(weightKey(20, 59), -0.04024323795284775);
        weightUpdate.put(weightKey(20, 60), -2.430588746249315E-4);
        weightUpdate.put(weightKey(20, 61), 0.05295713691199068);
        weightUpdate.put(weightKey(20, 62), -0.01247084008451802);
        weightUpdate.put(weightKey(20, 63), -0.05533219724763528);
        weightUpdate.put(weightKey(20, 64), -0.014474740749760942);
        weightUpdate.put(weightKey(20, 65), 0.04800449443481843);
        weightUpdate.put(weightKey(20, 66), 0.02180244356257781);
        weightUpdate.put(weightKey(20, 67), 0.0);
        weightUpdate.put(weightKey(21, 68), 0.04784151333421412);
        weightUpdate.put(weightKey(21, 69), -0.0068399436358872025);
        weightUpdate.put(weightKey(21, 70), -0.019628937519604962);
        weightUpdate.put(weightKey(21, 71), 0.01108299682193943);
        weightUpdate.put(weightKey(21, 72), 0.031225391081922495);
        weightUpdate.put(weightKey(21, 73), -0.0070262656672540075);
        weightUpdate.put(weightKey(21, 74), 0.008668897783195401);
        weightUpdate.put(weightKey(21, 75), -0.011949378881242798);
        weightUpdate.put(weightKey(21, 76), -0.04024323795284775);
        weightUpdate.put(weightKey(21, 77), -2.430588746249315E-4);
        weightUpdate.put(weightKey(21, 78), 0.05295713691199068);
        weightUpdate.put(weightKey(21, 79), -0.01247084008451802);
        weightUpdate.put(weightKey(21, 80), -0.05533219724763528);
        weightUpdate.put(weightKey(21, 81), -0.014474740749760942);
        weightUpdate.put(weightKey(21, 82), 0.04800449443481843);
        weightUpdate.put(weightKey(21, 83), 0.02180244356257781);
        weightUpdate.put(weightKey(21, 84), 0.0);
        weightUpdate.put(weightKey(22, 85), 0.04784151333421412);
        weightUpdate.put(weightKey(22, 86), -0.0068399436358872025);
        weightUpdate.put(weightKey(22, 87), -0.019628937519604962);
        weightUpdate.put(weightKey(22, 88), 0.01108299682193943);
        weightUpdate.put(weightKey(22, 89), 0.031225391081922495);
        weightUpdate.put(weightKey(22, 90), -0.0070262656672540075);
        weightUpdate.put(weightKey(22, 91), 0.008668897783195401);
        weightUpdate.put(weightKey(22, 92), -0.011949378881242798);
        weightUpdate.put(weightKey(22, 93), -0.04024323795284775);
        weightUpdate.put(weightKey(22, 94), -2.430588746249315E-4);
        weightUpdate.put(weightKey(22, 95), 0.05295713691199068);
        weightUpdate.put(weightKey(22, 96), -0.01247084008451802);
        weightUpdate.put(weightKey(22, 97), -0.05533219724763528);
        weightUpdate.put(weightKey(22, 98), -0.014474740749760942);
        weightUpdate.put(weightKey(22, 99), 0.04800449443481843);
        weightUpdate.put(weightKey(22, 100), 0.02180244356257781);
        weightUpdate.put(weightKey(22, 101), 0.0);
        weightUpdate.put(weightKey(23, 102), 0.04784151333421412);
        weightUpdate.put(weightKey(23, 103), -0.0068399436358872025);
        weightUpdate.put(weightKey(23, 104), -0.019628937519604962);
        weightUpdate.put(weightKey(23, 105), 0.01108299682193943);
        weightUpdate.put(weightKey(23, 106), 0.031225391081922495);
        weightUpdate.put(weightKey(23, 107), -0.0070262656672540075);
        weightUpdate.put(weightKey(23, 108), 0.008668897783195401);
        weightUpdate.put(weightKey(23, 109), -0.011949378881242798);
        weightUpdate.put(weightKey(23, 110), -0.04024323795284775);
        weightUpdate.put(weightKey(23, 111), -2.430588746249315E-4);
        weightUpdate.put(weightKey(23, 112), 0.05295713691199068);
        weightUpdate.put(weightKey(23, 113), -0.01247084008451802);
        weightUpdate.put(weightKey(23, 114), -0.05533219724763528);
        weightUpdate.put(weightKey(23, 115), -0.014474740749760942);
        weightUpdate.put(weightKey(23, 116), 0.04800449443481843);
        weightUpdate.put(weightKey(23, 117), 0.02180244356257781);
        weightUpdate.put(weightKey(23, 118), 0.0);
        weightUpdate.put(weightKey(24, 119), 0.04784151333421412);
        weightUpdate.put(weightKey(24, 120), -0.0068399436358872025);
        weightUpdate.put(weightKey(24, 121), -0.019628937519604962);
        weightUpdate.put(weightKey(24, 122), 0.01108299682193943);
        weightUpdate.put(weightKey(24, 123), 0.031225391081922495);
        weightUpdate.put(weightKey(24, 124), -0.0070262656672540075);
        weightUpdate.put(weightKey(24, 125), 0.008668897783195401);
        weightUpdate.put(weightKey(24, 126), -0.011949378881242798);
        weightUpdate.put(weightKey(24, 127), -0.04024323795284775);
        weightUpdate.put(weightKey(24, 128), -2.430588746249315E-4);
        weightUpdate.put(weightKey(24, 129), 0.05295713691199068);
        weightUpdate.put(weightKey(24, 130), -0.01247084008451802);
        weightUpdate.put(weightKey(24, 131), -0.05533219724763528);
        weightUpdate.put(weightKey(24, 132), -0.014474740749760942);
        weightUpdate.put(weightKey(24, 133), 0.04800449443481843);
        weightUpdate.put(weightKey(24, 134), 0.02180244356257781);
        weightUpdate.put(weightKey(24, 135), 0.0);
        weightUpdate.put(weightKey(25, 136), 0.04784151333421412);
        weightUpdate.put(weightKey(25, 137), -0.0068399436358872025);
        weightUpdate.put(weightKey(25, 138), -0.019628937519604962);
        weightUpdate.put(weightKey(25, 139), 0.01108299682193943);
        weightUpdate.put(weightKey(25, 140), 0.031225391081922495);
        weightUpdate.put(weightKey(25, 141), -0.0070262656672540075);
        weightUpdate.put(weightKey(25, 142), 0.008668897783195401);
        weightUpdate.put(weightKey(25, 143), -0.011949378881242798);
        weightUpdate.put(weightKey(25, 144), -0.04024323795284775);
        weightUpdate.put(weightKey(25, 145), -2.430588746249315E-4);
        weightUpdate.put(weightKey(25, 146), 0.05295713691199068);
        weightUpdate.put(weightKey(25, 147), -0.01247084008451802);
        weightUpdate.put(weightKey(25, 148), -0.05533219724763528);
        weightUpdate.put(weightKey(25, 149), -0.014474740749760942);
        weightUpdate.put(weightKey(25, 150), 0.04800449443481843);
        weightUpdate.put(weightKey(25, 151), 0.02180244356257781);
        weightUpdate.put(weightKey(25, 152), 0.0);
        weightUpdate.put(weightKey(26, 153), 0.04784151333421412);
        weightUpdate.put(weightKey(26, 154), -0.0068399436358872025);
        weightUpdate.put(weightKey(26, 155), -0.019628937519604962);
        weightUpdate.put(weightKey(26, 156), 0.01108299682193943);
        weightUpdate.put(weightKey(26, 157), 0.031225391081922495);
        weightUpdate.put(weightKey(26, 158), -0.0070262656672540075);
        weightUpdate.put(weightKey(26, 159), 0.008668897783195401);
        weightUpdate.put(weightKey(26, 160), -0.011949378881242798);
        weightUpdate.put(weightKey(26, 161), -0.04024323795284775);
        weightUpdate.put(weightKey(26, 162), -2.430588746249315E-4);
        weightUpdate.put(weightKey(26, 163), 0.05295713691199068);
        weightUpdate.put(weightKey(26, 164), -0.01247084008451802);
        weightUpdate.put(weightKey(26, 165), -0.05533219724763528);
        weightUpdate.put(weightKey(26, 166), -0.014474740749760942);
        weightUpdate.put(weightKey(26, 167), 0.04800449443481843);
        weightUpdate.put(weightKey(26, 168), 0.02180244356257781);
        weightUpdate.put(weightKey(26, 169), 0.0);
        weightUpdate.put(weightKey(27, 170), 0.04784151333421412);
        weightUpdate.put(weightKey(27, 171), -0.0068399436358872025);
        weightUpdate.put(weightKey(27, 172), -0.019628937519604962);
        weightUpdate.put(weightKey(27, 173), 0.01108299682193943);
        weightUpdate.put(weightKey(27, 174), 0.031225391081922495);
        weightUpdate.put(weightKey(27, 175), -0.0070262656672540075);
        weightUpdate.put(weightKey(27, 176), 0.008668897783195401);
        weightUpdate.put(weightKey(27, 177), -0.011949378881242798);
        weightUpdate.put(weightKey(27, 178), -0.04024323795284775);
        weightUpdate.put(weightKey(27, 179), -2.430588746249315E-4);
        weightUpdate.put(weightKey(27, 180), 0.05295713691199068);
        weightUpdate.put(weightKey(27, 181), -0.01247084008451802);
        weightUpdate.put(weightKey(27, 182), -0.05533219724763528);
        weightUpdate.put(weightKey(27, 183), -0.014474740749760942);
        weightUpdate.put(weightKey(27, 184), 0.04800449443481843);
        weightUpdate.put(weightKey(27, 185), 0.02180244356257781);
        weightUpdate.put(weightKey(27, 186), 0.0);
        weightUpdate.put(weightKey(28, 187), 0.04784151333421412);
        weightUpdate.put(weightKey(28, 188), -0.0068399436358872025);
        weightUpdate.put(weightKey(28, 189), -0.019628937519604962);
        weightUpdate.put(weightKey(28, 190), 0.01108299682193943);
        weightUpdate.put(weightKey(28, 191), 0.031225391081922495);
        weightUpdate.put(weightKey(28, 192), -0.0070262656672540075);
        weightUpdate.put(weightKey(28, 193), 0.008668897783195401);
        weightUpdate.put(weightKey(28, 194), -0.011949378881242798);
        weightUpdate.put(weightKey(28, 195), -0.04024323795284775);
        weightUpdate.put(weightKey(28, 196), -2.430588746249315E-4);
        weightUpdate.put(weightKey(28, 197), 0.05295713691199068);
        weightUpdate.put(weightKey(28, 198), -0.01247084008451802);
        weightUpdate.put(weightKey(28, 199), -0.05533219724763528);
        weightUpdate.put(weightKey(28, 200), -0.014474740749760942);
        weightUpdate.put(weightKey(28, 201), 0.04800449443481843);
        weightUpdate.put(weightKey(28, 202), 0.02180244356257781);
        weightUpdate.put(weightKey(28, 203), 0.0);
        weightUpdate.put(weightKey(29, 204), 0.04784151333421412);
        weightUpdate.put(weightKey(29, 205), -0.0068399436358872025);
        weightUpdate.put(weightKey(29, 206), -0.019628937519604962);
        weightUpdate.put(weightKey(29, 207), 0.01108299682193943);
        weightUpdate.put(weightKey(29, 208), 0.031225391081922495);
        weightUpdate.put(weightKey(29, 209), -0.0070262656672540075);
        weightUpdate.put(weightKey(29, 210), 0.008668897783195401);
        weightUpdate.put(weightKey(29, 211), -0.011949378881242798);
        weightUpdate.put(weightKey(29, 212), -0.04024323795284775);
        weightUpdate.put(weightKey(29, 213), -2.430588746249315E-4);
        weightUpdate.put(weightKey(29, 214), 0.05295713691199068);
        weightUpdate.put(weightKey(29, 215), -0.01247084008451802);
        weightUpdate.put(weightKey(29, 216), -0.05533219724763528);
        weightUpdate.put(weightKey(29, 217), -0.014474740749760942);
        weightUpdate.put(weightKey(29, 218), 0.04800449443481843);
        weightUpdate.put(weightKey(29, 219), 0.02180244356257781);
        weightUpdate.put(weightKey(29, 220), 0.0);
        weightUpdate.put(weightKey(30, 221), 0.04784151333421412);
        weightUpdate.put(weightKey(30, 222), -0.0068399436358872025);
        weightUpdate.put(weightKey(30, 223), -0.019628937519604962);
        weightUpdate.put(weightKey(30, 224), 0.01108299682193943);
        weightUpdate.put(weightKey(30, 225), 0.031225391081922495);
        weightUpdate.put(weightKey(30, 226), -0.0070262656672540075);
        weightUpdate.put(weightKey(30, 227), 0.008668897783195401);
        weightUpdate.put(weightKey(30, 228), -0.011949378881242798);
        weightUpdate.put(weightKey(30, 229), -0.04024323795284775);
        weightUpdate.put(weightKey(30, 230), -2.430588746249315E-4);
        weightUpdate.put(weightKey(30, 231), 0.05295713691199068);
        weightUpdate.put(weightKey(30, 232), -0.01247084008451802);
        weightUpdate.put(weightKey(30, 233), -0.05533219724763528);
        weightUpdate.put(weightKey(30, 234), -0.014474740749760942);
        weightUpdate.put(weightKey(30, 235), 0.04800449443481843);
        weightUpdate.put(weightKey(30, 236), 0.02180244356257781);
        weightUpdate.put(weightKey(30, 237), 0.0);
        weightUpdate.put(weightKey(31, 238), 0.04784151333421412);
        weightUpdate.put(weightKey(31, 239), -0.0068399436358872025);
        weightUpdate.put(weightKey(31, 240), -0.019628937519604962);
        weightUpdate.put(weightKey(31, 241), 0.01108299682193943);
        weightUpdate.put(weightKey(31, 242), 0.031225391081922495);
        weightUpdate.put(weightKey(31, 243), -0.0070262656672540075);
        weightUpdate.put(weightKey(31, 244), 0.008668897783195401);
        weightUpdate.put(weightKey(31, 245), -0.011949378881242798);
        weightUpdate.put(weightKey(31, 246), -0.04024323795284775);
        weightUpdate.put(weightKey(31, 247), -2.430588746249315E-4);
        weightUpdate.put(weightKey(31, 248), 0.05295713691199068);
        weightUpdate.put(weightKey(31, 249), -0.01247084008451802);
        weightUpdate.put(weightKey(31, 250), -0.05533219724763528);
        weightUpdate.put(weightKey(31, 251), -0.014474740749760942);
        weightUpdate.put(weightKey(31, 252), 0.04800449443481843);
        weightUpdate.put(weightKey(31, 253), 0.02180244356257781);
        weightUpdate.put(weightKey(31, 254), 0.0);
        weightUpdate.put(weightKey(32, 255), 0.04784151333421412);
        weightUpdate.put(weightKey(32, 256), -0.0068399436358872025);
        weightUpdate.put(weightKey(32, 257), -0.019628937519604962);
        weightUpdate.put(weightKey(32, 258), 0.01108299682193943);
        weightUpdate.put(weightKey(32, 259), 0.031225391081922495);
        weightUpdate.put(weightKey(32, 260), -0.0070262656672540075);
        weightUpdate.put(weightKey(32, 261), 0.008668897783195401);
        weightUpdate.put(weightKey(32, 262), -0.011949378881242798);
        weightUpdate.put(weightKey(32, 263), -0.04024323795284775);
        weightUpdate.put(weightKey(32, 264), -2.430588746249315E-4);
        weightUpdate.put(weightKey(32, 265), 0.05295713691199068);
        weightUpdate.put(weightKey(32, 266), -0.01247084008451802);
        weightUpdate.put(weightKey(32, 267), -0.05533219724763528);
        weightUpdate.put(weightKey(32, 268), -0.014474740749760942);
        weightUpdate.put(weightKey(32, 269), 0.04800449443481843);
        weightUpdate.put(weightKey(32, 270), 0.02180244356257781);
        weightUpdate.put(weightKey(32, 271), 0.0);
        weightUpdate.put(weightKey(33, 272), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 273), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 274), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 275), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 276), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 277), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 278), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 279), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 280), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 281), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 282), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 283), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 284), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 285), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 286), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 287), 0.46652446705893663);
        weightUpdate.put(weightKey(33, 288), 0.0);

        return weightUpdate;
    }

    static String weightKey(int neuronId, int conId) {
        return "N" + neuronId + "_C" + conId;
    }
}
