package com.gungoren.compress.util;

public class ImageToByteConverter
{

    public byte[] readRowsAsCircular(byte[][] image)
    {
        int k = 0, idx = 0;
        int rows = image.length, cols = image[0].length;
        byte[] newArr = new byte[rows * cols];
        while(k < rows)
        {
            for(int i = 0; i < cols; i++)
            {
                newArr[idx++] = image[k][i];
            }
            k++;

            if (k == rows)
                break;

            for(int i = cols - 1; i >= 0; i--)
            {
                newArr[idx++] = image[k][i];
            }
            k++;
        }

        return newArr;
    }

    public byte[] readColsAsCircular(byte[][] image)
    {
        int k = 0, idx = 0;
        int rows = image.length, cols = image[0].length;
        byte[] newArr = new byte[rows * cols];
        while(k < cols)
        {
            for(int i = 0; i < rows; i++)
            {
                newArr[idx++] = image[i][k];
            }
            k++;

            if (k == cols)
                break;

            for(int i = rows - 1; i >= 0; i--)
            {
                newArr[idx++] = image[i][k];
            }
            k++;
        }

        return newArr;
    }

    public byte[] readAsZigZag(byte[][] image)
    {
        int i = 1, j = 1, idx = 0;
        int rows = image.length, cols = image[0].length;
        byte[] newArr = new byte[rows * cols];
        while(idx < cols* rows)
        {
            newArr[idx++] = image[i-1][j-1];
            if ((j + i) % 2 == 0)
            {
                if(j < cols)
                {
                    j++;
                }
                else
                {
                    i += 2;
                }

                if(i > 1)
                    i--;
            }else {

                if ( i < rows){
                    i++;
                } else {
                    j+=2;
                }

                if (j > 1)
                    j--;
            }
        }

        return newArr;
    }

    public static void main(String[] args)
    {
        byte[][] xx = new byte[3][2];

        xx[0][0] = '1';
        xx[0][1] = '2';
        xx[1][0] = '3';
        xx[1][1] = '4';
        xx[2][0] = '5';
        xx[2][1] = '6';

        ImageToByteConverter imageToByteConverter = new ImageToByteConverter();
        byte[] byCols = imageToByteConverter.readColsAsCircular(xx);
        byte[] byRows = imageToByteConverter.readRowsAsCircular(xx);
        byte[] byZigZag = imageToByteConverter.readAsZigZag(xx);

        for(int i = 0; i < xx.length * xx[0].length; i++)
        {
            System.out.println(byCols[i] + " " + byRows[i] + " " + byZigZag[i]);
        }

    }
}
