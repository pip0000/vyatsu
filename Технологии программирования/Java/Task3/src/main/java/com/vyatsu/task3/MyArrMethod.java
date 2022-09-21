package com.vyatsu.task3;

public class MyArrMethod {
    public static void Calc(String[][] arr)
    {
        int res = 0;
        int i = 0;
        int j = 0;

        try {
            for (i = 0; i < arr.length; i++) {
                if (!(arr.length == 4 & arr[i].length == 4))
                    throw new MyArraySizeException("Неправильный размер массива");
            }
            try {
                for (i = 0; i < arr.length; i++) {
                    for (j = 0; j < arr[i].length; j++) {
                        try {
                            res += Integer.valueOf(arr[i][j]);
                        }
                        catch (NumberFormatException e)
                        {
                            throw new MyArrayDataException("Проверьте значение ячейки в строке " + (i+1) + " столбце "  + (j+1) + ", там находится "+arr[i][j]);
                        }
                    }
                }
                System.out.println(res);
            }
            catch (MyArrayDataException ad) {
                System.out.println("Неправильное значение в ячейке массива");
                ad.printStackTrace();
            }
        }
        catch(MyArraySizeException as) {
            System.out.println("Измените размер массива на 4 на 4");
            as.printStackTrace();
        }


    }
}
