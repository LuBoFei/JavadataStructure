package avl;

import avl.Node;

public class AVLTreeDemo {

	public static void main(String[] args) {

		int arr[] = {4,3,6,5,7,8};
		AVLTree avlTree = new AVLTree();
		
		for(int i = 0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}
		System.out.println("中序遍历");
		avlTree.infixOrder();
		
		System.out.println("没平衡前~~~");
		System.out.println("树的高度="+avlTree.getRoot().height());
		System.out.println("左子树的高度="+avlTree.getRoot().leftHeight());
		System.out.println("右子树的高度="+avlTree.getRoot().rightHeight());
	}

}

class AVLTree {
	private Node root;
	
	public Node getRoot() {
		return root;
	}

	public void add(Node node) {	
		if (root == null) {
			root = node;
		} else {
			root.add(node);
		}
	}

	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("no");
		}
	}

	public Node search(int value) {
		if (root == null) {
			return null;
		} else {
			return root.search(value);
		}
	}

	public Node searchParent(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	// 以node为节点最小节点的值
	public int delRightTreeMin(Node node) {
		Node targe = node;
		while (targe.left != null) {
			targe = targe.left;
		}

		delNode(targe.value);
		return targe.value;
	}

	public void delNode(int value) {
		if (root == null) {
			return;
		} else {
			Node targetNode = search(value);
			if (targetNode == null) {
				return;
			}

			// 树只有一个节点
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}

			Node parent = searchParent(value);
			if (targetNode.left == null && targetNode.right == null) {
				// 叶子节点
				if (parent.left != null && parent.left.value == value) {
					parent.left = null;
				} else if (parent.right != null && parent.right.value == value) {
					parent.right = null;
				}
			} else if (targetNode.left != null && targetNode.right != null) {// 有两颗子树节点

				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;

			} else {// 只有一颗子树的节点

				if (targetNode.left != null) {
					if (parent != null) {
						if (parent.left.value == value) {
							parent.left = targetNode.left;
						} else {
							parent.right = targetNode.left;
						}
					} else {
						root = targetNode.left;
					}
				} else {
					if (parent != null) {
						if (parent.left.value == value) {
							parent.left = targetNode.right;
						} else {
							parent.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}

			}

		}

	}
}

class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	// 返回当前节点为根节点的高度
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}

	// 返回左子树的高度
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}

	// 返回右子树的高度
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	public void add(Node node) {
		// 递归添加
		if (node == null) {
			return;
		}

		if (node.value < this.value) {
			if (this.left == null) {
				this.left = node;
			} else {
				// 递归添加
				this.left.add(node);
			}
		} else {
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.add(node);
			}
		}
	}

	public Node search(int value) {
		if (value == this.value) {
			return this;
		} else if (value < this.value) {
			if (this.left == null) {
				return null;
			}
			return this.left.search(value);
		} else {
			if (this.right == null) {
				return null;
			}
			return this.right.search(value);
		}
	}

	public Node searchParent(int value) {
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			if (value < this.value && this.left != null) {
				return this.left.searchParent(value);
			} else if (value >= this.value && this.right != null) {
				return this.right.searchParent(value);
			} else {
				return null;
			}
		}
	}

	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

}
