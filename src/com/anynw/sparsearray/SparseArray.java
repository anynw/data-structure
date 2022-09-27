package com.anynw.sparsearray;

/**
 * 模拟11*11数组分析稀疏数组
 * 1：黑色棋子，2：蓝色棋子，0：没有棋子
 *
 * @author Administrator
 * @date 2019��9��29�� ����9:26:25
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个二维数组11*11
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;//第二行第三列值为1：黑子
        chessArr1[2][3] = 2;//第三行第四列值为2：篮子
        chessArr1[5][6] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组:");
        for (int[] row : chessArr1) {
            System.out.println();
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
        }

        // 将二维数组转换为稀疏数组
        // 遍历原始数组，计算非零个数sum
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println();
        System.out.println("稀疏数组的行数" + (sum + 1));
        System.out.println("稀疏数组的列数固定是3列");

        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零的值存入稀疏数组中
        //用于记录第几个非零数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println();
        System.out.println("生成的稀疏数组：");
        // \t:tab，\n:enter
        for (int j = 0; j < sparseArr.length; j++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[j][0], sparseArr[j][1], sparseArr[j][2]);
        }
        System.out.println();

        //将稀疏数组还原成原始数组，定义原始数组chessArr2

        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("输出恢复后的原始数组：");
        for (int[] row : chessArr2) {
            System.out.println();
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
        }

    }

}
