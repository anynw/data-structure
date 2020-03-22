package com.ceair.sort;

/**
 * 由于是生成的随机数，所以测试结果并不准，Plus版运行时间肯定小于等于普通版本
 * 
 * @author huaping
 * @time 2020-03-22 7:25:26 PM
 */
public class BubbleSortTest {

	public static void main(String[] args) {
		// 测试10万条数据的耗时
		int[] arr = new int[100000];
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			arr[i] = (int) (Math.random() * 100000);
		}
//		System.out.println("排序前:" + Arrays.toString(arr));
		bublleSort(arr);
//		bubbleSortPlus(arr);
		long end = System.currentTimeMillis();
//		System.out.println("排序后:" + Arrays.toString(arr));
		System.out.println("耗时：" + (end - start) / 1000 + "秒");//

	}

	/**
	 * 普通版冒泡排序
	 * 
	 * @param arr 传入的int集合
	 */
	public static void bublleSort(int[] arr) {
		int temp = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 优化版冒泡排序
	 * 
	 * @param arr 传入的int集合
	 */
	public static void bubbleSortPlus(int[] arr) {
		int temp = 0;
		boolean flag = false;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					flag = true;
				}
			}
			if (!flag) {
				break;
			} else {
				flag = false;
			}
		}
	}

}
