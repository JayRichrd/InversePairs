package com.jy;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] array = new int[] { 7, 5, 6, 4 };
		System.out.println("输入的数组元素:" + Arrays.toString(array));
		System.out.println("数组中的逆序对数量:" + inversePairs(array));
	}

	/**
	 * 求数组的逆序对
	 * 
	 * @param array
	 *            待求逆序对的数组
	 * @return 返回逆序对的数量
	 */
	public static int inversePairs(int[] array) {
		int arrayLength = array.length;
		if (array == null || arrayLength <= 0)
			return 0;
		// 新建辅助数组，并填充辅助数组
		int[] copy = new int[arrayLength];
		System.arraycopy(array, 0, copy, 0, arrayLength);
		// 调用重载函数
		return inversePairs(array, copy, 0, arrayLength - 1);
	}

	/**
	 * 求数组的逆序对
	 * 
	 * @param array
	 *            待求逆序对的数组
	 * @param copy
	 *            辅助用的数组
	 * @param start
	 *            待求逆序对的数组的开始索引
	 * @param end
	 *            待求逆序对的数组的结束索引
	 * @return 返回逆序对的数量
	 */
	public static int inversePairs(int[] array, int[] copy, int start, int end) {
		if (start == end) {
			copy[start] = array[start];
			return 0;
		}
		// 每一个子数组的长度
		int length = (end - start) >> 1;
		// 递归左右子数组，求得子数组的逆序对
		// 将此时的源数组作为辅助数组
		// 这样递归的堆栈返回时，源数组的左右子数组是已经排好顺序的
		int left = inversePairs(copy, array, start, start + length);
		int right = inversePairs(copy, array, start + length + 1, end);

		// 求两个子数组之间的逆序对
		// 注意此时的源数组经过递归，左右两个子数组已经从小打到排序

		// 左子数组的最后一个元素
		int i = start + length;
		// 右子数组的最后一个元素
		int j = end;
		// 辅助数组开始填充的地方，从本次参加计算的数组的最后一个元素开始往前
		int indexCopy = end;
		// 左右子数组之间的逆序对数
		int count = 0;
		// 循环查找左右子数组之间的逆序对
		while (i >= start && j >= start + length + 1) {
			if (array[i] > array[j]) {
				// 将较大数保存到辅助数组中
				copy[indexCopy--] = array[i--];
				count += j - (start + length);
			} else {
				copy[indexCopy--] = array[j--];
			}
		}
		// 将左/右子数组剩余的元素直接全部复制到辅助数组中
		while (i >= start)
			copy[indexCopy--] = array[i--];
		while (j >= start + length + 1)
			copy[indexCopy--] = array[j--];

		// 返回逆序对的总数：左子数组内部的逆序对数+右子数组内部的逆序对数+左右子数组之间的逆序对数
		return left + right + count;
	}

}
