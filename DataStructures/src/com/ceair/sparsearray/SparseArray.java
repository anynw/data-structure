package com.ceair.sparsearray;

/**
 * 模拟11*11的五子棋实现稀疏数组的转换
 * 
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0
 * 
 * @author Administrator
 * @date 2019年9月29日 下午9:26:25
 */
public class SparseArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建一个原始的二维数组11*11
		// 0表示没有棋子，1代表黑子，2代表蓝子
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;// 第二行第三个位置是1：黑子
		chessArr1[2][3] = 2;// 第三行第四个位置是2：篮子
		chessArr1[5][6] = 2;
		// 输出原始的二维数组
		System.out.println("原始的二维数组:");
		for (int[] row : chessArr1) {
			System.out.println();
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
		}

		// 将二维数组转为稀疏数组
		// 遍历二维数组，获取非零数据个数sum
		int sum = 0;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr1[i][j] != 0) {
					sum++;
				}
			}
		}
		System.out.println();
		System.out.println("稀疏数组的行数:" + (sum + 1));
		System.out.println("稀疏数组的列数固定为3列");

		// 创建出对应稀疏数组
		int sparseArr[][] = new int[sum + 1][3];
		// 赋值稀疏数组
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;

		// 遍历二维数组，将非零的值存放在稀疏数组中
		/**
		 * row col val 11 11 2 2 3 1 3 4 2
		 */
		// 记录第count个非零数据
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

		// 遍历输出稀疏数组
		System.out.println();
		System.out.println("生成的稀疏数组：");
		for (int j = 0; j < sparseArr.length; j++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArr[j][0], sparseArr[j][1], sparseArr[j][2]);
		}
		System.out.println();
		
		//将稀疏数组转为原始的二维数组
		
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		
		
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
		System.out.println("恢复后的二维数组：");
		for (int[] row : chessArr2) {
			System.out.println();
			for (int data : row) {
				//全是0的二维数组
				System.out.printf("%d\t", data);
			}
		}
		
		
		
		
	}

}
