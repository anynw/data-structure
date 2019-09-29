package com.ceair.sparsearray;

/**
 * ģ��11*11��������ʵ��ϡ�������ת��
 * 
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0
 * 
 * @author Administrator
 * @date 2019��9��29�� ����9:26:25
 */
public class SparseArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ����һ��ԭʼ�Ķ�ά����11*11
		// 0��ʾû�����ӣ�1������ӣ�2��������
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;// �ڶ��е�����λ����1������
		chessArr1[2][3] = 2;// �����е��ĸ�λ����2������
		chessArr1[5][6] = 2;
		// ���ԭʼ�Ķ�ά����
		System.out.println("ԭʼ�Ķ�ά����:");
		for (int[] row : chessArr1) {
			System.out.println();
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
		}

		// ����ά����תΪϡ������
		// ������ά���飬��ȡ�������ݸ���sum
		int sum = 0;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr1[i][j] != 0) {
					sum++;
				}
			}
		}
		System.out.println();
		System.out.println("ϡ�����������:" + (sum + 1));
		System.out.println("ϡ������������̶�Ϊ3��");

		// ��������Ӧϡ������
		int sparseArr[][] = new int[sum + 1][3];
		// ��ֵϡ������
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;

		// ������ά���飬�������ֵ�����ϡ��������
		/**
		 * row col val 11 11 2 2 3 1 3 4 2
		 */
		// ��¼��count����������
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

		// �������ϡ������
		System.out.println();
		System.out.println("���ɵ�ϡ�����飺");
		for (int j = 0; j < sparseArr.length; j++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArr[j][0], sparseArr[j][1], sparseArr[j][2]);
		}
		System.out.println();
		
		//��ϡ������תΪԭʼ�Ķ�ά����
		
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		
		
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
		System.out.println("�ָ���Ķ�ά���飺");
		for (int[] row : chessArr2) {
			System.out.println();
			for (int data : row) {
				//ȫ��0�Ķ�ά����
				System.out.printf("%d\t", data);
			}
		}
		
		
		
		
	}

}
