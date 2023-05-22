package Huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {
		int arr[] = { 13, 7, 8, 3, 29, 6, 1 };

		Node node = createHuffmanTree(arr);
		
		preOrder(node);
		
	}
	
	//前序遍历
	public static void preOrder(Node node) {
		if(node != null) {
			node.preOrder();
		}else {
			System.out.println("是空术");
		}
	}

	// 创建赫夫曼殊
	public static Node createHuffmanTree(int[] arr) {
		List<Node> nodes = new ArrayList<Node>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}

		while (nodes.size() > 1) {
			// 从小到大排序
			Collections.sort(nodes);

			System.out.println(nodes);

			// 取出权值最小的两个结点（二叉树）
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			// 形成新的二叉树
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;

			// 删除处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			nodes.add(parent);

		}
		return nodes.get(0);
	}

}

//创建结点类
class Node implements Comparable<Node> {

	int value;// 节点权值
	Node left;// 左子节点
	Node right;

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		// 从小到大排
		return this.value - o.value;
	}
	
	//前序遍历
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}

}