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
	
	//ǰ�����
	public static void preOrder(Node node) {
		if(node != null) {
			node.preOrder();
		}else {
			System.out.println("�ǿ���");
		}
	}

	// �����շ�����
	public static Node createHuffmanTree(int[] arr) {
		List<Node> nodes = new ArrayList<Node>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}

		while (nodes.size() > 1) {
			// ��С��������
			Collections.sort(nodes);

			System.out.println(nodes);

			// ȡ��Ȩֵ��С��������㣨��������
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			// �γ��µĶ�����
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;

			// ɾ��������Ķ�����
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			nodes.add(parent);

		}
		return nodes.get(0);
	}

}

//���������
class Node implements Comparable<Node> {

	int value;// �ڵ�Ȩֵ
	Node left;// ���ӽڵ�
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
		// ��С������
		return this.value - o.value;
	}
	
	//ǰ�����
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